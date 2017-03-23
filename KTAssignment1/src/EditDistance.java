
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;
import java.util.Dictionary;

/**
 * Computes the edit distance between pairs of words.  Can be used for applications
 * like finding near-match names in Kevin Bacon or spelling correction.
 * 
 * There are two versions: a recursive version and a dynamic programming version 
 * that memoizes the function by storing previously solved problems in a map.
 * 
 * @author Scot Drysdale
 */
public class EditDistance {
  private Map<SimpleEntry<String, String>, Integer> solvedProblems = new HashMap<SimpleEntry<String,String>, Integer>();
  
  
  /**
   * Computes the edit distance between two strings.  The valid operations are:
   *   1) Insert a character
   *   2) Delete a character
   *   3) Replace a character
   *   4) Twiddle (Swap two characters to match the output).
   *  
   * This version is memoized to avoid re-solving problems.
   *   
   * @param s1 The source string
   * @param s2 The destination string
   * @return the number of edit operations to turn s1 into s2
   */
  public int memoizedEditDist(String s1, String s2) {
    //solvedProblems = new HashMap<StringPair, Integer>();
    
    return editDist(s1, s2);
  }
  
  
  /**
   * A helper function for memoizedEditDistance that uses a Map
   * to keep track of problems that have already been solved.
   * 
   * @param s1 The source string
   * @param s2 The destination string
   * @return the number of edit operations to turn s1 into s2
   */
  private int editDist(String s1, String s2) {
	  int i = 1;
	  int r = 1;
	  int d = 1;
	  int m = 0;
	  
	  
    int matchDist;   // Edit distance if first char. match or do a replace
    int insertDist;  // Edit distance if insert first char of s1 in front of s2.
    int deleteDist;  // Edit distance if delete first char of s2.
    //int swapDist;    // edit distance for twiddle (first 2 char. must swap).
    
    if(s1.length() == 0)
      return s2.length();   // Insert the remainder of s2
    else  if (s2.length()== 0)
      return s1.length();   // Delete the remainder of s1
    else {
      SimpleEntry<String, String> pair =new SimpleEntry<String, String>(s1, s2);
      Integer result = solvedProblems.containsKey(pair)? solvedProblems.get(pair): null;
      
      if(result != null)  // Did we find the subproblem in the map?
        return result;    // If so, return the answer
      else {
        matchDist = editDist(s1.substring(1), s2.substring(1));
        if(s1.charAt(0) != s2.charAt(0)){
        	matchDist = matchDist + r;  // If first 2 char. don't match must replace
          }
        else{
        	matchDist = matchDist + m;
        }
        insertDist = editDist(s1.substring(1), s2) + i;
        deleteDist = editDist(s1, s2.substring(1)) + d;

//        if(s1.length() > 1 && s2.length() > 1 && 
//            s1.charAt(0) == s2.charAt(1) && s1.charAt(1) == s2.charAt(0)) 
//          swapDist = editDist(s1.substring(2), s2.substring(2)) + 1;
//        else
//          swapDist = Integer.MAX_VALUE;  // Can't swap if first 2 char. don't match
        
        int dist = Math.min(matchDist, Math.min(insertDist, deleteDist /*Math.min(deleteDist, swapDist)*/));

        solvedProblems.put(pair, dist);  // Save the result for future
        
        return dist;
      }
    }
  }
  
  /**
   * Testing program
   */
  public static void main(String [] args) {
    Scanner input = new Scanner(System.in);
    String s1, s2;    // The strings to find the edit distance between
    EditDistance calc = new EditDistance();
    
    System.out.println("Enter two strings, one per line");
    s1 = input.nextLine();
    s2 = input.nextLine();
    
    while(s1.length() > 0) {
      
      System.out.println("The edit distance between \"" + s1 + "\" and \"" + s2 +
          "\" is " + calc.memoizedEditDist(s1,s2));
      System.out.println();
      
      System.out.println("Enter two strings, one per line");
      s1 = input.nextLine();
      s2 = input.nextLine();
    }    
  }
}