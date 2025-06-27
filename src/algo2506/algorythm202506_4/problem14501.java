package algo2506.algorythm202506_4;

import java.io.*;
import java.util.StringTokenizer;

public class problem14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N: 퇴사까지 남은 일수
        int N = Integer.parseInt(br.readLine());

        // T: 상담을 완료하는데 걸리는 기간
        // P: 상담을 했을 때 받을 수 있는 금액
        // dp: 해당 일자부터 얻을 수 있는 최대 수익
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        int[] dp = new int[N + 2];

        // 상담 정보 입력 받기
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // 뒤에서부터 앞으로 오면서 dp 배열 채우기
        for (int i = N; i > 0; i--) {
            // 현재 일자에서 상담이 끝나는 날짜
            int next = i + T[i];

            // 상담이 퇴사일을 넘어가는 경우
            if (next > N + 1) {
                dp[i] = dp[i + 1];
            }
            // 상담이 가능한 경우
            else {
                // 현재 상담을 하는 경우와 하지 않는 경우 중 최대값 선택
                dp[i] = Math.max(dp[i + 1], P[i] + dp[next]);
            }
        }

        // 최대 수익 출력
        bw.write(String.valueOf(dp[1]));
        bw.flush();
        bw.close();
        br.close();
    }
}
