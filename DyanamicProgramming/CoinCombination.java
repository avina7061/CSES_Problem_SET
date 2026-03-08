//package CSES_Problem_SET.DynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CoinCombination {

    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int c[] = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        long dp[] = new long[x + 1];
        dp[0] = 1;

        for (int i = 1; i <= x; i++) {
            for (int j = 0; j < c.length; j++) {
                if (c[j] <= i) {
                    dp[i] = (dp[i] + dp[i - c[j]]) % MOD;
                }
            }
        }

        System.out.println(dp[x]);
    }
}