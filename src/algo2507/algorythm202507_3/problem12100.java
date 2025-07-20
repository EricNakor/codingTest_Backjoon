package algo2507.algorythm202507_3;

import java.util.*;

public class problem12100 {
    static int n, answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                map[i][j] = sc.nextInt();

        dfs(map, 0);
        System.out.println(answer);
    }

    static void dfs(int[][] board, int depth) {
        if (depth == 5) {
            answer = Math.max(answer, getMax(board));
            return;
        }
        for (int d = 0; d < 4; d++) {
            int[][] newBoard = move(board, d);
            dfs(newBoard, depth + 1);
        }
    }

    static int getMax(int[][] board) {
        int max = 0;
        for (int[] row : board)
            for (int v : row) max = Math.max(max, v);
        return max;
    }

    // d: 0=위, 1=아래, 2=좌, 3=우
    static int[][] move(int[][] board, int d) {
        int[][] newBoard = new int[n][n];
        for (int i = 0; i < n; i++)
            newBoard[i] = board[i].clone();

        for (int i = 0; i < n; i++) {
            Queue<Integer> q = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                int val = 0;
                switch (d) {
                    case 0:
                        val = newBoard[j][i];
                        break;
                    case 1:
                        val = newBoard[n - 1 - j][i];
                        break;
                    case 2:
                        val = newBoard[i][j];
                        break;
                    case 3:
                        val = newBoard[i][n - 1 - j];
                        break;
                }
                if (val != 0) q.add(val);
            }

            int idx = (d == 1 || d == 3) ? n - 1 : 0;
            int step = (d == 1 || d == 3) ? -1 : 1;

            int last = 0;
            while (!q.isEmpty()) {
                int curr = q.poll();
                if (last == 0) {
                    last = curr;
                } else if (last == curr) {
                    setBlock(newBoard, i, idx, d, last * 2);
                    idx += step;
                    last = 0;
                } else {
                    setBlock(newBoard, i, idx, d, last);
                    idx += step;
                    last = curr;
                }
            }
            if (last != 0) {
                setBlock(newBoard, i, idx, d, last);
            }
            idx += step;
            while ((step > 0 && idx < n) || (step < 0 && idx >= 0)) {
                setBlock(newBoard, i, idx, d, 0);
                idx += step;
            }
        }
        return newBoard;
    }

    static void setBlock(int[][] board, int i, int idx, int d, int val) {
        switch (d) {
            case 0:
                board[idx][i] = val;
                break;
            case 1:
                board[idx][i] = val;
                break;
            case 2:
                board[i][idx] = val;
                break;
            case 3:
                board[i][idx] = val;
                break;
        }
    }
}