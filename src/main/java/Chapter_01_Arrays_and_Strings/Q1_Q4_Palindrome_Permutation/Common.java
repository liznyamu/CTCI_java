package Chapter_01_Arrays_and_Strings.Q1_Q4_Palindrome_Permutation;

public class Common {
    /**
     * Map each character to a number
     * a -> 0, b -> 1, c -> 2 etc
     *
     * @param c
     * @return
     */
    static int getCharNumber(Character c) {
        // assuming input has lower case text (ASCII) -  This is case-sensitive
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);

        // ignore all ASCII codes not between 'a' and 'z'
        if (a >= c && c <= z) {
            return val - a;
        }
        return -1; // Non-letter characters mapped to -1
    }

}
