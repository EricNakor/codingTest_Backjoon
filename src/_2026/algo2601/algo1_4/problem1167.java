package _2026.algo2601.algo1_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problem1167 {

    // 간선 정보를 저장할 클래스
    static class Node {
        int e; // 연결된 정점 번호
        int cost; // 거리(비용)

        public Node(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
    }

    static ArrayList<Node>[] list;
    static boolean[] visited;
    static int maxDist = 0;
    static int maxNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());
        list = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        // 입력 처리
        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 시작 정점

            while (true) {
                int e = Integer.parseInt(st.nextToken());
                if (e == -1) break; // -1이 나오면 해당 줄 종료
                int cost = Integer.parseInt(st.nextToken());
                list[s].add(new Node(e, cost));
            }
        }

        // 1. 임의의 점(1번)에서 가장 먼 노드 찾기
        visited = new boolean[V + 1];
        dfs(1, 0);

        // 2. 찾은 가장 먼 노드(maxNode)에서 다시 가장 먼 노드까지의 거리 구하기
        visited = new boolean[V + 1];
        maxDist = 0; // 거리 초기화
        dfs(maxNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int node, int currentDist) {
        // 최대 거리 갱신
        if (currentDist > maxDist) {
            maxDist = currentDist;
            maxNode = node;
        }

        visited[node] = true;

        for (Node next : list[node]) {
            if (!visited[next.e]) {
                dfs(next.e, currentDist + next.cost);
            }
        }
    }
}
