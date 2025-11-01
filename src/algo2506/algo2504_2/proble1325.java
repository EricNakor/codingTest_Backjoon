package algo2506.algo2504_2;

import java.io.*;
import java.util.*;

public class proble1325 {
    static int N, M;
    static ArrayList<Integer>[] adj; // 인접 리스트로 그래프 표현
    static int[] resultCounts;      // 각 컴퓨터가 해킹할 수 있는 수를 저장하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // 신뢰 관계 입력받아 그래프 구성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            // A가 B를 신뢰 -> B를 해킹하면 A도 해킹 가능 (B -> A)
            adj[B].add(A);
        }

        resultCounts = new int[N + 1];
        int maxCount = 0;

        // 1번부터 N번 컴퓨터까지 각각 시작점으로 하여 해킹 가능한 컴퓨터 수 계산
        for (int i = 1; i <= N; i++) {
            resultCounts[i] = bfs(i);
            maxCount = Math.max(maxCount, resultCounts[i]); // 최대값 갱신
        }

        // 최대값과 같은 개수를 해킹할 수 있는 컴퓨터 번호 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (resultCounts[i] == maxCount) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }

    // BFS를 사용하여 특정 컴퓨터에서 시작해 해킹 가능한 컴퓨터 수를 계산
    static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int count = 1; // 자기 자신을 포함하므로 1부터 시작

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : adj[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                    count++;
                }
            }
        }
        return count;
    }
}
