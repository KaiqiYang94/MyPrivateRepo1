import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import com.google.gson.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import mpi.*;




public class TwitterGeoProcessing {

	private static int prevSentProcess = 0;

	public static void main(String[] args) {

		try {
			long startTime = System.currentTimeMillis();
			long stopTime;

			// initialize mpi comm world
			MPI.Init(args) ;

			System.out.println("Size is " + MPI.COMM_WORLD.Size() + " Rank is " + MPI.COMM_WORLD.Rank());

			//MPI.Barrier();
			// get all the data from file
			ArrayList<GeoGrid> geoGrids = new ArrayList<GeoGrid>();

			// read all the file only if it's the main process
			if (isMainProcess()) {
				// get all the grids
				String allGrid = ReadFullFile("melbGrid.json");
				// process the files
				JsonArray gridArray = new JsonParser().parse(allGrid).getAsJsonObject().getAsJsonArray("features");
				// read all the grids
				for (JsonElement singleData : gridArray) {
					GeoGrid tempGrid = new GeoGrid(singleData);
					geoGrids.add(tempGrid);
				}
				// bcast gird data
				MPICommands.BcastGridData(geoGrids);

				FileInputStream fstream = new FileInputStream("bigTwitter.json");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String strLine;
				while ((strLine = br.readLine()) != null) {
					//System.out.println("The size is " + MPI.COMM_WORLD.Size());
					if (MPI.COMM_WORLD.Size() > 1) {
						MPICommands.SendSingleTwitter(strLine.toString().replaceAll(",$", ""), nextRankToSend());

					} else {
						ProcessSingleJson(strLine.toString().replaceAll(",$", "") , geoGrids);
					}
				}
				br.close();

				printOutTime(startTime, "The total time of bcast info is ");

				if (MPI.COMM_WORLD.Size() > 1) {
					MPICommands.BcastFinished();

					MPICommands.ResvResults(geoGrids);
				}

				printOutTime(startTime, "The total time of gathering results is ");

			} else {
				startTime = System.currentTimeMillis();

				char command = MPICommands.ReceiveCommandType(geoGrids);

				while (command != MPICommands.FINISHECMD) {

					if (command == MPICommands.GRIDDATACMD) {
						MPICommands.ResvGridData(geoGrids);
					} else if (command == MPICommands.SINGLETWITTERCMD) {
						String singleTwitter = MPICommands.ResvSingleTwitter();
						ProcessSingleJson(singleTwitter , geoGrids);
					}

					command = MPICommands.ReceiveCommandType(geoGrids);
				}

				MPICommands.SendResults(geoGrids);

				printOutTime(startTime, "The total time of processing twitters is ");

			}

			// sort the ArrayList
			Collections.sort(geoGrids);

			if (isMainProcess()) {
				// output the results
				PrintOutGrids(geoGrids);
				// for (GeoGrid geoGrid : geoGrids) {
				// 	System.out.println(MPICommands.indentation() + "\t#" + (int)geoGrid.internalID + "\t(" + geoGrid.name + "):\t " + geoGrid.Counter);
				// }
			}


			printOutTime(startTime, "The total time of execution is ");

			System.out.println("++++++++++++++++++++++++++++++++++++++++++");

			MPI.Finalize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String ReadFullFile(String fileName) {
		try {
			FileInputStream fstream = new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String allData = "";
			String strLine;
			while ((strLine = br.readLine()) != null) {
				allData += strLine;
			}
			br.close();
			return allData;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void ProcessSingleJson(String singleTwitter, ArrayList<GeoGrid> geoGrids) {

		try {
			JsonElement singleData = new JsonParser().parse(singleTwitter);

			Coordinate tempCoor = new Coordinate();
			JsonArray geo = singleData.getAsJsonObject().getAsJsonObject("json").getAsJsonObject("coordinates")
			                .getAsJsonArray("coordinates");

			tempCoor.longitude = geo.get(0).getAsJsonPrimitive().getAsDouble();
			tempCoor.latitude = geo.get(1).getAsJsonPrimitive().getAsDouble();

			// find which grid it's in
			for (GeoGrid geoGrid : geoGrids) {
				if (geoGrid.isInGrid(tempCoor)) {
					geoGrid.Counter++;
					break;
				}
			}
		} catch (Exception e) {

			System.out.println("Exception when processing the data, the data is  " + singleTwitter);
			//e.printStackTrace();
		}
	}



	public static Boolean isMainProcess() {
		try {
			int myrank = MPI.COMM_WORLD.Rank() ;
			return myrank == MPICommands.mainProcessRank;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	public static int nextRankToSend() throws Exception {

		int myrank = MPI.COMM_WORLD.Rank() ;
		int size = MPI.COMM_WORLD.Size() ;

		int next = (prevSentProcess + 1) % size;
		if (next == myrank) {
			next = (next + 1) % size;
		}

		prevSentProcess = next;

		return next;
	}

	public static void printOutTime(long prevTimeMs, String comment) {
		long elapsedTime = System.currentTimeMillis() - prevTimeMs;
		System.out.println(MPICommands.indentation() + comment + elapsedTime + " ms");

	}

	public static void PrintOutGrids (ArrayList<GeoGrid> geoGrids) throws Exception {

		// output the results
		for (GeoGrid geoGrid : geoGrids) {
			System.out.println(MPICommands.indentation()
			                   + "\t#" + (int)geoGrid.internalID + "\t(" + geoGrid.name + "):\t " + geoGrid.Counter);
		}


		String[] rows = new String[] {"A", "B", "C", "D"};

		Map<String, Integer> rowsMap = new HashMap<String, Integer>();

		String[] columns = new String[] {"1", "2", "3", "4", "5"};

		Map<String, Integer> columnsMap = new HashMap<String, Integer>();

		for (String row : rows) {
			int rowCounter = 0;
			for (GeoGrid geoGrid : geoGrids) {
				if(geoGrid.name.indexOf(row) != -1)
				{
					rowCounter += geoGrid.Counter;
				}
				rowCounter += geoGrid.Counter;
			}
			rowsMap.put(row, rowCounter);
		}

		for (String col : columns) {
			int colCounter = 0;
			for (GeoGrid geoGrid : geoGrids) {
				if(geoGrid.name.indexOf(col) != -1)
				{
					colCounter += geoGrid.Counter;
				}
			}
			columnsMap.put(col, colCounter);
		}

		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		Map<String, Integer> sortedMap = sortByValue(columnsMap);
		printMap(sortedMap, "Col-");

		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		sortedMap = sortByValue(rowsMap);
		printMap(sortedMap, "Rows");

		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
	}
	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

		// 1. Convert Map to List of Map
		List<Map.Entry<String, Integer>> list =
		    new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

		// 2. Sort list with Collections.sort(), provide a custom Comparator
		//    Try switch the o1 o2 position for a different order
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
			                   Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		/*
		//classic iterator example
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
		    Map.Entry<String, Integer> entry = it.next();
		    sortedMap.put(entry.getKey(), entry.getValue());
		}*/


		return sortedMap;
	}

	public static <K, V> void printMap(Map<K, V> map, String str) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			System.out.println(MPICommands.indentation() + str + " :" + entry.getKey()
			                   + "  : " + entry.getValue());
		}
	}

}
