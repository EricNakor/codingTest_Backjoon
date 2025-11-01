package algo2502.algo2502_2;

import java.io.*;

public class problem2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 처리
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[] wine = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 0;

        // 동적 프로그래밍 계산
        for (int i = 1; i <= N; i++) {
            if (i == 1) {
                dp[i] = wine[i];
            } else if (i == 2) {
                dp[i] = wine[i] + wine[i - 1];
            } else {
                dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
            }
        }

        // 결과 출력
        bw.write(String.valueOf(dp[N]));
        bw.flush();
        bw.close();
    }
}
