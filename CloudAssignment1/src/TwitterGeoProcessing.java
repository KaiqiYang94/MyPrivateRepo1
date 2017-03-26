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
			
			String allGrid = ReadFullFile("melbGrid.json");
			JsonArray gridArray = new JsonParser().parse(allGrid).getAsJsonObject().getAsJsonArray("features");

			for(JsonElement singleData : gridArray)
			{
				GeoGrid tempGrid = new GeoGrid(singleData);
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
