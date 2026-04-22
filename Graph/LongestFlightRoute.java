import java.io.*;
import java.util.*;

public class LongestFlightRoute {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a - 1).add(b - 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MIN_VALUE);

        dist[0] = 0;
        pq.add(new int[]{0, 0});

        int[] parent = new int[n];

        while (!pq.isEmpty()) {
            int[] node = pq.poll();

            for (int i : list.get(node[0])) {
                int cs = dist[i];
                if (dist[node[0]] + 1 > cs) {
                    parent[i] = node[0];

                    pq.add(new int[]{i, dist[i]});
                    dist[i] = dist[node[0]] + 1;

                }
            }
        }

        if (dist[n - 1] == Integer.MIN_VALUE) {
            out.println("IMPOSSIBLE");
        } else {
            int node = n - 1;
            ArrayList<Integer> ans = new ArrayList<>();

            while (node != 0) {
                ans.add(node);
                node = parent[node];
            }
            ans.add(0);

            Collections.reverse(ans);

            out.println(ans.size());

            StringBuilder sb = new StringBuilder();
            for (int i : ans) {
                sb.append(i + 1).append(" ");
            }
            out.println(sb);
        }

        out.flush();
    }
}