package algo2506.algo2504_2;

import java.io.*;
import java.util.*;

public class problem1707 {
    static ArrayList<Integer>[] graph;
    static int[] colors;
    static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // 정점 수
            E = Integer.parseInt(st.nextToken()); // 간선 수

            // 그래프와 색상 배열 초기화
            graph = new ArrayList[V + 1];
            colors = new int[V + 1]; // 0: 미방문, 1: 그룹A, -1: 그룹B
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            // 간선 정보 입력받아 그래프 생성
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            boolean isBipartite = true;
            // 비연결 그래프를 처리하기 위해 모든 정점에서 탐색 시도
            for (int i = 1; i <= V; i++) {
                if (colors[i] == 0) { // 아직 방문하지 않은 정점이라면
                    if (!bfs(i)) { // BFS 탐색으로 이분 그래프가 아님이 판명되면
                        isBipartite = false;
                        break;
                    }
                }
            }

            System.out.println(isBipartite ? "YES" : "NO");
        }
    }

    // BFS로 이분 그래프 판별
    static boolean bfs(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        colors[startNode] = 1; // 시작 노드를 그룹 1로 지정

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (colors[next] == 0) {
                    // 인접한 노드가 아직 색칠되지 않았다면 반대 색으로 칠하고 큐에 추가
                    colors[next] = -colors[current];
                    queue.add(next);
                } else if (colors[next] == colors[current]) {
                    // 인접한 노드가 이미 색칠되어 있는데 같은 색이라면 이분 그래프가 아님
                    return false;
                }
            }
        }
        return true; // 탐색이 끝날 때까지 모순이 없으면 이분 그래프 조건 만족
    }
}
