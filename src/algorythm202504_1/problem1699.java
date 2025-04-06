package algorythm202504_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = i; // 초기값 1제곱으로
            int maxJ = (int) Math.sqrt(i);

            // 큰 제곱수부터 탐색
            for (int j = maxJ; j >= 1; j--) {
                int square = j * j;
                if (dp[i - square] + 1 < dp[i]) {
                    dp[i] = dp[i - square] + 1;
                    break; // 최소값 찾으면 즉시 종료
                }
            }
        }

        System.out.println(dp[N]);
    }
}