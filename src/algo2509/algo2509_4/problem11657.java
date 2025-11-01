package algo2509.algo2509_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem11657 {
    static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        // N-1번의 간선 완화
        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] != Long.MAX_VALUE && dist[edge.to] > dist[edge.from] + edge.weight) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                }
            }
        }

        // 음수 사이클 확인
        boolean hasNegativeCycle = false;
        for (Edge edge : edges) {
            if (dist[edge.from] != Long.MAX_VALUE && dist[edge.to] > dist[edge.from] + edge.weight) {
                hasNegativeCycle = true;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (hasNegativeCycle) {
            sb.append(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] == Long.MAX_VALUE) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(dist[i]).append('\n');
                }
            }
        }
        System.out.println(sb);
    }
}