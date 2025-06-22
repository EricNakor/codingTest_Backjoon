package algo2503.algorythm202503_2;

import java.io.*;

public class problem9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 파도반 수열 계산 (DP 배열 초기화)
        long[] dp = new long[101];
        dp[1] = dp[2] = dp[3] = 1;
        for (int i = 4; i <= 100; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }

        // 입력
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            bw.write(dp[N] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
