import java.io.*;
import java.util.*;

public class PlanetsCycle {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int arr[] = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        int degree[] = new int[n];

        for (int i = 0; i < n; i++) {
            list.get(i).add(arr[i]);
            degree[arr[i]]++;
        }

        HashSet<Integer> set = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                q.add(i);
                set.add(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int j : list.get(node)) {
                degree[j]--;
                if (degree[j] == 0) {
                    set.add(j);
                    q.add(j);
                }
            }
        }

        // cycle length calculation
        int ans[] = new int[n];

        for (int i = 0; i < n; i++) {
            if(ans[i]>0)continue;
            if (!set.contains(i)) {
                int cnt = 1;
                int node = i;


                while (arr[node] != i) {
                    cnt++;
                    node = arr[node];
                }

                node = i;
                ans[node] = cnt;

                while (arr[node] != i) {
                    ans[node] = cnt;
                    node = arr[node];
                }
            }
        }

        // reverse graph
        ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
        for (int i = 0; i < n; i++) edge.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            for (int j : list.get(i)) {
                edge.get(j).add(i);
            }
        }
        boolean vis[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!set.contains(i)&&!vis[i]) {
                vis[i]=true;
                DFS(ans[i], ans, edge, set, i, vis);
            }
        }

        // output
        StringBuilder sb = new StringBuilder();
        for (int i : ans) {
            sb.append(i).append(" ");
        }

        System.out.println(sb.toString());
    }

    public static void DFS(int val, int ans[], ArrayList<ArrayList<Integer>> list, HashSet<Integer> set, int node,boolean vis[]) {
        ans[node] = val;
        vis[node]=true;

        for (int i : list.get(node)) {
            if (set.contains(i)&&!vis[i]) {
                DFS(val + 1, ans, list, set, i,vis);
            }
        }
    }
}