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

public class Solution_3 {

    public static void main(String[] args) {
        String pali = "Rats live on no evil star";
        System.out.println(isPermutationOfPalindrome(pali));
    }

    /**
     * Thinking more deeply about the problem, we see don't actually need to know the counts.
     *
     * We just need to know if the count is "even" or "odd". Think of flipping a light switch "on/off"
     * (that is initially off).  If the light winds up "off", we don't know how many times we flipped it, but we know
     * the count is even.
     *
     * Solution: (using Bit vectors and Bit-masking)
     *      Given this, we can use a single integer (as a bit vector). When we see a letter, we map it to an integer
     *      between 0 and 26 (assuming an English alphabet). Then we toggle the bit at that value. At the end of the
     *      iteration, we check that at most one bit in the integer is set to 1.
     *
     *      We can easily check that no bits in the integer are 1:
     *          Just compare the integer to 0.
     *
     *      To check that an integer has exactly one bit set to 1.
     *
     *      Picture an integer like `00010000` :
     *          We could shift the integer repeatedly to check that there's only a single `1`
     *                      OR
     *          Alternatively, if we subtract `1` from the number, we'll get `00001111`
     *          What's notable is that there's no overlap between the numbers (as opposed to `00101000` which when we
     *          subtract from '1' we get `00100111`)
     *
     *          So, we can check to see a number has exactly one bit set to `1` because if we subtract `1` from it and
     *          then "AND" it with the new number we should get 0.
     *
     *          00010000 - 1 = 00001111
     *          00010000 & 00001111 = 0
     *
     * @param phrase
     * @return
     */
    static boolean isPermutationOfPalindrome(String phrase){
        int bitVector = createBitVector(phrase);
        // check whether we have only even character counts
        //          OR
        // we have only 1 odd character count
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    /**
     * Toggle the ith bit in the integer
     * @param bitVector
     * @param index
     * @return
     */
    static int toggle(int bitVector, int index){
        // ignore all ASCII (case-sensitive) characters not between `a and z`
        if(index < 0 ) return bitVector;

        // TODO need better understanding of this
        int mask = 1 << index;
        if((bitVector & mask) == 0){
            // if there's no overlap of bits on the `mask` and `bitVector`
            // perform Bitwise `OR` with the `mask`
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }


    /**
     * Create a bit vector for the string. For each letter with value 1, toggle the ith bit
     *
     * @param phrase
     * @return
     */
    static int createBitVector(String phrase){
        int bitVector = 0;
        for (char c : phrase.toCharArray()){
            int index = getCharNumber(c);
            bitVector = toggle(bitVector, index);
        }
        return  bitVector;
    }

    /**
     * Check that exactly one bit is set by subtracting one from the integer and
     * ANDing it with the original integer
     *
     * @param bitVector
     * @return
     */
    static boolean checkExactlyOneBitSet(int bitVector){
        return (bitVector & (bitVector - 1)) == 0;
    }

}
