package algorythm202506_2;

import java.io.*;
import java.util.*;

public class problem7569 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][][] box = new int[H][N][M];
        Queue<int[]> queue = new LinkedList<>();
        int unripeCount = 0;

        // 1. 토마토 정보 입력 및 초기 익은 토마토 큐에 추가
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(st.nextToken());
                    if (box[h][n][m] == 1) {
                        queue.offer(new int[]{h, n, m}); // 익은 토마토 위치를 큐에 추가
                    } else if (box[h][n][m] == 0) {
                        unripeCount++; // 익지 않은 토마토 개수 카운트
                    }
                }
            }
        }

        // 2. 처음부터 모든 토마토가 익어있는 경우
        if (unripeCount == 0) {
            System.out.println(0);
            return;
        }

        // 3. BFS 탐색
        // 탐색 방향: 상, 하, 좌, 우, 앞, 뒤
        int[] dh = {1, -1, 0, 0, 0, 0};
        int[] dn = {0, 0, 0, 0, 1, -1};
        int[] dm = {0, 0, 1, -1, 0, 0};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int ch = current[0];
            int cn = current[1];
            int cm = current[2];

            for (int i = 0; i < 6; i++) {
                int nh = ch + dh[i];
                int nn = cn + dn[i];
                int nm = cm + dm[i];

                // 상자 범위 내에 있고, 익지 않은 토마토인 경우
                if (nh >= 0 && nh < H && nn >= 0 && nn < N && nm >= 0 && nm < M) {
                    if (box[nh][nn][nm] == 0) {
                        queue.offer(new int[]{nh, nn, nm});
                        // 이전 날짜 + 1을 하여 현재까지 걸린 일수를 기록
                        box[nh][nn][nm] = box[ch][cn][cm] + 1;
                    }
                }
            }
        }

        // 4. 결과 계산
        int maxDays = 0;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    // BFS가 끝난 후에도 익지 않은 토마토가 있다면
                    if (box[h][n][m] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    // 가장 큰 값이 마지막으로 익은 토마토의 날짜
                    maxDays = Math.max(maxDays, box[h][n][m]);
                }
            }
        }

        // 시작을 1로 했으므로, 총 걸린 날짜는 최대값 - 1
        System.out.println(maxDays - 1);
    }
}
