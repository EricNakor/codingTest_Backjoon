package algo2507.algo2507_2;

import java.io.*;
import java.util.*;

public class problem2580 {
    static int[][] sudoku = new int[9][9];
    static ArrayList<int[]> blanks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 스도쿠 판 입력 및 빈 칸 위치 저장
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if (sudoku[i][j] == 0) {
                    blanks.add(new int[]{i, j});
                }
            }
        }

        // 백트래킹 시작
        solve(0);
    }

    static boolean solve(int idx) {
        // 모든 빈 칸을 채웠으면 완성
        if (idx == blanks.size()) {
            printSudoku();
            return true;
        }

        int[] pos = blanks.get(idx);
        int row = pos[0];
        int col = pos[1];

        // 1부터 9까지 숫자 시도
        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                sudoku[row][col] = num;

                // 다음 빈 칸으로 재귀 호출
                if (solve(idx + 1)) {
                    return true;
                }

                // 백트래킹: 원래 상태로 복구
                sudoku[row][col] = 0;
            }
        }

        return false;
    }

    // 해당 위치에 숫자를 놓을 수 있는지 검증
    static boolean isValid(int row, int col, int num) {
        // 같은 행에 중복 숫자 확인
        for (int j = 0; j < 9; j++) {
            if (sudoku[row][j] == num) {
                return false;
            }
        }

        // 같은 열에 중복 숫자 확인
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == num) {
                return false;
            }
        }

        // 같은 3×3 정사각형에 중복 숫자 확인
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (sudoku[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // 완성된 스도쿠 출력
    static void printSudoku() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(sudoku[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
        System.exit(0);
    }
}