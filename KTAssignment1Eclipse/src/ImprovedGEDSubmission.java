
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ImprovedGEDSubmission {

	// the distances
	private float GeneralInsertDist = 2;
	private float GeneralReplaceDist = 2;
	private float GeneralDeleteDist = 2;
	private float DeleteFromFirstDist = (float) 0.9;
	private float GeneralMatchDist = 0;
	private float VowelsReplaceDist = (float) 0.5;
	private float VowelsNonVowelsReplaceDist = 3;
	private float DoubleLetterInsertDist = (float) 0.5;
	private float VowelsInsertDist = 0;
	private float PlosiveInsertDist = (float) 3.1;
	private float PlosiveDeleteDist = 4;

	// a map to store the solved problems to speed up
	private Map<SimpleEntry<String, String>, Float> solvedProblems = new HashMap<SimpleEntry<String, String>, Float>();

	private List<Character> vowelSet = Arrays.asList('a', 'e', 'i', 'o', 'u', 'y');

	private List<Character> plosiveSet = Arrays.asList('b', 'd', 'g', 'p', 't', 'k');

	private List<Character> removeFromFirstSet = Arrays.asList('a');

	private Map<String, Float> CharMaps = new HashMap<String, Float>();

	public ImprovedGEDSubmission() {

		// adding the replacing pairs and its distance
		// y i
		CharMaps.put(GetMapKey('y', 'i'), (float) 0.0);
		CharMaps.put(GetMapKey('i', 'y'), (float) 0.0);
		// v o
		CharMaps.put(GetMapKey('v', 'o'), (float) 0.0);
		CharMaps.put(GetMapKey('o', 'v'), (float) 0.0);
		// k x
		CharMaps.put(GetMapKey('k', 'x'), (float) 0.0);
		// k q
		CharMaps.put(GetMapKey('k', 'q'), (float) 0.0);
		// k c
		CharMaps.put(GetMapKey('k', 'c'), (float) 0.0);
		// v w
		CharMaps.put(GetMapKey('v', 'w'), (float) 0.0);
		// v u
		CharMaps.put(GetMapKey('v', 'u'), (float) 0.0);
		// z s
		CharMaps.put(GetMapKey('z', 's'), (float) 0.0);
		// z j
		CharMaps.put(GetMapKey('z', 'j'), (float) 0.0);
		// s c
		CharMaps.put(GetMapKey('s', 'c'), (float) 0.0);

		// s sh
		CharMaps.put(GetMapKey("s", "sh"), (float) 0.0);
		// t th
		CharMaps.put(GetMapKey("t", "th"), (float) 0.0);
		// y eizs
		CharMaps.put(GetMapKey("y", "ei"), (float) 0.0);
		// l ll
		CharMaps.put(GetMapKey("l", "ll"), (float) 0.5);
		// j dg
		CharMaps.put(GetMapKey("j", "dg"), (float) 0.0);
		// v w
		CharMaps.put(GetMapKey("v", "oo"), (float) 0.0);
		// f ph
		CharMaps.put(GetMapKey("f", "ph"), (float) 0.0);
		// y ie
		CharMaps.put(GetMapKey("y", "ie"), (float) 0.0);
		CharMaps.put(GetMapKey("y", "ee"), (float) 0.0);
		// c ch
		CharMaps.put(GetMapKey("c", "ch"), (float) -0.3);
		// k ck
		CharMaps.put(GetMapKey("c", "ck"), (float) 0.0);
		// ks x
		CharMaps.put(GetMapKey("ks", "x"), (float) 0.0);

		// as a
		CharMaps.put(GetMapKey("as", "s"), (float) 0.0);
	}

	public String GetMapKey(char c1, char c2) {
		return GetMapKey(Character.toString(c1), Character.toString(c2));
	}

	public String GetMapKey(String c1, String c2) {
		return c1 + "-" + c2;
	}

	private float GlobalEditDist(String s1, String s2, Character preCharOfS1, Character preCharOfS2) {

		float matchCharStrDist = Float.MAX_VALUE;
		float matchStrCharDist = Float.MAX_VALUE;
		float matchCharDist;
		float insertDist;
		float deleteDist;

		if (s1.length() == 0)
			return insertString(s2);
		else if (s2.length() == 0)
			return removeString(s1);
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
					matchCharStrDist = GlobalEditDist(s1.substring(1), s2.substring(2), s1.charAt(0), s2.charAt(1))
							+ GetReplaceCost(oldChar, newStr);
				}

				// try using string to char
				String oldStr = s1.length() > 2 ? s1.substring(0, 2).toLowerCase()
						: s1.length() == 2 ? s1.toLowerCase() : "";

				String newChar = s2.substring(0, 1);

				String strCharMapKey = GetMapKey(oldStr, newChar);

				if (CharMaps.containsKey(strCharMapKey)) {
					// map on string
					matchStrCharDist = GlobalEditDist(s1.substring(2), s2.substring(1), s1.charAt(1), s2.charAt(0))
							+ GetReplaceCost(oldStr, newChar);
				}

				// match on char
				matchCharDist = GlobalEditDist(s1.substring(1), s2.substring(1), s1.charAt(0), s2.charAt(0));
				if (Character.toLowerCase(s1.charAt(0)) != Character.toLowerCase(s2.charAt(0))) {
					matchCharDist = matchCharDist
							+ GetReplaceCost(Character.toLowerCase(s1.charAt(0)), Character.toLowerCase(s2.charAt(0)));
				} else {
					matchCharDist = matchCharDist + GetMatchCost();
				}
				deleteDist = GlobalEditDist(s1.substring(1), s2, s1.charAt(0), preCharOfS2)
						+ GetDeleteCost(s1.charAt(0), preCharOfS1);
				insertDist = GlobalEditDist(s1, s2.substring(1), preCharOfS1, s2.charAt(0))
						+ GetInsertCost(s2.charAt(0), preCharOfS2);

				float dist = Math.min(matchCharDist,
						Math.min(insertDist, Math.min(deleteDist, Math.min(matchCharStrDist, matchStrCharDist))));

				solvedProblems.put(pair, dist);
				return dist;
			}
		}
	}

	// distance of inserting the whole string
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

	// distance of removing the whole string
	public float removeString(String str) {
		float dist = 0;
		Character preChar = str.charAt(0);
		for (Character character : str.toCharArray()) {
			dist += GetDeleteCost(character, preChar);
			preChar = character;
		}
		return dist;
	}

	public float GetMatchCost() {
		return GeneralMatchDist;
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
				return VowelsReplaceDist;
			}

			// vowels replaced by non-vowels will be punished
			if ((vowelSet.contains(oldc) && !vowelSet.contains(newc))
					|| (!vowelSet.contains(oldc) && vowelSet.contains(newc))) {
				return VowelsNonVowelsReplaceDist;
			}
			return GeneralReplaceDist;
		}
		return GeneralReplaceDist;
	}

	public float GetInsertCost(char insertChar, Character preChar) {
		// doubled same letter will not be punished too hard
		if (preChar != null && preChar.charValue() == insertChar) {
			return DoubleLetterInsertDist;
		}
		// inserting vowel, small distance
		if (preChar != null && vowelSet.contains(insertChar)) {
			return VowelsInsertDist;
		}
		// inserting plosive, long distance
		if (plosiveSet.contains(insertChar)) {
			return PlosiveInsertDist;
		}
		return GeneralInsertDist;
	}

	public float GetDeleteCost(char removedChar, Character preChar) {
		// remove the first char?
		if (preChar == null) {
			if (removeFromFirstSet.contains(removedChar)) {
				return DeleteFromFirstDist;
			}
		}

		// remove the plosive
		if (plosiveSet.contains(removedChar)) {
			return PlosiveDeleteDist;
		}
		return GeneralDeleteDist;
	}

	public float GetDistance(String s1, String s2) {
		this.solvedProblems = new HashMap<SimpleEntry<String, String>, Float>();
		return GlobalEditDist(s1, s2, null, null);
	}

	public static String fixedLength(String string) {
		int length = 15;
		return String.format("%1$" + length + "s", string);
	}

	private static Map<String, ArrayList<String>> sortByValue(Map<String, ArrayList<String>> unsortMap) {

		List<Map.Entry<String, ArrayList<String>>> list = new LinkedList<Map.Entry<String, ArrayList<String>>>(
				unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<String>>>() {
			public int compare(Map.Entry<String, ArrayList<String>> o1, Map.Entry<String, ArrayList<String>> o2) {
				return (o1.getKey()).compareTo(o2.getKey());
			}
		});

		Map<String, ArrayList<String>> sortedMap = new LinkedHashMap<String, ArrayList<String>>();
		for (Map.Entry<String, ArrayList<String>> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	/**
	 * Testing program
	 */
	public static void main(String[] args) {

		// All the names in the names.txt
		ArrayList<String> nameDict = new ArrayList<String>();

		Map<String, ArrayList<String>> trainData = new HashMap<String, ArrayList<String>>();

		try {
			// the names.txt
			FileInputStream fstream = new FileInputStream("names.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				nameDict.add(strLine);
			}

			// the train.txt
			fstream = new FileInputStream("train.txt");
			br = new BufferedReader(new InputStreamReader(fstream));
			while ((strLine = br.readLine()) != null) {
				// System.out.println(strLine);
				String[] temp = strLine.split("\t");

				if (trainData.containsKey(temp[0])) {
					trainData.get(temp[0]).add(temp[1]);
				} else {
					trainData.put(temp[0], new ArrayList<String>());
					trainData.get(temp[0]).add(temp[1]);
				}
			}

			// Close the input stream
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, ArrayList<String>> sortedMap = sortByValue(trainData);

		List<Map.Entry<String, ArrayList<String>>> list = new LinkedList<Map.Entry<String, ArrayList<String>>>(
				sortedMap.entrySet());

		int lowerB = 0;
		int upperB = Integer.MAX_VALUE;

		int allattempt = 0;
		int CorrectSize = 0;
		int recall = 0;
		int i = 0;
		for (Map.Entry<String, ArrayList<String>> trainPair : list) {

			// processing part of the data // for testing 
			if (i++ < lowerB) {
				continue;
			}

			if (i >= upperB) {
				break;
			}

			ImprovedGEDSubmission calc = new ImprovedGEDSubmission();

			float minDistance = Float.MAX_VALUE;
			List<String> matchedName = new ArrayList<String>();
			for (String name : nameDict) {
				float editDistance = calc.GetDistance(trainPair.getKey(), name);
				if (editDistance < minDistance) {
					minDistance = editDistance;
					matchedName = new ArrayList<String>();
					matchedName.add(name);
				} else if (editDistance == minDistance) {
					matchedName.add(name);
				}
				if (editDistance == -0.3) {
					break;
				}
			}

			if (matchedName.size() > 3) {
				matchedName = matchedName.subList(0, 2);
			}

			if (trainPair.getValue().contains(matchedName.get(0))) {
				CorrectSize++;
			}

			for (String str : matchedName) {
				if (trainPair.getValue().contains(str)) {
					recall++;
				}
			}

			allattempt += matchedName.size();

			System.out.println("The name " + fixedLength(trainPair.getKey().toString()) + "\t should be "
					+ fixedLength(trainPair.getValue().toString()) + "\t matched to "
					+ fixedLength(matchedName.toString()) + "\t Distance is " + minDistance + "\t"
					+ (trainPair.getValue().contains(matchedName.get(0)) ? "succeeded" : "failed")
					);
		}

		System.out.println("Test size = " + (i - lowerB) + " Correct first attempts are " + CorrectSize
				+ " ( Accuracy of just using the first attempt is " + (((float) CorrectSize / (i - lowerB)) * 100) + "%)");

		System.out.println("Test size = " + (i - lowerB) + " All corret answers are " + recall + " ( Recall is "
				+ (((float) recall / (i - lowerB)) * 100) + "%)");

		System.out.println("Test size = " + (i - lowerB) + " All attempts are " + allattempt + " ( precision is "
				+ (((float) recall / allattempt) * 100) + "%)");
	}
}
