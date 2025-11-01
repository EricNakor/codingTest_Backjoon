package algo2502.algo2502_2;

import java.io.*;

public class problem12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 처리
        String[] NK = br.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);

        int[][] items = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] WV = br.readLine().split(" ");
            items[i][0] = Integer.parseInt(WV[0]); // 무게
            items[i][1] = Integer.parseInt(WV[1]); // 가치
        }

        // 동적 프로그래밍 테이블 초기화
        int[][] dp = new int[N + 1][K + 1];

        // 동적 프로그래밍 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                // 현재 물품의 무게가 현재 배낭의 무게보다 크면 포함하지 않음
                if (items[i - 1][0] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 포함할지 말지 결정
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i - 1][0]] + items[i - 1][1]);
                }
            }
        }

        // 결과 출력
        bw.write(String.valueOf(dp[N][K]));
        bw.flush();
    }
}
