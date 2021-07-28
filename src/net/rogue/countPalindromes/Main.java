
package net.rogue.countPalindromes;

/*
 * Given a string return how many unique palindromes appear within all substrings.
 * Single characters count as a palindrome
 * */

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        String filename = "/countPalindromes.txt";
        InputStream is = Main.class.getResourceAsStream(filename);
        if(is == null) {
            System.out.printf("file not found: %s\n", filename);
            return;
        }
        Scanner s = new Scanner(is);
        while(s.hasNext()) {
            String str = s.next();
            int p = Integer.parseInt(s.next());
            test(str, p);
        }
    }
    
    public static boolean checkPalindrome(String s) {
        int middle = s.length() / 2;
        StringBuilder reversed = new StringBuilder(s.substring(s.length() - middle));
        reversed.reverse();
        return s.substring(0, middle).equals(reversed.toString());
    }
    
    public static int solution(String s) {
        HashSet<String> tested = new HashSet<>();
        int count = 0;
        // iterate through all possible lengths of substrings
        for(int length = 1; length < s.length() + 1; length++) {
            // iterate through all starting positions for substring
            for(int i = 0; i + length < s.length() + 1; i++) {
                String substring = s.substring(i, i + length);
                if(tested.contains(substring)) continue; // if reoccurring string skip it
                tested.add(substring);
                if(checkPalindrome(substring)) count++;
            }
        }
        return count;
    }
    
    public static void test(String s, int p) {
        System.out.printf("Testing string (expected output %d):\n%s\n", p, s);
        int result = solution(s);
        if(result == p) System.out.println("Test PASSED\n");
        else System.out.printf("Tested FAILED\noutput: %d\n\n", result);
    }
    
}
