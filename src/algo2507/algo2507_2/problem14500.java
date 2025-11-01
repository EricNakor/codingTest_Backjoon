package algo2507.algo2507_2;

import java.io.*;
import java.util.*;

public class problem14500 {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int maxSum = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 위치에서 탐색 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, board[i][j]);
                visited[i][j] = false;

                // T자 모양 별도 처리
                checkTShape(i, j);
            }
        }

        System.out.println(maxSum);
    }

    // DFS로 4칸 연속 탐색 (I, O, S, L 모양)
    static void dfs(int x, int y, int count, int sum) {
        if (count == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, count + 1, sum + board[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    // T자 모양 별도 처리 (ㅗ, ㅜ, ㅓ, ㅏ)
    static void checkTShape(int x, int y) {
        // ㅗ 모양: 위쪽 3개 + 아래 1개
        if (x - 1 >= 0 && y - 1 >= 0 && y + 1 < M) {
            int sum = board[x][y] + board[x - 1][y - 1] + board[x - 1][y] + board[x - 1][y + 1];
            maxSum = Math.max(maxSum, sum);
        }

        // ㅜ 모양: 아래쪽 3개 + 위 1개
        if (x + 1 < N && y - 1 >= 0 && y + 1 < M) {
            int sum = board[x][y] + board[x + 1][y - 1] + board[x + 1][y] + board[x + 1][y + 1];
            maxSum = Math.max(maxSum, sum);
        }

        // ㅓ 모양: 왼쪽 3개 + 오른쪽 1개
        if (x - 1 >= 0 && x + 1 < N && y - 1 >= 0) {
            int sum = board[x][y] + board[x - 1][y - 1] + board[x][y - 1] + board[x + 1][y - 1];
            maxSum = Math.max(maxSum, sum);
        }

        // ㅏ 모양: 오른쪽 3개 + 왼쪽 1개
        if (x - 1 >= 0 && x + 1 < N && y + 1 < M) {
            int sum = board[x][y] + board[x - 1][y + 1] + board[x][y + 1] + board[x + 1][y + 1];
            maxSum = Math.max(maxSum, sum);
        }
    }

}
