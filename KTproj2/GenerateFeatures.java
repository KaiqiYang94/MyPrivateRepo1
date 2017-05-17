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
		// allAttributes.add(new SimpleEntry("id", "NUMERIC"));
		allAttributes.add(new SimpleEntry("i", "NUMERIC"));// = 1.0984 1044 2099 1957
		allAttributes.add(new SimpleEntry("NEGATIONWORD", "NUMERIC"));// = 1.0602 1830 2316 977
		allAttributes.add(new SimpleEntry("scam", "NUMERIC"));// = 1.0011 11 0 0
		allAttributes.add(new SimpleEntry("excitement", "NUMERIC"));// = 1.0011 1 0 10
		allAttributes.add(new SimpleEntry("scum", "NUMERIC"));// = 1.0011 11 0 0
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
		allAttributes.add(new SimpleEntry("idiots", "NUMERIC"));// = 0.9102 10 1 0
		allAttributes.add(new SimpleEntry("corbyn", "NUMERIC"));// = 0.9102 9 1 1
		allAttributes.add(new SimpleEntry("screening", "NUMERIC"));// = 0.9102 2 1 8
		allAttributes.add(new SimpleEntry("msm", "NUMERIC"));// = 0.9102 10 1 0
		allAttributes.add(new SimpleEntry("fool", "NUMERIC"));// = 0.9102 9 1 1
		allAttributes.add(new SimpleEntry("pathetic", "NUMERIC"));// = 0.9102 9 1 1
		allAttributes.add(new SimpleEntry("cream", "NUMERIC"));// = 0.9074 0 17 140
		allAttributes.add(new SimpleEntry("excellent", "NUMERIC"));// = 0.9069 0 2 19
		allAttributes.add(new SimpleEntry("criminal", "NUMERIC"));// = 0.8966 17 2 0
		allAttributes.add(new SimpleEntry("fucking", "NUMERIC"));// = 0.8963 58 11 28
		allAttributes.add(new SimpleEntry("nazi", "NUMERIC"));// = 0.894 70 9 0
		allAttributes.add(new SimpleEntry("freaking", "NUMERIC"));// = 0.8907 6 2 10
		allAttributes.add(new SimpleEntry("yay", "NUMERIC"));// = 0.8907 1 2 15
		allAttributes.add(new SimpleEntry("stupid", "NUMERIC"));// = 0.8898 44 6 2
		allAttributes.add(new SimpleEntry("love", "NUMERIC"));// = 0.8862 27 76 369
		allAttributes.add(new SimpleEntry("pissed", "NUMERIC"));// = 0.8841 14 2 1
		allAttributes.add(new SimpleEntry("enjoy", "NUMERIC"));// = 0.884 7 9 57
		allAttributes.add(new SimpleEntry("exciting", "NUMERIC"));// = 0.8766 0 2 14
		allAttributes.add(new SimpleEntry("trash", "NUMERIC"));// = 0.8766 10 2 4
		allAttributes.add(new SimpleEntry("fun", "NUMERIC"));// = 0.8741 9 12 68
		allAttributes.add(new SimpleEntry("cute", "NUMERIC"));// = 0.8722 3 5 30
		allAttributes.add(new SimpleEntry("proud", "NUMERIC"));// = 0.8722 4 5 29
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
		allAttributes.add(new SimpleEntry("perfect", "NUMERIC"));// = 0.8627 4 8 44
		allAttributes.add(new SimpleEntry("racist", "NUMERIC"));// = 0.8627 47 8 1
		allAttributes.add(new SimpleEntry("hilarious", "NUMERIC"));// = 0.8592 5 3 13
		allAttributes.add(new SimpleEntry("elect", "NUMERIC"));// = 0.8585 11 2 1
		allAttributes.add(new SimpleEntry("holidays", "NUMERIC"));// = 0.8585 2 2 10
		allAttributes.add(new SimpleEntry("bullshit", "NUMERIC"));// = 0.8585 11 2 1
		allAttributes.add(new SimpleEntry("stole", "NUMERIC"));// = 0.8585 8 2 4
		allAttributes.add(new SimpleEntry("forced", "NUMERIC"));// = 0.852 17 3 0
		allAttributes.add(new SimpleEntry("sucks", "NUMERIC"));// = 0.8488 21 4 1
		allAttributes.add(new SimpleEntry("damn", "NUMERIC"));// = 0.8478 26 9 22
		allAttributes.add(new SimpleEntry("nope", "NUMERIC"));// = 0.8475 8 2 3
		allAttributes.add(new SimpleEntry("ignorant", "NUMERIC"));// = 0.8475 11 2 0
		allAttributes.add(new SimpleEntry("healthy", "NUMERIC"));// = 0.8475 1 2 10
		allAttributes.add(new SimpleEntry("liked", "NUMERIC"));// = 0.8475 4 12 59
		allAttributes.add(new SimpleEntry("scoop", "NUMERIC"));// = 0.8475 0 2 11
		allAttributes.add(new SimpleEntry("murder", "NUMERIC"));// = 0.8475 11 2 0
		allAttributes.add(new SimpleEntry("knowing", "NUMERIC"));// = 0.8475 3 2 8
		allAttributes.add(new SimpleEntry("finds", "NUMERIC"));// = 0.8475 7 2 4
		allAttributes.add(new SimpleEntry("fuck", "NUMERIC"));// = 0.8463 89 19 7
		allAttributes.add(new SimpleEntry("shit", "NUMERIC"));// = 0.8453 117 33 40
		allAttributes.add(new SimpleEntry("bless", "NUMERIC"));// = 0.8449 1 9 46
		allAttributes.add(new SimpleEntry("great", "NUMERIC"));// = 0.8423 33 67 248
		allAttributes.add(new SimpleEntry("dog", "NUMERIC"));// = 0.8376 14 27 111
		allAttributes.add(new SimpleEntry("respect", "NUMERIC"));// = 0.8375 15 7 20
		allAttributes.add(new SimpleEntry("thank", "NUMERIC"));// = 0.837 9 21 90
		allAttributes.add(new SimpleEntry("beautiful", "NUMERIC"));// = 0.8364 1 10 48
		allAttributes.add(new SimpleEntry("lies", "NUMERIC"));// = 0.8363 25 5 0
		allAttributes.add(new SimpleEntry("mubarak", "NUMERIC"));// = 0.8351 0 3 15
		allAttributes.add(new SimpleEntry("shitty", "NUMERIC"));// = 0.8345 9 2 1
		allAttributes.add(new SimpleEntry("policies", "NUMERIC"));// = 0.8345 10 2 0
		allAttributes.add(new SimpleEntry("hitler", "NUMERIC"));// = 0.8345 8 2 2
		allAttributes.add(new SimpleEntry("easily", "NUMERIC"));// = 0.8345 7 2 3
		allAttributes.add(new SimpleEntry("dirty", "NUMERIC"));// = 0.8345 7 2 3
		allAttributes.add(new SimpleEntry("funds", "NUMERIC"));// = 0.8345 8 2 2
		allAttributes.add(new SimpleEntry("blind", "NUMERIC"));// = 0.8345 6 2 4
		allAttributes.add(new SimpleEntry("parade", "NUMERIC"));// = 0.8345 7 2 3
		allAttributes.add(new SimpleEntry("bitches", "NUMERIC"));// = 0.8345 8 2 2
		allAttributes.add(new SimpleEntry("insurance", "NUMERIC"));// = 0.8345 7 2 3
		allAttributes.add(new SimpleEntry("boruto", "NUMERIC"));// = 0.8345 0 2 10
		allAttributes.add(new SimpleEntry("killer", "NUMERIC"));// = 0.8345 6 2 4
		allAttributes.add(new SimpleEntry("filled", "NUMERIC"));// = 0.8345 4 2 6
		allAttributes.add(new SimpleEntry("steal", "NUMERIC"));// = 0.8284 17 4 2
		allAttributes.add(new SimpleEntry("favourite", "NUMERIC"));// = 0.8252 3 3 11
		allAttributes.add(new SimpleEntry("horrible", "NUMERIC"));// = 0.8252 14 3 0
		allAttributes.add(new SimpleEntry("cinema", "NUMERIC"));// = 0.8252 0 3 14
		allAttributes.add(new SimpleEntry("gays", "NUMERIC"));// = 0.8252 14 3 0
		allAttributes.add(new SimpleEntry("supremacists", "NUMERIC"));// = 0.8208 60 14 1
		allAttributes.add(new SimpleEntry("congrats", "NUMERIC"));// = 0.8204 0 4 18
		allAttributes.add(new SimpleEntry("crap", "NUMERIC"));// = 0.8204 15 4 3
		allAttributes.add(new SimpleEntry("wonderful", "NUMERIC"));// = 0.8204 0 4 18
		allAttributes.add(new SimpleEntry("proved", "NUMERIC"));// = 0.8193 8 2 1
		allAttributes.add(new SimpleEntry("pride", "NUMERIC"));// = 0.8193 4 2 5
		allAttributes.add(new SimpleEntry("doctors", "NUMERIC"));// = 0.8193 8 2 1
		allAttributes.add(new SimpleEntry("😍", "NUMERIC"));// = 0.8193 0 2 9
		allAttributes.add(new SimpleEntry("glory", "NUMERIC"));// = 0.8193 1 2 8
		allAttributes.add(new SimpleEntry("worse", "NUMERIC"));// = 0.8183 34 8 1
		allAttributes.add(new SimpleEntry("dogs", "NUMERIC"));// = 0.8157 4 6 22
		allAttributes.add(new SimpleEntry("films", "NUMERIC"));// = 0.8141 0 3 13
		allAttributes.add(new SimpleEntry("paid", "NUMERIC"));// = 0.8137 22 8 12
		allAttributes.add(new SimpleEntry("trust", "NUMERIC"));// = 0.8116 12 4 5
		allAttributes.add(new SimpleEntry("wishing", "NUMERIC"));// = 0.8116 1 4 16
		allAttributes.add(new SimpleEntry("lying", "NUMERIC"));// = 0.8103 20 5 1
		allAttributes.add(new SimpleEntry("thankful", "NUMERIC"));// = 0.8103 0 5 21
		allAttributes.add(new SimpleEntry("nobody", "NUMERIC"));// = 0.8096 16 6 9
		allAttributes.add(new SimpleEntry("killed", "NUMERIC"));// = 0.808 57 16 7
		allAttributes.add(new SimpleEntry("liberals", "NUMERIC"));// = 0.8064 81 21 1
		allAttributes.add(new SimpleEntry("best", "NUMERIC"));// = 0.8046 28 95 280
		allAttributes.add(new SimpleEntry("sick", "NUMERIC"));// = 0.804 29 8 3
		allAttributes.add(new SimpleEntry("terrorist", "NUMERIC"));// = 0.804 31 8 1
		allAttributes.add(new SimpleEntry("forever", "NUMERIC"));// = 0.8035 7 7 21
		allAttributes.add(new SimpleEntry("fat", "NUMERIC"));// = 0.8025 18 5 2
		allAttributes.add(new SimpleEntry("ridiculous", "NUMERIC"));// = 0.802 15 4 1
		allAttributes.add(new SimpleEntry("acting", "NUMERIC"));// = 0.802 8 4 8
		allAttributes.add(new SimpleEntry("congratulations", "NUMERIC"));// = 0.802 2 4 14
		allAttributes.add(new SimpleEntry("threat", "NUMERIC"));// = 0.802 16 4 0
		allAttributes.add(new SimpleEntry("leftists", "NUMERIC"));// = 0.8018 56 15 2
		allAttributes.add(new SimpleEntry("peaceful", "NUMERIC"));// = 0.8015 6 3 6
		allAttributes.add(new SimpleEntry("kills", "NUMERIC"));// = 0.8015 10 3 2
		allAttributes.add(new SimpleEntry("janet", "NUMERIC"));// = 0.7993 4 18 64
		allAttributes.add(new SimpleEntry("hell", "NUMERIC"));// = 0.799 51 20 24
		allAttributes.add(new SimpleEntry("celebrating", "NUMERIC"));// = 0.7975 4 7 23
		allAttributes.add(new SimpleEntry("gift", "NUMERIC"));// = 0.796 3 6 20
		allAttributes.add(new SimpleEntry("good", "NUMERIC"));// = 0.7958 72 154 360
		allAttributes.add(new SimpleEntry("pretty", "NUMERIC"));// = 0.7942 18 20 55
		allAttributes.add(new SimpleEntry("celebrate", "NUMERIC"));// = 0.7942 6 20 67
		allAttributes.add(new SimpleEntry("loving", "NUMERIC"));// = 0.7941 3 5 16
		allAttributes.add(new SimpleEntry("books", "NUMERIC"));// = 0.7941 6 5 13
		allAttributes.add(new SimpleEntry("bloody", "NUMERIC"));// = 0.7919 31 10 6
		allAttributes.add(new SimpleEntry("disappointed", "NUMERIC"));// = 0.7914 13 4 2
		allAttributes.add(new SimpleEntry("racism", "NUMERIC"));// = 0.7914 14 4 1
		allAttributes.add(new SimpleEntry("wow", "NUMERIC"));// = 0.7899 12 17 49
		allAttributes.add(new SimpleEntry("appreciate", "NUMERIC"));// = 0.7871 2 3 9
		allAttributes.add(new SimpleEntry("hail", "NUMERIC"));// = 0.7871 4 3 7
		allAttributes.add(new SimpleEntry("heaven", "NUMERIC"));// = 0.7871 4 3 7
		allAttributes.add(new SimpleEntry("excuse", "NUMERIC"));// = 0.7871 10 3 1
		allAttributes.add(new SimpleEntry("epic", "NUMERIC"));// = 0.7871 1 3 10
		allAttributes.add(new SimpleEntry("ass", "NUMERIC"));// = 0.7853 54 22 22
		allAttributes.add(new SimpleEntry("keeps", "NUMERIC"));// = 0.7849 11 5 7
		allAttributes.add(new SimpleEntry("favorite", "NUMERIC"));// = 0.7841 2 14 47
		allAttributes.add(new SimpleEntry("fake", "NUMERIC"));// = 0.7828 51 15 1
		allAttributes.add(new SimpleEntry("day", "NUMERIC"));// = 0.7828 125 432 702
		allAttributes.add(new SimpleEntry("bitch", "NUMERIC"));// = 0.7823 31 10 4
		allAttributes.add(new SimpleEntry("awful", "NUMERIC"));// = 0.7796 10 4 4
		allAttributes.add(new SimpleEntry("hahaha", "NUMERIC"));// = 0.7796 7 4 7
		allAttributes.add(new SimpleEntry("absolutely", "NUMERIC"));// = 0.779 11 9 20
		allAttributes.add(new SimpleEntry("character", "NUMERIC"));// = 0.7773 7 7 17
		allAttributes.add(new SimpleEntry("hot", "NUMERIC"));// = 0.7769 14 51 144
		allAttributes.add(new SimpleEntry("wars", "NUMERIC"));// = 0.7767 17 27 71
		allAttributes.add(new SimpleEntry("national", "NUMERIC"));// = 0.7766 21 78 208
		allAttributes.add(new SimpleEntry("drone", "NUMERIC"));// = 0.775 52 16 1
		allAttributes.add(new SimpleEntry("luck", "NUMERIC"));// = 0.7744 7 12 33
		allAttributes.add(new SimpleEntry("melania's", "NUMERIC"));// = 0.7705 7 3 3
		allAttributes.add(new SimpleEntry("ignore", "NUMERIC"));// = 0.7705 9 3 1
		allAttributes.add(new SimpleEntry("vma's", "NUMERIC"));// = 0.7705 3 3 7
		allAttributes.add(new SimpleEntry("warren", "NUMERIC"));// = 0.7705 8 3 2
		allAttributes.add(new SimpleEntry("mouth", "NUMERIC"));// = 0.7705 6 3 4
		allAttributes.add(new SimpleEntry("zero", "NUMERIC"));// = 0.7705 9 3 1
		allAttributes.add(new SimpleEntry("bright", "NUMERIC"));// = 0.7705 3 3 7
		allAttributes.add(new SimpleEntry("knowledge", "NUMERIC"));// = 0.7705 5 3 5
		allAttributes.add(new SimpleEntry("carol", "NUMERIC"));// = 0.7705 0 3 10
		allAttributes.add(new SimpleEntry("alt-rightists", "NUMERIC"));// = 0.7697 22 7 1
		allAttributes.add(new SimpleEntry("$", "NUMERIC"));// = 0.7664 11 4 2
		allAttributes.add(new SimpleEntry("fed", "NUMERIC"));// = 0.7664 12 4 1
		allAttributes.add(new SimpleEntry("society", "NUMERIC"));// = 0.7664 10 4 3
		allAttributes.add(new SimpleEntry("rich", "NUMERIC"));// = 0.7664 13 4 0
		allAttributes.add(new SimpleEntry("emotional", "NUMERIC"));// = 0.7664 4 4 9
		allAttributes.add(new SimpleEntry("killing", "NUMERIC"));// = 0.7661 29 10 3
		allAttributes.add(new SimpleEntry("glad", "NUMERIC"));// = 0.7661 7 10 25
		allAttributes.add(new SimpleEntry("nice", "NUMERIC"));// = 0.7651 6 25 71
		allAttributes.add(new SimpleEntry("false", "NUMERIC"));// = 0.764 15 5 1
		allAttributes.add(new SimpleEntry("birthday", "NUMERIC"));// = 0.7639 9 54 147
		allAttributes.add(new SimpleEntry("seeing", "NUMERIC"));// = 0.7629 16 26 63
		allAttributes.add(new SimpleEntry("tomorrow", "NUMERIC"));// = 0.7621 216 863 901
		allAttributes.add(new SimpleEntry("crying", "NUMERIC"));// = 0.7609 15 8 10
		allAttributes.add(new SimpleEntry("blame", "NUMERIC"));// = 0.7605 26 9 2
		// allAttributes.add(new SimpleEntry("can't", "NUMERIC"));// = 0.7577 121 123 187
		// allAttributes.add(new SimpleEntry("ant-man", "NUMERIC"));// = 0.7564 13 53 135
		// allAttributes.add(new SimpleEntry("especially", "NUMERIC"));// = 0.7532 10 8 14
		// allAttributes.add(new SimpleEntry("royal", "NUMERIC"));// = 0.7532 3 8 21
		// allAttributes.add(new SimpleEntry("shame", "NUMERIC"));// = 0.7528 16 7 5
		// allAttributes.add(new SimpleEntry("nigga", "NUMERIC"));// = 0.7524 13 6 5
		// allAttributes.add(new SimpleEntry("100%", "NUMERIC"));// = 0.7524 5 6 13
		// allAttributes.add(new SimpleEntry("feed", "NUMERIC"));// = 0.752 10 5 5
		// allAttributes.add(new SimpleEntry("b/c", "NUMERIC"));// = 0.7516 8 4 4
		// allAttributes.add(new SimpleEntry("grab", "NUMERIC"));// = 0.7516 2 4 10
		// allAttributes.add(new SimpleEntry("bringing", "NUMERIC"));// = 0.7516 1 4 11
		// allAttributes.add(new SimpleEntry("corrupt", "NUMERIC"));// = 0.7516 11 4 1
		// allAttributes.add(new SimpleEntry("passing", "NUMERIC"));// = 0.7516 2 4 10
		// allAttributes.add(new SimpleEntry("fill", "NUMERIC"));// = 0.7516 5 4 7
		// allAttributes.add(new SimpleEntry("otherwise", "NUMERIC"));// = 0.7516 10 4 2
		// allAttributes.add(new SimpleEntry("enjoying", "NUMERIC"));// = 0.7516 0 4 12
		// allAttributes.add(new SimpleEntry("risk", "NUMERIC"));// = 0.7512 9 3 0
		// allAttributes.add(new SimpleEntry("kitchen", "NUMERIC"));// = 0.7512 6 3 3
		// allAttributes.add(new SimpleEntry("soldier", "NUMERIC"));// = 0.7512 6 3 3
		// allAttributes.add(new SimpleEntry("desperate", "NUMERIC"));// = 0.7512 5 3 4
		// allAttributes.add(new SimpleEntry("aim", "NUMERIC"));// = 0.7512 5 3 4
		// allAttributes.add(new SimpleEntry("bodies", "NUMERIC"));// = 0.7512 8 3 1
		// allAttributes.add(new SimpleEntry("dope", "NUMERIC"));// = 0.7512 1 3 8
		// allAttributes.add(new SimpleEntry("children", "NUMERIC"));// = 0.751 28 14 13
		// allAttributes.add(new SimpleEntry("reason", "NUMERIC"));// = 0.7488 32 21 28
		// allAttributes.add(new SimpleEntry("holy", "NUMERIC"));// = 0.7475 6 10 23
		// allAttributes.add(new SimpleEntry("hopefully", "NUMERIC"));// = 0.7461 7 14 33
		// allAttributes.add(new SimpleEntry("honestly", "NUMERIC"));// = 0.745 11 8 12
		// allAttributes.add(new SimpleEntry("wait", "NUMERIC"));// = 0.7439 34 78 163
		// allAttributes.add(new SimpleEntry("fraud", "NUMERIC"));// = 0.7434 18 7 2
		// allAttributes.add(new SimpleEntry("alt-right", "NUMERIC"));// = 0.7423 31 11 0
		// allAttributes.add(new SimpleEntry("hate", "NUMERIC"));// = 0.7416 82 35 12
		// allAttributes.add(new SimpleEntry("pence", "NUMERIC"));// = 0.7414 15 6 2
		// allAttributes.add(new SimpleEntry("rapper", "NUMERIC"));// = 0.7414 0 6 17
		// allAttributes.add(new SimpleEntry("manslaughter", "NUMERIC"));// = 0.7406 28 10 0
		// allAttributes.add(new SimpleEntry("effort", "NUMERIC"));// = 0.7387 6 5 8
		// allAttributes.add(new SimpleEntry("present", "NUMERIC"));// = 0.7387 2 5 12
		// allAttributes.add(new SimpleEntry("civilians", "NUMERIC"));// = 0.7387 14 5 0
		// allAttributes.add(new SimpleEntry("mass", "NUMERIC"));// = 0.7363 20 8 2
		// allAttributes.add(new SimpleEntry("finale", "NUMERIC"));// = 0.7348 1 4 10
		// allAttributes.add(new SimpleEntry("horse", "NUMERIC"));// = 0.7348 5 4 6
		// allAttributes.add(new SimpleEntry("cuts", "NUMERIC"));// = 0.7348 9 4 2
		// allAttributes.add(new SimpleEntry("runner", "NUMERIC"));// = 0.7348 4 4 7
		// allAttributes.add(new SimpleEntry("grant", "NUMERIC"));// = 0.7348 2 4 9
		// allAttributes.add(new SimpleEntry("families", "NUMERIC"));// = 0.7348 6 4 5
		// allAttributes.add(new SimpleEntry("burning", "NUMERIC"));// = 0.7348 9 4 2
		// allAttributes.add(new SimpleEntry("animals", "NUMERIC"));// = 0.7348 7 4 4
		// allAttributes.add(new SimpleEntry("ss", "NUMERIC"));// = 0.7348 11 4 0
		// allAttributes.add(new SimpleEntry("mvp", "NUMERIC"));// = 0.7348 1 4 10
		// allAttributes.add(new SimpleEntry("seriously", "NUMERIC"));// = 0.734 21 13 14
		// allAttributes.add(new SimpleEntry("conservatives", "NUMERIC"));// = 0.7339 45 18 3
		// allAttributes.add(new SimpleEntry("anymore", "NUMERIC"));// = 0.7306 18 9 6
		// allAttributes.add(new SimpleEntry("funny", "NUMERIC"));// = 0.7299 21 16 21
		// allAttributes.add(new SimpleEntry("hombres", "NUMERIC"));// = 0.7299 35 16 7
		// allAttributes.add(new SimpleEntry("highest", "NUMERIC"));// = 0.7295 4 6 12
		// allAttributes.add(new SimpleEntry("shut", "NUMERIC"));// = 0.7295 15 6 1
		// allAttributes.add(new SimpleEntry("harper", "NUMERIC"));// = 0.7295 1 6 15
		// allAttributes.add(new SimpleEntry("teachers", "NUMERIC"));// = 0.7284 4 3 4
		// allAttributes.add(new SimpleEntry("exposed", "NUMERIC"));// = 0.7284 6 3 2
		// allAttributes.add(new SimpleEntry("covers", "NUMERIC"));// = 0.7284 0 3 8
		// allAttributes.add(new SimpleEntry("fingers", "NUMERIC"));// = 0.7284 0 3 8
		// allAttributes.add(new SimpleEntry("workers", "NUMERIC"));// = 0.7284 4 3 4
		// allAttributes.add(new SimpleEntry("sympathy", "NUMERIC"));// = 0.7284 6 3 2
		// allAttributes.add(new SimpleEntry("tape", "NUMERIC"));// = 0.7284 6 3 2
		// allAttributes.add(new SimpleEntry("goodnight", "NUMERIC"));// = 0.7284 0 3 8
		// allAttributes.add(new SimpleEntry("remix", "NUMERIC"));// = 0.7284 1 3 7
		// allAttributes.add(new SimpleEntry("cat", "NUMERIC"));// = 0.7284 5 3 3
		// allAttributes.add(new SimpleEntry("w/o", "NUMERIC"));// = 0.7284 7 3 1
		// allAttributes.add(new SimpleEntry("9pm", "NUMERIC"));// = 0.7284 0 3 8
		// allAttributes.add(new SimpleEntry("outfit", "NUMERIC"));// = 0.7284 0 3 8
		// allAttributes.add(new SimpleEntry("fuckin", "NUMERIC"));// = 0.7284 5 3 3
		// allAttributes.add(new SimpleEntry("cancer", "NUMERIC"));// = 0.7284 5 3 3
		// allAttributes.add(new SimpleEntry("attacked", "NUMERIC"));// = 0.7284 8 3 0
		// allAttributes.add(new SimpleEntry("lmfao", "NUMERIC"));// = 0.7284 5 3 3
		// allAttributes.add(new SimpleEntry("anti", "NUMERIC"));// = 0.7252 30 12 1
		// allAttributes.add(new SimpleEntry("terrorists", "NUMERIC"));// = 0.7252 29 12 2
		// allAttributes.add(new SimpleEntry("definitely", "NUMERIC"));// = 0.725 12 14 24
		// allAttributes.add(new SimpleEntry("bombing", "NUMERIC"));// = 0.724 13 5 0
		// allAttributes.add(new SimpleEntry("honest", "NUMERIC"));// = 0.724 4 5 9
		// allAttributes.add(new SimpleEntry("funding", "NUMERIC"));// = 0.724 11 5 2
		// allAttributes.add(new SimpleEntry("truly", "NUMERIC"));// = 0.7225 6 7 12
		// allAttributes.add(new SimpleEntry("supporting", "NUMERIC"));// = 0.7225 11 7 7
		// allAttributes.add(new SimpleEntry(";)", "NUMERIC"));// = 0.7225 2 7 16
		// allAttributes.add(new SimpleEntry("entire", "NUMERIC"));// = 0.7218 15 11 13
		// allAttributes.add(new SimpleEntry("alive", "NUMERIC"));// = 0.7218 8 11 20
		// allAttributes.add(new SimpleEntry("cool", "NUMERIC"));// = 0.7213 11 20 39
		// allAttributes.add(new SimpleEntry("boko", "NUMERIC"));// = 0.7201 81 43 22
		// allAttributes.add(new SimpleEntry("haram", "NUMERIC"));// = 0.7201 81 43 22
		// allAttributes.add(new SimpleEntry("cry", "NUMERIC"));// = 0.7192 10 14 25
		// allAttributes.add(new SimpleEntry("huge", "NUMERIC"));// = 0.7178 17 17 25
		// allAttributes.add(new SimpleEntry("brown", "NUMERIC"));// = 0.7165 11 37 77
		// allAttributes.add(new SimpleEntry("-)", "NUMERIC"));// = 0.7164 1 6 14
		// allAttributes.add(new SimpleEntry("iraq", "NUMERIC"));// = 0.7164 15 6 0
		// allAttributes.add(new SimpleEntry("charges", "NUMERIC"));// = 0.7164 14 6 1
		// allAttributes.add(new SimpleEntry("catching", "NUMERIC"));// = 0.7157 2 4 8
		// allAttributes.add(new SimpleEntry("caught", "NUMERIC"));// = 0.7157 6 4 4
		// allAttributes.add(new SimpleEntry("marry", "NUMERIC"));// = 0.7157 5 4 5
		// allAttributes.add(new SimpleEntry("fail", "NUMERIC"));// = 0.7157 7 4 3
		// allAttributes.add(new SimpleEntry("responsible", "NUMERIC"));// = 0.7157 7 4 3
		// allAttributes.add(new SimpleEntry("schools", "NUMERIC"));// = 0.7157 10 4 0
		// allAttributes.add(new SimpleEntry("NEGATIONWORD", "NUMERIC"));// = 0.703 1830 2316 977

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
