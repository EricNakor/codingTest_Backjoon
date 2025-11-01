package algo2504.algo2504_3;

import java.io.*;
import java.util.*;

public class problem18352 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        // 인접 리스트 생성
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
        }

        // 거리 배열 초기화
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        dist[X] = 0;

        // BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                if (dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    queue.add(next);
                }
            }
        }

        // 거리 K인 도시 출력
        boolean found = false;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                System.out.println(i);
                found = true;
            }
        }
        if (!found) System.out.println(-1);
    }
}
