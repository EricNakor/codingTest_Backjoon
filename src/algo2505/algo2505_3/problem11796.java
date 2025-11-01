package algo2505.algo2505_3;

import java.io.*;
import java.util.*;

public class problem11796 {
    static final int MOD = 1000000007;
    static int N, M;
    static int[] A, fail;
    static int[][] trans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        // 1. KMP 실패함수 계산
        fail = new int[N];
        for (int i = 1; i < N; i++) {
            int j = fail[i - 1];
            while (j > 0 && A[i] != A[j]) j = fail[j - 1];
            if (A[i] == A[j]) j++;
            fail[i] = j;
        }

        // 2. 최소 길이 계산
        int minLen = N + 1 - fail[N - 1];

        // 3. KMP 전이표(transition table) 만들기
        trans = new int[N][M + 2];
        for (int state = 0; state < N; state++) {
            for (int num = 1; num <= M; num++) {
                int next = state;
                while (next > 0 && num != A[next]) next = fail[next - 1];
                if (num == A[next]) next++;
                trans[state][num] = next;
            }
        }

        // 4. DP로 개수 세기
        int[][] dp = new int[minLen + 1][N];
        dp[0][0] = 1;
        for (int i = 0; i < minLen; i++) {
            for (int state = 0; state < N; state++) {
                if (dp[i][state] == 0) continue;
                for (int num = 1; num <= M; num++) {
                    int next = trans[state][num];
                    if (next == N) continue; // A가 부분수열로 등장하면 제외
                    dp[i + 1][next] = (dp[i + 1][next] + dp[i][state]) % MOD;
                }
            }
        }

        // 정답: dp[minLen][0 ~ N-1]의 합
        int total = 0;
        for (int state = 0; state < N; state++) {
            total = (total + dp[minLen][state]) % MOD;
        }

        System.out.println(minLen);
        System.out.println(total);
    }
}
