
package net.rogue.playerLevelCount;

/*
* Given a sorted list of player scores and a rank K, return the amount of players that can level up.
* In order for a player to level up they must score more than 0 and rank at or above rank K.
* Players with tied scores tie for that rank, make sure to skip the amount of ranks for tied players.
* Ex. Given the scores [100, 99, 99, 85]
*   The player at rank 1 scored 100 while two players tied for rank 2. The player scoring 85 is considered rank 4.
* */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    
    public static int rank;
    public static int players;
    
    public static void main(String[] args) {
        String filename = "/playerLevelCount.txt";
        InputStream is = net.rogue.countPalindromes.Main.class.getResourceAsStream(filename);
        if(is == null) {
            System.out.printf("file not found: %s\n", filename);
            return;
        }
        Scanner s = new Scanner(is);
        while(s.hasNext()) {
            Scanner s2 = new Scanner(s.nextLine());
            int k = Integer.parseInt(s2.next());
            ArrayList<Integer> scores = new ArrayList<>();
            while(s2.hasNext()) {
                scores.add(Integer.parseInt(s2.next()));
            }
            System.out.print("List: [");
            printList(scores);
            System.out.printf("] K: %d\n", k);
            System.out.printf("%d players may level up\n", solution(scores, k));
        }
    }
    
    public static int solution(ArrayList<Integer> scores, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        for(int score : scores) {
            if(score == 0) continue;
            int n = map.getOrDefault(score, 0);
            map.put(score, n + 1);
        }
        rank = 1;
        players = 0;
        map.forEach((key, value) -> {
            if(rank > k) return;
            players += value;
            rank += value;
        });
        return players;
    }
    
    public static void printList(List<Integer> list) {
        list.forEach(x -> System.out.print(x + " "));
    }
    
}
