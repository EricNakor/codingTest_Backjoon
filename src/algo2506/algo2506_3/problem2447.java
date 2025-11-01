package algo2506.algo2506_3;

import java.io.*;

public class problem2447 {
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new char[N][N];

        star(0, 0, N); // (0,0)에서 시작

        StringBuilder sb = new StringBuilder();
        // 배열을 StringBuilder에 한 줄씩 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    // (x, y)에서 시작하는 N*N 영역을 재귀적으로 채움
    static void star(int x, int y, int N) {
        if (N == 1) {
            arr[x][y] = '*';
            return;
        }
        int size = N / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    // 가운데 부분을 공백으로 채움
                    for (int a = x + size; a < x + 2 * size; a++) {
                        for (int b = y + size; b < y + 2 * size; b++) {
                            arr[a][b] = ' ';
                        }
                    }
                } else {
                    // 나머지 부분은 재귀 호출
                    star(x + i * size, y + j * size, size);
                }
            }
        }
    }
}