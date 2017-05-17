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
		// allAttributes.add(new SimpleEntry("id", "NUMERIC"));
		allAttributes.add(new SimpleEntry("i", "NUMERIC"));// = 1.0984 1044 2099 1957
		allAttributes.add(new SimpleEntry("NEGATIONWORD", "NUMERIC"));// = 1.0381 1748 2252 940
		allAttributes.add(new SimpleEntry("scam", "NUMERIC"));// = 1.0014 14 0 0
		allAttributes.add(new SimpleEntry("zionist", "NUMERIC"));// = 1.0012 10 0 2
		allAttributes.add(new SimpleEntry("disgrac", "NUMERIC"));// = 1.0011 11 0 0
		allAttributes.add(new SimpleEntry("puppet", "NUMERIC"));// = 1.0011 11 0 0
		allAttributes.add(new SimpleEntry("scum", "NUMERIC"));// = 1.0011 11 0 0
		allAttributes.add(new SimpleEntry("fantast", "NUMERIC"));// = 0.9767 1 1 35
		allAttributes.add(new SimpleEntry("excit", "NUMERIC"));// = 0.9723 7 6 130
		allAttributes.add(new SimpleEntry("disgust", "NUMERIC"));// = 0.9545 20 1 0
		allAttributes.add(new SimpleEntry("amaz", "NUMERIC"));// = 0.9534 2 5 83
		allAttributes.add(new SimpleEntry("idiot", "NUMERIC"));// = 0.948 33 2 1
		allAttributes.add(new SimpleEntry("awesom", "NUMERIC"));// = 0.9464 2 5 74
		allAttributes.add(new SimpleEntry("happi", "NUMERIC"));// = 0.9399 23 35 305
		allAttributes.add(new SimpleEntry("violent", "NUMERIC"));// = 0.9391 11 1 4
		allAttributes.add(new SimpleEntry("incred", "NUMERIC"));// = 0.9386 3 2 26
		allAttributes.add(new SimpleEntry("brilliant", "NUMERIC"));// = 0.9386 2 2 27
		allAttributes.add(new SimpleEntry("&lt;3", "NUMERIC"));// = 0.9363 2 2 26
		allAttributes.add(new SimpleEntry("worst", "NUMERIC"));// = 0.9328 50 4 1
		allAttributes.add(new SimpleEntry("fascist", "NUMERIC"));// = 0.93 13 1 0
		allAttributes.add(new SimpleEntry("attract", "NUMERIC"));// = 0.93 4 1 9
		allAttributes.add(new SimpleEntry("moron", "NUMERIC"));// = 0.9179 10 1 1
		allAttributes.add(new SimpleEntry("corbyn", "NUMERIC"));// = 0.9102 9 1 1
		allAttributes.add(new SimpleEntry("hip", "NUMERIC"));// = 0.9102 1 1 9
		allAttributes.add(new SimpleEntry("pathet", "NUMERIC"));// = 0.9102 9 1 1
		allAttributes.add(new SimpleEntry("lib", "NUMERIC"));// = 0.9102 9 1 1
		allAttributes.add(new SimpleEntry("msm", "NUMERIC"));// = 0.9102 10 1 0
		allAttributes.add(new SimpleEntry("nazi", "NUMERIC"));// = 0.9089 89 10 0
		allAttributes.add(new SimpleEntry("cream", "NUMERIC"));// = 0.9082 0 17 141
		allAttributes.add(new SimpleEntry("love", "NUMERIC"));// = 0.9037 40 91 456
		allAttributes.add(new SimpleEntry("stupid", "NUMERIC"));// = 0.9024 50 6 2
		allAttributes.add(new SimpleEntry("bless", "NUMERIC"));// = 0.8938 3 10 74
		allAttributes.add(new SimpleEntry("yai", "NUMERIC"));// = 0.8907 1 2 15
		allAttributes.add(new SimpleEntry("enjoi", "NUMERIC"));// = 0.8892 9 15 98
		allAttributes.add(new SimpleEntry("trash", "NUMERIC"));// = 0.8841 11 2 4
		allAttributes.add(new SimpleEntry("dumb", "NUMERIC"));// = 0.8774 19 3 2
		allAttributes.add(new SimpleEntry("suck", "NUMERIC"));// = 0.877 35 6 6
		allAttributes.add(new SimpleEntry("cute", "NUMERIC"));// = 0.8757 3 5 31
		allAttributes.add(new SimpleEntry("fun", "NUMERIC"));// = 0.8741 9 12 68
		allAttributes.add(new SimpleEntry("fuck", "NUMERIC"));// = 0.8731 161 35 37
		allAttributes.add(new SimpleEntry("proud", "NUMERIC"));// = 0.8722 4 5 29
		allAttributes.add(new SimpleEntry("excel", "NUMERIC"));// = 0.8719 1 3 19
		allAttributes.add(new SimpleEntry("crimin", "NUMERIC"));// = 0.8697 26 4 0
		allAttributes.add(new SimpleEntry("bullshit", "NUMERIC"));// = 0.8682 12 2 1
		allAttributes.add(new SimpleEntry("stole", "NUMERIC"));// = 0.8682 9 2 4
		allAttributes.add(new SimpleEntry("perfect", "NUMERIC"));// = 0.8679 4 8 46
		allAttributes.add(new SimpleEntry("omg", "NUMERIC"));// = 0.8678 10 7 34
		allAttributes.add(new SimpleEntry("murder", "NUMERIC"));// = 0.8647 31 5 0
		allAttributes.add(new SimpleEntry("enemi", "NUMERIC"));// = 0.8585 11 2 1
		allAttributes.add(new SimpleEntry("racist", "NUMERIC"));// = 0.8551 55 10 1
		allAttributes.add(new SimpleEntry("damn", "NUMERIC"));// = 0.8534 28 9 22
		allAttributes.add(new SimpleEntry("li", "NUMERIC"));// = 0.8501 32 6 1
		allAttributes.add(new SimpleEntry("supremacist", "NUMERIC"));// = 0.8497 68 13 1
		allAttributes.add(new SimpleEntry("nope", "NUMERIC"));// = 0.8475 8 2 3
		allAttributes.add(new SimpleEntry("adult", "NUMERIC"));// = 0.8475 4 2 7
		allAttributes.add(new SimpleEntry("healthi", "NUMERIC"));// = 0.8475 1 2 10
		allAttributes.add(new SimpleEntry("scoop", "NUMERIC"));// = 0.8475 0 2 11
		allAttributes.add(new SimpleEntry("privileg", "NUMERIC"));// = 0.8475 7 2 4
		allAttributes.add(new SimpleEntry("sexual", "NUMERIC"));// = 0.8475 10 2 1
		allAttributes.add(new SimpleEntry("hop", "NUMERIC"));// = 0.8475 2 2 9
		allAttributes.add(new SimpleEntry("ignor", "NUMERIC"));// = 0.8453 36 7 1
		allAttributes.add(new SimpleEntry("disappoint", "NUMERIC"));// = 0.8425 19 4 2
		allAttributes.add(new SimpleEntry("great", "NUMERIC"));// = 0.842 33 68 251
		allAttributes.add(new SimpleEntry("shit", "NUMERIC"));// = 0.841 120 35 41
		allAttributes.add(new SimpleEntry("dog", "NUMERIC"));// = 0.8391 18 33 133
		allAttributes.add(new SimpleEntry("trust", "NUMERIC"));// = 0.8357 14 4 6
		allAttributes.add(new SimpleEntry("hilari", "NUMERIC"));// = 0.8357 7 4 13
		allAttributes.add(new SimpleEntry("mubarak", "NUMERIC"));// = 0.8351 0 3 15
		allAttributes.add(new SimpleEntry("horribl", "NUMERIC"));// = 0.8351 15 3 0
		allAttributes.add(new SimpleEntry("favourit", "NUMERIC"));// = 0.8351 3 3 12
		allAttributes.add(new SimpleEntry("shitti", "NUMERIC"));// = 0.8345 9 2 1
		allAttributes.add(new SimpleEntry("hitler", "NUMERIC"));// = 0.8345 8 2 2
		allAttributes.add(new SimpleEntry("easili", "NUMERIC"));// = 0.8345 7 2 3
		allAttributes.add(new SimpleEntry("dirti", "NUMERIC"));// = 0.8345 7 2 3
		allAttributes.add(new SimpleEntry("profit", "NUMERIC"));// = 0.8345 8 2 2
		allAttributes.add(new SimpleEntry("insur", "NUMERIC"));// = 0.8345 7 2 3
		allAttributes.add(new SimpleEntry("corpor", "NUMERIC"));// = 0.8345 9 2 1
		allAttributes.add(new SimpleEntry("traitor", "NUMERIC"));// = 0.8345 10 2 0
		allAttributes.add(new SimpleEntry("mp", "NUMERIC"));// = 0.8345 10 2 0
		allAttributes.add(new SimpleEntry("boruto", "NUMERIC"));// = 0.8345 0 2 10
		allAttributes.add(new SimpleEntry("deport", "NUMERIC"));// = 0.8252 14 3 0
		allAttributes.add(new SimpleEntry("cheat", "NUMERIC"));// = 0.8252 14 3 0
		allAttributes.add(new SimpleEntry("monster", "NUMERIC"));// = 0.8252 4 3 10
		allAttributes.add(new SimpleEntry("congrat", "NUMERIC"));// = 0.8204 0 4 18
		allAttributes.add(new SimpleEntry("ridicul", "NUMERIC"));// = 0.8204 16 4 2
		allAttributes.add(new SimpleEntry("crook", "NUMERIC"));// = 0.8193 9 2 0
		allAttributes.add(new SimpleEntry("pride", "NUMERIC"));// = 0.8193 4 2 5
		allAttributes.add(new SimpleEntry("😍", "NUMERIC"));// = 0.8193 0 2 9
		allAttributes.add(new SimpleEntry("genocid", "NUMERIC"));// = 0.8193 9 2 0
		allAttributes.add(new SimpleEntry("asshol", "NUMERIC"));// = 0.8193 8 2 1
		allAttributes.add(new SimpleEntry("glori", "NUMERIC"));// = 0.8193 1 2 8
		allAttributes.add(new SimpleEntry("ador", "NUMERIC"));// = 0.8193 1 2 8
		allAttributes.add(new SimpleEntry("wors", "NUMERIC"));// = 0.8183 34 8 1
		allAttributes.add(new SimpleEntry("violat", "NUMERIC"));// = 0.8141 13 3 0
		allAttributes.add(new SimpleEntry("dare", "NUMERIC"));// = 0.8141 10 3 3
		allAttributes.add(new SimpleEntry("killer", "NUMERIC"));// = 0.8141 9 3 4
		allAttributes.add(new SimpleEntry("paid", "NUMERIC"));// = 0.8137 22 8 12
		allAttributes.add(new SimpleEntry("threaten", "NUMERIC"));// = 0.8116 17 4 0
		allAttributes.add(new SimpleEntry("ly", "NUMERIC"));// = 0.8103 20 5 1
		allAttributes.add(new SimpleEntry("piss", "NUMERIC"));// = 0.8096 23 6 2
		allAttributes.add(new SimpleEntry("nobodi", "NUMERIC"));// = 0.8096 16 6 9
		allAttributes.add(new SimpleEntry("forev", "NUMERIC"));// = 0.8092 7 7 22
		allAttributes.add(new SimpleEntry("sick", "NUMERIC"));// = 0.809 30 8 3
		allAttributes.add(new SimpleEntry("best", "NUMERIC"));// = 0.8046 28 95 280
		allAttributes.add(new SimpleEntry("corrupt", "NUMERIC"));// = 0.804 31 8 1
		allAttributes.add(new SimpleEntry("favorit", "NUMERIC"));// = 0.8033 2 13 49
		allAttributes.add(new SimpleEntry("fat", "NUMERIC"));// = 0.8025 18 5 2
		allAttributes.add(new SimpleEntry("emot", "NUMERIC"));// = 0.8025 8 5 12
		allAttributes.add(new SimpleEntry("threat", "NUMERIC"));// = 0.8025 19 5 1
		allAttributes.add(new SimpleEntry("pretend", "NUMERIC"));// = 0.802 12 4 4
		allAttributes.add(new SimpleEntry("welcom", "NUMERIC"));// = 0.8017 4 11 39
		allAttributes.add(new SimpleEntry("persecut", "NUMERIC"));// = 0.8015 9 3 3
		allAttributes.add(new SimpleEntry("desper", "NUMERIC"));// = 0.8015 8 3 4
		allAttributes.add(new SimpleEntry("epic", "NUMERIC"));// = 0.8015 2 3 10
		allAttributes.add(new SimpleEntry("janet", "NUMERIC"));// = 0.7993 4 18 64
		allAttributes.add(new SimpleEntry("hell", "NUMERIC"));// = 0.799 51 20 24
		allAttributes.add(new SimpleEntry("bitch", "NUMERIC"));// = 0.7989 40 12 6
		allAttributes.add(new SimpleEntry("shame", "NUMERIC"));// = 0.7975 22 7 5
		allAttributes.add(new SimpleEntry("leftist", "NUMERIC"));// = 0.7955 61 17 2
		allAttributes.add(new SimpleEntry("good", "NUMERIC"));// = 0.7945 73 157 363
		allAttributes.add(new SimpleEntry("pretti", "NUMERIC"));// = 0.7942 18 20 55
		allAttributes.add(new SimpleEntry("bloodi", "NUMERIC"));// = 0.7919 31 10 6
		allAttributes.add(new SimpleEntry("racism", "NUMERIC"));// = 0.7914 14 4 1
		allAttributes.add(new SimpleEntry("wow", "NUMERIC"));// = 0.7899 12 17 49
		allAttributes.add(new SimpleEntry("treat", "NUMERIC"));// = 0.7899 12 9 21
		allAttributes.add(new SimpleEntry("rape", "NUMERIC"));// = 0.7871 10 3 1
		allAttributes.add(new SimpleEntry("mouth", "NUMERIC"));// = 0.7871 7 3 4
		allAttributes.add(new SimpleEntry("blind", "NUMERIC"));// = 0.7871 7 3 4
		allAttributes.add(new SimpleEntry("carol", "NUMERIC"));// = 0.7871 0 3 11
		allAttributes.add(new SimpleEntry("absolut", "NUMERIC"));// = 0.785 15 11 24
		allAttributes.add(new SimpleEntry("crap", "NUMERIC"));// = 0.7849 15 5 3
		allAttributes.add(new SimpleEntry("thi", "NUMERIC"));// = 0.7844 535 843 641
		allAttributes.add(new SimpleEntry("ass", "NUMERIC"));// = 0.7824 55 23 23
		allAttributes.add(new SimpleEntry("rich", "NUMERIC"));// = 0.7796 14 4 0
		allAttributes.add(new SimpleEntry("hahaha", "NUMERIC"));// = 0.7796 7 4 7
		allAttributes.add(new SimpleEntry("beauti", "NUMERIC"));// = 0.7794 2 19 62
		allAttributes.add(new SimpleEntry("kill", "NUMERIC"));// = 0.779 159 58 20
		allAttributes.add(new SimpleEntry("dai", "NUMERIC"));// = 0.7779 148 505 743
		allAttributes.add(new SimpleEntry("hot", "NUMERIC"));// = 0.7769 14 51 144
		allAttributes.add(new SimpleEntry("liber", "NUMERIC"));// = 0.7765 98 31 2
		allAttributes.add(new SimpleEntry("ruin", "NUMERIC"));// = 0.7749 23 8 4
		allAttributes.add(new SimpleEntry("luck", "NUMERIC"));// = 0.7744 7 12 33
		allAttributes.add(new SimpleEntry("charact", "NUMERIC"));// = 0.7731 9 9 21
		allAttributes.add(new SimpleEntry("birthdai", "NUMERIC"));// = 0.7708 9 52 147
		allAttributes.add(new SimpleEntry("neg", "NUMERIC"));// = 0.7705 8 3 2
		allAttributes.add(new SimpleEntry("vma'", "NUMERIC"));// = 0.7705 3 3 7
		allAttributes.add(new SimpleEntry("warren", "NUMERIC"));// = 0.7705 8 3 2
		allAttributes.add(new SimpleEntry("melania'", "NUMERIC"));// = 0.7705 7 3 3
		allAttributes.add(new SimpleEntry("bright", "NUMERIC"));// = 0.7705 3 3 7
		allAttributes.add(new SimpleEntry("elig", "NUMERIC"));// = 0.7705 8 3 2
		allAttributes.add(new SimpleEntry("nationalist", "NUMERIC"));// = 0.7705 10 3 0
		allAttributes.add(new SimpleEntry("hypocrit", "NUMERIC"));// = 0.7705 10 3 0
		allAttributes.add(new SimpleEntry("dope", "NUMERIC"));// = 0.7705 2 3 8
		allAttributes.add(new SimpleEntry("unbeliev", "NUMERIC"));// = 0.7705 1 3 9
		allAttributes.add(new SimpleEntry("alt-rightist", "NUMERIC"));// = 0.7697 22 7 1
		allAttributes.add(new SimpleEntry("thank", "NUMERIC"));// = 0.769 26 78 195
		allAttributes.add(new SimpleEntry("fake", "NUMERIC"));// = 0.7677 53 17 1
		allAttributes.add(new SimpleEntry("nice", "NUMERIC"));// = 0.7676 6 25 72
		allAttributes.add(new SimpleEntry("terrorist", "NUMERIC"));// = 0.7673 60 20 3
		allAttributes.add(new SimpleEntry("$", "NUMERIC"));// = 0.7664 11 4 2
		allAttributes.add(new SimpleEntry("finger", "NUMERIC"));// = 0.7664 2 4 11
		allAttributes.add(new SimpleEntry("societi", "NUMERIC"));// = 0.7664 10 4 3
		allAttributes.add(new SimpleEntry("warm", "NUMERIC"));// = 0.7664 5 4 8
		allAttributes.add(new SimpleEntry("bash", "NUMERIC"));// = 0.7664 8 4 5
		allAttributes.add(new SimpleEntry("cat", "NUMERIC"));// = 0.7664 7 4 6
		allAttributes.add(new SimpleEntry("glad", "NUMERIC"));// = 0.7661 7 10 25
		allAttributes.add(new SimpleEntry("fals", "NUMERIC"));// = 0.764 15 5 1
		allAttributes.add(new SimpleEntry("feed", "NUMERIC"));// = 0.7625 12 6 7
		allAttributes.add(new SimpleEntry("hate", "NUMERIC"));// = 0.7625 113 43 13
		allAttributes.add(new SimpleEntry("tomorrow", "NUMERIC"));// = 0.7621 216 863 901
		allAttributes.add(new SimpleEntry("nation", "NUMERIC"));// = 0.7619 39 99 223
		allAttributes.add(new SimpleEntry("respect", "NUMERIC"));// = 0.76 19 13 21
		allAttributes.add(new SimpleEntry("can't", "NUMERIC"));// = 0.7577 121 123 187
		allAttributes.add(new SimpleEntry("mai", "NUMERIC"));// = 0.7577 603 1224 664
		allAttributes.add(new SimpleEntry("ant-man", "NUMERIC"));// = 0.7564 13 53 135
		allAttributes.add(new SimpleEntry("holi", "NUMERIC"));// = 0.754 6 10 24
		allAttributes.add(new SimpleEntry("steal", "NUMERIC"));// = 0.7536 23 9 4
		allAttributes.add(new SimpleEntry("especi", "NUMERIC"));// = 0.7532 10 8 14
		allAttributes.add(new SimpleEntry("fraud", "NUMERIC"));// = 0.7528 19 7 2
		allAttributes.add(new SimpleEntry("pain", "NUMERIC"));// = 0.7528 13 7 8
		allAttributes.add(new SimpleEntry("100%", "NUMERIC"));// = 0.7524 5 6 13
		allAttributes.add(new SimpleEntry("excus", "NUMERIC"));// = 0.752 13 5 2
		allAttributes.add(new SimpleEntry("complain", "NUMERIC"));// = 0.752 15 5 0
		allAttributes.add(new SimpleEntry("b/c", "NUMERIC"));// = 0.7516 8 4 4
		allAttributes.add(new SimpleEntry("otherwis", "NUMERIC"));// = 0.7516 10 4 2
		allAttributes.add(new SimpleEntry("annoi", "NUMERIC"));// = 0.7516 11 4 1
		allAttributes.add(new SimpleEntry("heaven", "NUMERIC"));// = 0.7516 4 4 8
		allAttributes.add(new SimpleEntry("pump", "NUMERIC"));// = 0.7516 0 4 12
		allAttributes.add(new SimpleEntry("german", "NUMERIC"));// = 0.7512 7 3 2
		allAttributes.add(new SimpleEntry("premium", "NUMERIC"));// = 0.7512 6 3 3
		allAttributes.add(new SimpleEntry("kitchen", "NUMERIC"));// = 0.7512 6 3 3
		allAttributes.add(new SimpleEntry("day", "NUMERIC"));// = 0.7512 0 3 9
		allAttributes.add(new SimpleEntry("depress", "NUMERIC"));// = 0.7512 9 3 0
		allAttributes.add(new SimpleEntry("children", "NUMERIC"));// = 0.751 28 14 13
		allAttributes.add(new SimpleEntry("blame", "NUMERIC"));// = 0.751 39 14 2
		allAttributes.add(new SimpleEntry("hopefulli", "NUMERIC"));// = 0.7461 7 14 33
		allAttributes.add(new SimpleEntry("honestli", "NUMERIC"));// = 0.745 11 8 12
		allAttributes.add(new SimpleEntry("alt-right", "NUMERIC"));// = 0.7423 31 11 0
		allAttributes.add(new SimpleEntry("cinema", "NUMERIC"));// = 0.7414 0 6 17
		allAttributes.add(new SimpleEntry("kiss", "NUMERIC"));// = 0.7414 11 6 6
		allAttributes.add(new SimpleEntry("penc", "NUMERIC"));// = 0.7414 15 6 2

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



			String mode = "train";

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
							if (count != 0) {
								break;
							}
						}

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

			// stemming
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
