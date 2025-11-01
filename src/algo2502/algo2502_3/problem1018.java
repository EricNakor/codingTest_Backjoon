package algo2502.algo2502_3;

import java.io.*;

public class problem1018 {
    // 틀림
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                minCount = Math.min(minCount, countChanges(i, j));
            }
        }

        bw.write(minCount + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // 다시 칠해야 하는 정사각형의 개수 계산
    static int countChanges(int startRow, int startCol) {
        int minCount = Integer.MAX_VALUE;

        // 두 가지 체스판 패턴
        char[][] pattern1 = createPattern(true);
        char[][] pattern2 = createPattern(false);

        int count1 = comparePattern(startRow, startCol, pattern1);
        int count2 = comparePattern(startRow, startCol, pattern2);

        minCount = Math.min(count1, count2);
        return minCount;
    }

    // 체스판 패턴 생성
    static char[][] createPattern(boolean startWithWhite) {
        char[][] pattern = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    pattern[i][j] = startWithWhite ? 'W' : 'B';
                } else {
                    pattern[i][j] = startWithWhite ? 'B' : 'W';
                }
            }
        }
        return pattern;
    }

    // 잘라낸 체스판과 패턴 비교
    static int comparePattern(int startRow, int startCol, char[][] pattern) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[startRow + i][startCol + j] != pattern[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
