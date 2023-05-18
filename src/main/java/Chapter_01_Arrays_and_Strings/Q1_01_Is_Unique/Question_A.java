package Chapter_01_Arrays_and_Strings.Q1_01_Is_Unique;

/**
 * 1.1 Is Unique
 *      Implement an algorithm to determine if a string  has all unique characters.
 *      What if you cannot use additional data structures ?
 */
public class Question_A {

    public static void main(String[] args) {
        String[] words = {"abcde", "hello", "apple", "kite", "padle"};
        for (String word : words) {
            System.out.println(word + ": " + isUniqueChars(word));
        }
    }

    /**
     * Assuming we're using ASCII String (with 0-127 ie 128 possible characters)
     * instead of Extended ASCII String (with 0-255 ie  256 possible characters) OR Unicode String
     * (with 0–2^21 possible  -though not all numbers are currently assigned, and some are reserved))
     * @param str
     * @return
     */
    public static boolean isUniqueChars(String str){
        // Return false if string length exceeds the number of unique characters
        //      ie if string.length = 280 with 128 possible characters -
        //      we will see duplicated characters in the string

        if(str.length() > 128) {
            return false;
        }

        // create a flag array of 128 booleans
        boolean[] char_set = new boolean[128];

        // loop each character on the string
        for(int i = 0; i<str.length(); i++){

            // Java char to int conversion using Character.getNumericValue()
            //      get int (ASCII) value conversion eg 'A' = 95, 'Z' = 90
            int val = str.charAt(i);

            // check if flag at index `val` is unique or not
            if(char_set[val]) {
                return false;
            }

            // else set ASCII character has been set on `str`
            char_set[val] = true;

        }

        // then String includes unique characters only
        return true;
    }
}
