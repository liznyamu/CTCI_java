package Chapter_01_Arrays_and_Strings.Q1_02_Check_Permutation;

/**
 * 1.2 Check Permutation
 *      Given two strings, write a method to decide id one is a permutation of the other
 *
 *  We're assuming that the comparison is case-sensitive and whitespace is significant
 *      ie Permutation (re-arrangement) of "God " can be " doG" and not "dog"
 *
 *  - We first observe that strings of different lengths can not be permutations of each other
 *
 */

/**
 * Solution #1 : Sort the strings
 *
 *  If two strings are permutations - then they have the same characters, but in different orders
 *      Therefore we can sort the strings, in same order, then compare them
 */
public class Solution_1 {

    public static void main(String[] args) {
        String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean anagram = permutation(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + anagram);
        }
    }

    /**
     * Sort the string ([] char) into ascending numerical order
     * @param str
     * @return
     */
    static String sort(String str){
        char[] content = str.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }

    static boolean permutation(String s, String t){
        // not permutations - if the pair of strings have different lengths
        if(s.length() != t.length()){
            return false;
        }

        // check if sorted strings are equal:
        //  true - if they are permutations (else not)
        return sort(s).equals(sort(t));
    }
}
