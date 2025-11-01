package algo2504.algo2504_3;

import java.io.*;
import java.util.*;

public class problem5719 {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, S, D;
    static List<Edge>[] graph;
    static List<Integer>[] prev;
    static int[] dist;

    static class Edge {
        int to, cost;
        boolean removed;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
            this.removed = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N];
            for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                graph[u].add(new Edge(v, p));
            }

            // 1차 다익스트라: 최단 경로 찾기 및 prev 저장
            dijkstra(S);

            // 최단 경로 역추적하여 간선 제거
            removeShortest(D);

            // 2차 다익스트라: 거의 최단 경로 찾기
            dijkstra(S);

            System.out.println(dist[D] == INF ? -1 : dist[D]);
        }
    }

    static void dijkstra(int start) {
        dist = new int[N];
        Arrays.fill(dist, INF);
        prev = new ArrayList[N];
        for (int i = 0; i < N; i++) prev[i] = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        dist[start] = 0;
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int u = now[0], cost = now[1];
            if (dist[u] < cost) continue;
            for (Edge e : graph[u]) {
                if (e.removed) continue;
                int v = e.to, nextCost = cost + e.cost;
                if (dist[v] > nextCost) {
                    dist[v] = nextCost;
                    pq.offer(new int[]{v, nextCost});
                    prev[v].clear();
                    prev[v].add(u);
                } else if (dist[v] == nextCost) {
                    prev[v].add(u);
                }
            }
        }
    }

    // D에서 S로 역추적하며 최단 경로 간선 제거
    static void removeShortest(int now) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];
        q.add(now);
        visited[now] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int pre : prev[cur]) {
                for (Edge e : graph[pre]) {
                    if (e.to == cur && !e.removed && dist[cur] == dist[pre] + e.cost) {
                        e.removed = true;
                    }
                }
                if (!visited[pre]) {
                    visited[pre] = true;
                    q.add(pre);
                }
            }
        }
    }
}
