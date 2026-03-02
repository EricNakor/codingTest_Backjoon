package _2026.algo2602.algo2_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problem1865 {
    // 간선 정보를 담을 클래스
    static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static int N, M, W;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int t = 0; t < TC; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();

            // 1. 도로 정보 입력 (양방향, 양수 가중치)
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, time));
                edges.add(new Edge(e, s, time));
            }

            // 2. 웜홀 정보 입력 (단방향, 음수 가중치)
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, -time)); // 웜홀은 시간이 거꾸로 가므로 마이너스 처리
            }

            // 3. 벨만-포드 알고리즘 실행 및 결과 저장
            if (bellmanFord()) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.print(sb.toString());
    }

    static boolean bellmanFord() {
        // 거리 배열 초기화 (여기서는 굳이 무한대로 채우지 않고 0으로 둬도 됩니다. 
        // 갱신 여부만 중요하기 때문입니다.)
        int[] dist = new int[N + 1];

        // N번 반복 (N-1번까지는 정상적인 최단 경로 찾기, N번째는 사이클 확인)
        for (int i = 1; i <= N; i++) {
            boolean isUpdated = false; // 이번 반복에서 갱신이 일어났는지 확인하는 플래그

            // 모든 간선을 하나씩 확인
            for (Edge edge : edges) {
                // 핵심: dist[edge.start] != INF 조건이 없습니다! (단절된 그래프 탐색 위함)
                if (dist[edge.end] > dist[edge.start] + edge.weight) {
                    dist[edge.end] = dist[edge.start] + edge.weight;
                    isUpdated = true;

                    // 만약 N번째 반복인데도 갱신이 일어났다면? -> 무한히 줄어드는 음수 사이클 존재!
                    if (i == N) {
                        return true;
                    }
                }
            }

            // 만약 간선을 다 돌았는데 단 한 번도 갱신되지 않았다면, 
            // 이미 최적화가 끝난 것이므로 더 이상 반복할 필요가 없습니다. (최적화)
            if (!isUpdated) {
                break;
            }
        }

        // N번째 반복까지 갔음에도 갱신이 없었다면 음수 사이클이 없는 것입니다.
        return false;
    }
}