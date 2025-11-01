package algo2501.algo2501_4;

import java.io.*;
import java.util.*;

public class problem1260 {
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        // 인접 리스트 초기화
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        // 간선 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        // 정렬 및 중복 제거
        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
            ArrayList<Integer> temp = new ArrayList<>();
            if (!adj[i].isEmpty()) {
                temp.add(adj[i].get(0));
                for (int j = 1; j < adj[i].size(); j++) {
                    if (adj[i].get(j) != temp.get(temp.size() - 1)) {
                        temp.add(adj[i].get(j));
                    }
                }
            }
            adj[i] = temp;
        }

        // DFS 수행
        List<Integer> dfsResult = dfs(N, V);

        // BFS 수행
        List<Integer> bfsResult = bfs(N, V);

        // 결과 출력
        printResult(dfsResult, bw);
        printResult(bfsResult, bw);

        bw.flush();
        bw.close();
        br.close();
    }

    // DFS 구현
    private static List<Integer> dfs(int N, int start) {
        boolean[] visited = new boolean[N + 1];
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (visited[current]) continue;
            visited[current] = true;
            result.add(current);

            for (int i = adj[current].size() - 1; i >= 0; i--) {
                int next = adj[current].get(i);
                if (!visited[next]) {
                    stack.push(next);
                }
            }
        }
        return result;
    }

    // BFS 구현
    private static List<Integer> bfs(int N, int start) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        queue.offer(start);
        visited[start] = true;
        result.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : adj[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    result.add(next);
                    queue.offer(next);
                }
            }
        }
        return result;
    }

    // 결과 출력 함수
    private static void printResult(List<Integer> list, BufferedWriter bw) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int num : list) {
            sb.append(num).append(' ');
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        bw.write(sb.toString() + "\n");
    }
}