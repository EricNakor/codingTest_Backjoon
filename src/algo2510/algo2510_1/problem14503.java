package algo2510.algo2510_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem14503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 방향 벡터 (0:북, 1:동, 2:남, 3:서)
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int cleanCount = 0;

        while (true) {
            // 1단계: 현재 위치 청소
            if (map[r][c] == 0) {
                map[r][c] = 2; // 청소 완료 상태를 2로 표시
                cleanCount++;
            }

            boolean canMoveForward = false;
            // 2단계: 주변 탐색
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4; // 왼쪽으로 방향 전환
                int next_r = r + dr[d];
                int next_c = c + dc[d];

                // 왼쪽 방향이 청소 가능한 공간인지 확인
                if (next_r >= 0 && next_r < N && next_c >= 0 && next_c < M && map[next_r][next_c] == 0) {
                    r = next_r; // 전진
                    c = next_c;
                    canMoveForward = true;
                    break;
                }
            }

            // 전진했다면 다시 1단계부터 시작
            if (canMoveForward) {
                continue;
            }

            // 3단계: 후진 또는 정지
            // 현재 방향을 유지한 채 후진할 좌표 계산
            int back_r = r - dr[d];
            int back_c = c - dc[d];

            if (back_r >= 0 && back_r < N && back_c >= 0 && back_c < M && map[back_r][back_c] != 1) {
                r = back_r; // 후진
                c = back_c;
            } else {
                break; // 뒤가 벽이거나 맵 밖이라 후진 불가 시 작동 중지
            }
        }

        System.out.println(cleanCount);
    }
}
