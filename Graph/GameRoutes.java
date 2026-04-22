// package CSES_Problem_SET.Graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GameRoutes {
    static int MOD=1000000007;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(System.out);
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a - 1).add(b - 1);
        }

        long dp[] = new long[n];
        Arrays.fill(dp, -1);

        pw.println(solve(list, dp, 0));

        pw.close();
        br.close();
    }

    public static long solve(ArrayList<ArrayList<Integer>> list, long dp[], int node) {
        if (dp[node] != -1) {
            return dp[node];
        }
        if (node == dp.length - 1) {
            return 1;
        }

        long sum = 0;
        for (int i : list.get(node)) {
            sum = (sum+solve(list, dp, i))%MOD;
        }

        return dp[node] = sum%MOD;
    }
}
