package algo2504.algo2504_3;

import java.io.*;
import java.util.*;

public class problem1504 {
    static class Node implements Comparable<Node> {
        int to, weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static final int INF = 200_000_000;
    static int N, E;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] from1 = dijkstra(1);
        int[] fromV1 = dijkstra(v1);
        int[] fromV2 = dijkstra(v2);

        long route1 = 0L + from1[v1] + fromV1[v2] + fromV2[N];
        long route2 = 0L + from1[v2] + fromV2[v1] + fromV1[N];
        long answer = Math.min(route1, route2);

        if (answer >= INF) System.out.println(-1);
        else System.out.println(answer);
    }

    static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.to] < now.weight) continue;
            for (Node next : graph.get(now.to)) {
                if (dist[next.to] > dist[now.to] + next.weight) {
                    dist[next.to] = dist[now.to] + next.weight;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
        return dist;
    }
}
