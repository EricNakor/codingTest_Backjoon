package _2026.algo1_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem11657 {

    // 간선 정보를 저장할 클래스
    static class Edge {
        int u; // 시작 도시
        int v; // 도착 도시
        int w; // 비용 (시간)

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static int N, M;
    static ArrayList<Edge> edges = new ArrayList<>();
    static long[] dist; // 거리 합이 int 범위를 넘을 수 있으므로 long 사용
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new long[N + 1];
        Arrays.fill(dist, INF); // 거리 배열 초기화

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, w));
        }

        // 벨만-포드 알고리즘 수행
        if (bellmanFord(1)) {
            // 음수 사이클이 존재하면 -1 출력
            System.out.println("-1");
        } else {
            // 음수 사이클이 없으면 거리 출력
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= N; i++) {
                if (dist[i] == INF) {
                    sb.append("-1\n"); // 도달할 수 없는 경우
                } else {
                    sb.append(dist[i]).append("\n");
                }
            }
            System.out.print(sb);
        }
    }

    // 벨만-포드 알고리즘: 음수 사이클이 있으면 true 반환
    static boolean bellmanFord(int start) {
        dist[start] = 0;

        // N번 반복 (N-1번은 최단거리 갱신, 마지막 1번은 음수 사이클 확인용)
        for (int i = 0; i < N; i++) {
            // 모든 간선을 확인
            for (int j = 0; j < M; j++) {
                Edge edge = edges.get(j);

                // 현재 간선의 시작점이 방문된 적이 있고(INF가 아니고),
                // 더 짧은 경로를 발견했다면 갱신
                if (dist[edge.u] != INF && dist[edge.v] > dist[edge.u] + edge.w) {
                    dist[edge.v] = dist[edge.u] + edge.w;

                    // N번째 라운드(i == N-1)에서도 갱신이 일어난다면 음수 사이클 존재
                    if (i == N - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}