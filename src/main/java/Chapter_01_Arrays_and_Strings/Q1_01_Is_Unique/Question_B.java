package Chapter_01_Arrays_and_Strings.Q1_01_Is_Unique;

/**
 * 1.1 Is Unique
 * Implement an algorithm to determine if a string  has all unique characters.
 * What if you cannot use additional data structures ?
 */
public class Question_B {

    public static void main(String[] args) {
        String[] words = {"abcde", "hello", "apple", "kite", "padle"};
        for (String word : words) {
            System.out.println(word + ": " + isUniqueChars(word));
        }
    }

    /**
     * Using a Bit vector -  assuming the string only contains lowercase letters (a-z)
     *
     * The operator ‘<<‘  - is a left Bitwise shifter
     * The operator ‘|=’  - is the Bitwise OR assignment (x |=y is equivalent to x = x | y)
     * The operator ‘&=’  - is the Bitwise AND assignment (x &=y is equivalent to x = x & y)
     *
     * We have a Hash table which is being stored in 32 bit binary number every time the
     *      `checker` gets OR’d  `(checker |= (1 << val))` with the designated
     *      Binary value of a letter its corresponding bit it is being set to `true` or `1`
     *
     * The character’s value is AND’d with the `checker`  - `(checker & (1 << val) > 0)`
     *      - if it is greater than 0 we know  we have a duplicate - because
     *        two identical bits se to true AND’d together will return `true ` or  `1`
     *
     * We are assuming the string only contains lowercase letters (a-z) -
     *      there are 26 binary places each corresponding to a lowercase letter
     *      (So we have 6 more [of the 32 bit integer] place left to consume)
     *
     * 00000000000000000000000000000001 a 2^0
     * 00000000000000000000000000000010 b 2^1
     * 00000000000000000000000000000100 c 2^2
     * 00000000000000000000000000001000 d 2^3
     * ...
     * 00000001000000000000000000000000 y 2^24
     * 00000010000000000000000000000000 z 2^25
     *
     * @param str
     * @return
     */
    public static boolean isUniqueChars(String str){

        // if there are more than 26 characters (with only 26 possible lower alphabet characters)
        //      we can assume there are duplicates within the `str`
        if(str.length() > 26) {
            return false;
        }

        // create `checker` with 32 bit vector (ie int data type)
        int checker = 0;
        for(int i = 0; i < str.length(); i++){

            // get current char at index `i`
            int val = str.charAt(i);

            // compare the checker and current char at index `i` using `Bitwise AND`
            //      0 - no duplicates found ; 1 - duplicates found
            if((checker & (1 << val)) > 0){
                return false;
            }

            // else set the checker with the current char using `Bitwise OR`
            checker |= (1 << val);
        }

        // no duplicates found within the `str` ie the `str` is unique
        return true;
    }

    /**
     * STEP BY STEP SOLUTION :
     *
     * So for an input string “azya” -
     *
     * String “a” ::
     *
     * ‘a’      = 00000000000000000000000000000001 // 2^0
     * checker  = 00000000000000000000000000000000
     *
     * ‘a’ AND checker =  0  (no duplicates) // ie  checker & (1 << val) > 0)
     *
     * checker = ‘a’ OR checker;  //  ie (checker |= (1 << val))
     * // check now becomes = 00000000000000000000000000000001
     * checker = 00000000000000000000000000000001
     *
     *
     * String “az” ::
     *
     * checker  = 00000000000000000000000000000001
     * ‘z’ 	   =  00000010000000000000000000000000 // 2^25
     *
     * ‘z’ AND checker =  0 (no duplicates) // ie  checker & (1 << val) > 0)
     *
     * checker = ‘z’ OR checker;  //  ie (checker |= (1 << val))
     * // check now becomes = 00000010000000000000000000000001
     * checker = 00000010000000000000000000000001
     *
     *
     * String “azy” ::
     *
     * checker  = 00000010000000000000000000000001
     * ‘y’ 	   =  00000001000000000000000000000000 // 2^24
     *
     * ‘y’ AND checker =  0 (no duplicates) // ie  checker & (1 << val) > 0)
     *
     * checker = ‘y’ OR checker;  //  ie (checker |= (1 << val))
     * // check now becomes = 00000011000000000000000000000001
     * checker = 00000011000000000000000000000001
     *
     *
     * String “azya” ::
     *
     * checker  =  00000011000000000000000000000001
     * ‘a’ 	    =  00000000000000000000000000000001 // 2^1
     *
     * ‘a’ AND checker =  1 (we have a duplicate) // ie  checker & (1 << val) > 0)
     */
}
