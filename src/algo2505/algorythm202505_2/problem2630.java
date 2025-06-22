package algo2505.algorythm202505_2;

import java.io.*;
import java.util.*;

public class problem2630 {
    static int N, white = 0, blue = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // 종이 색 정보 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N); // 분할정복 시작

        System.out.println(white); // 하얀색 색종이 개수 출력
        System.out.println(blue);  // 파란색 색종이 개수 출력
    }

    // (r, c)에서 시작하는 size 크기 영역을 분할정복
    static void solve(int r, int c, int size) {
        int color = map[r][c];
        boolean isSame = true;
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] != color) {
                    isSame = false;
                    break;
                }
            }
            if (!isSame) break;
        }
        if (isSame) {
            if (color == 0) white++;
            else blue++;
            return;
        }
        int half = size / 2;
        solve(r, c, half);
        solve(r, c + half, half);
        solve(r + half, c, half);
        solve(r + half, c + half, half);
    }
}
