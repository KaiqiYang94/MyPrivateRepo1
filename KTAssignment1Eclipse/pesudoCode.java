



private float EditDistance(String s1, String s2) {

	// match from char to string
	float matchCharStrDist = EditDistance(s1.substring(1), s2.substring(2))
	                         + GetReplaceDistance(s1.substring(0, 1), s2.substring(0, 2));

	// match from string to char
	float matchStrCharDist = EditDistance(s1.substring(2), s2.substring(1))
	                         + GetReplaceDistance(s1.substring(0, 2), s2.substring(0, 1));

	// match on char
	float matchCharDist = EditDistance(s1.substring(1), s2.substring(1))
	                      + isMatching(s1.charAt(0), s2.charAt(0))
	                      ? GetMatchDistance() : GetReplaceDistance(s1.charAt(0), s2.charAt(0));

	float deleteDist = EditDistance(s1.substring(1), s2) + GetDeleteDistance(s1.charAt(0));

	float insertDist = EditDistance(s1, s2.substring(1)) + GetInsertDistance(s2.charAt(0));

	return Min(matchCharDist, insertDist, deleteDist, matchCharStrDist, matchStrCharDist);
}