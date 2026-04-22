import java.util.*;

class Triplet {
    long first;
    long second;
    long third;

    Triplet(long f, long s, long t) {
        first = f;
        second = s;
        third = t;
    }
}

public class CycleFinding {

    static int n, m;
    static ArrayList<Triplet> edges = new ArrayList<>();
    static long[] dist;
    static int[] relaxant;

    static void bellmanFord() {
        int x = -1;

        for (int i = 1; i <= n; i++) {// run n-times to detect cycle at nth iteration
            x = -1;
            for (Triplet e : edges) {
                int u = (int) e.first;
                int v = (int) e.second;
                long d = e.third;

                if (dist[u] + d < dist[v]) { //--->a-->b-->c-->d
                    //                             \  /
                    //                              x
                    dist[v] = dist[u] + d;
                    relaxant[v] = u;
                    x = v;
//                  System.out.println(x);
                }
            }
        }
//        System.out.println(x);

        if (x == -1) {
            System.out.println("NO");
        } else {
            for (int i = 1; i <= n; i++) {
                x = relaxant[x];
            }

            ArrayList<Integer> cycle = new ArrayList<>();

            int v = x;
            while (true) {
                cycle.add(v);
                v = relaxant[v];
                if (v == x ) break;
            }
            cycle.add(x);

            Collections.reverse(cycle);

            System.out.println("YES");
            for (int node : cycle) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        dist = new long[n + 1];
        relaxant = new int[n + 1];

        for (int i = 0; i < m; i++) {
            long u = sc.nextLong();
            long v = sc.nextLong();
            long w = sc.nextLong();
            edges.add(new Triplet(u, v, w));
        }

        Arrays.fill(relaxant, -1);

        Arrays.fill(dist, 0);

        bellmanFord();
    }
}