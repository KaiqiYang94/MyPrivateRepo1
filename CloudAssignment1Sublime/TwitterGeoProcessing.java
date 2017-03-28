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

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		long stopTime;
		long elapsedTime;
		try {
			// get all the data from file
			ArrayList<GeoGrid> geoGrids = new ArrayList<GeoGrid>();

			// get all the grids
			String allGrid = ReadFullFile("./Data/melbGrid.json");

			// get all twitter data
			String allData = ReadFullFile("./Data/tinyTwitterError.json");

			stopTime = System.currentTimeMillis();
			elapsedTime = stopTime - startTime;
			System.out.println("The total time of reading files is " + elapsedTime + " ms");

			// process the files
			JsonArray gridArray = new JsonParser().parse(allGrid).getAsJsonObject().getAsJsonArray("features");

			for (JsonElement singleData : gridArray) {
				GeoGrid tempGrid = new GeoGrid(singleData);
				geoGrids.add(tempGrid);
			}

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

			stopTime = System.currentTimeMillis();
			elapsedTime = stopTime - startTime;
			System.out.println("The total time of processing files is " + elapsedTime + " ms");

			// sort the ArrayList
			Collections.sort(geoGrids);

			// output the results
			for (GeoGrid geoGrid : geoGrids) {
				System.out.println(geoGrid.id + ": " + geoGrid.Counter);
			}

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}

		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println("The total time of execution is " + elapsedTime + " ms");

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

}
