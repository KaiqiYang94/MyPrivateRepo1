import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
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

				for (JsonElement singleData : gridArray) {
					GeoGrid tempGrid = new GeoGrid(singleData);
					geoGrids.add(tempGrid);
				}

				BcastGridData(geoGrids);

				JsonArray jsonArray = new JsonParser().parse(allData).getAsJsonArray();
				for (JsonElement singleData : jsonArray) {
					try {
						ProcessSingleJson(singleData, geoGrids);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Exception when processing the data, the data is  " + singleData.getAsString());
						continue;
					}
				}
			} 
			else
			{
				ReceiveGridData(geoGrids);
			} 

			stopTime = System.currentTimeMillis();
			elapsedTime = stopTime - startTime;
			System.out.println(indentation() + "The total time of processing files is " + elapsedTime + " ms");

			// sort the ArrayList
			Collections.sort(geoGrids);

			// output the results
			for (GeoGrid geoGrid : geoGrids) {
				System.out.println("\t\t" + geoGrid.id + ": " + geoGrid.Counter);
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

	public static void BcastGridData (ArrayList<GeoGrid> geoGrids) throws Exception{
		char[] commandType = new char[1];
		commandType[0] = 'G';
		MPI.COMM_WORLD.send(commandType, 1, MPI.CHAR, 1, tag);
	}


	public static void ReceiveGridData (ArrayList<GeoGrid> geoGrids) throws Exception{
		char[] commandType = new char[1];
		MPI.COMM_WORLD.recv(commandType, 1, MPI.CHAR, mainProcessRank, tag);
		System.out.println(indentation() + "The received commandType is " + commandType[0] + ". ");
	}
}
