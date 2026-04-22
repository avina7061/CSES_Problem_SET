//package CSES_Problem_SET.Graph;

import java.util.Arrays;
import java.util.Scanner;

public class ShortestRoute2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int m=sc.nextInt();
        int q=sc.nextInt();

        long dist[][] = new long[n][n];
        for(long it[]: dist){
            Arrays.fill(it,Long.MAX_VALUE);
        }
        for(int i=0; i<n; i++)dist[i][i]=0;

        for(int i=0; i<m; i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            int c=sc.nextInt();
            dist[a-1][b-1]=Math.min(dist[a-1][b-1],c);
            dist[b-1][a-1]=Math.min(dist[b-1][a-1],c);
        }


        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(dist[i][k]!=Long.MAX_VALUE&&dist[k][j]!=Long.MAX_VALUE){
                        dist[i][j]=Math.min(dist[i][k]+dist[k][j],dist[i][j]);
                    }
                }
            }
        }

        for(int i=0; i<q; i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            if(dist[a-1][b-1]!=Long.MAX_VALUE){
                System.out.println(dist[a-1][b-1]);
            }
            else{
                System.out.println(-1);
            }
        }

    }
}
