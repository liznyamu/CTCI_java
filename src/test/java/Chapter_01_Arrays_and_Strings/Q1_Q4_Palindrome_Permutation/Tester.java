package Chapter_01_Arrays_and_Strings.Q1_Q4_Palindrome_Permutation;

public class Tester {


    public static void main(String[] args) {
        String[] strings = {"Rats live on no evil star",
                "A man, a plan, a canal, panama",
                "Lleve",
                "Tacotac",
                "asda"};
        for (String s : strings) {
            boolean a = Solution_1.isPermutationOfPalindrome(s);
            boolean b = Solution_2.isPermutationOfPalindrome(s);
            boolean c = Solution_3.isPermutationOfPalindrome(s);
            System.out.println(s);
            if (a == b && b == c) {
                System.out.println("Agree: " + a);
            } else {
                System.out.println("Disagree: " + a + ", " + b + ", " + c);
            }
            System.out.println();
        }

    }

}