package CSES_Problem_SET.Graph;

import java.util.ArrayList;
import java.util.Scanner;

public class Queen_4 {
    public static boolean check(ArrayList<int []>list,int i, int j){
        for(int k[]: list){
            int a=k[0];
            int b=k[1];
            if(a==i||b==j||Math.abs(a - i) == Math.abs(b - j)){
                return false;
            }
        }
        return true;
    }
    public static int solve(int grid[][], ArrayList<int []> list,int Queen){
        if(Queen==0)return 1;

        int ans=0;

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid.length; j++){
                if(check(list,i,j)){
                    list.add(new int[]{i,j});
                    ans+=solve(grid,list,Queen-1);
                    list.remove(list.size()-1);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        //we have to arrange 4 Queen onto the chessboard

        int grid[][] = new int[n][n];


        ArrayList<int []> list = new ArrayList<>();

        System.out.println(solve(grid,list,2)/2);//it give 8me total arrangement of qeens when we divide with factorail give ans

    }
}
