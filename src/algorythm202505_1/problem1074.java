package algorythm202505_1;

import java.io.*;
import java.util.*;

public class problem1074 {
    static int N, r, c, result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        solve((int) Math.pow(2, N), 0, 0, 0); // 전체 배열에서 시작
    }

    // size: 현재 영역의 한 변 길이, x, y: 현재 영역의 시작 좌표, count: 현재 영역의 시작 순서
    static void solve(int size, int x, int y, int count) {
        // base case: 1x1 크기면 정답 출력
        if (size == 1) {
            System.out.println(count);
            return;
        }
        int half = size / 2;
        // 1사분면 (왼쪽 위)
        if (r < x + half && c < y + half) {
            solve(half, x, y, count);
        }
        // 2사분면 (오른쪽 위)
        else if (r < x + half && c >= y + half) {
            solve(half, x, y + half, count + half * half);
        }
        // 3사분면 (왼쪽 아래)
        else if (r >= x + half && c < y + half) {
            solve(half, x + half, y, count + 2 * half * half);
        }
        // 4사분면 (오른쪽 아래)
        else {
            solve(half, x + half, y + half, count + 3 * half * half);
        }
    }
}
