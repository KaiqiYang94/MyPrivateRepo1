
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;

import javax.print.attribute.TextSyntax;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

import apple.laf.JRSUIState.TitleBarHeightState;
import sun.management.counter.Variability;

import java.util.ArrayList;
import java.util.Dictionary;

/**
 * Computes the edit distance between pairs of words. Can be used for
 * applications like finding near-match names in Kevin Bacon or spelling
 * correction.
 * 
 * There are two versions: a recursive version and a dynamic programming version
 * that memoizes the function by storing previously solved problems in a map.
 * 
 * @author Scot Drysdale
 */
public class BasicGED {
	private Map<SimpleEntry<String, String>, Integer> solvedProblems = new HashMap<SimpleEntry<String, String>, Integer>();

	private int GlobalEditDist(String s1, String s2) {
		int i = 1;
		int r = 1;
		int d = 1;
		int m = 0;

		int matchDist; // Edit distance if first char. match or do a replace
		int insertDist; // Edit distance if insert first char of s1 in front of
						// s2.
		int deleteDist; // Edit distance if delete first char of s2.

		if (s1.length() == 0)
			return s2.length(); // Insert the remainder of s2
		else if (s2.length() == 0)
			return s1.length(); // Delete the remainder of s1
		else {
			SimpleEntry<String, String> pair = new SimpleEntry<String, String>(s1, s2);
			Integer result = solvedProblems.containsKey(pair) ? solvedProblems.get(pair) : null;

			if (result != null) // Did we find the subproblem in the map?
				return result; // If so, return the answer
			else {
				matchDist = GlobalEditDist(s1.substring(1), s2.substring(1));
				if (Character.toLowerCase( s1.charAt(0)) != Character.toLowerCase( s2.charAt(0))) {
					matchDist = matchDist + r; // If first 2 char. don't match
												// must replace
				} else {
					matchDist = matchDist + m;
				}
				insertDist = GlobalEditDist(s1.substring(1), s2) + i;
				deleteDist = GlobalEditDist(s1, s2.substring(1)) + d;

				int dist = Math.min(matchDist, Math.min(insertDist,deleteDist));

				solvedProblems.put(pair, dist); // Save the result for future
				return dist;
			}
		}
	}
	
	public int GetDistance(String s1, String s2) {
		this.solvedProblems = new HashMap<SimpleEntry<String, String>, Integer>();
		return GlobalEditDist(s1, s2);
	}
	
	
	public static String fixedLength(String string) {
		int length = 15;
	    return String.format("%1$"+length+ "s", string);
	}
	

	/**
	 * Testing program
	 */
	public static void main(String[] args) {
		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		
		// All the names in the names.txt
		ArrayList<String> nameDict = new ArrayList<String>();

		// all the train data in the train.txt
		ArrayList<SimpleEntry<String, String>> trainData = new ArrayList<SimpleEntry<String, String>>();

		try {
			// the names.txt
			FileInputStream fstream = new FileInputStream("names.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				//System.out.println(strLine);
				nameDict.add(strLine);
			}
			System.out.println("the names.txt contains " + nameDict.size());
			
			// the train.txt
			fstream = new FileInputStream("train.txt");
			br = new BufferedReader(new InputStreamReader(fstream));
			while ((strLine = br.readLine()) != null) {
				//System.out.println(strLine);
				String[] temp = strLine.split("\t");
				trainData.add(new SimpleEntry<String, String>(temp[0], temp[1]));
			}
			System.out.println("the train.txt contains " + trainData.size());

			// Close the input stream
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BasicGED calc = new BasicGED();
		int testSize  = 1000;
		int CorrectSize = 0;
		int i = testSize;
		for (SimpleEntry<String, String> trainPair : trainData) {
			
			
			if(i-- <= 0)
			{
				break;
			}
			
			int minDistance = Integer.MAX_VALUE;
			String matchedName = "";
			for ( String name : nameDict) {
				int editDistance = calc.GetDistance(trainPair.getKey() , name);
				if(editDistance < minDistance)
				{
					matchedName = name;
					minDistance = editDistance;
				}
				if(editDistance == 0) {break;}
			}
			
			if(matchedName.equalsIgnoreCase(trainPair.getValue()))
			{
				CorrectSize ++;
			}
			
			System.out.println("The name " + fixedLength(trainPair.getKey().toString())
			+ "\t should be "+ fixedLength(trainPair.getValue().toString())
			+ "\t matched to " + fixedLength(matchedName.toString())
			+ "\t Distance is " + minDistance
			+ "\t"+ (matchedName.equalsIgnoreCase(trainPair.getValue())? "succeed" :"faild") );
		}
		
		System.out.println("Test size = " + testSize + " Corrcte ones are " + CorrectSize + "("+ (((float)CorrectSize/(float)testSize) * 100) +"%)");

	}
}