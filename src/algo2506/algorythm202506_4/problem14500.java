package algo2506.algorythm202506_4;

import java.io.*;
import java.util.StringTokenizer;

// 테트로미노 문제를 해결하는 클래스
public class problem14500 {
    // N: 보드의 세로 크기, M: 보드의 가로 크기
    static int N, M;
    // board: 게임 보드의 점수 배열
    static int[][] board;
    // maxSum: 테트로미노를 놓아 얻을 수 있는 최대 점수
    static int maxSum = 0;
    // tetromino: 가능한 모든 테트로미노 모양의 좌표 배열
    static int[][] tetromino = {
            {0, 0}, {0, 1}, {0, 2}, {0, 3},  // ㅡ
            {0, 0}, {1, 0}, {2, 0}, {3, 0},  // ㅣ
            {0, 0}, {0, 1}, {1, 0}, {1, 1},  // ㅁ
            {0, 0}, {1, 0}, {2, 0}, {2, 1},  // L
            {0, 1}, {1, 1}, {2, 1}, {2, 0},  // ㄱ
            {0, 0}, {0, 1}, {0, 2}, {1, 0},  // ㄴ
            {0, 0}, {0, 1}, {0, 2}, {1, 2},  // ㄱ
            {0, 0}, {1, 0}, {1, 1}, {2, 1},  // Z
            {0, 1}, {1, 1}, {1, 0}, {2, 0},  // S
            {0, 1}, {1, 0}, {1, 1}, {1, 2},  // ㅗ
            {0, 0}, {0, 1}, {0, 2}, {1, 1},  // ㅜ
            {0, 0}, {1, 0}, {2, 0}, {1, 1},  // ㅏ
            {1, 0}, {0, 1}, {1, 1}, {2, 1},  // ㅓ
            {0, 0}, {0, 1}, {1, 1}, {1, 2},  // Z
            {1, 0}, {1, 1}, {0, 1}, {0, 2},  // S
            {0, 0}, {1, 0}, {1, 1}, {2, 0},  // ㅏ
            {0, 1}, {1, 0}, {1, 1}, {2, 1},  // ㅓ
            {0, 0}, {0, 1}, {1, 0}, {2, 0},  // L
            {0, 0}, {0, 1}, {1, 1}, {2, 1}   // ㄱ
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                checkTetromino(i, j);
            }
        }

        bw.write(String.valueOf(maxSum));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void checkTetromino(int x, int y) {
        // 모든 테트로미노 모양에 대해 반복 (4개의 좌표씩)
        for (int i = 0; i < tetromino.length; i += 4) {
            // 현재 테트로미노의 점수 합계
            int sum = 0;
            // 테트로미노를 놓을 수 있는지 여부
            boolean possible = true;

            // 테트로미노의 4개 블록에 대해 반복
            for (int j = 0; j < 4; j++) {
                // 현재 블록의 x좌표 계산
                int nx = x + tetromino[i + j][0];
                // 현재 블록의 y좌표 계산
                int ny = y + tetromino[i + j][1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    possible = false;
                    break;
                }
                sum += board[nx][ny];
            }

            if (possible) maxSum = Math.max(maxSum, sum);
        }
    }
}
