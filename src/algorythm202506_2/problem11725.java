package algorythm202506_2;

import java.io.*;
import java.util.*;

public class problem11725 {
    static int N;
    static ArrayList<Integer>[] graph; // 인접 리스트로 그래프 표현
    static int[] parent;               // 각 노드의 부모를 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 그래프와 부모 배열 초기화
        graph = new ArrayList[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 1. 간선 정보 입력받아 그래프 생성 (양방향)
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // 2. 루트 노드(1)부터 BFS 탐색 시작
        bfs(1);

        // 3. 2번 노드부터 부모 노드 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append('\n');
        }
        System.out.print(sb);
    }

    // BFS를 사용하여 각 노드의 부모를 찾음
    static void bfs(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        parent[startNode] = 1; // 루트 노드를 방문 처리하여 무한 루프 방지

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 현재 노드와 연결된 모든 노드를 확인
            for (int next : graph[current]) {
                // 아직 부모가 지정되지 않은(방문하지 않은) 노드라면
                if (parent[next] == 0) {
                    parent[next] = current; // 현재 노드를 부모로 지정
                    queue.add(next);        // 다음 탐색을 위해 큐에 추가
                }
            }
        }
    }
}
