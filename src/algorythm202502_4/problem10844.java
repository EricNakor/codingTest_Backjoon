package algorythm202502_4;

import java.io.*;

public class problem10844 {
    static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 처리
        int N = Integer.parseInt(br.readLine());

        // 동적 프로그래밍 테이블 초기화
        int[][] dp = new int[N + 1][10];

        // 길이가 1인 계단 수 초기화
        for (int j = 1; j <= 9; j++) {
            dp[1][j] = 1;
        }

        // 동적 프로그래밍 계산
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j > 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if (j < 9) {
                    dp[i][j] += dp[i - 1][j + 1];
                }
                dp[i][j] %= MOD;
            }
        }

        // 결과 계산 및 출력
        long result = 0;
        for (int j = 0; j <= 9; j++) {
            result += dp[N][j];
        }
        result %= MOD;

        bw.write(String.valueOf(result));
        bw.flush();
    }
}
