import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import sun.org.mozilla.javascript.internal.json.JsonParser;

import java.util.AbstractMap.SimpleEntry;

public class TwitterGeoProcessing {

	public static void main(String[] args) {
		// All the names in the names.txt
		ArrayList<String> nameDict = new ArrayList<String>();

		// all the train data in the train.txt
		ArrayList<SimpleEntry<String, String>> trainData = new ArrayList<SimpleEntry<String, String>>();

		try {
			// the names.txt
			FileInputStream fstream = new FileInputStream("tinyTwitter.json");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
			
			
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// System.out.println(strLine);
				nameDict.add(strLine);
			}
			System.out.println("the names.txt contains " + nameDict.size());

			// Close the input stream
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
