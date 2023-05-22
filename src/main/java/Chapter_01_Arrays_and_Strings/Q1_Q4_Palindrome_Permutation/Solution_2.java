package Chapter_01_Arrays_and_Strings.Q1_Q4_Palindrome_Permutation;

import static Chapter_01_Arrays_and_Strings.Q1_Q4_Palindrome_Permutation.Common.getCharNumber;

/**
 * 1.4 Palindrome Permutation
 *
 *      Given a string, write a function to check if it is a permutation of a palindrome.
 *      A palindrome is a word or phrase that is the same forwards and backwards. A permutation
 *      is a rearrangement of letters. The panlindrome does not need to be limited to just dictionary words.
 *
 *      EXAMPLE
 *      Input:  Tact Coa
 *      Output: True (permutations: "taco cat", "atco cta", etc.)
 *
 *
 * To write a set of characters the same way forwards and backwards (ie a palindrome)
 *
 *      We need to have:
 *          1. even number of almost all characters
 *              - so that half can be one side and half can be on the other side
 *          2. at most character (the middle character) to have an odd count
 *
 *      Eg "tactcoapapa" - is a permutation (re-arrangement) of a palindrome because it has
 *          2 - T's
 *          4 - A's
 *          2 - C's
 *          2 - P's
 *          1 - O - the `O` - would need to be at the center of all possible palindromes
 *
 *
 *  To be more precise, strings with even length (after removing all non-letter characters) must have all even
 *  counts of characters. Strings of an odd length must have exactly one character with an odd count. Of course, an
 *  "even" String can't have odd number of exactly one character, otherwise it wouldn't be an even-length string
 *  (an odd number + many even numbers = odd number). Likewise, a string with odd length can't have all characters
 *  with even counts (sum of evens is even). It's therefore sufficient to say that, to be a permutation of a palindrome,
 *  a string can have no more than one character that is odd. This will cover both odd and even cases.
 */

public class Solution_2 {

    public static void main(String[] args) {
        String pali = "Ratzs live on no evil starz";
        System.out.println(isPermutationOfPalindrome(pali));
        String pali2 = "Zeus was deified, saw Suez";
        System.out.println(isPermutationOfPalindrome(pali2));
    }

    /**
     * Instead of checking the number of odd counts at the end of, we can check as we go along. Then, as
     * soon as we get to end, we have an answer
     *
     * @param phrase
     * @return
     */
   static boolean isPermutationOfPalindrome(String phrase){
       // represent number/count/frequency of odd character counts we have on `phrase`
        int countOdd = 0;

        // create map with size 26 ie `a to z`
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];

        // Loop entire phrase
        for(Character c : phrase.toCharArray()){
            int val = getCharNumber(c);
            if(val != -1) {
                // ignore all ASCII (case-sensitive) characters not between `a and z`
                table[val]++;

                // check if count/frequency of `c` is odd or even
                if (table[val] % 2 == 1) {
                    // increase we have `countOdd` number of "odd" counts on `phrase`
                    countOdd++;
                } else {
                    // decrease we have `countOdd` number of "odd" counts on `phrase`
                    countOdd--;
                }
            }

        }

        // check if we don't have more than 1 character with odd count/frequency
        return countOdd <= 1;
    }


}
