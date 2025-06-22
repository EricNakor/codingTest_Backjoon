package algo2504.algorythm202504_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine()); // 열 개수
            int[][] sticker = new int[2][n + 1]; // 스티커 점수 배열
            int[][] dp = new int[2][n + 1]; // DP 배열

            // 스티커 점수 입력받기
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 초기값 설정
            dp[0][1] = sticker[0][1];
            dp[1][1] = sticker[1][1];
            if (n > 1) {
                dp[0][2] = sticker[0][2] + dp[1][1];
                dp[1][2] = sticker[1][2] + dp[0][1];
            }

            // DP 점화식 계산
            for (int i = 3; i <= n; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + sticker[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + sticker[1][i];
            }

            // 결과 저장
            sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");
        }

        System.out.print(sb);
    }
}
