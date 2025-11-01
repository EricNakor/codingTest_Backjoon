package algo2508.algo2508_3;

import java.io.*;
import java.util.*;

public class problem16234 {
    static int N, L, R;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0; // 인구 이동이 발생한 일수

        // 더 이상 인구 이동이 발생하지 않을 때까지 반복
        while (true) {
            visited = new boolean[N][N]; // 매일 방문 배열 초기화
            boolean moved = false; // 해당 날에 인구 이동이 발생했는지 확인

            // 모든 국가를 순회하며 연합 생성 시도
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        // BFS로 연합 찾기 및 인구 이동 처리
                        if (bfs(i, j)) {
                            moved = true; // 인구 이동 발생
                        }
                    }
                }
            }

            // 인구 이동이 발생하지 않았다면 종료
            if (!moved) break;
            days++; // 인구 이동 발생 시 일수 증가
        }

        System.out.println(days);
    }

    // BFS로 연합을 찾고 인구 이동을 처리하는 메서드
    static boolean bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> union = new ArrayList<>(); // 연합에 속한 국가들 저장

        queue.offer(new int[]{x, y});
        union.add(new int[]{x, y});
        visited[x][y] = true;

        int totalPopulation = board[x][y]; // 연합의 총 인구수

        // BFS 탐색으로 연합 구성
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[11];

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 체크 및 방문 여부 확인
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    // 인구 차이 계산
                    int diff = Math.abs(board[cx][cy] - board[nx][ny]);

                    // 연합 조건 확인 (L 이상 R 이하)
                    if (diff >= L && diff <= R) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        union.add(new int[]{nx, ny});
                        totalPopulation += board[nx][ny]; // 연합 총 인구수에 추가
                    }
                }
            }
        }

        // 연합이 2개 이상의 국가로 구성되었다면 인구 이동 발생
        if (union.size() >= 2) {
            // 연합의 새로운 인구수 = 총 인구수 / 국가 수
            int newPopulation = totalPopulation / union.size();

            // 연합에 속한 모든 국가의 인구수 갱신
            for (int[] country : union) {
                board[country[0]][country[1]] = newPopulation;
            }

            return true; // 인구 이동 발생
        }

        return false; // 연합이 구성되지 않음
    }
}
