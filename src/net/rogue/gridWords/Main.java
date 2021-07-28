
package net.rogue.gridWords;

/*
* You are given a grid of characters and a list of words.
* For each word in the list print "Yes" if it appears in the grid, otherwise print "No".
* The word may appear as a substring in the grid and can appear horizontally or vertically, forward or backward.
* */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        String filename = "/gridWords.txt";
        InputStream is = net.rogue.countPalindromes.Main.class.getResourceAsStream(filename);
        if(is == null) {
            System.out.printf("file not found: %s\n", filename);
            return;
        }
        Scanner s = new Scanner(is);
        int n = Integer.parseInt(s.next());
        String[][] grid = new String[n][n];
        for(int i = 0; i < n * n; i++) {
            grid[i / n][i % n] = s.next();
        }
        ArrayList<String> words = new ArrayList<>();
        n = Integer.parseInt(s.next());
        s.nextLine();
        for(int i = 0; i < n; i++) {
            words.add(s.nextLine());
        }
        solution(grid, words);
    }
    
    public static void solution(String[][] grid, ArrayList<String> words) {
        HashSet<String> gridWords = getWords(grid);
        for(String word : words) {
            if(checkWord(gridWords, word)) System.out.print("Yes ");
            else System.out.print("No ");
        }
    }
    
    public static HashSet<String> getWords(String[][] grid) {
        HashSet<String> words = new HashSet<>();
        for(int y = 0; y < grid.length; y++) {
            StringBuilder horizontal = new StringBuilder();
            StringBuilder vertical = new StringBuilder();
            for(int x = 0; x < grid.length; x++) {
                horizontal.append(grid[y][x]);
                vertical.append(grid[x][y]);
            }
            words.add(horizontal.toString());
            words.add(vertical.toString());
            words.add(horizontal.reverse().toString());
            words.add(vertical.reverse().toString());
        }
        return words;
    }
    
    public static boolean checkWord(HashSet<String> words, String word) {
        return words.stream().anyMatch(w -> w.contains(word));
    }
    
}
