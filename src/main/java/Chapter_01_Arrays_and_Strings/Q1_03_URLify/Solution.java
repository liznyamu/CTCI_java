package Chapter_01_Arrays_and_Strings.Q1_03_URLify;

/**
 * 1.3 URLify
 *      Write a method to replace all spaces in a string with "%20".
 *          You may assume that the string has sufficient space at the end to hold all the additional characters, and that
 *          you are given the "true" length of the string.
 *
 *       (Note: if implementing in Java, please use a character array so that you can perform this operation in place)
 *
 *       EXAMPLE
 *          Input :   "Mr John Smith      ", 13
 *          Output:   "Mr%20John%20Smith"
 */
public class Solution {

    /**      Note: Java Strings are immutable
     *          We have implemented this problem using character arrays, because Java strings are immutable.
     *              If we used strings directly, the function would have to return a new copy of the string, but it would
     *              allow us to implement this in just one pass
     *
     *     Note: Null Terminated Strings `\0`
     *          A String is defined to be a fixed length sequence of char values
     *              All possible char values (from 0 to 65535 - char is 16-bit integer representing a Unicode-encoded character)
     *                  may be used in a String.
     *              There is no "distinguished" value that means that the string ends.
     *                  - So how they track string ending? Using length
     *
     *          The \0 in this case converts to 0o0 in octal, when this is converted to ASCII , reads as no string at all
     *          This suggests in Java \0 is not used to null terminate a string,
     *              and further research shows Java doesn't use anything to null terminate strings,
     *              as the length of the string is enough to tell it that it has reached the end of the string
     */

    public static void main(String[] args) {
        String str = "Mr John Smith    ";
        char[] arr = str.toCharArray();
        int trueLength = findIndexOfLastCharacter(arr) + 1;
        replaceSpaces(arr, trueLength);
        //        System.out.println("\"" + AssortedMethods.charArrayToString(arr) + "\"");
        System.out.println("\"" + String.valueOf(arr) + "\"");
    }

    /**
     * Get the "true" length of the string
     *      Since we are given a string with `sufficient space at the end to hold all the additional characters`
     *      we need to get the "true" length of the string
     *          Input : "Mr John Smith      "   // length = 13 + 2(2) = 17
     *          Output: "Mr John Smith"         // length = 13
     *
     * @param str
     * @return
     */
    static int findIndexOfLastCharacter(char[] str){
        for(int i = str.length-1; i >=0; i--){
            // Get the index of the last non-space character
            if(str[i] != ' '){
                return i;
            }
        }
        return -1;
    }

    /**
     *      Common approach for string manipulation is
     *          to edit the string starting from the end and working backwards
     *               - Since we have an extra buffer at the end, this approach allows us to change characters without
     *                 over-writing them
     *
     *      We'll use a 2-scan approach:
     *          First scan
     *              - count number of spaces
     *              - triple this count to compute how many extra character (ie 2 chars) we will have in the final string
     *          Second scan / pass (in reverse order)
     *              - we edit the string
     *                  - if there's a space: we replace it with "%20"
     *                  - else : we copy the original character
     */
    static void replaceSpaces(char[] str, int trueLength){
        // First scan
        //      - count number of spaces
        //      - triple this count to compute how many extra character (ie 2 chars) we will have in the final string
        int spaceCount = 0, i = 0, index = 0;
        for(i = 0; i < trueLength; i++){
            if(str[i] == ' '){
                spaceCount++;
            }
        }
        index = trueLength + spaceCount*2; // add 2 more chars for each `spaceCount`

        // Second scan / pass (in reverse order)
        //      - we edit the string
        //          - if there's a space: we replace it with "%20"
        //          - else : we copy the original character
        for(i = trueLength - 1 ; i >=0; i--){
            if(str[i] == ' '){
                // if there's a space: we replace it with "%20" - in reverse order
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index -= 3; // left shift in index by 3 chars
            } else{
                // else: we copy the original character
                str[index - 1] = str[i];
                index--; // left shift in index by 1 char
            }
        }

    }

}
