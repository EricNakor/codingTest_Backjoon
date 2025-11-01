package algo2508.algo2508_4;

import java.io.*;
import java.util.*;

public class problem15684 {
    static int N, M, H, answer = -1;
    static int[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H + 1][N + 1];

        // 이미 설치된 가로선 기록
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = 1;
        }
        // 0~3개의 사다리 추가
        for (int add = 0; add <= 3; add++) {
            dfs(0, add);
            if (answer != -1) {
                System.out.println(answer);
                return;
            }
        }
        System.out.println(-1); // 불가능한 경우
    }

    // depth: 현재까지 설치한 가로줄 수, goal: 추가할 가로줄 수
    static void dfs(int depth, int goal) {
        if (depth == goal) {
            if (check()) answer = goal;
            return;
        }
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (ladder[i][j] == 0 && ladder[i][j - 1] == 0 && ladder[i][j + 1] == 0) {
                    ladder[i][j] = 1; // 가로선 설치
                    dfs(depth + 1, goal);
                    ladder[i][j] = 0; // 원복(백트래킹)
                }
            }
        }
    }

    // i번 세로선을 출발해서 같은 번호로 끝나는지 체크
    static boolean check() {
        for (int start = 1; start <= N; start++) {
            int k = start;
            for (int i = 1; i <= H; i++) {
                if (ladder[i][k] == 1) k++;
                else if (ladder[i][k - 1] == 1) k--;
            }
            if (k != start) return false;
        }
        return true;
    }
}

