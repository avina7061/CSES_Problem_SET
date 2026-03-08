package CSES_Problem_SET.DyanamicProgramming;

import java.util.Scanner;

public class DiceCombination {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(solve(n, 1));
    }

    public static long solve(int n, int i) {
        if (i > 6) return 0;
        if (n == 0) return 1;
        if (n < 0) return 0;


        long ans = 0;

        for (int j = 0; j <= n; j += i) {
            ans += solve(n - j, i + 1);
        }
        return ans;
    }

}
