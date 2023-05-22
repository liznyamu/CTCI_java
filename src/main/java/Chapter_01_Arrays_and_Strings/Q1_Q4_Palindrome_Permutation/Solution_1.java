package Chapter_01_Arrays_and_Strings.Q1_Q4_Palindrome_Permutation;

import static Chapter_01_Arrays_and_Strings.Q1_Q4_Palindrome_Permutation.Common.getCharNumber;

/**
 * 1.4 Palindrome Permutation
 * <p>
 * Given a string, write a function to check if it is a permutation of a palindrome.
 * A palindrome is a word or phrase that is the same forwards and backwards. A permutation
 * is a rearrangement of letters. The panlindrome does not need to be limited to just dictionary words.
 * <p>
 * EXAMPLE
 * Input:  Tact Coa
 * Output: True (permutations: "taco cat", "atco cta", etc.)
 * <p>
 * <p>
 * To write a set of characters the same way forwards and backwards (ie a palindrome)
 * <p>
 * We need to have:
 * 1. even number of almost all characters
 * - so that half can be one side and half can be on the other side
 * 2. at most character (the middle character) to have an odd count
 * <p>
 * Eg "tactcoapapa" - is a permutation (re-arrangement) of a palindrome because it has
 * 2 - T's
 * 4 - A's
 * 2 - C's
 * 2 - P's
 * 1 - O - the `O` - would need to be at the center of all possible palindromes
 * <p>
 * <p>
 * To be more precise, strings with even length (after removing all non-letter characters) must have all even
 * counts of characters. Strings of an odd length must have exactly one character with an odd count. Of course, an
 * "even" string can't have odd number of exactly one character, otherwise it wouldn't be an even-length string
 * (an odd number + many even numbers = odd number). Likewise, a string with odd length can't have all characters
 * with even counts (sum of evens is even). It's therefore sufficient to say that, to be a permutation of a palindrome,
 * a string can have no more than one character that is odd. This will cover both odd and even cases.
 */

public class Solution_1 {
    public static void main(String[] args) {
        String palindrome = "Rats live on no evil star";
        System.out.println(isPermutationOfPalindrome(palindrome));
    }

    /**
     * Solution:
     * We need a hash table to count how many times each character appears. Then iterate through the hash table to ensure
     * that no more than one character has an odd count
     * <p>
     * Assumption
     * - input has lower case ASCII text   (ie  This is case-sensitive)
     * - ignore all ASCII codes not between 'a' and 'z' (ie Non-letter characters mapped to -1)
     *
     * @param phrase
     * @return
     */
    static boolean isPermutationOfPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    /**
     * Check that no more than ONE character has and odd count
     *
     * @param table
     */
    static boolean checkMaxOneOdd(int[] table) {
        boolean foundOddCount = false;
        for (int count : table) {
            if (count % 2 == 1) {
                if (foundOddCount) {
                    return false; // more than 1 character counts/frequencies has an odd count
                }
                foundOddCount = true;
            }
        }
        return true; // all character counts/frequencies are even
    }

    /**
     * Count how many times each character between 'a' and 'z' appears
     *
     * @param phrase
     * @return
     */
    static int[] buildCharFrequencyTable(String phrase) {
        // create a map/array of length 26 ie `a to z`
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (Character c : phrase.toCharArray()) {
            int val = getCharNumber(c);
            if (val != -1) {
                table[val]++;
            }
        }
        return table;
    }



}
