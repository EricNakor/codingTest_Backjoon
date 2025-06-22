package algo2505.algorythm202505_2;

import java.io.*;
import java.util.*;

public class problem1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(z(N, r, c));
    }

    // n: 현재 단계, r: 행, c: 열
    static int z(int n, int r, int c) {
        if (n == 0) return 0; // 1x1이면 0번째 방문

        int half = 1 << (n - 1); // 2^(n-1)
        int size = half * half;

        // 1사분면 (왼쪽 위)
        if (r < half && c < half) {
            return z(n - 1, r, c);
        }
        // 2사분면 (오른쪽 위)
        else if (r < half && c >= half) {
            return size + z(n - 1, r, c - half);
        }
        // 3사분면 (왼쪽 아래)
        else if (r >= half && c < half) {
            return 2 * size + z(n - 1, r - half, c);
        }
        // 4사분면 (오른쪽 아래)
        else {
            return 3 * size + z(n - 1, r - half, c - half);
        }
    }
}
