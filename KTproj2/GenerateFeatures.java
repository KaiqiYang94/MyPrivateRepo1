import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class GenerteFeatures {

	// static List<String> matchList = new ArrayList<String>();
	static Map<String, Integer> allEmoji = new HashMap<String, Integer>();

	public static void main(String args[]) {
		try {
			Scanner scan = new Scanner(new File("test-tweets.txt"));
			//int i = 10;
			Map<String, Integer> attributes = new HashMap<String, Integer>();

			while (scan.hasNextLine()) {
				//if (i -- <= 0) break;

				String line = scan.nextLine();
				String[] array = line.split("\t");
				outputEmoji(array[1]);

				String[] words = array[1].split(" ");
				for (String word : words ) {
					if (attributes.containsKey(word.toLowerCase())) {
						int times = attributes.get(word.toLowerCase());
						attributes.put(word.toLowerCase(), times + 1);
					} else {
						attributes.put(word.toLowerCase(), 1);
					}
				}
				//System.out.println(line);
				//System.out.println("the id is " + array[0] + " content is " + array[1]);
				//Here you can manipulate the string the way you want
			}

			attributes = sortByValue(attributes);
			System.out.println();
			System.out.println(" Map Elements " + attributes.size());
			System.out.println("\t" + attributes);

			allEmoji = sortByValue(allEmoji);

			System.out.println(" Map Elements " + allEmoji.size());

			System.out.println("\t" + allEmoji);


			// for (int i = 0; i < matchList.size(); i++) {
			// 	System.out.println(i + ":" + matchList.get(i));
			// }

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

	public static void outputEmoji(String string) throws Exception {
		String face = ":)";


		String regexPattern = "[\uD83C-\uDBFF\uDC00-\uDFFF]";
		byte[] utf8 = string.getBytes("UTF-8");

		String string1 = new String(utf8, "UTF-8");

		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(string1);

		while (matcher.find()) {
			if (allEmoji.containsKey(matcher.group())){
				int times = allEmoji.get(matcher.group());
				allEmoji.put(matcher.group(), times + 1);
			} else {
				allEmoji.put(matcher.group(), 1);
			}
			// matchList.add(matcher.group());
		}

	}



}
