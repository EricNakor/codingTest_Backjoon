package algorythm202502_3;

import java.io.*;

public class problem1987 {
    static char[][] board;
    static boolean[] visitedAlphabet = new boolean[26];
    static int maxPathLength = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        visitedAlphabet[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        bw.write(maxPathLength + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // DFS 함수
    static void dfs(int row, int col, int pathLength) {
        maxPathLength = Math.max(maxPathLength, pathLength);

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                char newAlphabet = board[newRow][newCol];
                if (!visitedAlphabet[newAlphabet - 'A']) {
                    visitedAlphabet[newAlphabet - 'A'] = true;
                    dfs(newRow, newCol, pathLength + 1);
                    visitedAlphabet[newAlphabet - 'A'] = false;
                }
            }
        }
    }
}
