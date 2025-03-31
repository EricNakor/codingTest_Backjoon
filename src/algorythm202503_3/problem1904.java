package algorythm202503_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // DP 배열 초기화
        if (N == 1) {
            System.out.println(1);
            return;
        }

        int[] dp = new int[N + 1];
        dp[1] = 1; // 길이가 1일 때 경우의 수
        dp[2] = 2; // 길이가 2일 때 경우의 수

        // 점화식을 이용해 DP 배열 채우기
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        // 결과 출력
        System.out.println(dp[N]);
    }
}