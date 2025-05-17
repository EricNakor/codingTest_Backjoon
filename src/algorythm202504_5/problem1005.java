package algorythm202504_5;

import java.io.*;
import java.util.*;

public class problem1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int t = 0; t < T; t++) {
            // 건물 개수 N, 규칙 개수 K 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            // 건물별 건설 시간 저장 배열
            int[] buildTime = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
            }

            // 건물 간 선후관계 그래프, 진입차수 배열 초기화
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }
            int[] degree = new int[N + 1]; // 진입차수

            // 규칙 입력 및 그래프, 진입차수 갱신
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                graph.get(X).add(Y);
                degree[Y]++;
            }

            // 목표 건물 번호 입력
            int W = Integer.parseInt(br.readLine());

            // 건물별 누적 건설 시간 배열
            int[] result = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                result[i] = buildTime[i]; // 초기값은 자기 건설 시간
            }

            // 위상 정렬에 사용할 큐
            Queue<Integer> q = new LinkedList<>();
            // 진입차수 0인 건물 큐에 삽입
            for (int i = 1; i <= N; i++) {
                if (degree[i] == 0) {
                    q.offer(i);
                }
            }

            // 위상 정렬 수행
            while (!q.isEmpty()) {
                int now = q.poll();
                for (int next : graph.get(now)) {
                    // 다음 건물의 누적 시간 갱신 (최댓값)
                    result[next] = Math.max(result[next], result[now] + buildTime[next]);
                    // 진입차수 감소, 0이면 큐에 삽입
                    if (--degree[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            // 목표 건물의 누적 시간 출력
            bw.write(result[W] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
