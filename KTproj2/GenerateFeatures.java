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


	public static void initialization() {
		allAttributes.add(new SimpleEntry("id", "NUMERIC"));
		allAttributes.add(new SimpleEntry("a", "NUMERIC"));
		allAttributes.add(new SimpleEntry("amazing", "NUMERIC"));
		allAttributes.add(new SimpleEntry("antman", "NUMERIC"));
		allAttributes.add(new SimpleEntry("are", "NUMERIC"));
		allAttributes.add(new SimpleEntry("at", "NUMERIC"));
		allAttributes.add(new SimpleEntry("awesome", "NUMERIC"));
		allAttributes.add(new SimpleEntry("best", "NUMERIC"));
		// allAttributes.add(new SimpleEntry("NEGATION", "NUMERIC"));
		allAttributes.add(new SimpleEntry("fantastic", "NUMERIC"));// = 0.9758 0 1 35
		allAttributes.add(new SimpleEntry("enjoyed", "NUMERIC"));// = 0.9314 1 1 26
		allAttributes.add(new SimpleEntry("amazing", "NUMERIC"));// = 0.9302 1 6 82
		allAttributes.add(new SimpleEntry("incredible", "NUMERIC"));// = 0.9257 1 1 24
		allAttributes.add(new SimpleEntry("excited", "NUMERIC"));// = 0.9245 6 4 105
		allAttributes.add(new SimpleEntry("awesome", "NUMERIC"));// = 0.9217 2 5 74
		allAttributes.add(new SimpleEntry("cream", "NUMERIC"));// = 0.9074 0 17 140
		allAttributes.add(new SimpleEntry("excellent", "NUMERIC"));// = 0.9069 0 2 19
		allAttributes.add(new SimpleEntry("i", "NUMERIC"));// = 0.8937 1044 2099 1957
		allAttributes.add(new SimpleEntry("brilliant", "NUMERIC"));// = 0.8741 2 2 27
		allAttributes.add(new SimpleEntry("happy", "NUMERIC"));// = 0.8739 23 33 292
		allAttributes.add(new SimpleEntry("&lt;3", "NUMERIC"));// = 0.8697 2 2 26
		allAttributes.add(new SimpleEntry("loved", "NUMERIC"));// = 0.8459 3 3 32
		allAttributes.add(new SimpleEntry("love", "NUMERIC"));// = 0.829 27 76 369
		allAttributes.add(new SimpleEntry("lovely", "NUMERIC"));// = 0.8284 1 3 19
		allAttributes.add(new SimpleEntry("bless", "NUMERIC"));// = 0.827 1 9 46
		allAttributes.add(new SimpleEntry("congrats", "NUMERIC"));// = 0.8204 0 4 18
		allAttributes.add(new SimpleEntry("wonderful", "NUMERIC"));// = 0.8204 0 4 18
		allAttributes.add(new SimpleEntry("beautiful", "NUMERIC"));// = 0.8195 1 10 48
		allAttributes.add(new SimpleEntry("thankful", "NUMERIC"));// = 0.8103 0 5 21
		allAttributes.add(new SimpleEntry("liked", "NUMERIC"));// = 0.7942 4 12 59
		allAttributes.add(new SimpleEntry("cute", "NUMERIC"));// = 0.7933 3 5 30
		allAttributes.add(new SimpleEntry("perfect", "NUMERIC"));// = 0.7913 4 8 44
		allAttributes.add(new SimpleEntry("enjoy", "NUMERIC"));// = 0.7881 7 9 57
		allAttributes.add(new SimpleEntry("fun", "NUMERIC"));// = 0.7729 9 12 68
		allAttributes.add(new SimpleEntry("welcome", "NUMERIC"));// = 0.7717 4 6 33
		allAttributes.add(new SimpleEntry("proud", "NUMERIC"));// = 0.767 4 5 29
		allAttributes.add(new SimpleEntry("wishing", "NUMERIC"));// = 0.764 1 4 16
		allAttributes.add(new SimpleEntry("thank", "NUMERIC"));// = 0.762 9 21 90
		allAttributes.add(new SimpleEntry("janet", "NUMERIC"));// = 0.7528 4 18 64
		allAttributes.add(new SimpleEntry("favorite", "NUMERIC"));// = 0.7523 2 14 47
		allAttributes.add(new SimpleEntry("great", "NUMERIC"));// = 0.7474 33 67 248
		allAttributes.add(new SimpleEntry("dog", "NUMERIC"));// = 0.7455 14 27 111
		allAttributes.add(new SimpleEntry("rapper", "NUMERIC"));// = 0.7414 0 6 17
		allAttributes.add(new SimpleEntry("best", "NUMERIC"));// = 0.7351 28 95 280
		allAttributes.add(new SimpleEntry("celebrate", "NUMERIC"));// = 0.7297 6 20 67
		allAttributes.add(new SimpleEntry("birthday", "NUMERIC"));// = 0.721 9 54 147
		allAttributes.add(new SimpleEntry("hot", "NUMERIC"));// = 0.7099 14 51 144
		allAttributes.add(new SimpleEntry("national", "NUMERIC"));// = 0.7082 21 78 208
		allAttributes.add(new SimpleEntry("nice", "NUMERIC"));// = 0.7063 6 25 71
		allAttributes.add(new SimpleEntry("gift", "NUMERIC"));// = 0.6926 3 6 20
		allAttributes.add(new SimpleEntry("ant-man", "NUMERIC"));// = 0.6917 13 53 135
		allAttributes.add(new SimpleEntry("dogs", "NUMERIC"));// = 0.6907 4 6 22
		allAttributes.add(new SimpleEntry("harper", "NUMERIC"));// = 0.684 1 6 15
		allAttributes.add(new SimpleEntry("day", "NUMERIC"));// = 0.6835 125 432 702
		allAttributes.add(new SimpleEntry("celebrating", "NUMERIC"));// = 0.6799 4 7 23

		allAttributes.add(new SimpleEntry("NEGATIONWORD", "NUMERIC"));// = 0.703 1830 2316 977



		allAttributes.add(new SimpleEntry ("sentiment", "{positive,negative,neutral}"));
	}

	public static void main(String args[]) {
		try {

			initialization();

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
					} else if (pair.getKey() == "NEGATIONWORD") {
						int count =0 ;
						for(String negationword: negationWords)
						{
							count += findAsWords(array[1], negationword);
							if(count != 0)
							{
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
