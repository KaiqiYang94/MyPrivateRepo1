
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.xml.internal.stream.Entity;

import sun.org.mozilla.javascript.internal.ast.WhileLoop;

import java.util.ArrayList;
import java.util.Arrays;

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
public class ImprovedGEDStringReplace {
	private Map<SimpleEntry<String, String>, Float> solvedProblems = new HashMap<SimpleEntry<String, String>, Float>();

	private List<Character> vowelSet = Arrays.asList('a', 'e', 'i', 'o', 'u', 'y');

	private List<Character> plosiveSet = Arrays.asList('b', 'd', 'g', 'p', 't', 'k');

	private Map<String, Float> CharMaps = new HashMap<String, Float>();

	public ImprovedGEDStringReplace() {
		CharMaps.put(GetMapKey('y', 'i'), (float) 0.0);
		CharMaps.put(GetMapKey('i', 'y'), (float) 0.0);
		CharMaps.put(GetMapKey('v', 'o'), (float) 0.0);
		CharMaps.put(GetMapKey('o', 'v'), (float) 0.0);
	}

	public String GetMapKey(char c1, char c2) {
		return GetMapKey(Character.toString(c1), Character.toString(c2));
	}

	public String GetMapKey(String c1, String c2) {
		return c1 + "-" + c2;
	}

	private float GlobalEditDist(String s1, String s2) {
		float matchDist; // Edit distance if first char. match or do a replace
		float insertDist; // Edit distance if insert first char of s1 in front
							// of
							// s2.
		float deleteDist; // Edit distance if delete first char of s2.

		if (s1.length() == 0)
			return insertString(s2); // Insert the remainder of s2
		else if (s2.length() == 0)
			return removeString(s1); // Delete the remainder of s1
		else {
			SimpleEntry<String, String> pair = new SimpleEntry<String, String>(s1, s2);
			Float result = solvedProblems.containsKey(pair) ? solvedProblems.get(pair) : null;

			if (result != null) // Did we find the subproblem in the map?
				return result; // If so, return the answer
			else {
				matchDist = GlobalEditDist(s1.substring(1), s2.substring(1));
				if (Character.toLowerCase(s1.charAt(0)) != Character.toLowerCase(s2.charAt(0))) {
					matchDist = matchDist
							+ GetReplaceCost(Character.toLowerCase(s1.charAt(0)), Character.toLowerCase(s2.charAt(0)));
				} else {
					matchDist = matchDist + GetMatchCost();
				}
				insertDist = GlobalEditDist(s1.substring(1), s2) + GetInsertCost(s1.charAt(0));
				deleteDist = GlobalEditDist(s1, s2.substring(1)) + GetDeleteCost(s2.charAt(0));

				float dist = Math.min(matchDist, Math.min(insertDist, deleteDist));

				// System.out.println("Selected dist " + dist + "m, i, d = "
				// matchDist +","+ insertDist + ", "+ deleteDist);

				solvedProblems.put(pair, dist); // Save the result for future
				return dist;
			}
		}
	}

	public float insertString(String str) {
		float dist = 0;
		for (Character character : str.toCharArray()) {
			dist += GetInsertCost(character);
		}
		return dist;
	}

	public float removeString(String str) {
		float dist = 0;
		for (Character character : str.toCharArray()) {
			dist += GetDeleteCost(character);
		}
		return dist;
	}

	public float GetMatchCost() {
		return 0;
	}

	public float GetReplaceCost(char oldChar, char newChar) {
		// for the interchangeable letters
		if (CharMaps.containsKey(GetMapKey(oldChar, newChar))) {
			return CharMaps.get(GetMapKey(oldChar, newChar));
		}
		
		// vowels are kind of interchangeable
		if (vowelSet.contains(oldChar) && vowelSet.contains(newChar)) {
			return 1;
		}
		
		// vowels replaced by non-vowels will be punished
		if ((vowelSet.contains(oldChar) && !vowelSet.contains(newChar))
				|| (!vowelSet.contains(oldChar) && vowelSet.contains(newChar))) {
			return 3;
		}
		return 2;
	}

	public float GetInsertCost(char insertChar) {
		if (vowelSet.contains(insertChar)) {
			return 1;
		}
		return 2;
	}

	public float GetDeleteCost(char removedChar) {
		// did not really work, means there are almost no plosive letters get
		// removed
		// if(plosiveSet.contains(removedChar))
		// {
		// return 4;
		// }
		return 2;
	}

	public float GetDistance(String s1, String s2) {
		this.solvedProblems = new HashMap<SimpleEntry<String, String>, Float>();
		return GlobalEditDist(s1, s2);
	}

	public static String fixedLength(String string) {
		int length = 15;
		return String.format("%1$" + length + "s", string);
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
				// System.out.println(strLine);
				nameDict.add(strLine);
			}
			System.out.println("the names.txt contains " + nameDict.size());

			// the train.txt
			fstream = new FileInputStream("train.txt");
			br = new BufferedReader(new InputStreamReader(fstream));
			while ((strLine = br.readLine()) != null) {
				// System.out.println(strLine);
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

		ImprovedGEDStringReplace calc = new ImprovedGEDStringReplace();
		// first
		// when the testSize = 100 the 23/100
		// when the testSize = 200 the 40/200

		// fix the return length
		// when the testSize = 100 the 30/100
		// when the testSize = 200 the 53/200

		// Using the vowelSet
		// when the testSize = 100 the 35/100
		// when the testSize = 200 the 60/200

		// Adding the y to the vowelSet
		// when the testSize = 100 the 38/100
		// when the testSize = 200 the 68/200
		// found that
		// The name AACTAY should be aechtie matched to mackay Distance is 4
		// faild

		// Adding punishment when replacing vowels and non-vowels
		// when the testSize = 100 the 42/100
		// when the testSize = 200 the 73/200
		// now
		// The name AACTAY should be aechtie matched to aechtie Distance is 5
		// succeed

		// Adding data for the interchangeable letters yi, vo
		// when the testSize = 100 the 46/100
		// when the testSize = 200 the 81/200
		
		// Change the order of the conditions in the GetReplaceCost
		// when the testSize = 100 the 46/100
		// when the testSize = 200 the 86/200
		int testSize = 100;

		int CorrectSize = 0;
		int i = testSize;
		for (SimpleEntry<String, String> trainPair : trainData) {

			if (i-- <= 0) {
				break;
			}

			float minDistance = Float.MAX_VALUE;
			String matchedName = "";
			for (String name : nameDict) {
				float editDistance = calc.GetDistance(trainPair.getKey(), name);
				if (editDistance < minDistance) {
					matchedName = name;
					minDistance = editDistance;
				}
				if (editDistance == 0) {
					break;
				}
			}

			if (matchedName.equalsIgnoreCase(trainPair.getValue())) {
				CorrectSize++;
			}

			System.out.println("The name " + fixedLength(trainPair.getKey().toString()) + "\t should be "
					+ fixedLength(trainPair.getValue().toString()) + "\t matched to "
					+ fixedLength(matchedName.toString()) + "\t Distance is " + minDistance + "\t"
					+ (matchedName.equalsIgnoreCase(trainPair.getValue()) ? "succeed" : "faild"));
		}

		System.out.println("Test size = " + testSize + " Corrcte ones are " + CorrectSize + "("
				+ (((float) CorrectSize / (float) testSize) * 100) + "%)");

	}
}
