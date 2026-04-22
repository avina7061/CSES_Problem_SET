
import java.io.*;
import java.util.*;

public class RoundTrip2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
        }

        boolean[] vis = new boolean[n];
        boolean[] path = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        boolean foundCycle = false;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                Arrays.fill(path, false);
                int cycleStart = dfs(graph, i, vis, path, parent);
                if (cycleStart != -1) {
                    foundCycle = true;
                    ArrayList<Integer> ans = new ArrayList<>();
                    ans.add(cycleStart + 1);
                    int cur = parent[cycleStart];
                    while (cur != cycleStart) {
                        ans.add(cur + 1);
                        cur = parent[cur];
                    }
                    ans.add(cycleStart + 1);
                    Collections.reverse(ans);

                    StringBuilder sb = new StringBuilder();
                    sb.append(ans.size()).append("\n");
                    for (int node : ans) sb.append(node).append(" ");
                    out.println(sb);
                    break;
                }
            }
        }

        if (!foundCycle) out.println("IMPOSSIBLE");
        out.flush();
    }

    private static int dfs(ArrayList<ArrayList<Integer>> graph, int node, boolean[] vis, boolean[] path, int[] parent) {
        vis[node] = true;
        path[node] = true;

        for (int next : graph.get(node)) {
            if (path[next]) {
                parent[next] = node;
                return next;
            }
            if (!vis[next]) {
                parent[next] = node;
                int cycleNode = dfs(graph, next, vis, path, parent);
                if (cycleNode != -1) return cycleNode;
            }
        }

        path[node] = false;
        return -1;
    }
}