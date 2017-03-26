import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
//import javax.json.Json;
//import javax.json.stream.*;
//import javax.json.stream.JsonParser.Event;
//import javax.json.stream.JsonParserFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TwitterGeoProcessing {

	public static void main(String[] args) {

		try {
			// the names.txt
			FileInputStream fstream = new FileInputStream("tinyTwitter.json");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String allData = "";
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				allData += strLine;
			}
			// Close the input stream
			br.close();

			
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

}
