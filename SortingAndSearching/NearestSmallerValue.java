//package CSES_Problem_SET.SortingAndSearching;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class NearestSmallerValue {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int arr[] = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        int ans[] = new int[n];

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                ans[i] = 0;
            } else {
                ans[i] = stack.peek() + 1;
            }

            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i : ans) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}
