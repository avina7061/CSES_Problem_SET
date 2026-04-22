import java.io.*;
import java.util.*;

public class ShortestRoute1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<long[]>> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            // Directed graph
            list.get(a - 1).add(new long[]{b - 1, c});
        }

        long dist[] = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<long[]> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

        pq.add(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] arr = pq.poll();
            int node = (int) arr[0];
            long cost = arr[1];

            // Skip outdated entries
            if (cost > dist[node]) continue;

            for (long[] edge : list.get(node)) {
                int nd = (int) edge[0];
                long c = edge[1];

                if (cost + c < dist[nd]) {
                    dist[nd] = cost + c;
                    pq.add(new long[]{nd, dist[nd]});
                }
            }
        }

        // Fast output
        StringBuilder sb = new StringBuilder();
        for (long d : dist) {
            sb.append(d).append(" ");
        }
        System.out.println(sb);
    }
}