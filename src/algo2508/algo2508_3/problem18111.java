package algo2508.algo2508_3;

import java.io.*;
import java.util.*;

public class problem18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] ground = new int[N][M];
        int minHeight = 256, maxHeight = 0;

        // 땅 높이 입력 및 최솟값/최댓값 구하기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, ground[i][j]);
                maxHeight = Math.max(maxHeight, ground[i][j]);
            }
        }

        int bestTime = Integer.MAX_VALUE;
        int bestHeight = 0;

        // 모든 가능한 높이에 대해 브루트포스
        for (int targetHeight = minHeight; targetHeight <= maxHeight; targetHeight++) {
            int time = 0;
            int inventory = B;
            boolean possible = true;

            // 각 칸에 대해 작업 시간 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int currentHeight = ground[i][j];

                    if (currentHeight > targetHeight) {
                        // 블록 제거: 2초, 인벤토리 증가
                        int removeCount = currentHeight - targetHeight;
                        time += removeCount * 2;
                        inventory += removeCount;
                    } else if (currentHeight < targetHeight) {
                        // 블록 설치: 1초, 인벤토리 감소
                        int addCount = targetHeight - currentHeight;
                        time += addCount * 1;
                        inventory -= addCount;
                    }
                }
            }

            // 인벤토리가 음수면 불가능
            if (inventory < 0) {
                possible = false;
            }

            // 가능한 경우 최적해 갱신
            if (possible) {
                if (time < bestTime || (time == bestTime && targetHeight > bestHeight)) {
                    bestTime = time;
                    bestHeight = targetHeight;
                }
            }
        }

        System.out.println(bestTime + " " + bestHeight);
    }
}
