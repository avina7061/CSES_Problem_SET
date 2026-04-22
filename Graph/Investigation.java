import java.io.*;
import java.util.*;

public class Investigation {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<int[]>> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a - 1).add(new int[]{b - 1, c});
        }

        long[] dist = new long[n];
        long[] routes = new long[n];
        long[] minFlight = new long[n];
        long[] maxFlight = new long[n];

        Arrays.fill(dist, Long.MAX_VALUE);

        int MOD = 1_000_000_007;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b) -> Long.compare(a[1], b[1]));

        dist[0] = 0;
        routes[0] = 1;
        minFlight[0] = 0;
        maxFlight[0] = 0;

        pq.add(new long[]{0, 0});

        while(!pq.isEmpty()){
            long[] cur = pq.poll();
            int node = (int)cur[0];
            long cost = cur[1];

            if(cost > dist[node]) continue;

            for(int[] edge : list.get(node)){
                int next = edge[0];
                long w = edge[1];

                if(dist[next] > cost + w){
                    dist[next] = cost + w;
                    routes[next] = routes[node];
                    minFlight[next] = minFlight[node] + 1;
                    maxFlight[next] = maxFlight[node] + 1;
                    pq.add(new long[]{next, dist[next]});
                }
                else if(dist[next] == cost + w){
                    routes[next] = (routes[next] + routes[node]) % MOD;
                    minFlight[next] = Math.min(minFlight[next], minFlight[node] + 1);
                    maxFlight[next] = Math.max(maxFlight[next], maxFlight[node] + 1);
                }
            }
        }

        System.out.println(dist[n-1] + " " + routes[n-1] + " " + minFlight[n-1] + " " + maxFlight[n-1]);
    }
}