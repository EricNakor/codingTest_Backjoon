package algo2501.algorythm202501_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem1260 {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        visited = new boolean[N + 1];
        dfs(V);
        sb.append("\n");

        visited = new boolean[N + 1];
        bfs(V);

        System.out.println(sb);
    }

    static void dfs(int v) {
        visited[v] = true;
        sb.append(v).append(" ");

        for (int next : graph[v]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            sb.append(v).append(" ");

            for (int next : graph[v]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
//    풀지 못함
//    방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
//    방문할 정점이 없는 경우 종료
//
//            | 1   | 2   | 4   |
//            | --- | --- | --- |
//            |     | 3   | 4   |
//            |     |     | 4   |
//    DFS : 1 > 2 > 4 > 3
//    BFS : 1 > 2 > 3 > 4
//
//            | 3   | 1   | 2   | 5   |     |
//            | --- | --- | --- | --- | --- |
//            |     | 4   | 5   |     |     |
//            |     |     |     |     |     |
//    DFS : 3 > 1 > 2 > 5 > 4
//    BFS : 3 > 1 > 4 > 2 > 5
//
//            | 1000 | 999 |
//            | ---- | --- |
//    DFS : 1000 999
//    BFS : 1000 999
//
//
//    DFS 깊이 우선 탐색
//1. V에서 시작
//2. 방문한 정점 이외 연결된 정점 비교
//3. 작은 수로 이동
//4. 2번 3번 반복
//5.  더 이상 방문 가능한 정점이 없을 경우 출력
//
//    BFS 너비 우선 탑색
//1. V에서 시작
//2. V부터 방문 가능한 정점 나열
//3. 나열된 수가 없는 경우 출력
//
        }
    }
}