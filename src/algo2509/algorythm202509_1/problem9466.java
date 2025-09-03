package algo2509.algorythm202509_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem9466 {
    static int[] choice;
    static boolean[] visited;
    static boolean[] finished;
    static int teamCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            choice = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            teamCount = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                choice[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!finished[i]) {
                    dfs(i);
                }
            }

            System.out.println(n - teamCount);
        }
    }

    static void dfs(int student) {
        visited[student] = true;
        int next = choice[student];

        if (!visited[next]) {
            // 다음 학생이 방문되지 않았으면 계속 탐색
            dfs(next);
        } else if (!finished[next]) {
            // 다음 학생이 방문되었지만 탐색이 완료되지 않음 → 사이클 발견
            int current = next;
            do {
                teamCount++;
                current = choice[current];
            } while (current != next);
        }

        finished[student] = true;
    }
}
