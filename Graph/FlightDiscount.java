//package CSES_Problem_SET.Graph;
//
//import CP.Contest.Hello2026.A;

import java.util.*;

public class FlightDiscount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int m=sc.nextInt();

        ArrayList<ArrayList<int []>> list = new ArrayList<>();

        for(int i=0; i<n; i++){
            list.add(new ArrayList<>());
        }

        HashMap<ArrayList<Integer>,Long>map = new HashMap<>();

        for(int i=0;i<m; i++){
              int a=sc.nextInt();
              int b=sc.nextInt();
              int c=sc.nextInt();
              list.get(a-1).add(new int[]{b-1,c});
              list.get(b-1).add(new int[]{a-1,c});
              ArrayList<Integer>tmp = new ArrayList<>();
              tmp.add(a-1);
              tmp.add(b-1);
              map.put(tmp,Math.min(map.getOrDefault(tmp,Long.MAX_VALUE),c));
              ArrayList<Integer>lp = new ArrayList<>();
              lp.add(b-1);
              lp.add(a-1);
              map.put(lp,Math.min(map.getOrDefault(lp,Long.MAX_VALUE),c));
        }

//        for(ArrayList<Integer>lt :map.keySet()){
//            System.out.println(lt);
//        }

        long dist[] = new long[n];
        Arrays.fill(dist,Long.MAX_VALUE);

        int path[] = new int[n];
        Arrays.fill(path,-1);

        dist[0]=0;

        PriorityQueue<long []>pq = new PriorityQueue<>((a,b)->Long.compare(a[1],b[1]));
        pq.add(new long[]{0,0});

        while (!pq.isEmpty()){
            long arr[] = pq.poll();
            int node=(int)arr[0];
            long cost=arr[1];

            for(int i[]: list.get(node)){
                int d=i[0];
                int c=i[1];
                if(cost+c<dist[d]){
                    dist[d]=c+cost;
                    path[d]=node;
                    pq.add(new long[]{d,dist[d]});
                }
            }

        }
        ArrayList<Integer>nodes = new ArrayList<>();
        int nd=path[n-1];
        nodes.add(n-1);

//        for(int i: path){
//            System.out.println(i);
//        }

        while(nd!=0&&nd!=-1){
            nodes.add(nd);
            nd=path[nd];
        }
        nodes.add(0);
        Collections.reverse(nodes);

       long totalCost=dist[n-1];
        System.out.println(totalCost);


       for(int i=1; i<nodes.size(); i++){
           ArrayList<Integer>l = new ArrayList<>();
           l.add(nodes.get(i-1));
           l.add(nodes.get(i));
           long c=map.getOrDefault(l,0L);
//           System.out.println(c);
           totalCost=Math.min(totalCost,dist[n-1]-(c-c/2));
       }
        System.out.println(totalCost);

    }
}
