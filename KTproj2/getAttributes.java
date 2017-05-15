import java.util.*;
import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class getAttributes {

	// static List<String> matchList = new ArrayList<String>();
	static Map<String, Integer> allEmoji = new HashMap<String, Integer>();

	public static void main(String args[]) {
		try {

			Scanner scan = new Scanner(new File("dev-tweets.txt"));

			Map<String, Integer> attributes = new HashMap<String, Integer>();

			while (scan.hasNextLine()) {

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

				outputEmoji(array[1]);

				String[] words = array[1].split(" ");
				for (String word : words ) {
					if (word.length() <= 0) {continue;}
					// filter out mentions
					if (word.charAt(0) == '@') {continue;}
					// filter out topics
					if (word.charAt(0) == '#') {continue;}
					// filtrt out https
					if (word.indexOf("http") == 0 ) {continue;}
					if (Arrays.asList(stopWords).contains(word.toLowerCase())) {continue;}

					AddToCount(attributes, word.toLowerCase().replace(":", ""));

					if (Arrays.asList(negationWords).contains(word.toLowerCase())) {
						AddToCount(attributes, "NEGATIONWORD");

					}


				}
			}

			attributes = sortByValue(attributes);
			System.out.println();
			System.out.println(" Map Elements " + attributes.size());
			System.out.println("\t" + attributes);

			allEmoji = sortByValue(allEmoji);

			System.out.println(" Map Elements " + allEmoji.size());

			System.out.println("\t" + allEmoji);
		} catch (Exception e) {
			e.printStackTrace();
			//error handling code
		}
	}

	// to sort the map
	public static <K, V extends Comparable<? super V>> Map<K, V>
	sortByValue( Map<K, V> map ) {
		List<Map.Entry<K, V>> list =
		    new LinkedList<Map.Entry<K, V>>( map.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		} );

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put( entry.getKey(), entry.getValue() );
		}
		return result;
	}

	public static void outputEmoji(String str) throws Exception {
		// String face = ":)";
		String[] faces = {":-)", ":)", ":D", ":-D", ":P", ":-P", ":(", ":-/", ":/", ";)", "LOL", "HAHA"};

		for (String face : faces) {
			if (str.toUpperCase().contains(face)) {
				AddToCount(allEmoji, face);
				// System.out.println("Get a face" + face + " " + str);
			}
		}


		String regexPattern = "[\uD83C-\uDBFF\uDC00-\uDFFF]";
		byte[] utf8 = str.getBytes("UTF-8");

		String string1 = new String(utf8, "UTF-8");

		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(string1);

		while (matcher.find()) {
			AddToCount(allEmoji, matcher.group());
		}

	}

	public static void AddToCount(Map<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			int times = map.get(key);
			map.put(key, times + 1);
		} else {
			map.put(key, 1);
		}
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

		// "See",
		// "Unless", "Due", "Also", "Must", "Might", "Like", "Will", "May", "Can", "Much",
		// "Every", "The", "In", "Other", "This", "The", "Many", "Any", "An", "Or", "For",
		// "In", "An", "An ", "Is", "A", "About", "Above", "After", "Again", "Against",
		// "All", "Am", "An", "And", "Any", "Are",  "As", "At", "Be", "Because",
		// "Been", "Before", "Being", "Below", "Between", "Both", "But", "By",
		// "Could", "Did",  "Do", "Does",  "Doing",
		// "Down", "During", "Each",  "For", "From", "Further", "Had",
		// "Has",  "Have",  "Having", "He", "He'd", "He'll", "He's",
		// "Her", "Here", "Here's", "Hers", "Herself", "Him", "Himself", "His", "How",
		// "How's", "I ", " I", "I'd", "I'll", "I'm", "I've", "If", "In", "Into", "Is",
		// "Isn't", "It", "It's", "Its", "Itself", "Let's", "Me", "More", "Most", "Mustn't",
		// "My", "Myself",  "Of", "Off", "On", "Once", "Only", "Ought",
		// "Our", "Ours", "Ourselves", "Out", "Over", "Own", "Same",  "She", "She'd",
		// "She'll", "She's", "Should",  "So", "Some", "Such", "Than", "That",
		// "That's", "Their", "Theirs", "Them", "Themselves", "Then", "There", "There's",
		// "These", "They", "They'd", "They'll", "They're", "They've",
		// "This", "Those", "Through", "To", "Too", "Under", "Until", "Up", "Very", "Was",
		// "Wasn't", "We", "We'd", "We'll", "We're", "We've", "Were", "Weren't", "What",
		// "What's", "When", "When's", "Where", "Where's", "Which", "While", "Who", "Who's", "Whom",
		// "Why", "Why's", "With", "Would",  "You", "You'd", "You'll",
		// "You're", "You've", "Your", "Yours", "Yourself", "Yourselves"
	};
}
