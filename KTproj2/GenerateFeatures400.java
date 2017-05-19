import java.util.*;
import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.AbstractMap.*;

class GenerateFeatures400 {

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
// from posi
allAttributes.add(new SimpleEntry("delici", "NUMERIC"));// = 1.001 0 0 10 
allAttributes.add(new SimpleEntry("❤", "NUMERIC"));// = 1.0009 0 0 9 
allAttributes.add(new SimpleEntry("merri", "NUMERIC"));// = 1.0008 0 0 8 
allAttributes.add(new SimpleEntry("▓", "NUMERIC"));// = 1.0008 0 0 8 
allAttributes.add(new SimpleEntry("cutest", "NUMERIC"));// = 1.0007 0 0 7 
allAttributes.add(new SimpleEntry("vibe", "NUMERIC"));// = 1.0007 0 0 7 
allAttributes.add(new SimpleEntry("handsom", "NUMERIC"));// = 1.0007 0 0 7 
allAttributes.add(new SimpleEntry("otra", "NUMERIC"));// = 1.0006 0 0 6 
allAttributes.add(new SimpleEntry("fantast", "NUMERIC"));// = 0.9496 1 1 35 
allAttributes.add(new SimpleEntry("amaz", "NUMERIC"));// = 0.9312 2 5 83 
allAttributes.add(new SimpleEntry("excit", "NUMERIC"));// = 0.9234 7 6 130 
allAttributes.add(new SimpleEntry("awesom", "NUMERIC"));// = 0.9217 2 5 74 
allAttributes.add(new SimpleEntry("cream", "NUMERIC"));// = 0.9082 0 17 141 
allAttributes.add(new SimpleEntry("i", "NUMERIC"));// = 0.8937 1044 2099 1957 
allAttributes.add(new SimpleEntry("unbreak", "NUMERIC"));// = 0.8898 0 1 8 
allAttributes.add(new SimpleEntry("delight", "NUMERIC"));// = 0.8898 0 1 8 
allAttributes.add(new SimpleEntry("❤️", "NUMERIC"));// = 0.8898 0 1 8 
allAttributes.add(new SimpleEntry("happi", "NUMERIC"));// = 0.8765 23 35 305 
allAttributes.add(new SimpleEntry("coolest", "NUMERIC"));// = 0.8758 0 1 7 
allAttributes.add(new SimpleEntry("funniest", "NUMERIC"));// = 0.8758 0 1 7 
allAttributes.add(new SimpleEntry("stoke", "NUMERIC"));// = 0.8758 0 1 7 
// allAttributes.add(new SimpleEntry("\m/", "NUMERIC"));// = 0.8758 0 1 7 
allAttributes.add(new SimpleEntry("gorgeou", "NUMERIC"));// = 0.8758 0 1 7 
allAttributes.add(new SimpleEntry("brilliant", "NUMERIC"));// = 0.8741 2 2 27 
allAttributes.add(new SimpleEntry("&lt;3", "NUMERIC"));// = 0.8697 2 2 26 
allAttributes.add(new SimpleEntry("bless", "NUMERIC"));// = 0.8593 3 10 74 
allAttributes.add(new SimpleEntry("almighti", "NUMERIC"));// = 0.8578 1 0 6 
allAttributes.add(new SimpleEntry("scoop", "NUMERIC"));// = 0.8475 0 2 11 
allAttributes.add(new SimpleEntry("incred", "NUMERIC"));// = 0.8418 3 2 26 
allAttributes.add(new SimpleEntry("love", "NUMERIC"));// = 0.8355 40 91 456 
allAttributes.add(new SimpleEntry("yai", "NUMERIC"));// = 0.8351 1 2 15 
allAttributes.add(new SimpleEntry("mubarak", "NUMERIC"));// = 0.8351 0 3 15 
allAttributes.add(new SimpleEntry("boruto", "NUMERIC"));// = 0.8345 0 2 10 
allAttributes.add(new SimpleEntry("sweetheart", "NUMERIC"));// = 0.8339 1 0 5 
allAttributes.add(new SimpleEntry("happier", "NUMERIC"));// = 0.8339 1 0 5 
allAttributes.add(new SimpleEntry("hottest", "NUMERIC"));// = 0.8339 1 0 5 
allAttributes.add(new SimpleEntry("^^", "NUMERIC"));// = 0.8339 0 1 5 
allAttributes.add(new SimpleEntry("bloodborn", "NUMERIC"));// = 0.8339 0 1 5 
allAttributes.add(new SimpleEntry("flavor", "NUMERIC"));// = 0.8339 0 1 5 
allAttributes.add(new SimpleEntry("rc", "NUMERIC"));// = 0.8339 0 1 5 
allAttributes.add(new SimpleEntry("shoutout", "NUMERIC"));// = 0.8339 1 0 5 
allAttributes.add(new SimpleEntry("combo", "NUMERIC"));// = 0.8339 0 1 5 
allAttributes.add(new SimpleEntry("excel", "NUMERIC"));// = 0.8284 1 3 19 
allAttributes.add(new SimpleEntry("congrat", "NUMERIC"));// = 0.8204 0 4 18 
allAttributes.add(new SimpleEntry("hip", "NUMERIC"));// = 0.8193 1 1 9 
// allAttributes.add(new SimpleEntry("😍", "NUMERIC"));// = 0.8193 0 2 9 
allAttributes.add(new SimpleEntry("enjoi", "NUMERIC"));// = 0.8155 9 15 98 
allAttributes.add(new SimpleEntry("<3", "NUMERIC"));// = 0.801 0 2 8 
allAttributes.add(new SimpleEntry("8pm", "NUMERIC"));// = 0.801 0 2 8 
allAttributes.add(new SimpleEntry("perfect", "NUMERIC"));// = 0.7989 4 8 46 
allAttributes.add(new SimpleEntry("cute", "NUMERIC"));// = 0.7988 3 5 31 
allAttributes.add(new SimpleEntry("carol", "NUMERIC"));// = 0.7871 0 3 11 
allAttributes.add(new SimpleEntry("dan", "NUMERIC"));// = 0.7787 0 2 7 
allAttributes.add(new SimpleEntry("fun", "NUMERIC"));// = 0.7729 9 12 68 
allAttributes.add(new SimpleEntry("favorit", "NUMERIC"));// = 0.772 2 13 49 
allAttributes.add(new SimpleEntry("healthi", "NUMERIC"));// = 0.7705 1 2 10 
allAttributes.add(new SimpleEntry("proud", "NUMERIC"));// = 0.767 4 5 29 
allAttributes.add(new SimpleEntry("beauti", "NUMERIC"));// = 0.7553 2 19 62 
allAttributes.add(new SimpleEntry("janet", "NUMERIC"));// = 0.7528 4 18 64 
allAttributes.add(new SimpleEntry("pump", "NUMERIC"));// = 0.7516 0 4 12 
allAttributes.add(new SimpleEntry("day", "NUMERIC"));// = 0.7512 0 3 9 
allAttributes.add(new SimpleEntry("pit", "NUMERIC"));// = 0.7508 1 1 6 
allAttributes.add(new SimpleEntry("ameen", "NUMERIC"));// = 0.7508 0 2 6 
allAttributes.add(new SimpleEntry("leftov", "NUMERIC"));// = 0.7508 1 1 6 
allAttributes.add(new SimpleEntry("fascin", "NUMERIC"));// = 0.7508 2 0 6 
allAttributes.add(new SimpleEntry("max", "NUMERIC"));// = 0.7508 0 2 6 
allAttributes.add(new SimpleEntry("amen", "NUMERIC"));// = 0.7508 1 1 6 
allAttributes.add(new SimpleEntry("great", "NUMERIC"));// = 0.7483 33 68 251 
allAttributes.add(new SimpleEntry("cinema", "NUMERIC"));// = 0.7414 0 6 17 
allAttributes.add(new SimpleEntry("dog", "NUMERIC"));// = 0.7412 18 33 133 
allAttributes.add(new SimpleEntry("best", "NUMERIC"));// = 0.7351 28 95 280 
allAttributes.add(new SimpleEntry("goodnight", "NUMERIC"));// = 0.7284 0 3 8 
allAttributes.add(new SimpleEntry("9pm", "NUMERIC"));// = 0.7284 0 3 8 
allAttributes.add(new SimpleEntry("babe", "NUMERIC"));// = 0.7284 0 3 8 
allAttributes.add(new SimpleEntry("glori", "NUMERIC"));// = 0.7284 1 2 8 
allAttributes.add(new SimpleEntry("ador", "NUMERIC"));// = 0.7284 1 2 8 
allAttributes.add(new SimpleEntry("welcom", "NUMERIC"));// = 0.7276 4 11 39 
allAttributes.add(new SimpleEntry("birthdai", "NUMERIC"));// = 0.7275 9 52 147 
allAttributes.add(new SimpleEntry("outfit", "NUMERIC"));// = 0.7157 0 4 10 
allAttributes.add(new SimpleEntry("sock", "NUMERIC"));// = 0.715 2 0 5 
allAttributes.add(new SimpleEntry("stud", "NUMERIC"));// = 0.715 0 2 5 
allAttributes.add(new SimpleEntry("gr8", "NUMERIC"));// = 0.715 0 2 5 
allAttributes.add(new SimpleEntry("southpaw", "NUMERIC"));// = 0.715 0 2 5 
allAttributes.add(new SimpleEntry("that'd", "NUMERIC"));// = 0.715 1 1 5 
allAttributes.add(new SimpleEntry("wee", "NUMERIC"));// = 0.715 1 1 5 
allAttributes.add(new SimpleEntry("pleasur", "NUMERIC"));// = 0.715 1 1 5 
allAttributes.add(new SimpleEntry("grate", "NUMERIC"));// = 0.715 0 2 5 
allAttributes.add(new SimpleEntry("innov", "NUMERIC"));// = 0.715 1 1 5 
allAttributes.add(new SimpleEntry("perry'", "NUMERIC"));// = 0.715 0 2 5 
allAttributes.add(new SimpleEntry("hot", "NUMERIC"));// = 0.7099 14 51 144 
allAttributes.add(new SimpleEntry("nice", "NUMERIC"));// = 0.7093 6 25 72 
allAttributes.add(new SimpleEntry("relax", "NUMERIC"));// = 0.7076 0 5 12 
allAttributes.add(new SimpleEntry("mood", "NUMERIC"));// = 0.701 2 1 7 
allAttributes.add(new SimpleEntry("unbeliev", "NUMERIC"));// = 0.6936 1 3 9 
allAttributes.add(new SimpleEntry("hop", "NUMERIC"));// = 0.6936 2 2 9 
allAttributes.add(new SimpleEntry("rapper", "NUMERIC"));// = 0.6926 1 8 20 
allAttributes.add(new SimpleEntry("ant-man", "NUMERIC"));// = 0.6917 13 53 135 
allAttributes.add(new SimpleEntry("NEGATIONWORD", "NUMERIC"));// = 0.6843 1748 2252 940 
allAttributes.add(new SimpleEntry("harper", "NUMERIC"));// = 0.684 1 6 15 
allAttributes.add(new SimpleEntry("thank", "NUMERIC"));// = 0.6821 26 78 195 
allAttributes.add(new SimpleEntry("omg", "NUMERIC"));// = 0.6718 10 7 34 
allAttributes.add(new SimpleEntry("dai", "NUMERIC"));// = 0.6718 148 505 743 
allAttributes.add(new SimpleEntry("good", "NUMERIC"));// = 0.6714 73 157 363 
allAttributes.add(new SimpleEntry("-)", "NUMERIC"));// = 0.6688 1 6 14 
allAttributes.add(new SimpleEntry("favourit", "NUMERIC"));// = 0.6685 3 3 12 
allAttributes.add(new SimpleEntry("mvp", "NUMERIC"));// = 0.6682 1 4 10 
allAttributes.add(new SimpleEntry("epic", "NUMERIC"));// = 0.6682 2 3 10 
allAttributes.add(new SimpleEntry("solid", "NUMERIC"));// = 0.6679 0 4 8 
allAttributes.add(new SimpleEntry("brave", "NUMERIC"));// = 0.6676 0 3 6 
allAttributes.add(new SimpleEntry("gaston", "NUMERIC"));// = 0.6676 1 2 6 
allAttributes.add(new SimpleEntry("upper", "NUMERIC"));// = 0.6676 0 3 6 
allAttributes.add(new SimpleEntry("fantasi", "NUMERIC"));// = 0.6676 0 3 6 
allAttributes.add(new SimpleEntry("dvd", "NUMERIC"));// = 0.6676 0 3 6 
allAttributes.add(new SimpleEntry("sensibl", "NUMERIC"));// = 0.6673 1 1 4 
allAttributes.add(new SimpleEntry("melt", "NUMERIC"));// = 0.6673 1 1 4 
allAttributes.add(new SimpleEntry("rogu", "NUMERIC"));// = 0.6673 2 0 4 
allAttributes.add(new SimpleEntry("dive", "NUMERIC"));// = 0.6673 0 2 4 
allAttributes.add(new SimpleEntry("sandwich", "NUMERIC"));// = 0.6673 0 2 4 
allAttributes.add(new SimpleEntry("soo", "NUMERIC"));// = 0.6673 0 2 4 
allAttributes.add(new SimpleEntry("steak", "NUMERIC"));// = 0.6673 1 1 4 
allAttributes.add(new SimpleEntry("ham", "NUMERIC"));// = 0.6673 0 2 4 
allAttributes.add(new SimpleEntry("2dai", "NUMERIC"));// = 0.6673 1 1 4 
allAttributes.add(new SimpleEntry("prosper", "NUMERIC"));// = 0.6673 0 2 4 
allAttributes.add(new SimpleEntry("nbc", "NUMERIC"));// = 0.6673 1 1 4 
allAttributes.add(new SimpleEntry("wld", "NUMERIC"));// = 0.6673 1 1 4 
allAttributes.add(new SimpleEntry("comeback", "NUMERIC"));// = 0.6673 0 2 4 
allAttributes.add(new SimpleEntry("padraig", "NUMERIC"));// = 0.6673 0 2 4 
allAttributes.add(new SimpleEntry("recip", "NUMERIC"));// = 0.6673 0 2 4 
allAttributes.add(new SimpleEntry("nerd", "NUMERIC"));// = 0.6673 0 2 4 
allAttributes.add(new SimpleEntry("6-0", "NUMERIC"));// = 0.6673 0 2 4 
allAttributes.add(new SimpleEntry("jackson", "NUMERIC"));// = 0.6592 5 29 63 
allAttributes.add(new SimpleEntry("congratul", "NUMERIC"));// = 0.6581 2 8 19 
allAttributes.add(new SimpleEntry("nation", "NUMERIC"));// = 0.6538 39 99 223 
allAttributes.add(new SimpleEntry("tomorrow", "NUMERIC"));// = 0.6531 216 863 901 
allAttributes.add(new SimpleEntry("ic", "NUMERIC"));// = 0.653 23 125 236 
allAttributes.add(new SimpleEntry(")", "NUMERIC"));// = 0.6493 6 50 97 
allAttributes.add(new SimpleEntry("finger", "NUMERIC"));// = 0.6488 2 4 11 
allAttributes.add(new SimpleEntry("23rd", "NUMERIC"));// = 0.645 1 17 32 
allAttributes.add(new SimpleEntry("stun", "NUMERIC"));// = 0.6443 1 4 9 
allAttributes.add(new SimpleEntry("attract", "NUMERIC"));// = 0.6443 4 1 9 
allAttributes.add(new SimpleEntry(";)", "NUMERIC"));// = 0.6425 2 7 16 
allAttributes.add(new SimpleEntry("luck", "NUMERIC"));// = 0.6398 7 12 33 
allAttributes.add(new SimpleEntry("smile", "NUMERIC"));// = 0.6397 2 10 21 
allAttributes.add(new SimpleEntry("grammi", "NUMERIC"));// = 0.6386 0 8 14 
allAttributes.add(new SimpleEntry("fanci", "NUMERIC"));// = 0.6375 1 3 7 
allAttributes.add(new SimpleEntry("pub", "NUMERIC"));// = 0.6375 1 3 7 
allAttributes.add(new SimpleEntry("wow", "NUMERIC"));// = 0.636 12 17 49 
allAttributes.add(new SimpleEntry("forward", "NUMERIC"));// = 0.6301 6 20 43 
allAttributes.add(new SimpleEntry("celebr", "NUMERIC"));// = 0.6297 22 61 129 
allAttributes.add(new SimpleEntry("brown", "NUMERIC"));// = 0.6285 11 37 77 
allAttributes.add(new SimpleEntry("champ", "NUMERIC"));// = 0.6274 2 7 15 
allAttributes.add(new SimpleEntry("wash", "NUMERIC"));// = 0.6258 1 2 5 
allAttributes.add(new SimpleEntry("thrill", "NUMERIC"));// = 0.6258 1 2 5 
allAttributes.add(new SimpleEntry("watchman'", "NUMERIC"));// = 0.6258 0 3 5 
allAttributes.add(new SimpleEntry("lee'", "NUMERIC"));// = 0.6258 2 1 5 
allAttributes.add(new SimpleEntry("blew", "NUMERIC"));// = 0.6258 1 2 5 
allAttributes.add(new SimpleEntry("asap", "NUMERIC"));// = 0.6258 1 2 5 
allAttributes.add(new SimpleEntry("dbz", "NUMERIC"));// = 0.6258 0 3 5 
allAttributes.add(new SimpleEntry("consist", "NUMERIC"));// = 0.6258 1 2 5 
allAttributes.add(new SimpleEntry("spiritu", "NUMERIC"));// = 0.6258 0 3 5 
allAttributes.add(new SimpleEntry("sweet", "NUMERIC"));// = 0.6193 4 11 24 
allAttributes.add(new SimpleEntry("cheer", "NUMERIC"));// = 0.618 1 9 16 
allAttributes.add(new SimpleEntry("honor", "NUMERIC"));// = 0.618 0 10 16 
allAttributes.add(new SimpleEntry("27th", "NUMERIC"));// = 0.6167 1 4 8 
allAttributes.add(new SimpleEntry("dope", "NUMERIC"));// = 0.6167 2 3 8 
allAttributes.add(new SimpleEntry("hopefulli", "NUMERIC"));// = 0.6165 7 14 33 
allAttributes.add(new SimpleEntry("greatest", "NUMERIC"));// = 0.6156 5 22 42 
allAttributes.add(new SimpleEntry("forev", "NUMERIC"));// = 0.6147 7 7 22 
allAttributes.add(new SimpleEntry("foo", "NUMERIC"));// = 0.6044 16 68 118 
allAttributes.add(new SimpleEntry("holi", "NUMERIC"));// = 0.604 6 10 24 
allAttributes.add(new SimpleEntry("remix", "NUMERIC"));// = 0.6015 1 5 9 
allAttributes.add(new SimpleEntry("bang", "NUMERIC"));// = 0.6015 2 4 9 
allAttributes.add(new SimpleEntry("fever", "NUMERIC"));// = 0.6015 1 5 9 
allAttributes.add(new SimpleEntry("da", "NUMERIC"));// = 0.601 1 3 6 
allAttributes.add(new SimpleEntry("hug", "NUMERIC"));// = 0.601 2 2 6 
allAttributes.add(new SimpleEntry("rewatch", "NUMERIC"));// = 0.601 0 4 6 
allAttributes.add(new SimpleEntry("bing", "NUMERIC"));// = 0.601 1 3 6 
allAttributes.add(new SimpleEntry("pretti", "NUMERIC"));// = 0.6007 18 20 55 
allAttributes.add(new SimpleEntry("glad", "NUMERIC"));// = 0.5994 7 10 25 
allAttributes.add(new SimpleEntry("gift", "NUMERIC"));// = 0.5983 3 12 22 
allAttributes.add(new SimpleEntry("weekend", "NUMERIC"));// = 0.5909 7 47 74 
allAttributes.add(new SimpleEntry("monster", "NUMERIC"));// = 0.5899 4 3 10 
allAttributes.add(new SimpleEntry("wine", "NUMERIC"));// = 0.5899 0 7 10 
allAttributes.add(new SimpleEntry("gig", "NUMERIC"));// = 0.5891 1 11 17 
allAttributes.add(new SimpleEntry("westworld", "NUMERIC"));// = 0.5857 3 7 14 
allAttributes.add(new SimpleEntry("domin", "NUMERIC"));// = 0.5845 0 5 7 
allAttributes.add(new SimpleEntry("billboard", "NUMERIC"));// = 0.5845 1 4 7 
allAttributes.add(new SimpleEntry("peep", "NUMERIC"));// = 0.5845 0 5 7 
allAttributes.add(new SimpleEntry("wait", "NUMERIC"));// = 0.5842 47 104 185 
allAttributes.add(new SimpleEntry("princ", "NUMERIC"));// = 0.5817 9 65 96 
allAttributes.add(new SimpleEntry("root", "NUMERIC"));// = 0.5808 3 5 11 
allAttributes.add(new SimpleEntry("saw", "NUMERIC"));// = 0.5798 18 41 77 
allAttributes.add(new SimpleEntry("band", "NUMERIC"));// = 0.578 14 62 97 
allAttributes.add(new SimpleEntry("cool", "NUMERIC"));// = 0.5766 11 20 41 
allAttributes.add(new SimpleEntry("george'", "NUMERIC"));// = 0.5749 1 14 20 
allAttributes.add(new SimpleEntry("thanksgiv", "NUMERIC"));// = 0.5737 15 34 63 
allAttributes.add(new SimpleEntry("theater", "NUMERIC"));// = 0.5735 2 7 12 
allAttributes.add(new SimpleEntry("pack", "NUMERIC"));// = 0.5735 3 6 12 


// from neg
allAttributes.add(new SimpleEntry("scam", "NUMERIC"));// = 1.0014 14 0 0 
allAttributes.add(new SimpleEntry("disgrac", "NUMERIC"));// = 1.0011 11 0 0 
allAttributes.add(new SimpleEntry("puppet", "NUMERIC"));// = 1.0011 11 0 0 
allAttributes.add(new SimpleEntry("scum", "NUMERIC"));// = 1.0011 11 0 0 
allAttributes.add(new SimpleEntry("neo", "NUMERIC"));// = 1.0009 9 0 0 
allAttributes.add(new SimpleEntry("extremist", "NUMERIC"));// = 1.0008 8 0 0 
allAttributes.add(new SimpleEntry("harass", "NUMERIC"));// = 1.0008 8 0 0 
allAttributes.add(new SimpleEntry("disast", "NUMERIC"));// = 1.0007 7 0 0 
allAttributes.add(new SimpleEntry("asham", "NUMERIC"));// = 1.0007 7 0 0 
allAttributes.add(new SimpleEntry("40%", "NUMERIC"));// = 1.0006 6 0 0 
allAttributes.add(new SimpleEntry("disgust", "NUMERIC"));// = 0.9545 20 1 0 
allAttributes.add(new SimpleEntry("fascist", "NUMERIC"));// = 0.93 13 1 0 
allAttributes.add(new SimpleEntry("idiot", "NUMERIC"));// = 0.9203 33 2 1 
allAttributes.add(new SimpleEntry("worst", "NUMERIC"));// = 0.9146 50 4 1 
allAttributes.add(new SimpleEntry("msm", "NUMERIC"));// = 0.9102 10 1 0 
allAttributes.add(new SimpleEntry("nazi", "NUMERIC"));// = 0.9089 89 10 0 
allAttributes.add(new SimpleEntry("nightmar", "NUMERIC"));// = 0.901 9 0 1 
allAttributes.add(new SimpleEntry("divid", "NUMERIC"));// = 0.901 9 1 0 
allAttributes.add(new SimpleEntry("dnc", "NUMERIC"));// = 0.8898 8 1 0 
allAttributes.add(new SimpleEntry("denial", "NUMERIC"));// = 0.8758 7 1 0 
allAttributes.add(new SimpleEntry("assang", "NUMERIC"));// = 0.8758 7 0 1 
allAttributes.add(new SimpleEntry("vet", "NUMERIC"));// = 0.8758 7 0 1 
allAttributes.add(new SimpleEntry("crimin", "NUMERIC"));// = 0.8697 26 4 0 
allAttributes.add(new SimpleEntry("stupid", "NUMERIC"));// = 0.8679 50 6 2 
allAttributes.add(new SimpleEntry("murder", "NUMERIC"));// = 0.8647 31 5 0 
allAttributes.add(new SimpleEntry("rnc", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("isil", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("rude", "NUMERIC"));// = 0.8578 6 1 0 
allAttributes.add(new SimpleEntry("racial", "NUMERIC"));// = 0.8578 6 1 0 
// allAttributes.add(new SimpleEntry("NEGATIONWORD", "NUMERIC"));// = 0.8478 1748 2252 940 
allAttributes.add(new SimpleEntry("racist", "NUMERIC"));// = 0.8399 55 10 1 
allAttributes.add(new SimpleEntry("supremacist", "NUMERIC"));// = 0.8375 68 13 1 
allAttributes.add(new SimpleEntry("horribl", "NUMERIC"));// = 0.8351 15 3 0 
allAttributes.add(new SimpleEntry("zionist", "NUMERIC"));// = 0.8345 10 0 2 
allAttributes.add(new SimpleEntry("mp", "NUMERIC"));// = 0.8345 10 2 0 
allAttributes.add(new SimpleEntry("traitor", "NUMERIC"));// = 0.8345 10 2 0 
allAttributes.add(new SimpleEntry("moron", "NUMERIC"));// = 0.8345 10 1 1 
allAttributes.add(new SimpleEntry("whine", "NUMERIC"));// = 0.8339 5 0 1 
allAttributes.add(new SimpleEntry("disavow", "NUMERIC"));// = 0.8339 5 1 0 
allAttributes.add(new SimpleEntry("cruel", "NUMERIC"));// = 0.8339 5 1 0 
// allAttributes.add(new SimpleEntry("😒", "NUMERIC"));// = 0.8339 5 1 0 
allAttributes.add(new SimpleEntry("people", "NUMERIC"));// = 0.8339 5 1 0 
allAttributes.add(new SimpleEntry("tyrant", "NUMERIC"));// = 0.8339 5 0 1 
allAttributes.add(new SimpleEntry("divorc", "NUMERIC"));// = 0.8339 5 1 0 
allAttributes.add(new SimpleEntry("supremaci", "NUMERIC"));// = 0.8339 5 1 0 
allAttributes.add(new SimpleEntry("margin", "NUMERIC"));// = 0.8339 5 1 0 
allAttributes.add(new SimpleEntry("deadli", "NUMERIC"));// = 0.8339 5 0 1 
allAttributes.add(new SimpleEntry("clue", "NUMERIC"));// = 0.8339 5 1 0 
allAttributes.add(new SimpleEntry("unfollow", "NUMERIC"));// = 0.8339 5 1 0 
allAttributes.add(new SimpleEntry("nonsens", "NUMERIC"));// = 0.8339 5 1 0 
allAttributes.add(new SimpleEntry("nuke", "NUMERIC"));// = 0.8339 5 1 0 
allAttributes.add(new SimpleEntry("deport", "NUMERIC"));// = 0.8252 14 3 0 
allAttributes.add(new SimpleEntry("cheat", "NUMERIC"));// = 0.8252 14 3 0 
allAttributes.add(new SimpleEntry("li", "NUMERIC"));// = 0.8244 32 6 1 
allAttributes.add(new SimpleEntry("ignor", "NUMERIC"));// = 0.8226 36 7 1 
allAttributes.add(new SimpleEntry("pathet", "NUMERIC"));// = 0.8193 9 1 1 
allAttributes.add(new SimpleEntry("genocid", "NUMERIC"));// = 0.8193 9 2 0 
allAttributes.add(new SimpleEntry("crook", "NUMERIC"));// = 0.8193 9 2 0 
allAttributes.add(new SimpleEntry("corbyn", "NUMERIC"));// = 0.8193 9 1 1 
allAttributes.add(new SimpleEntry("lib", "NUMERIC"));// = 0.8193 9 1 1 
allAttributes.add(new SimpleEntry("violat", "NUMERIC"));// = 0.8141 13 3 0 
allAttributes.add(new SimpleEntry("threaten", "NUMERIC"));// = 0.8116 17 4 0 
allAttributes.add(new SimpleEntry("bullshit", "NUMERIC"));// = 0.8015 12 2 1 
allAttributes.add(new SimpleEntry("pussi", "NUMERIC"));// = 0.801 8 1 1 
allAttributes.add(new SimpleEntry("failur", "NUMERIC"));// = 0.801 8 2 0 
allAttributes.add(new SimpleEntry("biz", "NUMERIC"));// = 0.801 8 2 0 
allAttributes.add(new SimpleEntry("garbag", "NUMERIC"));// = 0.801 8 1 1 
allAttributes.add(new SimpleEntry("tortur", "NUMERIC"));// = 0.801 8 2 0 
allAttributes.add(new SimpleEntry("wors", "NUMERIC"));// = 0.795 34 8 1 
allAttributes.add(new SimpleEntry("dumb", "NUMERIC"));// = 0.7941 19 3 2 
allAttributes.add(new SimpleEntry("enemi", "NUMERIC"));// = 0.7871 11 2 1 
allAttributes.add(new SimpleEntry("rich", "NUMERIC"));// = 0.7796 14 4 0 
allAttributes.add(new SimpleEntry("corrupt", "NUMERIC"));// = 0.779 31 8 1 
allAttributes.add(new SimpleEntry("terrifi", "NUMERIC"));// = 0.7787 7 1 1 
allAttributes.add(new SimpleEntry("dislik", "NUMERIC"));// = 0.7787 7 1 1 
allAttributes.add(new SimpleEntry("loos", "NUMERIC"));// = 0.7787 7 1 1 
allAttributes.add(new SimpleEntry("retard", "NUMERIC"));// = 0.7787 7 2 0 
allAttributes.add(new SimpleEntry("toler", "NUMERIC"));// = 0.7787 7 1 1 
allAttributes.add(new SimpleEntry("ly", "NUMERIC"));// = 0.7718 20 5 1 
allAttributes.add(new SimpleEntry("hypocrit", "NUMERIC"));// = 0.7705 10 3 0 
allAttributes.add(new SimpleEntry("leftist", "NUMERIC"));// = 0.7705 61 17 2 
allAttributes.add(new SimpleEntry("nationalist", "NUMERIC"));// = 0.7705 10 3 0 
allAttributes.add(new SimpleEntry("sexual", "NUMERIC"));// = 0.7705 10 2 1 
allAttributes.add(new SimpleEntry("threat", "NUMERIC"));// = 0.7625 19 5 1 
allAttributes.add(new SimpleEntry("disappoint", "NUMERIC"));// = 0.7625 19 4 2 
allAttributes.add(new SimpleEntry("liber", "NUMERIC"));// = 0.7612 98 31 2 
allAttributes.add(new SimpleEntry("fake", "NUMERIC"));// = 0.7536 53 17 1 
allAttributes.add(new SimpleEntry("complain", "NUMERIC"));// = 0.752 15 5 0 
allAttributes.add(new SimpleEntry("corpor", "NUMERIC"));// = 0.7512 9 2 1 
allAttributes.add(new SimpleEntry("depress", "NUMERIC"));// = 0.7512 9 3 0 
allAttributes.add(new SimpleEntry("shitti", "NUMERIC"));// = 0.7512 9 2 1 
allAttributes.add(new SimpleEntry("tf", "NUMERIC"));// = 0.7508 6 1 1 
allAttributes.add(new SimpleEntry("loser", "NUMERIC"));// = 0.7508 6 2 0 
allAttributes.add(new SimpleEntry("suck", "NUMERIC"));// = 0.7494 35 6 6 
allAttributes.add(new SimpleEntry("piss", "NUMERIC"));// = 0.745 23 6 2 
allAttributes.add(new SimpleEntry("alt-right", "NUMERIC"));// = 0.7423 31 11 0 
allAttributes.add(new SimpleEntry("racism", "NUMERIC"));// = 0.7387 14 4 1 
allAttributes.add(new SimpleEntry("alt-rightist", "NUMERIC"));// = 0.7363 22 7 1 
allAttributes.add(new SimpleEntry("sick", "NUMERIC"));// = 0.7358 30 8 3 
allAttributes.add(new SimpleEntry("insult", "NUMERIC"));// = 0.7348 11 4 0 
allAttributes.add(new SimpleEntry("ss", "NUMERIC"));// = 0.7348 11 4 0 
allAttributes.add(new SimpleEntry("manslaught", "NUMERIC"));// = 0.7334 27 10 0 
allAttributes.add(new SimpleEntry("terrorist", "NUMERIC"));// = 0.7312 60 20 3 
allAttributes.add(new SimpleEntry("ridicul", "NUMERIC"));// = 0.7295 16 4 2 
allAttributes.add(new SimpleEntry("prosecut", "NUMERIC"));// = 0.7284 8 3 0 
allAttributes.add(new SimpleEntry("liar", "NUMERIC"));// = 0.7284 8 3 0 
allAttributes.add(new SimpleEntry("asshol", "NUMERIC"));// = 0.7284 8 2 1 
allAttributes.add(new SimpleEntry("fat", "NUMERIC"));// = 0.7225 18 5 2 
allAttributes.add(new SimpleEntry("drone", "NUMERIC"));// = 0.7196 52 20 1 
allAttributes.add(new SimpleEntry("dictat", "NUMERIC"));// = 0.7164 15 6 0 
allAttributes.add(new SimpleEntry("iraq", "NUMERIC"));// = 0.7164 15 6 0 
allAttributes.add(new SimpleEntry("fals", "NUMERIC"));// = 0.7164 15 5 1 
allAttributes.add(new SimpleEntry("rape", "NUMERIC"));// = 0.7157 10 3 1 
allAttributes.add(new SimpleEntry("vile", "NUMERIC"));// = 0.715 5 2 0 
allAttributes.add(new SimpleEntry("ironi", "NUMERIC"));// = 0.715 5 2 0 
allAttributes.add(new SimpleEntry("snake", "NUMERIC"));// = 0.715 5 1 1 
allAttributes.add(new SimpleEntry("duck", "NUMERIC"));// = 0.715 5 2 0 
allAttributes.add(new SimpleEntry("creepi", "NUMERIC"));// = 0.715 5 2 0 
allAttributes.add(new SimpleEntry("socialist", "NUMERIC"));// = 0.715 5 2 0 
allAttributes.add(new SimpleEntry("th…", "NUMERIC"));// = 0.715 5 2 0 
// allAttributes.add(new SimpleEntry("i", "NUMERIC"));// = 0.7147 1044 2099 1957 
allAttributes.add(new SimpleEntry("blame", "NUMERIC"));// = 0.7146 39 14 2 
allAttributes.add(new SimpleEntry("fuck", "NUMERIC"));// = 0.7143 161 35 37 
allAttributes.add(new SimpleEntry("anti", "NUMERIC"));// = 0.702 30 12 1 
allAttributes.add(new SimpleEntry("kkk", "NUMERIC"));// = 0.701 7 3 0 
allAttributes.add(new SimpleEntry("assault", "NUMERIC"));// = 0.701 7 3 0 
allAttributes.add(new SimpleEntry("disrespect", "NUMERIC"));// = 0.701 7 3 0 
allAttributes.add(new SimpleEntry("lefti", "NUMERIC"));// = 0.701 7 3 0 
allAttributes.add(new SimpleEntry("borno", "NUMERIC"));// = 0.701 7 2 1 
allAttributes.add(new SimpleEntry("massacr", "NUMERIC"));// = 0.701 7 3 0 
allAttributes.add(new SimpleEntry("innoc", "NUMERIC"));// = 0.6962 27 12 0 
allAttributes.add(new SimpleEntry("bitch", "NUMERIC"));// = 0.6955 40 12 6 
allAttributes.add(new SimpleEntry("kill", "NUMERIC"));// = 0.6946 159 58 20 
allAttributes.add(new SimpleEntry("bs", "NUMERIC"));// = 0.6936 9 4 0 
allAttributes.add(new SimpleEntry("violent", "NUMERIC"));// = 0.6891 11 1 4 
allAttributes.add(new SimpleEntry("annoi", "NUMERIC"));// = 0.6891 11 4 1 
allAttributes.add(new SimpleEntry("moral", "NUMERIC"));// = 0.6861 13 6 0 
allAttributes.add(new SimpleEntry("fed", "NUMERIC"));// = 0.6861 13 5 1 
allAttributes.add(new SimpleEntry("hate", "NUMERIC"));// = 0.6855 113 43 13 
allAttributes.add(new SimpleEntry("civilian", "NUMERIC"));// = 0.684 15 6 1 
allAttributes.add(new SimpleEntry("destroi", "NUMERIC"));// = 0.6836 42 17 3 
allAttributes.add(new SimpleEntry("fraud", "NUMERIC"));// = 0.6814 19 7 2 
allAttributes.add(new SimpleEntry("terror", "NUMERIC"));// = 0.6706 26 12 1 
allAttributes.add(new SimpleEntry("satan", "NUMERIC"));// = 0.6682 10 5 0 
allAttributes.add(new SimpleEntry("hitler", "NUMERIC"));// = 0.6679 8 2 2 
allAttributes.add(new SimpleEntry("profit", "NUMERIC"));// = 0.6679 8 2 2 
allAttributes.add(new SimpleEntry("unfortun", "NUMERIC"));// = 0.6679 8 4 0 
allAttributes.add(new SimpleEntry("overturn", "NUMERIC"));// = 0.6676 6 3 0 
allAttributes.add(new SimpleEntry("bastard", "NUMERIC"));// = 0.6676 6 3 0 
allAttributes.add(new SimpleEntry("scandal", "NUMERIC"));// = 0.6676 6 2 1 
allAttributes.add(new SimpleEntry("demon", "NUMERIC"));// = 0.6676 6 3 0 
allAttributes.add(new SimpleEntry("boycott", "NUMERIC"));// = 0.6676 6 2 1 
allAttributes.add(new SimpleEntry("enforc", "NUMERIC"));// = 0.6676 6 1 2 
allAttributes.add(new SimpleEntry("disappear", "NUMERIC"));// = 0.6676 6 2 1 
allAttributes.add(new SimpleEntry("justifi", "NUMERIC"));// = 0.6676 6 3 0 
allAttributes.add(new SimpleEntry("frustrat", "NUMERIC"));// = 0.6676 6 2 1 
allAttributes.add(new SimpleEntry("michelle'", "NUMERIC"));// = 0.6676 6 3 0 
allAttributes.add(new SimpleEntry("sect", "NUMERIC"));// = 0.6676 6 3 0 
allAttributes.add(new SimpleEntry("heil", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("rot", "NUMERIC"));// = 0.6673 4 1 1 
allAttributes.add(new SimpleEntry("spy", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("dismantl", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("hater", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("stir", "NUMERIC"));// = 0.6673 4 0 2 
allAttributes.add(new SimpleEntry("merkel", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("notori", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("ia", "NUMERIC"));// = 0.6673 4 1 1 
allAttributes.add(new SimpleEntry("hatr", "NUMERIC"));// = 0.6673 4 1 1 
allAttributes.add(new SimpleEntry("witch", "NUMERIC"));// = 0.6673 4 1 1 
allAttributes.add(new SimpleEntry("arrog", "NUMERIC"));// = 0.6673 4 1 1 
allAttributes.add(new SimpleEntry("forest", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("hypocrisi", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("breed", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("curs", "NUMERIC"));// = 0.6673 4 1 1 
allAttributes.add(new SimpleEntry("(like", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("basi", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("pardon", "NUMERIC"));// = 0.6673 4 1 1 
allAttributes.add(new SimpleEntry("behead", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("convent", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("bankrupt", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("credibl", "NUMERIC"));// = 0.6673 4 2 0 
allAttributes.add(new SimpleEntry("cheater", "NUMERIC"));// = 0.6673 4 1 1 
allAttributes.add(new SimpleEntry("taxpay", "NUMERIC"));// = 0.6673 4 1 1 
allAttributes.add(new SimpleEntry("illeg", "NUMERIC"));// = 0.6657 35 16 2 
allAttributes.add(new SimpleEntry("bloodi", "NUMERIC"));// = 0.6643 31 10 6 
allAttributes.add(new SimpleEntry("ruin", "NUMERIC"));// = 0.6606 23 8 4 
allAttributes.add(new SimpleEntry("crap", "NUMERIC"));// = 0.6545 15 5 3 
allAttributes.add(new SimpleEntry("hrc", "NUMERIC"));// = 0.6545 15 7 1 
allAttributes.add(new SimpleEntry("penc", "NUMERIC"));// = 0.6545 15 6 2 
allAttributes.add(new SimpleEntry("deplor", "NUMERIC"));// = 0.6522 33 15 3 
allAttributes.add(new SimpleEntry("excus", "NUMERIC"));// = 0.652 13 5 2 
allAttributes.add(new SimpleEntry("shame", "NUMERIC"));// = 0.6505 22 7 5 
allAttributes.add(new SimpleEntry("$", "NUMERIC"));// = 0.6488 11 4 2 
allAttributes.add(new SimpleEntry("trash", "NUMERIC"));// = 0.6488 11 2 4 
allAttributes.add(new SimpleEntry("polic", "NUMERIC"));// = 0.6483 20 11 0 
allAttributes.add(new SimpleEntry("zero", "NUMERIC"));// = 0.6443 9 4 1 
allAttributes.add(new SimpleEntry("steal", "NUMERIC"));// = 0.6425 23 9 4 
allAttributes.add(new SimpleEntry("rig", "NUMERIC"));// = 0.6375 7 4 0 


// posnegnu
allAttributes.add(new SimpleEntry("thine", "NUMERIC"));// = 1.0015 0 15 0 
allAttributes.add(new SimpleEntry("reportedli", "NUMERIC"));// = 1.0011 0 11 0 
allAttributes.add(new SimpleEntry("owen", "NUMERIC"));// = 1.0011 0 11 0 
allAttributes.add(new SimpleEntry("committe", "NUMERIC"));// = 1.0009 0 9 0 
allAttributes.add(new SimpleEntry("stephen", "NUMERIC"));// = 1.0009 0 9 0 
allAttributes.add(new SimpleEntry("31", "NUMERIC"));// = 1.0009 0 9 0 
allAttributes.add(new SimpleEntry("unmention", "NUMERIC"));// = 1.0009 0 9 0 
allAttributes.add(new SimpleEntry("obi", "NUMERIC"));// = 1.0009 0 9 0 
allAttributes.add(new SimpleEntry("prospect", "NUMERIC"));// = 1.0009 0 9 0 
allAttributes.add(new SimpleEntry("exo", "NUMERIC"));// = 1.0008 0 8 0 
allAttributes.add(new SimpleEntry("web", "NUMERIC"));// = 1.0008 0 8 0 
allAttributes.add(new SimpleEntry("lawsuit", "NUMERIC"));// = 1.0007 0 7 0 
allAttributes.add(new SimpleEntry("regul", "NUMERIC"));// = 1.0007 0 7 0 
allAttributes.add(new SimpleEntry("entranc", "NUMERIC"));// = 1.0007 0 7 0 
allAttributes.add(new SimpleEntry("juve", "NUMERIC"));// = 1.0007 0 7 0 
allAttributes.add(new SimpleEntry("pace", "NUMERIC"));// = 1.0007 0 7 0 
allAttributes.add(new SimpleEntry("scoreless", "NUMERIC"));// = 1.0007 0 7 0 
allAttributes.add(new SimpleEntry("conduct", "NUMERIC"));// = 1.0007 0 7 0 
// allAttributes.add(new SimpleEntry("gea'", "NUMERIC"));// = 1.0007 0 7 0 
allAttributes.add(new SimpleEntry("span", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("rai", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("oriol", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("batter", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("hunter", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("wade", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("4-3", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("verifi", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("leon", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("monument", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("humanitarian", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("mario", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("cannabi", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("cough", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("victor", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("bifurc", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("hardcor", "NUMERIC"));// = 1.0006 0 6 0 
allAttributes.add(new SimpleEntry("walkoff", "NUMERIC"));// = 1.0006 0 6 0 
// allAttributes.add(new SimpleEntry("NEGATIONWORD", "NUMERIC"));// = 0.9499 1748 2252 940 
allAttributes.add(new SimpleEntry("tanzania", "NUMERIC"));// = 0.9493 1 18 0 
allAttributes.add(new SimpleEntry("deadlin", "NUMERIC"));// = 0.9462 0 17 1 
allAttributes.add(new SimpleEntry("top20", "NUMERIC"));// = 0.9391 0 15 1 
allAttributes.add(new SimpleEntry("trends", "NUMERIC"));// = 0.9348 0 14 1 
allAttributes.add(new SimpleEntry("castro'", "NUMERIC"));// = 0.93 1 13 0 
allAttributes.add(new SimpleEntry("context", "NUMERIC"));// = 0.9244 0 12 1 
// allAttributes.add(new SimpleEntry("i", "NUMERIC"));// = 0.9216 1044 2099 1957 
allAttributes.add(new SimpleEntry("stan", "NUMERIC"));// = 0.9179 1 11 0 
allAttributes.add(new SimpleEntry("file", "NUMERIC"));// = 0.9153 0 21 2 
allAttributes.add(new SimpleEntry("wednesday'", "NUMERIC"));// = 0.9102 0 10 1 
allAttributes.add(new SimpleEntry("diego", "NUMERIC"));// = 0.9102 1 10 0 
allAttributes.add(new SimpleEntry("mumbai", "NUMERIC"));// = 0.9102 1 10 0 
allAttributes.add(new SimpleEntry("madrid'", "NUMERIC"));// = 0.9102 1 10 0 
allAttributes.add(new SimpleEntry("aleppo", "NUMERIC"));// = 0.903 3 27 0 
allAttributes.add(new SimpleEntry("wisconsin", "NUMERIC"));// = 0.902 2 18 0 
allAttributes.add(new SimpleEntry("resolut", "NUMERIC"));// = 0.901 0 9 1 
allAttributes.add(new SimpleEntry("hokag", "NUMERIC"));// = 0.901 0 9 1 
allAttributes.add(new SimpleEntry("varieti", "NUMERIC"));// = 0.901 0 9 1 
allAttributes.add(new SimpleEntry("foam", "NUMERIC"));// = 0.901 0 9 1 
allAttributes.add(new SimpleEntry("tonight'", "NUMERIC"));// = 0.901 0 9 1 
allAttributes.add(new SimpleEntry("luci", "NUMERIC"));// = 0.901 0 9 1 
allAttributes.add(new SimpleEntry("tenni", "NUMERIC"));// = 0.901 0 9 1 
allAttributes.add(new SimpleEntry("wyatt", "NUMERIC"));// = 0.901 1 9 0 
allAttributes.add(new SimpleEntry("msnbc", "NUMERIC"));// = 0.8898 1 8 0 
allAttributes.add(new SimpleEntry("cait", "NUMERIC"));// = 0.8898 0 8 1 
allAttributes.add(new SimpleEntry("foot", "NUMERIC"));// = 0.8898 1 8 0 
allAttributes.add(new SimpleEntry("bale", "NUMERIC"));// = 0.8898 0 8 1 
allAttributes.add(new SimpleEntry("uncl", "NUMERIC"));// = 0.8898 0 8 1 
allAttributes.add(new SimpleEntry("leadership", "NUMERIC"));// = 0.8841 1 15 1 
allAttributes.add(new SimpleEntry("upton", "NUMERIC"));// = 0.8766 1 14 1 
allAttributes.add(new SimpleEntry("mikheil", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("braun'", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("suplex", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("3b", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("billi", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("neymar", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("friday", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("philippin", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("meani", "NUMERIC"));// = 0.8758 1 7 0 
allAttributes.add(new SimpleEntry("witsel", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("workout", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("asia", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("roma", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("reflect", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("arsen", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("vital", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("farm", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("sacrific", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("co-lead", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("naruto'", "NUMERIC"));// = 0.8758 0 7 1 
allAttributes.add(new SimpleEntry("gea", "NUMERIC"));// = 0.8712 3 39 3 
allAttributes.add(new SimpleEntry("rbi", "NUMERIC"));// = 0.8686 0 32 5 
allAttributes.add(new SimpleEntry("six", "NUMERIC"));// = 0.8682 1 13 1 
allAttributes.add(new SimpleEntry("bench", "NUMERIC"));// = 0.8682 1 13 1 
allAttributes.add(new SimpleEntry("governor", "NUMERIC"));// = 0.8682 1 13 1 
allAttributes.add(new SimpleEntry("exchang", "NUMERIC"));// = 0.8585 2 12 0 
allAttributes.add(new SimpleEntry("eastern", "NUMERIC"));// = 0.8585 2 12 0 
allAttributes.add(new SimpleEntry("cabl", "NUMERIC"));// = 0.8578 0 6 1 
allAttributes.add(new SimpleEntry("quadrupl", "NUMERIC"));// = 0.8578 0 6 1 
allAttributes.add(new SimpleEntry("ric", "NUMERIC"));// = 0.8578 0 6 1 


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
