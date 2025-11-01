package algo2509.algo2509_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem10830 {
    static final int MOD = 1000;
    static int N;
    static int[][] origin; // 원본 행렬 A

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        origin = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                // 입력 시 바로 MOD 연산을 수행
                origin[i][j] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }

        int[][] result = power(origin, B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    // 분할 정복을 이용한 거듭제곱 (재귀)
    public static int[][] power(int[][] A, long exp) {
        if (exp == 1L) {
            return A;
        }

        int[][] half = power(A, exp / 2);
        int[][] squared = multiply(half, half);

        if (exp % 2 == 1L) {
            return multiply(squared, origin);
        }

        return squared;
    }

    // N x N 행렬 곱셈
    public static int[][] multiply(int[][] m1, int[][] m2) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
                result[i][j] %= MOD;
            }
        }
        return result;
    }
}
