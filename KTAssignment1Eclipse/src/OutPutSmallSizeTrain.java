
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.xml.internal.stream.Entity;

import sun.org.mozilla.javascript.internal.ast.WhileLoop;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Computes the edit distance between pairs of words. Can be used for
 * applications like finding near-match names in Kevin Bacon or spelling
 * correction.
 * 
 * There are two versions: a recursive version and a dynamic programming version
 * that memoizes the function by storing previously solved problems in a map.
 * 
 * @author Scot Drysdale
 */
public class OutPutSmallSizeTrain {

	/**
	 * Testing program
	 */
	public static void main(String[] args) {

		try {
			// the names.txt
			FileInputStream fstream = new FileInputStream("train.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if (Math.random() < 0.01) {
					System.out.println(strLine);
				}

			}

			// Close the input stream
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
