package algo2510.algo2510_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem11051 {
    static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // dp[n][k] = C(n, k) % MOD
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                // 기저 사례: nC0 = 1, nCn = 1
                if (j == 0 || j == i) {
                    dp[i][j] = 1;
                }
                // 점화식: nCk = (n-1Ck-1 + n-1Ck) % MOD
                else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
