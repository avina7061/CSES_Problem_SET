package CSES_Problem_SET.SortingAndSearching;

import java.util.Arrays;
import java.util.Scanner;

public class ConcertTickets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m =sc.nextInt();
        int a[] = new int[n];
        int b[] = new int[m];
        for(int i=0; i<n; i++){
            a[i]=sc.nextInt();
        }

        for(int j=0; j<m; j++){
            b[j]=sc.nextInt();
        }
        Arrays.sort(a);



    }
}
