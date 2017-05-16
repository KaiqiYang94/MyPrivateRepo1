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
// allAttributes.add(new SimpleEntry("&lt;3", "NUMERIC"));// = 0.8697 2 2 26 
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
allAttributes.add(new SimpleEntry("NEGATIONWORD", "NUMERIC"));// = 0.703 1830 2316 977 
allAttributes.add(new SimpleEntry("gift", "NUMERIC"));// = 0.6926 3 6 20 
allAttributes.add(new SimpleEntry("ant-man", "NUMERIC"));// = 0.6917 13 53 135 
allAttributes.add(new SimpleEntry("dogs", "NUMERIC"));// = 0.6907 4 6 22 
allAttributes.add(new SimpleEntry("harper", "NUMERIC"));// = 0.684 1 6 15 
allAttributes.add(new SimpleEntry("day", "NUMERIC"));// = 0.6835 125 432 702 
allAttributes.add(new SimpleEntry("celebrating", "NUMERIC"));// = 0.6799 4 7 23 
allAttributes.add(new SimpleEntry("good", "NUMERIC"));// = 0.6729 72 154 360 
allAttributes.add(new SimpleEntry("omg", "NUMERIC"));// = 0.6718 10 7 34 
allAttributes.add(new SimpleEntry("loving", "NUMERIC"));// = 0.6691 3 5 16 
allAttributes.add(new SimpleEntry("-)", "NUMERIC"));// = 0.6688 1 6 14 
allAttributes.add(new SimpleEntry("royal", "NUMERIC"));// = 0.6595 3 8 21 
allAttributes.add(new SimpleEntry("loves", "NUMERIC"));// = 0.6581 6 4 19 
allAttributes.add(new SimpleEntry("jackson", "NUMERIC"));// = 0.6554 5 29 62 
allAttributes.add(new SimpleEntry("tomorrow", "NUMERIC"));// = 0.6531 216 863 901 
allAttributes.add(new SimpleEntry("ice", "NUMERIC"));// = 0.6521 19 123 227 
allAttributes.add(new SimpleEntry(")", "NUMERIC"));// = 0.6493 6 50 97 
allAttributes.add(new SimpleEntry("23rd", "NUMERIC"));// = 0.645 1 17 32 
allAttributes.add(new SimpleEntry(";)", "NUMERIC"));// = 0.6425 2 7 16 
allAttributes.add(new SimpleEntry("luck", "NUMERIC"));// = 0.6398 7 12 33 
allAttributes.add(new SimpleEntry("forward", "NUMERIC"));// = 0.6392 5 20 43 
allAttributes.add(new SimpleEntry("wow", "NUMERIC"));// = 0.636 12 17 49 
allAttributes.add(new SimpleEntry("wars", "NUMERIC"));// = 0.6289 17 27 71 
allAttributes.add(new SimpleEntry("brown", "NUMERIC"));// = 0.6285 11 37 77 
allAttributes.add(new SimpleEntry("gig", "NUMERIC"));// = 0.6274 1 8 15 
allAttributes.add(new SimpleEntry("songs", "NUMERIC"));// = 0.6253 5 19 39 
allAttributes.add(new SimpleEntry("towns", "NUMERIC"));// = 0.6219 10 58 104 
allAttributes.add(new SimpleEntry("smile", "NUMERIC"));// = 0.6211 1 7 13 
allAttributes.add(new SimpleEntry("hilarious", "NUMERIC"));// = 0.6211 5 3 13 
allAttributes.add(new SimpleEntry("wait", "NUMERIC"));// = 0.6202 34 78 163 
allAttributes.add(new SimpleEntry("hopefully", "NUMERIC"));// = 0.6165 7 14 33 
allAttributes.add(new SimpleEntry("greatest", "NUMERIC"));// = 0.6156 5 22 42 
allAttributes.add(new SimpleEntry("seeing", "NUMERIC"));// = 0.6105 16 26 63 
allAttributes.add(new SimpleEntry("sweet", "NUMERIC"));// = 0.6091 4 11 23 
allAttributes.add(new SimpleEntry("foo", "NUMERIC"));// = 0.6044 16 68 118 
allAttributes.add(new SimpleEntry("forever", "NUMERIC"));// = 0.6035 7 7 21 
allAttributes.add(new SimpleEntry("dance", "NUMERIC"));// = 0.603 1 11 18 
allAttributes.add(new SimpleEntry("finally", "NUMERIC"));// = 0.6019 11 34 65 
allAttributes.add(new SimpleEntry("pretty", "NUMERIC"));// = 0.6007 18 20 55 
allAttributes.add(new SimpleEntry("glad", "NUMERIC"));// = 0.5994 7 10 25 
allAttributes.add(new SimpleEntry("holy", "NUMERIC"));// = 0.5936 6 10 23 
allAttributes.add(new SimpleEntry("treat", "NUMERIC"));// = 0.5931 6 3 13 
allAttributes.add(new SimpleEntry("weekend", "NUMERIC"));// = 0.5895 7 45 71 
allAttributes.add(new SimpleEntry("westworld", "NUMERIC"));// = 0.5857 3 7 14 
allAttributes.add(new SimpleEntry("beauty", "NUMERIC"));// = 0.5857 1 9 14 
allAttributes.add(new SimpleEntry("catch", "NUMERIC"));// = 0.5827 3 13 22 
allAttributes.add(new SimpleEntry("fighters", "NUMERIC"));// = 0.5821 19 75 120 
allAttributes.add(new SimpleEntry("prince", "NUMERIC"));// = 0.5817 9 65 96 
allAttributes.add(new SimpleEntry("band", "NUMERIC"));// = 0.5801 13 59 93 
// allAttributes.add(new SimpleEntry("george's", "NUMERIC"));// = 0.5791 1 13 19 
allAttributes.add(new SimpleEntry("saw", "NUMERIC"));// = 0.5765 18 41 76 
allAttributes.add(new SimpleEntry("fourth", "NUMERIC"));// = 0.5735 2 7 12 
allAttributes.add(new SimpleEntry("zac", "NUMERIC"));// = 0.5727 10 66 95 
allAttributes.add(new SimpleEntry("thanksgiving", "NUMERIC"));// = 0.5688 15 35 63 
allAttributes.add(new SimpleEntry("sing", "NUMERIC"));// = 0.568 2 15 22 
allAttributes.add(new SimpleEntry("cool", "NUMERIC"));// = 0.5641 11 20 39 
allAttributes.add(new SimpleEntry("maiden", "NUMERIC"));// = 0.5639 7 50 70 
allAttributes.add(new SimpleEntry("captain", "NUMERIC"));// = 0.5625 2 9 14 
allAttributes.add(new SimpleEntry("vr", "NUMERIC"));// = 0.5619 2 26 35 
allAttributes.add(new SimpleEntry("blanchett", "NUMERIC"));// = 0.5601 0 20 25 
allAttributes.add(new SimpleEntry("listening", "NUMERIC"));// = 0.5586 12 29 50 
allAttributes.add(new SimpleEntry("legend", "NUMERIC"));// = 0.5583 2 10 15 
allAttributes.add(new SimpleEntry("thanks", "NUMERIC"));// = 0.5581 17 52 82 
allAttributes.add(new SimpleEntry("paper", "NUMERIC"));// = 0.5553 16 75 105 
allAttributes.add(new SimpleEntry("character", "NUMERIC"));// = 0.5515 7 7 17 
allAttributes.add(new SimpleEntry("performance", "NUMERIC"));// = 0.5499 3 17 24 
allAttributes.add(new SimpleEntry("night", "NUMERIC"));// = 0.5488 63 394 395 
allAttributes.add(new SimpleEntry("cate", "NUMERIC"));// = 0.5481 2 19 25 
allAttributes.add(new SimpleEntry("highest", "NUMERIC"));// = 0.5477 4 6 12 
allAttributes.add(new SimpleEntry("books", "NUMERIC"));// = 0.5441 6 5 13 
// allAttributes.add(new SimpleEntry("100%", "NUMERIC"));// = 0.5441 5 6 13 
allAttributes.add(new SimpleEntry("iron", "NUMERIC"));// = 0.5424 8 59 75 
allAttributes.add(new SimpleEntry("j", "NUMERIC"));// = 0.5413 5 21 30 
allAttributes.add(new SimpleEntry("friends", "NUMERIC"));// = 0.5404 14 32 52 
allAttributes.add(new SimpleEntry("haha", "NUMERIC"));// = 0.5392 5 15 23 
allAttributes.add(new SimpleEntry("dream", "NUMERIC"));// = 0.5378 3 18 24 
allAttributes.add(new SimpleEntry("calibraska", "NUMERIC"));// = 0.5362 4 28 36 
allAttributes.add(new SimpleEntry("star", "NUMERIC"));// = 0.5346 19 66 91 
allAttributes.add(new SimpleEntry("afternoon", "NUMERIC"));// = 0.5301 4 14 20 
allAttributes.add(new SimpleEntry("christmas", "NUMERIC"));// = 0.5298 12 37 53 
allAttributes.add(new SimpleEntry("movie", "NUMERIC"));// = 0.5272 31 65 99 
allAttributes.add(new SimpleEntry("bro", "NUMERIC"));// = 0.5259 1 9 11 
allAttributes.add(new SimpleEntry("wedding", "NUMERIC"));// = 0.524 3 8 12 
allAttributes.add(new SimpleEntry("prayers", "NUMERIC"));// = 0.524 0 11 12 
allAttributes.add(new SimpleEntry("miss", "NUMERIC"));// = 0.5233 20 84 105 
allAttributes.add(new SimpleEntry("watching", "NUMERIC"));// = 0.5225 31 92 122 
allAttributes.add(new SimpleEntry("friday", "NUMERIC"));// = 0.521 100 422 393 
allAttributes.add(new SimpleEntry("strong", "NUMERIC"));// = 0.5192 4 11 16 
allAttributes.add(new SimpleEntry("alive", "NUMERIC"));// = 0.5167 8 11 20 
allAttributes.add(new SimpleEntry("23", "NUMERIC"));// = 0.5163 3 17 21 
allAttributes.add(new SimpleEntry("morning", "NUMERIC"));// = 0.5163 30 86 113 
allAttributes.add(new SimpleEntry("cry", "NUMERIC"));// = 0.5151 10 14 25 
allAttributes.add(new SimpleEntry("tonight", "NUMERIC"));// = 0.5147 30 146 163 
allAttributes.add(new SimpleEntry("bieber", "NUMERIC"));// = 0.5146 14 59 73 
allAttributes.add(new SimpleEntry("song", "NUMERIC"));// = 0.5119 19 70 87 
allAttributes.add(new SimpleEntry("vamps", "NUMERIC"));// = 0.5068 7 27 34 
allAttributes.add(new SimpleEntry("lamar", "NUMERIC"));// = 0.5057 8 45 52 
allAttributes.add(new SimpleEntry("music", "NUMERIC"));// = 0.5056 17 50 65 
allAttributes.add(new SimpleEntry("19th", "NUMERIC"));// = 0.5052 4 22 26 
allAttributes.add(new SimpleEntry("today", "NUMERIC"));// = 0.5045 65 203 224 
allAttributes.add(new SimpleEntry("absolutely", "NUMERIC"));// = 0.504 11 9 20 
allAttributes.add(new SimpleEntry("summer", "NUMERIC"));// = 0.5038 5 14 19 
allAttributes.add(new SimpleEntry("singing", "NUMERIC"));// = 0.5032 3 13 16 
allAttributes.add(new SimpleEntry("walking", "NUMERIC"));// = 0.5028 4 10 14 
// allAttributes.add(new SimpleEntry("dunkin'", "NUMERIC"));// = 0.5028 4 10 14 
allAttributes.add(new SimpleEntry("spend", "NUMERIC"));// = 0.5028 4 10 14 
allAttributes.add(new SimpleEntry("22nd", "NUMERIC"));// = 0.5026 2 11 13 
allAttributes.add(new SimpleEntry("festival", "NUMERIC"));// = 0.5026 4 9 13 
allAttributes.add(new SimpleEntry("seats", "NUMERIC"));// = 0.5024 3 9 12 
allAttributes.add(new SimpleEntry("hope", "NUMERIC"));// = 0.5024 50 115 147 
allAttributes.add(new SimpleEntry("lyrics", "NUMERIC"));// = 0.5024 1 11 12 
allAttributes.add(new SimpleEntry("deserves", "NUMERIC"));// = 0.5022 4 7 11 
allAttributes.add(new SimpleEntry("turns", "NUMERIC"));// = 0.5022 2 9 11 
allAttributes.add(new SimpleEntry("concert", "NUMERIC"));// = 0.5005 21 97 108 
allAttributes.add(new SimpleEntry("video", "NUMERIC"));// = 0.4993 30 91 110 
allAttributes.add(new SimpleEntry("week", "NUMERIC"));// = 0.4977 15 61 71 
allAttributes.add(new SimpleEntry("winner", "NUMERIC"));// = 0.4959 5 22 26 
allAttributes.add(new SimpleEntry("valentines", "NUMERIC"));// = 0.4953 3 23 25 
allAttributes.add(new SimpleEntry("cover", "NUMERIC"));// = 0.4927 6 30 34 
allAttributes.add(new SimpleEntry("perform", "NUMERIC"));// = 0.4881 1 16 16 
allAttributes.add(new SimpleEntry("god", "NUMERIC"));// = 0.4876 35 55 80 
allAttributes.add(new SimpleEntry("toronto", "NUMERIC"));// = 0.487 0 16 15 
allAttributes.add(new SimpleEntry("saturday", "NUMERIC"));// = 0.4863 46 234 217 
allAttributes.add(new SimpleEntry("lord", "NUMERIC"));// = 0.4857 5 10 14 
allAttributes.add(new SimpleEntry("sister", "NUMERIC"));// = 0.4857 2 13 14 
allAttributes.add(new SimpleEntry("fan", "NUMERIC"));// = 0.4853 10 35 41 
allAttributes.add(new SimpleEntry("definitely", "NUMERIC"));// = 0.485 12 14 24 
allAttributes.add(new SimpleEntry("bowl", "NUMERIC"));// = 0.4842 1 13 13 
allAttributes.add(new SimpleEntry("tribute", "NUMERIC"));// = 0.4842 1 13 13 
allAttributes.add(new SimpleEntry("win", "NUMERIC"));// = 0.4837 26 135 134 
allAttributes.add(new SimpleEntry("meeting", "NUMERIC"));// = 0.4829 6 18 22 
allAttributes.add(new SimpleEntry("truly", "NUMERIC"));// = 0.4825 6 7 12 
allAttributes.add(new SimpleEntry("richard", "NUMERIC"));// = 0.4825 3 10 12 
allAttributes.add(new SimpleEntry("laughing", "NUMERIC"));// = 0.4825 2 11 12 
allAttributes.add(new SimpleEntry("rookie", "NUMERIC"));// = 0.4806 0 12 11 
allAttributes.add(new SimpleEntry("remembered", "NUMERIC"));// = 0.4806 3 9 11 
allAttributes.add(new SimpleEntry("leonard", "NUMERIC"));// = 0.4805 9 22 28 
allAttributes.add(new SimpleEntry("respect", "NUMERIC"));// = 0.4804 15 7 20 
allAttributes.add(new SimpleEntry("watched", "NUMERIC"));// = 0.48 15 31 41 
allAttributes.add(new SimpleEntry("watchman", "NUMERIC"));// = 0.4791 6 39 40 
allAttributes.add(new SimpleEntry("chat", "NUMERIC"));// = 0.4783 3 8 10 
allAttributes.add(new SimpleEntry("19", "NUMERIC"));// = 0.4775 0 20 18 
// allAttributes.add(new SimpleEntry("thor's", "NUMERIC"));// = 0.4775 4 16 18 
allAttributes.add(new SimpleEntry("early", "NUMERIC"));// = 0.4772 11 32 38 
// allAttributes.add(new SimpleEntry("can't", "NUMERIC"));// = 0.477 121 123 187 
allAttributes.add(new SimpleEntry("cohen", "NUMERIC"));// = 0.477 10 18 25 
allAttributes.add(new SimpleEntry("justin", "NUMERIC"));// = 0.4763 20 91 93 
allAttributes.add(new SimpleEntry("friend", "NUMERIC"));// = 0.4742 6 34 35 
allAttributes.add(new SimpleEntry("souls", "NUMERIC"));// = 0.4729 17 51 58 
allAttributes.add(new SimpleEntry("coffee", "NUMERIC"));// = 0.4728 7 18 22 
allAttributes.add(new SimpleEntry("special", "NUMERIC"));// = 0.4716 7 37 38 
// allAttributes.add(new SimpleEntry("tomorrow's", "NUMERIC"));// = 0.4712 2 22 21 
allAttributes.add(new SimpleEntry("followed", "NUMERIC"));// = 0.4697 3 13 14 
allAttributes.add(new SimpleEntry("going", "NUMERIC"));// = 0.4696 150 419 346 
allAttributes.add(new SimpleEntry("bowie", "NUMERIC"));// = 0.4685 12 51 53 
allAttributes.add(new SimpleEntry("kendrick", "NUMERIC"));// = 0.4673 35 122 123 
allAttributes.add(new SimpleEntry("champion", "NUMERIC"));// = 0.4671 2 13 13 
allAttributes.add(new SimpleEntry("monday", "NUMERIC"));// = 0.4664 66 300 249 
allAttributes.add(new SimpleEntry("twilight", "NUMERIC"));// = 0.4656 21 79 81 
allAttributes.add(new SimpleEntry("gotta", "NUMERIC"));// = 0.465 5 22 23 
allAttributes.add(new SimpleEntry("food", "NUMERIC"));// = 0.465 9 18 23 





		
		// allAttributes.add(new SimpleEntry("a", "NUMERIC"));
		// // allAttributes.add(new SimpleEntry("amazing", "NUMERIC"));
		// allAttributes.add(new SimpleEntry("antman", "NUMERIC"));
		// allAttributes.add(new SimpleEntry("are", "NUMERIC"));
		// allAttributes.add(new SimpleEntry("at", "NUMERIC"));
		// // allAttributes.add(new SimpleEntry("awesome", "NUMERIC"));
		// // allAttributes.add(new SimpleEntry("best", "NUMERIC"));
		// // allAttributes.add(new SimpleEntry("NEGATION", "NUMERIC"));
		// allAttributes.add(new SimpleEntry("fantastic", "NUMERIC"));// = 0.9758 0 1 35
		// allAttributes.add(new SimpleEntry("enjoyed", "NUMERIC"));// = 0.9314 1 1 26
		// allAttributes.add(new SimpleEntry("amazing", "NUMERIC"));// = 0.9302 1 6 82
		// allAttributes.add(new SimpleEntry("incredible", "NUMERIC"));// = 0.9257 1 1 24
		// allAttributes.add(new SimpleEntry("excited", "NUMERIC"));// = 0.9245 6 4 105
		// allAttributes.add(new SimpleEntry("awesome", "NUMERIC"));// = 0.9217 2 5 74
		// allAttributes.add(new SimpleEntry("cream", "NUMERIC"));// = 0.9074 0 17 140
		// allAttributes.add(new SimpleEntry("excellent", "NUMERIC"));// = 0.9069 0 2 19
		// allAttributes.add(new SimpleEntry("i", "NUMERIC"));// = 0.8937 1044 2099 1957
		// allAttributes.add(new SimpleEntry("brilliant", "NUMERIC"));// = 0.8741 2 2 27
		// allAttributes.add(new SimpleEntry("happy", "NUMERIC"));// = 0.8739 23 33 292
		// allAttributes.add(new SimpleEntry("&lt;3", "NUMERIC"));// = 0.8697 2 2 26
		// allAttributes.add(new SimpleEntry("loved", "NUMERIC"));// = 0.8459 3 3 32
		// allAttributes.add(new SimpleEntry("love", "NUMERIC"));// = 0.829 27 76 369
		// allAttributes.add(new SimpleEntry("lovely", "NUMERIC"));// = 0.8284 1 3 19
		// allAttributes.add(new SimpleEntry("bless", "NUMERIC"));// = 0.827 1 9 46
		// allAttributes.add(new SimpleEntry("congrats", "NUMERIC"));// = 0.8204 0 4 18
		// allAttributes.add(new SimpleEntry("wonderful", "NUMERIC"));// = 0.8204 0 4 18
		// allAttributes.add(new SimpleEntry("beautiful", "NUMERIC"));// = 0.8195 1 10 48
		// allAttributes.add(new SimpleEntry("thankful", "NUMERIC"));// = 0.8103 0 5 21
		// allAttributes.add(new SimpleEntry("liked", "NUMERIC"));// = 0.7942 4 12 59
		// allAttributes.add(new SimpleEntry("cute", "NUMERIC"));// = 0.7933 3 5 30
		// allAttributes.add(new SimpleEntry("perfect", "NUMERIC"));// = 0.7913 4 8 44
		// allAttributes.add(new SimpleEntry("enjoy", "NUMERIC"));// = 0.7881 7 9 57
		// allAttributes.add(new SimpleEntry("fun", "NUMERIC"));// = 0.7729 9 12 68
		// allAttributes.add(new SimpleEntry("welcome", "NUMERIC"));// = 0.7717 4 6 33
		// allAttributes.add(new SimpleEntry("proud", "NUMERIC"));// = 0.767 4 5 29
		// allAttributes.add(new SimpleEntry("wishing", "NUMERIC"));// = 0.764 1 4 16
		// allAttributes.add(new SimpleEntry("thank", "NUMERIC"));// = 0.762 9 21 90
		// allAttributes.add(new SimpleEntry("janet", "NUMERIC"));// = 0.7528 4 18 64
		// allAttributes.add(new SimpleEntry("favorite", "NUMERIC"));// = 0.7523 2 14 47
		// allAttributes.add(new SimpleEntry("great", "NUMERIC"));// = 0.7474 33 67 248
		// allAttributes.add(new SimpleEntry("dog", "NUMERIC"));// = 0.7455 14 27 111
		// allAttributes.add(new SimpleEntry("rapper", "NUMERIC"));// = 0.7414 0 6 17
		// allAttributes.add(new SimpleEntry("best", "NUMERIC"));// = 0.7351 28 95 280
		// allAttributes.add(new SimpleEntry("celebrate", "NUMERIC"));// = 0.7297 6 20 67
		// allAttributes.add(new SimpleEntry("birthday", "NUMERIC"));// = 0.721 9 54 147
		// allAttributes.add(new SimpleEntry("hot", "NUMERIC"));// = 0.7099 14 51 144
		// allAttributes.add(new SimpleEntry("national", "NUMERIC"));// = 0.7082 21 78 208
		// allAttributes.add(new SimpleEntry("nice", "NUMERIC"));// = 0.7063 6 25 71
		// allAttributes.add(new SimpleEntry("gift", "NUMERIC"));// = 0.6926 3 6 20
		// allAttributes.add(new SimpleEntry("ant-man", "NUMERIC"));// = 0.6917 13 53 135
		// allAttributes.add(new SimpleEntry("dogs", "NUMERIC"));// = 0.6907 4 6 22
		// allAttributes.add(new SimpleEntry("harper", "NUMERIC"));// = 0.684 1 6 15
		// allAttributes.add(new SimpleEntry("day", "NUMERIC"));// = 0.6835 125 432 702
		// allAttributes.add(new SimpleEntry("celebrating", "NUMERIC"));// = 0.6799 4 7 23

		// allAttributes.add(new SimpleEntry("NEGATIONWORD", "NUMERIC"));// = 0.703 1830 2316 977



		allAttributes.add(new SimpleEntry ("sentiment", "{positive,negative,neutral}"));
	}

	public static void main(String args[]) {
		try {

			initialization();

			// pritn out the headers for the arff file
			System.out.println("@RELATION twitter-posi-tops");
			for (SimpleEntry<String, String> pair : allAttributes) {
				System.out.println("@ATTRIBUTE " + pair.getKey() + " " + pair.getValue());
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
