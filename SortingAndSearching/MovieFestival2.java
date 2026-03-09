package CSES_Problem_SET.SortingAndSearching;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class MovieFestival2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int dp[][] = new int[n][2];

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            dp[i][0] = a;
            dp[i][1] = b;
        }

        Arrays.sort(dp, (a, b) -> Integer.compare(a[1], b[1]));

        TreeMap<Integer, Integer> map = new TreeMap<>();// we have to store all end

        for (int i = 0; i < k; i++) {
            map.put(0, map.getOrDefault(0, 0) + 1);
        }
        int ans = 0;

        for (int i = 1; i < n; i++) {
            int start = dp[i][0];
            int end = dp[i][1];

            Integer key = map.floorKey(start);
            if (key != null) {
                map.put(key, map.getOrDefault(key, 0) - 1);
                if (map.get(key) == 0) map.remove(key);
                map.put(end, map.getOrDefault(end, 0) + 1);
                ans++;
            }
        }
        System.out.println(ans);
    }
}
