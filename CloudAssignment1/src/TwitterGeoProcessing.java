import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
//import javax.json.Json;
//import javax.json.stream.*;
//import javax.json.stream.JsonParser.Event;
//import javax.json.stream.JsonParserFactory;
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
			
			String allGrid = ReadFullFile("melbGrid.json");
			JsonArray gridArray = new JsonParser().parse(allGrid).getAsJsonObject().getAsJsonArray("features");

			for(JsonElement singleData : gridArray)
			{
				//JsonObject grid = singleData.getAsJsonObject();
				GeoGrid tempGrid = new GeoGrid(singleData);
//				tempGrid.id = grid.getAsJsonObject("properties").get("id").getAsString();
//				tempGrid.xmin = grid.getAsJsonObject("properties").get("xmin").getAsDouble();
//				tempGrid.xmax = grid.getAsJsonObject("properties").get("xmax").getAsDouble();
//				tempGrid.ymin = grid.getAsJsonObject("properties").get("ymin").getAsDouble();
//				tempGrid.ymax = grid.getAsJsonObject("properties").get("ymax").getAsDouble();
//				
//				JsonArray geoData = grid.getAsJsonObject("geometry").getAsJsonArray("coordinates").get(0).getAsJsonArray();
//				
//				tempGrid.topLeft.longitude = geoData.get(0).getAsJsonArray().get(0).getAsJsonPrimitive().getAsDouble();
//				tempGrid.topLeft.latitude = geoData.get(0).getAsJsonArray().get(1).getAsJsonPrimitive().getAsDouble();
//				
//				tempGrid.topRight.longitude = geoData.get(1).getAsJsonArray().get(0).getAsJsonPrimitive().getAsDouble();
//				tempGrid.topRight.latitude = geoData.get(1).getAsJsonArray().get(1).getAsJsonPrimitive().getAsDouble();
//				
//				tempGrid.bottomRight.longitude = geoData.get(2).getAsJsonArray().get(0).getAsJsonPrimitive().getAsDouble();
//				tempGrid.bottomRight.latitude = geoData.get(2).getAsJsonArray().get(1).getAsJsonPrimitive().getAsDouble();
//				
//				tempGrid.bottomLeft.longitude = geoData.get(3).getAsJsonArray().get(0).getAsJsonPrimitive().getAsDouble();
//				tempGrid.bottomLeft.latitude = geoData.get(3).getAsJsonArray().get(1).getAsJsonPrimitive().getAsDouble();
//				
//				geoGrids.add(tempGrid);
				
				System.out.println(tempGrid.id + " " );
			}
			

			String allData = ReadFullFile("tinyTwitter.json");
			
			JsonArray jsonArray = new JsonParser().parse(allData).getAsJsonArray();

			for(JsonElement singleData : jsonArray)
			{
				JsonArray geo = singleData.getAsJsonObject().getAsJsonObject("json").getAsJsonObject("coordinates").getAsJsonArray("coordinates");
				for(JsonElement geodata : geo)
				{
					System.out.print(geodata.getAsDouble() + " ");
				}
				System.out.println();
			}

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
