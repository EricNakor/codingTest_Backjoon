package algo2512.algo12_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem2098 {
    static int N;
    static int[][] W;
    static int[][] dp;
    static final int INF = 16_000_000; // 최대 비용(100만) * N(16) 보다 큰 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];

        // DP 테이블 초기화: [현재도시][방문마스크]
        // 비트마스크 크기: 2^N
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
            // DP 테이블을 -1로 초기화하여 방문 여부 체크 (0으로 초기화하면 비용이 0인 경우와 혼동됨)
            Arrays.fill(dp[i], -1);
        }

        // 0번 도시에서 시작, 비트마스크 1 (0번 도시 방문 표시)
        System.out.println(dfs(0, 1));
    }

    // city: 현재 위치한 도시 인덱스
    // mask: 지금까지 방문한 도시들의 비트마스크
    static int dfs(int city, int mask) {
        // 1. 기저 사례: 모든 도시를 방문한 경우 (mask가 11...11)
        if (mask == (1 << N) - 1) {
            // 현재 도시에서 시작 도시(0)로 돌아가는 길이 없으면 INF 반환
            if (W[city][0] == 0) return INF;
            return W[city][0];
        }

        // 2. 이미 계산한 적 있는 상태라면 메모이제이션 값 반환
        if (dp[city][mask] != -1) {
            return dp[city][mask];
        }

        dp[city][mask] = INF; // 초기값 설정

        // 3. 다음 도시 탐색
        for (int i = 0; i < N; i++) {
            // 이미 방문한 도시거나(mask 비트 확인), 가는 길이 없으면(W==0) 스킵
            if ((mask & (1 << i)) != 0 || W[city][i] == 0) continue;

            // 점화식: 현재 비용 + 나머지 경로의 최소 비용
            int cost = dfs(i, mask | (1 << i)) + W[city][i];
            dp[city][mask] = Math.min(dp[city][mask], cost);
        }

        return dp[city][mask];
    }
}
