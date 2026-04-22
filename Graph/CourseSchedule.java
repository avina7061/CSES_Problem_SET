import java.io.*;
import java.util.*;

public class CourseSchedule {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        int[] indegree = new int[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a - 1).add(b - 1);
            indegree[b - 1]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.add(i);
        }

        ArrayList<Integer> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            int node = q.poll();
            ans.add(node);

            for (int next : graph.get(node)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        if (ans.size() == n) {
            StringBuilder sb = new StringBuilder();
            for (int node : ans) {
                sb.append(node + 1).append(" ");
            }
            out.println(sb);
        } else {
            out.println("IMPOSSIBLE");
        }

        out.flush();
    }
}