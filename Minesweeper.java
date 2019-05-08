/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;

/**
 *
 * @author archimede
 */
public class Minesweeper {
    int[][] grid= new int[9][9];
    int[][] visibility= new int[9][9];
    int[][] bombs;
    
    public Minesweeper(int[][] bombs) throws Exception{
        for (int[] coords : bombs) {
            if((coords[0] < 0 || coords[0] >= this.grid.length) || (coords[1] < 0 || coords[1] >= this.grid.length)){
                throw new Exception("Invalid coords");
                
            }
        }
        this.bombs = bombs;
        for (int[] coords : this.bombs) {
            int x = coords[0];
            int y = coords[1];
            ArrayList<int[]> near = this.getNear(x, y);
            // System.out.println("x:" + x +" y:" + y + " has near:" + near.size());
            for (int i = 0; i < near.size(); i++) { 		      
                int[] nearCoords = near.get(i); 
                this.grid[nearCoords[0]][nearCoords[1]]++;
                // near[i]
            } 
            /* for (int j = x-1; j <= x+1; j++) {
                if (j < 0 || j > 8) continue;
                for (int z = y-1 ; z <= y+1; z++) {
                    if (z < 0 || z > 8) continue;
                    this.grid[j][z]++;
                }
            }*/
        }
        for (int[] coords : this.bombs){
            int x = coords[0];
            int y = coords[1];
            this.grid[x][y] = -1;
        }
    }
    
    public boolean checkPosition(int x, int y){
        this.visibility[x][y]=1;
        for (int[] bomb : this.bombs) {
            if(bomb[0] == x && bomb[1] == y){ return true;}
        }
        if (this.grid[x][y] == 0) {
            this.showCeil(x, y);
        }
        return false;
    }
    
    public void showCeil(int x, int y) {
        ArrayList<int[]> near = this.getNear(x, y);
        for(int[] coords : near){
            if (this.visibility[coords[0]][coords[1]] == 1) continue;
            this.visibility[coords[0]][coords[1]] = 1;
            if (this.grid[coords[0]][coords[1]] == 0){
                this.showCeil(coords[0], coords[1]);
            }
        } 
    }
    
    public ArrayList<int[]> getNear(int x, int y){
        ArrayList a = new ArrayList<int[]>();
        for (int j = x-1; j <= x+1; j++) {
                if (j < 0 || j > 8) continue;
            for (int z = y-1 ; z <= y+1; z++) {
                if (z < 0 || z > 8 || (j==x && z == y)) continue;
                a.add(new int[]{j,z});
            }
        }
        return a;
    }

    @Override
    public String toString() {
        String s = "";
        
        for (int i=this.grid.length-1; i>=0; i--) {
            for (int j=0; j<this.grid[i].length; j++) {
                if (this.visibility[i][j] == 1) {
                    if ( this.grid[i][j] == -1) {
                        s+="| * |";
                    } else {
                         s+="| " + this.grid[i][j]+" |";
                    }
                } else {
                    s+="| # |";
                }
                
            }
            s+="\n";
        }
        
        s+="\n\n";
        
        return s;             
    }
  
    
    
}
