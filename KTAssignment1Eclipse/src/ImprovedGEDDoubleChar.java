
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
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
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
public class ImprovedGEDDoubleChar {
	private Map<SimpleEntry<String, String>, Float> solvedProblems = new HashMap<SimpleEntry<String, String>, Float>();

	private List<Character> vowelSet = Arrays.asList('a', 'e', 'i', 'o', 'u', 'y');

	private List<Character> plosiveSet = Arrays.asList('b', 'd', 'g', 'p', 't', 'k');

	private Map<String, Float> CharMaps = new HashMap<String, Float>();

	public ImprovedGEDDoubleChar() {
		// y i
		CharMaps.put(GetMapKey('y', 'i'), (float) 0.0);
		CharMaps.put(GetMapKey('i', 'y'), (float) 0.0);
		// v o
		CharMaps.put(GetMapKey('v', 'o'), (float) 0.0);
		CharMaps.put(GetMapKey('o', 'v'), (float) 0.0);
		// k x
		CharMaps.put(GetMapKey('k', 'x'), (float) 0.0);
		// k c
		CharMaps.put(GetMapKey('k', 'c'), (float) 0.0);
		// v w
		CharMaps.put(GetMapKey('v', 'w'), (float) 0.0);
		// v u
		CharMaps.put(GetMapKey('v', 'u'), (float) 0.0);

		// f ph
		CharMaps.put(GetMapKey("f", "ph"), (float) 0.0);
		// CharMaps.put(GetMapKey("ph", "f"), (float) 0.0);
		// y ie
		CharMaps.put(GetMapKey("y", "ie"), (float) 0.0);
		// CharMaps.put(GetMapKey("ie", "y"), (float) 0.0);
		// c ch
		CharMaps.put(GetMapKey("c", "ch"), (float) 0.0);
		// k ck
		CharMaps.put(GetMapKey("c", "ck"), (float) 0.0);
		// ks x
		CharMaps.put(GetMapKey("ks", "x"), (float) 0.0);
	}

	public String GetMapKey(char c1, char c2) {
		return GetMapKey(Character.toString(c1), Character.toString(c2));
	}

	public String GetMapKey(String c1, String c2) {
		return c1 + "-" + c2;
	}

	private float GlobalEditDist(String s1, String s2, Character preCharOfS1) {

		float matchCharStrDist = Float.MAX_VALUE; // mapping on string
		float matchStrCharDist = Float.MAX_VALUE; // mapping on string
		float matchCharDist; // Edit distance if first char. match or do a
								// replace
		float insertDist; // Edit distance if insert first char of s1 in front
							// of s2.
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

				// try using char to string
				String oldChar = s1.substring(0, 1).toLowerCase();
				String newStr = s2.length() > 2 ? s2.substring(0, 2).toLowerCase()
						: s2.length() == 2 ? s2.toLowerCase() : "";

				String strMapKey = GetMapKey(oldChar, newStr);

				if (CharMaps.containsKey(strMapKey)) {
					// map on string
					matchCharStrDist = GlobalEditDist(s1.substring(1), s2.substring(2), s1.charAt(0))
							+ GetReplaceCost(oldChar, newStr);
				}

				// try using string to char
				String oldStr = s1.length() > 2 ? s1.substring(0, 2).toLowerCase()
						: s1.length() == 2 ? s1.toLowerCase() : "";

				String newChar = s2.substring(0, 1);

				String strCharMapKey = GetMapKey(oldStr, newChar);

				if (CharMaps.containsKey(strCharMapKey)) {
					// map on string
					matchStrCharDist = GlobalEditDist(s1.substring(2), s2.substring(1), s1.charAt(1))
							+ GetReplaceCost(oldStr, newChar);
				}

				// match on char
				matchCharDist = GlobalEditDist(s1.substring(1), s2.substring(1), s1.charAt(0));
				if (Character.toLowerCase(s1.charAt(0)) != Character.toLowerCase(s2.charAt(0))) {
					matchCharDist = matchCharDist
							+ GetReplaceCost(Character.toLowerCase(s1.charAt(0)), Character.toLowerCase(s2.charAt(0)));
				} else {
					matchCharDist = matchCharDist + GetMatchCost();
				}
				insertDist = GlobalEditDist(s1.substring(1), s2, s1.charAt(0))
						+ GetInsertCost(s1.charAt(0), preCharOfS1);
				deleteDist = GlobalEditDist(s1, s2.substring(1), preCharOfS1) + GetDeleteCost(s2.charAt(0));

				float dist = Math.min(matchCharDist,
						Math.min(insertDist, Math.min(deleteDist, Math.min(matchCharStrDist, matchStrCharDist))));

				// System.out.println("Selected dist " + dist + "m, i, d = "
				// matchDist +","+ insertDist + ", "+ deleteDist);

				solvedProblems.put(pair, dist); // Save the result for future
				return dist;
			}
		}
	}

	public float insertString(String str) {
		float dist = 0;
		if (str.length() > 0) {
			Character preChar = str.charAt(0);
			for (Character character : str.toCharArray()) {
				dist += GetInsertCost(character, preChar);
				preChar = character;
			}
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

	public float GetReplaceCost(Character oldChar, Character newChar) {
		return GetReplaceCost(Character.toString(oldChar), Character.toString(newChar));
	}

	public float GetReplaceCost(String oldStr, String newStr) {
		// for the interchangeable letters
		if (CharMaps.containsKey(GetMapKey(oldStr, newStr))) {
			return CharMaps.get(GetMapKey(oldStr, newStr));
		}

		if (oldStr.length() == 1 && newStr.length() == 1) {
			char oldc = oldStr.charAt(0);
			char newc = newStr.charAt(0);
			// vowels are kind of interchangeable
			if (vowelSet.contains(oldc) && vowelSet.contains(newc)) {
				return 1;
			}

			// vowels replaced by non-vowels will be punished
			if ((vowelSet.contains(oldc) && !vowelSet.contains(newc))
					|| (!vowelSet.contains(oldc) && vowelSet.contains(newc))) {
				return 3;
			}

			return 2;
		}

		return 2;
	}

	public float GetInsertCost(char insertChar, Character preChar) {

		if (preChar != null && preChar.charValue() == insertChar) {
			return (float) 0.5;
		}

		if (vowelSet.contains(insertChar)) {
			return 1;
		}
		// if(plosiveSet.contains(insertChar)){
		// return (float) 3.1;
		// }
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
		return GlobalEditDist(s1, s2, null);
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

		ImprovedGEDDoubleChar calc = new ImprovedGEDDoubleChar();
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

		// Adding String matching
		// when the testSize = 100 the 51/100
		// when the testSize = 200 the 96/200
		// when the textSize = 300 the 131/300 43%

		// Adding the k and x mapping
		// when the textSize = 300 the 134/300 44%

		// Adding the v and w mapping (300 - 400)
		// when the textSize = 100 the 38/100 (from 37)

		// Adding the v and u mapping (300 - 400)
		// when the textSize = 100 the 41/100

		// Adding the k and c mapping (400 - 500)
		// when the textSize = 100 the 44/100 (from 42)

		// Adding the ks and x mapping (400 - 500) (from 44)
		// when the textSize = 100 the 47/100

		int lowerB = 400;
		int upperB = 500;

		int CorrectSize = 0;
		int i = 0;
		for (SimpleEntry<String, String> trainPair : trainData) {

			if (i++ <= lowerB) {
				continue;
			}

			if (i >= upperB) {
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

		System.out.println("Test size = " + (upperB - lowerB) + " Corrcte ones are " + CorrectSize + "("
				+ (((float) CorrectSize / (upperB - lowerB)) * 100) + "%)");

	}
}
