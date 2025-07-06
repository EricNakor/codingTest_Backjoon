package algo2507.algorythm202507_1;

import java.io.*;
import java.util.*;

public class problem1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            // 각 건물의 건설 시간
            int[] buildTime = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
            }

            // 그래프와 진입차수 배열
            ArrayList<Integer>[] graph = new ArrayList[N + 1];
            int[] inDegree = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            // 건설 순서 규칙 입력
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                graph[X].add(Y);
                inDegree[Y]++;
            }

            int W = Integer.parseInt(br.readLine());

            // 위상 정렬 + DP
            Queue<Integer> queue = new LinkedList<>();
            int[] totalTime = new int[N + 1];

            // 진입차수가 0인 건물들을 큐에 추가
            for (int i = 1; i <= N; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                    totalTime[i] = buildTime[i];
                }
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();

                // 현재 건물과 연결된 다음 건물들 처리
                for (int next : graph[current]) {
                    // 다음 건물까지의 최대 시간 갱신
                    totalTime[next] = Math.max(totalTime[next],
                            totalTime[current] + buildTime[next]);
                    inDegree[next]--;

                    // 진입차수가 0이 되면 큐에 추가
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

            System.out.println(totalTime[W]);
        }
    }
}