package algo2512.algo12_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem14499 {
    // 맵 크기와 주사위 위치
    static int N, M, x, y, K;
    static int[][] map;
    // 주사위 배열: 인덱스 1(윗면), 2(북), 3(동), 4(서), 5(남), 6(바닥)
    static int[] dice = new int[7];

    // 방향: 1:동, 2:서, 3:북, 4:남
    // x가 행(세로), y가 열(가로)임에 주의
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int k = 0; k < K; k++) {
            int command = Integer.parseInt(st.nextToken());

            // 1. 이동할 좌표 계산
            int nx = x + dx[command];
            int ny = y + dy[command];

            // 2. 맵 범위를 벗어나면 명령 무시
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }

            // 3. 좌표 갱신 및 주사위 굴리기
            x = nx;
            y = ny;
            roll(command);

            // 4. 지도와 주사위 바닥면 상호작용
            if (map[x][y] == 0) {
                // 지도가 0이면 주사위 바닥 -> 지도 복사
                map[x][y] = dice[6];
            } else {
                // 지도가 0이 아니면 지도 -> 주사위 바닥 복사, 지도는 0
                dice[6] = map[x][y];
                map[x][y] = 0;
            }

            // 5. 윗면 출력
            sb.append(dice[1]).append("\n");
        }
        System.out.print(sb);
    }

    // 주사위를 굴리는 함수
    // dice[1]:윗면, dice[2]:북, dice[3]:동, dice[4]:서, dice[5]:남, dice[6]:바닥
    static void roll(int dir) {
        int temp = dice[1];
        switch (dir) {
            case 1: // 동
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[3];
                dice[3] = temp;
                break;
            case 2: // 서
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = dice[4];
                dice[4] = temp;
                break;
            case 3: // 북
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[2];
                dice[2] = temp;
                break;
            case 4: // 남
                dice[1] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = temp;
                break;
        }
    }
}
