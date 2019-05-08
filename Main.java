/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author archimede
 */
public class Main {
    
    public static void main(String args[]){
        int[][] bombs = new int[][]{{0,0}, {0, 4}, {2,2}, {2,3}, {4,4}, {6, 7}, {8, 8}, {3, 5}};
       
           
        try {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            Minesweeper m = new Minesweeper(bombs);
            while(true) {        
                System.out.println("Enter column for x");
                int x = myObj.nextInt();  // Read user input
                if (x == -1) break;
                System.out.println("Enter column for y");
                int y = myObj.nextInt();  // Read user input
                boolean lose = m.checkPosition(x, y);
                if (lose) break;
                System.out.println(m);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
    
}
