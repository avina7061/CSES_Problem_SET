package CSES_Problem_SET.IntroductoryProblem;

import java.util.Scanner;

public class TrailingZero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n =sc.nextInt();
        long ans=0;
        for(int i=5; i<=n; i*=5){
            ans+=(n/i);
        }
        System.out.println(ans);
    }
}
