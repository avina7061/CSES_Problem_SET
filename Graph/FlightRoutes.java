//package CSES_Problem_SET.Graph;
//
//import CP.Contest.Hello2026.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FlightRoutes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        ArrayList<ArrayList<int[]>> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a=sc.nextInt();
            int b=sc.nextInt();
            int c=sc.nextInt();
            list.get(a-1).add(new int[]{b-1,c});
        }

        ArrayList<Long> ans = new ArrayList<>();
        boolean path[] = new boolean[n];
        solve(ans,list,0,n-1,path,0);

        Collections.sort(ans);
        for (int i=0; i<k; i++){
            System.out.print(ans.get(i)+" ");
        }
        System.out.println();
    }
    public static void solve(ArrayList<Long>ans,ArrayList<ArrayList<int []>>list,int start,int end,boolean path[], long cost){
        path[start]=true;
        if(start==end){
            ans.add(cost);
            path[end]=false;
            return ;
        }

        for (int i[]: list.get(start)){
            if(path[i[0]])continue;
            solve(ans,list,i[0],end,path,cost+i[1]);
        }

        path[start]=false;
    }
}
