package algo2511.algo11_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class problem9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int N = str1.length();
        int M = str2.length();

        int[][] dp = new int[N + 1][M + 1];

        // 1. DP 테이블을 채워 LCS 길이 구하기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 1-1. 길이 출력
        int lcsLength = dp[N][M];
        System.out.println(lcsLength);

        // 2. LCS 문자열 역추적
        StringBuilder sb = new StringBuilder();
        int i = N;
        int j = M;

        while (lcsLength > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
                lcsLength--; // LCS 길이를 1 줄여도 됨 (dp[i][j] == 0 체크 대신)
            }
            // 현재 dp값이 왼쪽 값과 같다면, 왼쪽으로 이동
            else if (dp[i][j] == dp[i][j - 1]) {
                j--;
            }
            // 현재 dp값이 위쪽 값과 같다면, 위로 이동
            else { // dp[i][j] == dp[i-1][j]
                i--;
            }
        }

        // 2-1. 문자열 출력 (역순으로 저장했으므로 reverse)
        System.out.println(sb.reverse().toString());
    }
}
