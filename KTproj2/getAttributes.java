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
			//int i = 10;
			Map<String, Integer> attributes = new HashMap<String, Integer>();

			while (scan.hasNextLine()) {

				String line = scan.nextLine();
				String[] array = line.split("\t");
				outputEmoji(array[1]);

				String[] words = array[1].split(" ");
				for (String word : words ) {
					AddToCount(attributes, word);
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
				System.out.println("Get a face" + face + " " + str);

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



}
