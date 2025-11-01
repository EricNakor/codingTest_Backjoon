package algo2505.algo2505_1;

import java.io.*;
import java.util.*;

public class problem14889 {
    static int N;
    static int[][] S;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        visited = new boolean[N];

        // 능력치 배열 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 백트래킹 시작
        backtracking(0, 0);

        // 결과 출력
        System.out.println(min);
    }

    // N명 중 N/2명을 선택하는 조합(백트래킹) 함수
    static void backtracking(int idx, int count) {
        // N/2명 선택 완료 시
        if (count == N / 2) {
            calcDiff(); // 능력치 차이 계산
            return;
        }
        // idx부터 N까지 조합
        for (int i = idx; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }

    // 두 팀의 능력치 차이 계산 함수
    static void calcDiff() {
        int start = 0, link = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visited[i] && visited[j]) {
                    // 둘 다 스타트 팀이면 능력치 합산
                    start += S[i][j] + S[j][i];
                } else if (!visited[i] && !visited[j]) {
                    // 둘 다 링크 팀이면 능력치 합산
                    link += S[i][j] + S[j][i];
                }
            }
        }
        int diff = Math.abs(start - link);
        // 최소값 갱신
        if (diff == 0) {
            // 차이가 0이면 더 이상 탐색할 필요 없음
            System.out.println(0);
            System.exit(0);
        }
        min = Math.min(min, diff);
    }
}
