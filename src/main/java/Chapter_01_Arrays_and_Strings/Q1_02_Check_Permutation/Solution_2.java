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
 * Solution #2 : Check if the two strings have identical character counts
 */
public class Solution_2 {

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
     *  Using the Definition of a Permutation
     *      -  two words with the same character counts
     *
     *   Anagram
     *      - a word, phrase, or name formed by rearranging the letters of another, such as "spar", formed from "rasp".
     *
     *  Assuming size of character set is ASCII (0 to 127 ie 128 possible characters) :
     *  We create an array - that works similar to a hash table
     *      - mapping each character to its frequency
     *          - increment (count or frequency) through the first string
     *          - decrement (count or frequency) through the second string
     *      - if the strings are permutations
     *          then we'll have an array of zeros in the end
     *              - since the strings are of same length then we'll have incremented
     *                the same number of times we decremented (on the same values)
     *
     * We can terminate early if a value (or char count/frequency) ever turns negative
     *      ie the strings are not permutations
     *
     * @param s
     * @param t
     * @return
     */
    static boolean permutation(String s, String t){
        // Permutations must be same length
        if(s.length() != t.length()){
            return false;
        }

        // Assuming strings are ASCII with possible 0 to 127 ie 128 possible characters
        //      we create an array to map each ASCII characters to frequency
        int[] letters = new int[128];

        // increment character count/frequency through the first string
        for(int i = 0; i < s.length(); i++){
            letters[s.charAt(i)]++;
        }

        // decrement character count/frequency through the second string
        for(int i = 0; i < t.length(); i++){
            letters[t.charAt(i)]--;

            // terminate early if character count/frequency has turned negative
            //      - the strings, of similar length, are not permutations
            if(letters[i] < 0){
                return false;
            }
        }

        // the strings are permutations
        return true;
    }
}
