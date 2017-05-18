import java.util.*;
import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.AbstractMap.*;

class GenerateFeatures {

	static String negative = "negative";
	static String neutral = "neutral";
	static String positive = "positive";

	static List<SimpleEntry<String, String>> allAttributes = new ArrayList<SimpleEntry<String, String>>();

	// <String, String> allAttributes = new HashMap<String, String>();

	// static List<String> matchList = new ArrayList<String>();
	static Map<String, Integer[]> allEmoji = new HashMap<String, Integer[]>();

	static Map<String, Integer[]> attributes = new HashMap<String, Integer[]>();

	static Stemmer s = new Stemmer();

	public static void initialization() {
		allAttributes.add(new SimpleEntry("i", "NUMERIC"));// = 1.0984 1044 2099 1957 
allAttributes.add(new SimpleEntry("NEGATIONWORD", "NUMERIC"));// = 1.0602 1830 2316 977 
allAttributes.add(new SimpleEntry("scam", "NUMERIC"));// = 1.0011 11 0 0 
allAttributes.add(new SimpleEntry("excitement", "NUMERIC"));// = 1.0011 1 0 10 
allAttributes.add(new SimpleEntry("scum", "NUMERIC"));// = 1.0011 11 0 0 
allAttributes.add(new SimpleEntry("delicious", "NUMERIC"));// = 1.001 0 0 10 
allAttributes.add(new SimpleEntry("puppet", "NUMERIC"));// = 1.001 10 0 0 
allAttributes.add(new SimpleEntry("❤", "NUMERIC"));// = 1.0009 0 0 9 
allAttributes.add(new SimpleEntry("destroying", "NUMERIC"));// = 1.0009 7 0 2 
allAttributes.add(new SimpleEntry("attractive", "NUMERIC"));// = 1.0009 3 0 6 
allAttributes.add(new SimpleEntry("neo", "NUMERIC"));// = 1.0009 9 0 0 
allAttributes.add(new SimpleEntry("merry", "NUMERIC"));// = 1.0008 0 0 8 
allAttributes.add(new SimpleEntry("disgrace", "NUMERIC"));// = 1.0008 8 0 0 
allAttributes.add(new SimpleEntry("▓", "NUMERIC"));// = 1.0008 0 0 8 
allAttributes.add(new SimpleEntry("ashamed", "NUMERIC"));// = 1.0008 8 0 0 
allAttributes.add(new SimpleEntry("assange", "NUMERIC"));// = 1.0008 7 0 1 
allAttributes.add(new SimpleEntry("supremacist", "NUMERIC"));// = 1.0008 8 0 0 
allAttributes.add(new SimpleEntry("nightmare", "NUMERIC"));// = 1.0008 8 0 0 
allAttributes.add(new SimpleEntry("cutest", "NUMERIC"));// = 1.0007 0 0 7 
allAttributes.add(new SimpleEntry("lied", "NUMERIC"));// = 1.0007 7 0 0 
allAttributes.add(new SimpleEntry("nite", "NUMERIC"));// = 1.0007 3 0 4 
allAttributes.add(new SimpleEntry("beasts", "NUMERIC"));// = 1.0007 0 0 7 
allAttributes.add(new SimpleEntry("sales", "NUMERIC"));// = 1.0007 4 0 3 
allAttributes.add(new SimpleEntry("boo", "NUMERIC"));// = 1.0007 5 0 2 
allAttributes.add(new SimpleEntry("handsome", "NUMERIC"));// = 1.0007 0 0 7 
allAttributes.add(new SimpleEntry("privilege", "NUMERIC"));// = 1.0007 5 0 2 
allAttributes.add(new SimpleEntry("almighty", "NUMERIC"));// = 1.0007 1 0 6 
allAttributes.add(new SimpleEntry("fascinating", "NUMERIC"));// = 1.0007 2 0 5 
allAttributes.add(new SimpleEntry("dislike", "NUMERIC"));// = 1.0007 6 0 1 
allAttributes.add(new SimpleEntry("extremists", "NUMERIC"));// = 1.0007 7 0 0 
allAttributes.add(new SimpleEntry("sweetheart", "NUMERIC"));// = 1.0006 1 0 5 
allAttributes.add(new SimpleEntry("happier", "NUMERIC"));// = 1.0006 1 0 5 
allAttributes.add(new SimpleEntry("zionists", "NUMERIC"));// = 1.0006 5 0 1 
allAttributes.add(new SimpleEntry("40%", "NUMERIC"));// = 1.0006 6 0 0 
allAttributes.add(new SimpleEntry("hottest", "NUMERIC"));// = 1.0006 1 0 5 
allAttributes.add(new SimpleEntry("zionist", "NUMERIC"));// = 1.0006 5 0 1 
allAttributes.add(new SimpleEntry("stupidity", "NUMERIC"));// = 1.0006 6 0 0 
allAttributes.add(new SimpleEntry("earn", "NUMERIC"));// = 1.0006 2 0 4 
allAttributes.add(new SimpleEntry("unpopular", "NUMERIC"));// = 1.0006 3 0 3 
allAttributes.add(new SimpleEntry("lately", "NUMERIC"));// = 1.0006 2 0 4 
allAttributes.add(new SimpleEntry("privatize", "NUMERIC"));// = 1.0006 5 0 1 
allAttributes.add(new SimpleEntry("mf", "NUMERIC"));// = 1.0006 4 0 2 
allAttributes.add(new SimpleEntry("fascists", "NUMERIC"));// = 1.0006 6 0 0 
allAttributes.add(new SimpleEntry("sucked", "NUMERIC"));// = 1.0006 4 0 2 
allAttributes.add(new SimpleEntry("shoutout", "NUMERIC"));// = 1.0006 1 0 5 
allAttributes.add(new SimpleEntry("mayor", "NUMERIC"));// = 1.0006 4 0 2 
allAttributes.add(new SimpleEntry("rogue", "NUMERIC"));// = 1.0006 2 0 4 
allAttributes.add(new SimpleEntry("deadly", "NUMERIC"));// = 1.0006 5 0 1 
allAttributes.add(new SimpleEntry("enemies", "NUMERIC"));// = 1.0006 6 0 0 
allAttributes.add(new SimpleEntry("essentially", "NUMERIC"));// = 1.0006 3 0 3 
allAttributes.add(new SimpleEntry("socks", "NUMERIC"));// = 1.0006 2 0 4 
allAttributes.add(new SimpleEntry("hateful", "NUMERIC"));// = 1.0006 6 0 0 
allAttributes.add(new SimpleEntry("dust", "NUMERIC"));// = 1.0006 3 0 3 
allAttributes.add(new SimpleEntry("threatens", "NUMERIC"));// = 1.0006 6 0 0 
allAttributes.add(new SimpleEntry("cult", "NUMERIC"));// = 1.0006 3 0 3 
allAttributes.add(new SimpleEntry("threatened", "NUMERIC"));// = 1.0006 6 0 0 
allAttributes.add(new SimpleEntry("minded", "NUMERIC"));// = 1.0006 6 0 0 
allAttributes.add(new SimpleEntry("disaster", "NUMERIC"));// = 1.0006 6 0 0 
allAttributes.add(new SimpleEntry("otra", "NUMERIC"));// = 1.0006 0 0 6 
allAttributes.add(new SimpleEntry("blessing", "NUMERIC"));// = 1.0006 2 0 4 
allAttributes.add(new SimpleEntry("excited", "NUMERIC"));// = 0.9767 6 4 105 
allAttributes.add(new SimpleEntry("fantastic", "NUMERIC"));// = 0.9758 0 1 35 
allAttributes.add(new SimpleEntry("enjoyed", "NUMERIC"));// = 0.9671 1 1 26 
allAttributes.add(new SimpleEntry("incredible", "NUMERIC"));// = 0.9641 1 1 24 
allAttributes.add(new SimpleEntry("idiot", "NUMERIC"));// = 0.9607 22 1 1 
allAttributes.add(new SimpleEntry("nazis", "NUMERIC"));// = 0.952 19 1 0 
allAttributes.add(new SimpleEntry("awesome", "NUMERIC"));// = 0.9464 2 5 74 
allAttributes.add(new SimpleEntry("blessed", "NUMERIC"));// = 0.9462 0 1 17 
allAttributes.add(new SimpleEntry("amazing", "NUMERIC"));// = 0.9415 1 6 82 
allAttributes.add(new SimpleEntry("happy", "NUMERIC"));// = 0.94 23 33 292 
allAttributes.add(new SimpleEntry("brilliant", "NUMERIC"));// = 0.9386 2 2 27 
allAttributes.add(new SimpleEntry("&lt;3", "NUMERIC"));// = 0.9363 2 2 26 
allAttributes.add(new SimpleEntry("violent", "NUMERIC"));// = 0.9348 10 1 4 
allAttributes.add(new SimpleEntry("hates", "NUMERIC"));// = 0.9348 14 1 0 
allAttributes.add(new SimpleEntry("disgusting", "NUMERIC"));// = 0.9348 14 1 0 
allAttributes.add(new SimpleEntry("worst", "NUMERIC"));// = 0.9328 50 4 1 
allAttributes.add(new SimpleEntry("suck", "NUMERIC"));// = 0.93 10 1 3 
allAttributes.add(new SimpleEntry("jokes", "NUMERIC"));// = 0.93 9 1 4 
allAttributes.add(new SimpleEntry("loved", "NUMERIC"));// = 0.9249 3 3 32 
allAttributes.add(new SimpleEntry("ruin", "NUMERIC"));// = 0.9244 9 1 3 
allAttributes.add(new SimpleEntry("iced", "NUMERIC"));// = 0.9244 4 1 8 
allAttributes.add(new SimpleEntry("deaths", "NUMERIC"));// = 0.9244 10 1 2 
allAttributes.add(new SimpleEntry("organization", "NUMERIC"));// = 0.9179 10 1 1 
allAttributes.add(new SimpleEntry("pumped", "NUMERIC"));// = 0.9179 0 1 11 
allAttributes.add(new SimpleEntry("fool", "NUMERIC"));// = 0.9102 9 1 1 
allAttributes.add(new SimpleEntry("pathetic", "NUMERIC"));// = 0.9102 9 1 1 
allAttributes.add(new SimpleEntry("idiots", "NUMERIC"));// = 0.9102 10 1 0 
allAttributes.add(new SimpleEntry("corbyn", "NUMERIC"));// = 0.9102 9 1 1 
allAttributes.add(new SimpleEntry("screening", "NUMERIC"));// = 0.9102 2 1 8 
allAttributes.add(new SimpleEntry("msm", "NUMERIC"));// = 0.9102 10 1 0 
allAttributes.add(new SimpleEntry("cream", "NUMERIC"));// = 0.9074 0 17 140 
allAttributes.add(new SimpleEntry("excellent", "NUMERIC"));// = 0.9069 0 2 19 
allAttributes.add(new SimpleEntry("stunning", "NUMERIC"));// = 0.901 1 1 8 
allAttributes.add(new SimpleEntry("hip", "NUMERIC"));// = 0.901 1 1 8 
allAttributes.add(new SimpleEntry("mood", "NUMERIC"));// = 0.901 2 1 7 
allAttributes.add(new SimpleEntry("unbelievable", "NUMERIC"));// = 0.901 1 1 8 
allAttributes.add(new SimpleEntry("hop", "NUMERIC"));// = 0.901 2 1 7 
allAttributes.add(new SimpleEntry("garbage", "NUMERIC"));// = 0.901 8 1 1 
allAttributes.add(new SimpleEntry("criminal", "NUMERIC"));// = 0.8966 17 2 0 
allAttributes.add(new SimpleEntry("fucking", "NUMERIC"));// = 0.8963 58 11 28 
allAttributes.add(new SimpleEntry("nazi", "NUMERIC"));// = 0.894 70 9 0 
allAttributes.add(new SimpleEntry("freaking", "NUMERIC"));// = 0.8907 6 2 10 
allAttributes.add(new SimpleEntry("yay", "NUMERIC"));// = 0.8907 1 2 15 
allAttributes.add(new SimpleEntry("moral", "NUMERIC"));// = 0.8898 8 1 0 
allAttributes.add(new SimpleEntry("deport", "NUMERIC"));// = 0.8898 8 1 0 
allAttributes.add(new SimpleEntry("pussy", "NUMERIC"));// = 0.8898 7 1 1 
allAttributes.add(new SimpleEntry("stupid", "NUMERIC"));// = 0.8898 44 6 2 
allAttributes.add(new SimpleEntry("loose", "NUMERIC"));// = 0.8898 7 1 1 
allAttributes.add(new SimpleEntry("sport", "NUMERIC"));// = 0.8898 2 1 6 
allAttributes.add(new SimpleEntry("unbreakable", "NUMERIC"));// = 0.8898 0 1 8 
allAttributes.add(new SimpleEntry("theaters", "NUMERIC"));// = 0.8898 1 1 7 
allAttributes.add(new SimpleEntry("asshole", "NUMERIC"));// = 0.8898 8 1 0 
allAttributes.add(new SimpleEntry("❤️", "NUMERIC"));// = 0.8898 0 1 8 
allAttributes.add(new SimpleEntry("dnc", "NUMERIC"));// = 0.8898 8 1 0 
allAttributes.add(new SimpleEntry("love", "NUMERIC"));// = 0.8862 27 76 369 
allAttributes.add(new SimpleEntry("pissed", "NUMERIC"));// = 0.8841 14 2 1 
allAttributes.add(new SimpleEntry("enjoy", "NUMERIC"));// = 0.884 7 9 57 
allAttributes.add(new SimpleEntry("exciting", "NUMERIC"));// = 0.8766 0 2 14 
allAttributes.add(new SimpleEntry("trash", "NUMERIC"));// = 0.8766 10 2 4 
allAttributes.add(new SimpleEntry("coolest", "NUMERIC"));// = 0.8758 0 1 7 
allAttributes.add(new SimpleEntry("gorgeous", "NUMERIC"));// = 0.8758 0 1 7 
allAttributes.add(new SimpleEntry("libs", "NUMERIC"));// = 0.8758 7 1 0 
allAttributes.add(new SimpleEntry("prez", "NUMERIC"));// = 0.8758 5 1 2 
allAttributes.add(new SimpleEntry("fascist", "NUMERIC"));// = 0.8758 7 1 0 
allAttributes.add(new SimpleEntry("lee's", "NUMERIC"));// = 0.8758 2 1 5 
allAttributes.add(new SimpleEntry("funniest", "NUMERIC"));// = 0.8758 0 1 7 
allAttributes.add(new SimpleEntry("belgium", "NUMERIC"));// = 0.8758 3 1 4 
allAttributes.add(new SimpleEntry("illegally", "NUMERIC"));// = 0.8758 7 1 0 
allAttributes.add(new SimpleEntry("asian", "NUMERIC"));// = 0.8758 4 1 3 
allAttributes.add(new SimpleEntry("mp", "NUMERIC"));// = 0.8758 7 1 0 
allAttributes.add(new SimpleEntry("tf", "NUMERIC"));// = 0.8758 6 1 1 
allAttributes.add(new SimpleEntry("pretending", "NUMERIC"));// = 0.8758 7 1 0 
allAttributes.add(new SimpleEntry("impressed", "NUMERIC"));// = 0.8758 3 1 4 
allAttributes.add(new SimpleEntry("persecution", "NUMERIC"));// = 0.8758 5 1 2 
allAttributes.add(new SimpleEntry("punch", "NUMERIC"));// = 0.8758 4 1 3 
// allAttributes.add(new SimpleEntry("\m/", "NUMERIC"));// = 0.8758 0 1 7 
allAttributes.add(new SimpleEntry("adult", "NUMERIC"));// = 0.8758 2 1 5 
allAttributes.add(new SimpleEntry("non", "NUMERIC"));// = 0.8758 5 1 2 
allAttributes.add(new SimpleEntry("surprising", "NUMERIC"));// = 0.8758 4 1 3 
allAttributes.add(new SimpleEntry("unite", "NUMERIC"));// = 0.8758 4 1 3 
allAttributes.add(new SimpleEntry("remarkable", "NUMERIC"));// = 0.8758 2 1 5 
allAttributes.add(new SimpleEntry("dare", "NUMERIC"));// = 0.8758 6 1 1 
allAttributes.add(new SimpleEntry("figured", "NUMERIC"));// = 0.8758 5 1 2 
allAttributes.add(new SimpleEntry("amen", "NUMERIC"));// = 0.8758 1 1 6 
allAttributes.add(new SimpleEntry("fun", "NUMERIC"));// = 0.8741 9 12 68 
allAttributes.add(new SimpleEntry("proud", "NUMERIC"));// = 0.8722 4 5 29 
allAttributes.add(new SimpleEntry("cute", "NUMERIC"));// = 0.8722 3 5 30 
allAttributes.add(new SimpleEntry("dumb", "NUMERIC"));// = 0.8719 18 3 2 
allAttributes.add(new SimpleEntry("lovely", "NUMERIC"));// = 0.8719 1 3 19 
allAttributes.add(new SimpleEntry("jew", "NUMERIC"));// = 0.8682 13 2 0 
allAttributes.add(new SimpleEntry("happiness", "NUMERIC"));// = 0.8682 0 2 13 
allAttributes.add(new SimpleEntry("blast", "NUMERIC"));// = 0.8682 6 2 7 
allAttributes.add(new SimpleEntry("powerful", "NUMERIC"));// = 0.8682 2 2 11 
allAttributes.add(new SimpleEntry("omg", "NUMERIC"));// = 0.8678 10 7 34 
allAttributes.add(new SimpleEntry("corruption", "NUMERIC"));// = 0.8658 19 3 0 
allAttributes.add(new SimpleEntry("treat", "NUMERIC"));// = 0.8658 6 3 13 
allAttributes.add(new SimpleEntry("loves", "NUMERIC"));// = 0.865 6 4 19 
allAttributes.add(new SimpleEntry("welcome", "NUMERIC"));// = 0.8648 4 6 33 
allAttributes.add(new SimpleEntry("racist", "NUMERIC"));// = 0.8627 47 8 1 
allAttributes.add(new SimpleEntry("perfect", "NUMERIC"));// = 0.8627 4 8 44 
allAttributes.add(new SimpleEntry("hilarious", "NUMERIC"));// = 0.8592 5 3 13 
allAttributes.add(new SimpleEntry("elect", "NUMERIC"));// = 0.8585 11 2 1 
allAttributes.add(new SimpleEntry("holidays", "NUMERIC"));// = 0.8585 2 2 10 
allAttributes.add(new SimpleEntry("bullshit", "NUMERIC"));// = 0.8585 11 2 1 
allAttributes.add(new SimpleEntry("stole", "NUMERIC"));// = 0.8585 8 2 4 
allAttributes.add(new SimpleEntry(";", "NUMERIC"));// = 0.8578 4 1 2 
allAttributes.add(new SimpleEntry("wherever", "NUMERIC"));// = 0.8578 3 1 3 
allAttributes.add(new SimpleEntry("rnc", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("isil", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("matters", "NUMERIC"));// = 0.8578 2 1 4 
allAttributes.add(new SimpleEntry("cheating", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("murdered", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("hammer", "NUMERIC"));// = 0.8578 1 1 5 
allAttributes.add(new SimpleEntry("leftovers", "NUMERIC"));// = 0.8578 1 1 5 
allAttributes.add(new SimpleEntry("rude", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("probs", "NUMERIC"));// = 0.8578 1 1 5 
allAttributes.add(new SimpleEntry("wash", "NUMERIC"));// = 0.8578 1 1 5 
allAttributes.add(new SimpleEntry("pleasure", "NUMERIC"));// = 0.8578 1 1 5 
allAttributes.add(new SimpleEntry("denial", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("disrespectful", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("racial", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("that'd", "NUMERIC"));// = 0.8578 1 1 5 
allAttributes.add(new SimpleEntry("sp", "NUMERIC"));// = 0.8578 2 1 4 
allAttributes.add(new SimpleEntry("communist", "NUMERIC"));// = 0.8578 2 1 4 
allAttributes.add(new SimpleEntry("kissing", "NUMERIC"));// = 0.8578 5 1 1 
allAttributes.add(new SimpleEntry("torture", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("bang", "NUMERIC"));// = 0.8578 2 1 4 
allAttributes.add(new SimpleEntry("socialism", "NUMERIC"));// = 0.8578 5 1 1 
allAttributes.add(new SimpleEntry("inspiring", "NUMERIC"));// = 0.8578 1 1 5 
allAttributes.add(new SimpleEntry("sexual", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("monsters", "NUMERIC"));// = 0.8578 0 1 6 
allAttributes.add(new SimpleEntry("forced", "NUMERIC"));// = 0.852 17 3 0 
allAttributes.add(new SimpleEntry("sucks", "NUMERIC"));// = 0.8488 21 4 1 
allAttributes.add(new SimpleEntry("damn", "NUMERIC"));// = 0.8478 26 9 22 
allAttributes.add(new SimpleEntry("scoop", "NUMERIC"));// = 0.8475 0 2 11 
allAttributes.add(new SimpleEntry("murder", "NUMERIC"));// = 0.8475 11 2 0 
allAttributes.add(new SimpleEntry("knowing", "NUMERIC"));// = 0.8475 3 2 8 

		allAttributes.add(new SimpleEntry("POSIEMOJI", "NUMERIC"));// = 0.7157 10 4 0
		allAttributes.add(new SimpleEntry("NEGEMOJI", "NUMERIC"));// = 0.7157 10 4 0


		allAttributes.add(new SimpleEntry ("sentiment", "{positive,negative,neutral}"));
	}

	public static void main(String args[]) {
		try {

			initialization();

			// pritn out the headers for the arff file
			System.out.println("@RELATION twitter-posi-tops");
			for (SimpleEntry<String, String> pair : allAttributes) {
				System.out.println("@ATTRIBUTE " + pair.getKey().replace("'", "").replace("%", "") + " " + pair.getValue());
			}
			System.out.println("@DATA");



			String mode = "dev";

			Scanner decisionScan = new Scanner(new File(mode + "-labels.txt"));

			Scanner scan = new Scanner(new File(mode + "-tweets.txt"));


			while (scan.hasNextLine()) {
				String  decison = decisionScan.nextLine();
				String decisionString = decison.split("\t")[1];

				String line = scan.nextLine();
				String[] array = line.split("\t");
				// preprocessing
				String content = array[1];
				content = content.toLowerCase();
				content = content.replace("\"", "");
				content = content.replace("?", "");
				content = content.replace(",", "");
				content = content.replace("!", "");
				content = content.replace(".", "");
				// content = content.replace(":","");
				array[1] = content;



				String dataRow = "";

				for (SimpleEntry<String, String> pair : allAttributes) {
					if (pair.getKey() == "sentiment") {
						dataRow += decisionString;
					} else if (pair.getKey() == "id") {
						dataRow += array[0] + ",";
					} else if (pair.getKey() == "NEGEMOJI") {
						int count = 0 ;
						for (String negationword : negEmojis) {
							count += countSubstring(array[1], negationword);
							// if (count != 0) {
							// 	break;
							// }
						}

						dataRow += count + ",";
					} else if (pair.getKey() == "POSIEMOJI") {
						int count = 0 ;
						for (String negationword : posEmojis) {
							count += countSubstring(array[1], negationword);
							// if (count != 0) {
							// 	break;
							// }
						}

						dataRow += count + ",";
					} else if (pair.getKey() == "NEGATIONWORD") {
						int count = 0 ;
						for (String negationword : negationWords) {
							count += findAsWords(array[1], negationword);
							// if (count != 0) {
							// 	break;
							// }
						}
						count = count % 2;

						dataRow += count + ",";
					} else {
						int count = findAsWords(array[1], pair.getKey());
						dataRow += count + ",";
					}
				}
				System.out.println(dataRow);
			}

		} catch (Exception e) {
			e.printStackTrace();
			//error handling code
		}
	}

	public static int findAsWords(String str, String subStr) {
		String[] allWord = str.split(" ");
		int count = 0;
		for (String word : allWord) {

			stemming
			s.add(word.toCharArray(), word.length());
			s.stem();
			word = s.toString();

			if (word.length() <= 0) {continue;}
			// filter out mentions
			if (word.charAt(0) == '@') {continue;}
			// filter out topics
			if (word.charAt(0) == '#') {continue;}
			// filtrt out https
			if (word.indexOf("http") == 0 ) {continue;}

			if (word.equals(subStr)) {
				count ++;
			}

			// if (Arrays.asList(stopWords).contains(word.toLowerCase())) {continue;}

			// AddToCount(attributes, word.toLowerCase().replace(":", ""), decisionString);

			// if (Arrays.asList(negationWords).contains(word.toLowerCase())) {
			// 	AddToCount(attributes, "NEGATIONWORD", decisionString);

			// }
		}
		return count;
	}

	public static int countSubstring(String str, String subStr) {

		int lastIndex = 0;
		int count = 0;

		while (lastIndex != -1) {

			lastIndex = str.indexOf(subStr, lastIndex);

			if (lastIndex != -1) {
				count ++;
				lastIndex += subStr.length();
			}
		}
		return count;
	}

	public static void printOutPersentage(Map<String, Integer[]> map) {
		Map<String, Double> percentage = new HashMap<String, Double>();
		int thereshold = 20;
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer[]> pair = (Map.Entry<String, Integer[]>)it.next();
			if ((pair.getValue()[0] + pair.getValue()[1] + pair.getValue()[2]) > thereshold) {
				// double perc = (pair.getValue()[0] + pair.getValue()[2]) * 1.00 / (pair.getValue()[0] + pair.getValue()[1] + pair.getValue()[2]) + (pair.getValue()[0] + pair.getValue()[1] + pair.getValue()[2]) * 0.0001;
				double perc = pair.getValue()[2] * 1.00 / (pair.getValue()[0] + pair.getValue()[1] + pair.getValue()[2]) + (pair.getValue()[0] + pair.getValue()[1] + pair.getValue()[2]) * 0.0001;
				percentage.put(pair.getKey(), round(perc, 4) );

			}
			// System.out.println(pair.getKey() + " = " + pair.getValue()[0] + " " + pair.getValue()[1] + " " + pair.getValue()[2] + " ");
			//it.remove(); // avoids a ConcurrentModificationException
		}
		percentage = sortByValue(percentage);


		Iterator percentageIT = percentage.entrySet().iterator();
		while (percentageIT.hasNext()) {
			Map.Entry<String, Double> percPair = (Map.Entry<String, Double>)percentageIT.next();
			Integer[] data = map.get(percPair.getKey());
			System.out.println(percPair.getKey() + " = " + percPair.getValue() + " " + (!map.containsKey(percPair.getKey()) ? " " : (data[0] + " " + data[1] + " " + data[2] + " ")));
			percentageIT.remove(); // avoids a ConcurrentModificationException
		}
		// System.out.println("\t" + percentage);
	}

	public static void printOutNumber(Map<String, Integer[]> map) {
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer[]> pair = (Map.Entry<String, Integer[]>)it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue()[0] + " " + pair.getValue()[1] + " " + pair.getValue()[2] + " ");
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

	// to sort the map
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ) {
		List<Map.Entry<K, V>> list =
		    new LinkedList<Map.Entry<K, V>>( map.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
				// return (o2.getValue()[0] + o2.getValue()[1] + o2.getValue()[2]).compareTo(o1.getValue()[0] + o1.getValue()[1] + o1.getValue()[2]);
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		} );

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put( entry.getKey(), entry.getValue() );
		}
		return result;
	}


	public static void outputEmoji(String str, String decisionStr) throws Exception {
		// String face = ":)";
		String[] faces = {":-)", ":)", ":D", ":-D", ":P", ":-P", ":(", ":-/", ":/", ";)", "LOL", "HAHA"};

		for (String face : faces) {
			if (str.toUpperCase().contains(face)) {
				AddToCount(allEmoji, face, decisionStr);
				// System.out.println("Get a face" + face + " " + str);
			}
		}


		String regexPattern = "[\uD83C-\uDBFF\uDC00-\uDFFF]";
		byte[] utf8 = str.getBytes("UTF-8");

		String string1 = new String(utf8, "UTF-8");

		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(string1);

		while (matcher.find()) {
			AddToCount(allEmoji, matcher.group(), decisionStr);
		}

	}

	public static void AddToCount(Map<String, Integer[]> map, String key, String decisionStr) {
		Integer[] countArray = new Integer[3];
		countArray[0] = 0;
		countArray[1] = 0;
		countArray[2] = 0;

		if (map.containsKey(key)) {
			countArray = map.get(key);
			// System.out.println("have the key " + key + " " + countArray[0] + " " + countArray[1] + " " + countArray[2] + " " + decisionStr);

		} else {

		}

		if (decisionStr.equals(negative)) {
			countArray[0] += 1;
		} else if (decisionStr.equals(neutral)) {
			countArray[1] += 1;
		} else if (decisionStr.equals(positive) ) {
			countArray[2] += 1;
		}


		map.put(key, countArray);
	}

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
	public static String[] negationWords = {
		"without", "no", "nor", "not", "cannot", "few",
		"neither", "never", "nobody", "non", "none", "nothing", "nowhere",
		"can't", "cannot", "couldn't", "didn't", "doesn't", "don't", "hadn't",
		"hasn't", "haven't", "isn't", "mustn't",
		"shan't", "shouldn't", "wasn't", "weren't", "won't", "wouldn't",
		"aren't"
	};

	public static String[] posEmojis = {
		"LOL",// = 0.228 56 125 99
		"🇺",// = 0.2249 6 14 7
		"🇸",// = 0.2107 5 14 5
		"HAHA",// = 0.2056 16 30 35
		"👇",// = 0.201 2 8 0
		":P",// = 0.1829 2 5 4
		"😊",// = 0.1551 2 5 6
		"👌",// = 0.1443 2 3 9
		"🤗",// = 0.1436 1 1 5
		"👸",// = 0.1258 1 0 7
// ️ = 0.1227 7 14 39
		"🏼",// = 0.118 3 8 15
		":D",// = 0.1178 4 5 26
		"🦃",// = 0.112 1 3 5
		";)",// = 0.0999 3 8 20
		"🏻",// = 0.0931 2 6 14
		"😁",// = 0.092 1 1 9
		"🏀",// = 0.092 1 6 4
		"🏾",// = 0.0782 1 2 10
		"👏",// = 0.0641 1 4 11
		"👍",// = 0.0605 1 6 10
		":-)",// = 0.0425 1 9 15
		"💙",// = 0.0411 1 1 24
		":)",// = 0.0354 3 35 102
		"😍",// = 0.004 0 7 33
		"🙏",// = 0.0019 0 14 5
		"🔥",// = 0.0019 0 7 12
		"🙌",// = 0.0016 0 5 11
		"😹",// = 0.0011 0 9 2
		"😎",// = 0.001 0 3 7
		"💋",// = 0.001 0 2 8
		"🏽",// = 8.0E-4 0 3 5
		"😀",// = 8.0E-4 0 3 5
		"😘",// = 8.0E-4 0 3 5
		"🎉"// = 7.0E-4 0 0 7

	};

	public static String[] negEmojis = {
		"😡",// = 0.9179 11 1 0
		"👀",// = 0.8578 6 1 0
		"😷",// = 0.7508 6 1 1
		"😢",// = 0.7348 11 1 3
		"😒",// = 0.715 5 2 0
// - = 0.6173 816 2401 1039
		"🙄",// = 0.5931 13 6 3
		":(",// = 0.5239 28 21 5
		"👊",// = 0.4293 3 1 3
		"🤔",// = 0.3658 8 8 6
		"😭",// = 0.3017 16 23 15
		":/",// = 0.2941 7 13 4
		"😩"// = 0.2871 4 9 1
	};

// 😂 = 0.2332 28 49 50

	public static String[] stopWords = {
		"see", "unless", "due", "also", "must", "might", "like",
		//"]","[", "}", "{", "<", ">", "?", "\"", "\\", "/", ")", "(",
		"will", "may",
		"can", "much", "every", "the", "in", "other", "this", "the", "many", "any",
		"an", "or", "for", "in", "an", "an ", "is", "a", "about", "above", "after",
		"again", "against", "all", "am", "an", "and", "any", "are", "aren't", "as",
		"at", "be", "because", "been", "before", "being", "below", "between",
		"both", "but", "by",  "could", "did",
		"do", "does", "doing",  "down", "during", "each", "few",
		"for", "from", "further", "had",  "has",  "have",
		"having", "he", "he'd", "he'll", "he's", "her", "here", "here's", "hers",
		"herself", "him", "himself", "his", "how", "how's", "i ", " i", "i'd", "i'll",
		"i'm", "i've", "if", "in", "into", "is", "it", "it's", "its", "itself",
		"let's", "me", "more", "most",  "my", "myself",
		"of", "off", "on", "once", "only", "ought", "our", "ours", "ourselves",
		"out", "over", "own", "same",  "she", "she'd", "she'll", "she's",
		"should",  "so", "some", "such", "than", "that", "that's", "their",
		"theirs", "them", "themselves", "then", "there", "there's", "these", "they",
		"they'd", "they'll", "they're", "they've", "this", "those", "through", "to",
		"too", "under", "until", "up", "very", "was",  "we", "we'd",
		"we'll", "we're", "we've", "were",  "what", "what's", "when",
		"when's", "where", "where's", "which", "while", "who", "who's", "whom",
		"why", "why's", "with", "would", "you", "you'd", "you'll",
		"you're", "you've", "your", "yours", "yourself", "yourselves"
	};
}
