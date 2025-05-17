package algorythm202505_1;

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
        if (check(r, c, size)) {
            // 모두 같은 색이면 색종이 개수 증가
            if (map[r][c] == 0) white++;
            else blue++;
            return;
        }
        // 색이 섞여 있으면 4분할하여 재귀 호출
        int newSize = size / 2;
        solve(r, c, newSize);                   // 왼쪽 위
        solve(r + newSize, c, newSize);         // 왼쪽 아래
        solve(r, c + newSize, newSize);         // 오른쪽 위
        solve(r + newSize, c + newSize, newSize); // 오른쪽 아래
    }

    // 해당 영역이 모두 같은 색인지 확인
    static boolean check(int r, int c, int size) {
        int color = map[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] != color) return false;
            }
        }
        return true;
    }
}
