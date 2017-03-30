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

	private static int prevSentProcess = 0;

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
				// process the files
				JsonArray gridArray = new JsonParser().parse(allGrid).getAsJsonObject().getAsJsonArray("features");
				// read all the grids
				for (JsonElement singleData : gridArray) {
					GeoGrid tempGrid = new GeoGrid(singleData);
					geoGrids.add(tempGrid);
				}
				// bcast gird data
				MPICommands.BcastGridData(geoGrids);

				FileInputStream fstream = new FileInputStream("./Data/smallTwitter.json");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String strLine;
				while ((strLine = br.readLine()) != null) {
					MPICommands.SendSingleTwitter(strLine.toString().replaceAll(",$", ""), nextRankToSend());
				}
				br.close();

				stopTime = System.currentTimeMillis();
				elapsedTime = stopTime - startTime;
				System.out.println(MPICommands.indentation() + "The total time of reading files is " + elapsedTime + " ms");

				MPICommands.BcastFinished();

				MPICommands.ResvResults(geoGrids);

				stopTime = System.currentTimeMillis();
				elapsedTime = stopTime - startTime;
				System.out.println(MPICommands.indentation() + "The total time of processing files is " + elapsedTime + " ms");

			} else {
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

			}



			// sort the ArrayList
			Collections.sort(geoGrids);

			if (isMainProcess()) {

				// output the results
				for (GeoGrid geoGrid : geoGrids) {
					System.out.println(MPICommands.indentation() + "\t#" + (int)geoGrid.internalID + "\t(" + geoGrid.name + "):\t " + geoGrid.Counter);
				}
			}

			//
			stopTime = System.currentTimeMillis();
			elapsedTime = stopTime - startTime;
			System.out.println(MPICommands.indentation() + "The total time of execution is " + elapsedTime + " ms");

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
			int myrank = MPI.COMM_WORLD.getRank() ;
			return myrank == MPICommands.mainProcessRank;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	public static int nextRankToSend() throws Exception {

		int myrank = MPI.COMM_WORLD.getRank() ;
		int size = MPI.COMM_WORLD.getSize() ;

		int next = (prevSentProcess + 1) % size;
		if (next == myrank) {
			next = (next + 1) % size;
		}

		prevSentProcess = next;

		return next;
	}


}
