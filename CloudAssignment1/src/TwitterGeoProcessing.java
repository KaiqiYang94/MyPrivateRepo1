import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import javax.swing.SortingFocusTraversalPolicy;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import sun.org.mozilla.javascript.internal.ast.NewExpression;

public class TwitterGeoProcessing {

	public static void main(String[] args) {

		try {
			ArrayList<GeoGrid> geoGrids = new ArrayList<GeoGrid>();
			ArrayList<Coordinate> allCoor = new ArrayList<Coordinate>();

			// get all the grids
			String allGrid = ReadFullFile("melbGrid.json");
			JsonArray gridArray = new JsonParser().parse(allGrid).getAsJsonObject().getAsJsonArray("features");

			for (JsonElement singleData : gridArray) {
				GeoGrid tempGrid = new GeoGrid(singleData);
				geoGrids.add(tempGrid);
			}

			// get all twitter data
			String allData = ReadFullFile("tinyTwitter.json");

			JsonArray jsonArray = new JsonParser().parse(allData).getAsJsonArray();
			for (JsonElement singleData : jsonArray) {
				Coordinate tempCoor = new Coordinate();
				JsonArray geo = singleData.getAsJsonObject().getAsJsonObject("json").getAsJsonObject("coordinates")
						.getAsJsonArray("coordinates");

				tempCoor.longitude = geo.get(0).getAsJsonPrimitive().getAsDouble();
				tempCoor.latitude = geo.get(1).getAsJsonPrimitive().getAsDouble();

				allCoor.add(tempCoor);
			}

			// process the data
			for (Coordinate coordinate : allCoor) {
				for (GeoGrid geoGrid : geoGrids) {
					if (geoGrid.isInGrid(coordinate)) {
						geoGrid.Counter++;
						break;
					}
				}
			}

			// output the results 
			for (GeoGrid geoGrid : geoGrids) {
				System.out.println(geoGrid.id + ": " + geoGrid.Counter);
			}

			System.out.println();

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

}
