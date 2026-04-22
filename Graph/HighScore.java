//package CSES_Problem_SET.Graph;


//import CP.Contest.Hello2026.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HighScore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<int[]>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(new ArrayList<>());
        ArrayList<int[]> edges = new ArrayList<>();
        boolean bt=false;
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            list.get(a - 1).add(new int[]{b - 1, c});
            edges.add(new int[]{a - 1, b - 1, c});
        }

        long dist[] = new long[n];
        Arrays.fill(dist, Long.MIN_VALUE);
        dist[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int ac[] : edges) {
                int a = ac[0];
                int b = ac[1];
                int c = ac[2];
                if (dist[a] == Long.MIN_VALUE) continue;

                if (dist[a] + c > dist[b]) {
                    dist[b] = dist[a] + c;
                }
            }
        }

        long finalCost = dist[n - 1];
//        System.out.println(finalCost);
        for (int i = 0; i < n; i++) {
            for (int ac[] : edges) {
                int a = ac[0];
                int b = ac[1];
                int c = ac[2];
                if (dist[a] == Long.MIN_VALUE) continue;

                if (dist[a] == Long.MAX_VALUE) {
                    dist[b] = Long.MAX_VALUE;
                }

                if (dist[a] + c > dist[b]) {
                    dist[b] = Long.MAX_VALUE;
                }
            }
        }
//        System.out.println(dist[n-1]);
        if (dist[n - 1] == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dist[n - 1]);
        }
    }
}
