package algorythm202505_1;

import java.io.*;

public class problem2447 {
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 입력받은 N
        arr = new char[N][N]; // 별 패턴을 저장할 배열

        star(0, 0, N, false); // (0,0)에서 시작, 전체 N, blank 아님

        StringBuilder sb = new StringBuilder();
        // 배열을 StringBuilder에 한 줄씩 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb); // 결과 출력
    }

    // 재귀적으로 별 또는 공백을 채우는 함수
    static void star(int x, int y, int N, boolean blank) {
        // blank가 true면 해당 영역 전체를 공백으로 채움
        if (blank) {
            for (int i = x; i < x + N; i++) {
                for (int j = y; j < y + N; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }
        // 더 이상 쪼갤 수 없는 최소 단위면 별로 채움
        if (N == 1) {
            arr[x][y] = '*';
            return;
        }
        // 9분할하여 재귀 호출, 가운데(5번째)는 blank로 호출
        int size = N / 3;
        int count = 0;
        for (int i = x; i < x + N; i += size) {
            for (int j = y; j < y + N; j += size) {
                count++;
                if (count == 5) {
                    // 가운데 부분은 공백으로
                    star(i, j, size, true);
                } else {
                    // 나머지는 별로 재귀 호출
                    star(i, j, size, false);
                }
            }
        }
    }
}
