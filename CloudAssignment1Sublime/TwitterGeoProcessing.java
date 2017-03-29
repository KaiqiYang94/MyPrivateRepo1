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
import mpi.*;


public class TwitterGeoProcessing {
	private static int mainProcessRank = 0;
	private static int tag = 10;

	protected static final int GRIDDATASIZE = 5;

	protected static final char GRIDDATACMD = 'G';
	protected static final char SINGLETWITTERCMD = 'S';
	protected static final char RESULTCMD = 'R';
	protected static final char FINISHECMD = 'F';

	public static void main(String[] args) {

		try {
			long startTime = System.currentTimeMillis();
			long stopTime;
			long elapsedTime;

			// initialize mpi comm world
			MPI.Init(args) ;

			// get all the data from file
			ArrayList<GeoGrid> geoGrids = new ArrayList<GeoGrid>();

			// read all the file only if it's the main process
			if (isMainProcess()) {
				// get all the grids
				String allGrid = ReadFullFile("./Data/melbGrid.json");

				// get all twitter data
				String allData = ReadFullFile("./Data/tinyTwitterError.json");

				stopTime = System.currentTimeMillis();
				elapsedTime = stopTime - startTime;
				System.out.println(indentation() + "The total time of reading files is " + elapsedTime + " ms");

				// process the files
				JsonArray gridArray = new JsonParser().parse(allGrid).getAsJsonObject().getAsJsonArray("features");
				// read all the grids
				for (JsonElement singleData : gridArray) {
					GeoGrid tempGrid = new GeoGrid(singleData);
					geoGrids.add(tempGrid);
				}
				// bcast gird data
				BcastGridData(geoGrids);

				JsonArray jsonArray = new JsonParser().parse(allData).getAsJsonArray();
				for (JsonElement singleData : jsonArray) {
					try {
						//ProcessSingleJson(singleData, geoGrids);
						SendSingleTwitter(singleData.toString(), 1);

						SendSingleTwitter(singleData.toString(), 2);

						break;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Exception when processing the data, the data is  " + singleData.getAsString());
						continue;
					}
				}

				stopTime = System.currentTimeMillis();
				elapsedTime = stopTime - startTime;
				System.out.println(indentation() + "The total time of processing files is " + elapsedTime + " ms");

			} else {
				char command = ReceiveCommandType(geoGrids);
				while (command != FINISHECMD) {
					if (command == GRIDDATACMD) {
						ResvGridData(geoGrids);
					} else if (command == SINGLETWITTERCMD) {
						ResvSingleTwitter();
					}

					command = ReceiveCommandType(geoGrids);
				}

			}

			// sort the ArrayList
			Collections.sort(geoGrids);

			// output the results
			for (GeoGrid geoGrid : geoGrids) {
				System.out.println(indentation() + "\t#" + (int)geoGrid.internalID + "\t(" + geoGrid.name + "):\t " + geoGrid.Counter);
			}

			//
			stopTime = System.currentTimeMillis();
			elapsedTime = stopTime - startTime;
			System.out.println(indentation() + "The total time of execution is " + elapsedTime + " ms");

			MPI.Finalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ProcessSingleJson(JsonElement singleData, ArrayList<GeoGrid> geoGrids) {
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
	}

	public static String indentation() {
		try {
			int myrank = MPI.COMM_WORLD.getRank() ;
			return "Rank " + myrank + ": ";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Boolean isMainProcess() {
		try {
			int myrank = MPI.COMM_WORLD.getRank() ;
			return myrank == mainProcessRank;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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

	public static char ReceiveCommandType (ArrayList<GeoGrid> geoGrids) throws Exception {
		char[] commandType = new char[1];
		MPI.COMM_WORLD.recv(commandType, 1, MPI.CHAR, mainProcessRank, tag);
		System.out.println(indentation() + "The received commandType is " + commandType[0] + ". ");

		return commandType[0];

	}


	public static void BcastGridData (ArrayList<GeoGrid> geoGrids) throws Exception {
		char[] commandType = new char[1];
		commandType[0] = GRIDDATACMD;
		int[] gridSize = new int[1];
		gridSize[0] = geoGrids.size();

		int size = MPI.COMM_WORLD.getSize() ;

		for (int i = 0 ; i < size ; i++) {
			// command type
			MPI.COMM_WORLD.send(commandType, 1, MPI.CHAR, i, tag);
			// Grid count
			MPI.COMM_WORLD.send(gridSize, 1, MPI.INT, i, tag);
			//System.out.println(indentation() + "The sent data is <" + geoGrids.size() + ">. ");


			// the grid data
			for (GeoGrid geoGrid : geoGrids) {
				MPI.COMM_WORLD.send(geoGrid.toArray(), GRIDDATASIZE, MPI.INT, i, tag);
			}
		}
	}

	public static void ResvGridData (ArrayList<GeoGrid> geoGrids) throws Exception {
		int[] numberOfGird = new int[1];
		// get the dataSize
		MPI.COMM_WORLD.recv(numberOfGird, 1, MPI.INT, mainProcessRank, tag);

		System.out.println(indentation() + "The received data is <" + numberOfGird[0] + ">. ");

		double[] gridData = new double[GRIDDATASIZE];

		for (int i = 0 ; i < numberOfGird[0] ; i++) {

			MPI.COMM_WORLD.recv(gridData, GRIDDATASIZE, MPI.DOUBLE, mainProcessRank, tag);

			System.out.println(indentation() + "The received data is <" + gridData[0] + " " + gridData[1] + ">. ");
			geoGrids.add(new GeoGrid(gridData));

		}
	}

	public static void SendSingleTwitter (String msg, int targetRank) throws Exception {
		char[] commandType = new char[1];
		commandType[0] = SINGLETWITTERCMD;
		int[] gridSize = new int[1];
		gridSize[0] = msg.length();

		// command type
		MPI.COMM_WORLD.send(commandType, 1, MPI.CHAR, targetRank, tag);
		// string length
		MPI.COMM_WORLD.send(gridSize, 1, MPI.INT, targetRank, tag);
		//System.out.println(indentation() + "The sent data is <" + geoGrids.size() + ">. ");

		MPI.COMM_WORLD.send(msg.toCharArray(), msg.length(), MPI.CHAR, targetRank, tag);
	}

	public static String ResvSingleTwitter () throws Exception {
		int[] strLength = new int[1];
		// get the dataSize
		MPI.COMM_WORLD.recv(strLength, 1, MPI.INT, mainProcessRank, tag);

		System.out.println(indentation() + "The received string Length is <" + strLength[0] + ">. ");

		char[] msg = new char[strLength[0]];

		MPI.COMM_WORLD.recv(msg, strLength[0], MPI.CHAR, mainProcessRank, tag);

		System.out.println(indentation() + "The received string is <" + new String(msg) + ">. ");
		
		return new String(msg);

	}


}
