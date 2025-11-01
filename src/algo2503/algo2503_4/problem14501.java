package algo2503.algo2503_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 총 일수 입력받기

        int[] T = new int[N + 1]; // 상담 기간 배열
        int[] P = new int[N + 1]; // 상담 금액 배열
        int[] dp = new int[N + 2]; // DP 배열 (크기: N+2)

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // DP 계산
        for (int i = N; i >= 1; i--) {
            if (i + T[i] > N + 1) { // 오늘 날짜부터 걸리는 시간이 퇴사날보다 클 때 상담이 불가한 경우
                dp[i] = dp[i + 1];
            } else { // 상담이 가능한 경우
                dp[i] = Math.max(dp[i + 1], dp[i + T[i]] + P[i]);
            }
        }

        // 결과 출력
        System.out.println(dp[1]);
    }
}
