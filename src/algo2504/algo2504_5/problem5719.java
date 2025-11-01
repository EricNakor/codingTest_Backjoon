package algo2504.algo2504_5;

import java.io.*;
import java.util.*;

public class problem5719 {
    static int N, M;
    static int[][] boards = new int[501][501]; // 그래프의 도로 정보
    static int[] weights = new int[501]; // S에서 각 정점까지의 최단 거리
    static Set<Integer>[] nodes = new Set[501]; // 각 정점까지 최단 거리로 도달할 때의 부모노드들
    static boolean[] check = new boolean[501]; // 경로 삭제 시 방문 체크

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        while (N != 0 && M != 0) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            init(); // 배열, 리스트 초기화

            // 도로 정보 입력받기
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());
                boards[U][V] = P;
            }

            // 1. 첫 번째 다익스트라: 최단 경로 찾기
            Dijkstra(S);

            // 2. 최단 경로에 포함된 도로 모두 제거
            deletePath(D);

            // 3. 두 번째 다익스트라: 거의 최단 경로 찾기
            weightsInit();
            Dijkstra(S);

            // 4. 결과 출력
            System.out.println(weights[D] == Integer.MAX_VALUE ? -1 : weights[D]);

            // 다음 테스트 케이스 입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }
    }

    // 다익스트라 알고리즘 (최단 경로 및 부모노드 추적)
    public static void Dijkstra(int S) {
        boolean[] visited = new boolean[501];
        weights[S] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node nowNode = pq.poll();
            if (visited[nowNode.index]) continue;
            visited[nowNode.index] = true;

            for (int i = 0; i < 501; i++) {
                if (visited[i] || boards[nowNode.index][i] == Integer.MAX_VALUE) continue;
                int nextWeight = nowNode.value + boards[nowNode.index][i];
                if (nextWeight > weights[i]) continue;

                if (nextWeight < weights[i]) {
                    nodes[i].clear(); // 더 짧은 경로 발견 시 부모노드 초기화
                }
                if (nextWeight <= weights[i]) {
                    nodes[i].add(nowNode.index); // 같은 거리면 부모노드 추가
                    weights[i] = nextWeight;
                    pq.add(new Node(i, weights[i]));
                }
            }
        }
    }

    // 최단 경로에 포함된 도로를 모두 제거 (역추적)
    public static void deletePath(int d) {
        if (check[d]) return;
        check[d] = true;
        for (int i : nodes[d]) {
            boards[i][d] = Integer.MAX_VALUE; // 도로 제거
            deletePath(i); // 부모노드도 재귀적으로 제거
        }
    }

    // 배열, 리스트, 가중치 초기화 함수
    public static void init() {
        for (int i = 0; i < 501; i++) {
            for (int j = 0; j < 501; j++) {
                boards[i][j] = Integer.MAX_VALUE;
            }
            check[i] = false;
        }
        weightsInit();
    }

    // 가중치, 부모노드 초기화
    public static void weightsInit() {
        for (int i = 0; i < 501; i++) {
            nodes[i] = new HashSet<>();
            weights[i] = Integer.MAX_VALUE;
        }
    }

    // 우선순위 큐용 노드 클래스
    static class Node implements Comparable<Node> {
        int index, value;
        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
}

