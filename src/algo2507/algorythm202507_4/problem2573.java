package algo2507.algorythm202507_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem2573 {
    public class Main {
        static int N, M;
        static int[][] map;
        static int[] dx = {-1, 1, 0, 0};
        static int[] dy = {0, 0, -1, 1};

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int year = 0;
            while (true) {
                int pieces = countPieces();

                if (pieces >= 2) {
                    System.out.println(year);
                    break;
                } else if (pieces == 0) {
                    System.out.println(0);
                    break;
                }

                melt();
                year++;
            }
        }

        // 빙산 덩어리 개수 세기
        public static int countPieces() {
            boolean[][] visited = new boolean[N][M];
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        bfs(i, j, visited);
                        count++;
                    }
                }
            }
            return count;
        }

        // 빙산 녹이기
        public static void melt() {
            // 동시에 녹는 것을 처리하기 위해 녹는 양을 따로 저장할 배열
            int[][] meltAmount = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0) {
                        int seaCount = 0;
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
                                seaCount++;
                            }
                        }
                        meltAmount[i][j] = seaCount;
                    }
                }
            }

            // 계산된 양만큼 한 번에 녹이기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = Math.max(0, map[i][j] - meltAmount[i][j]);
                }
            }
        }

        // 연결된 빙산 덩어리 탐색 (BFS)
        public static void bfs(int x, int y, boolean[][] visited) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{x, y});
            visited[x][y] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int cx = current[0];
                int cy = current[1];

                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (map[nx][ny] > 0 && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            queue.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
    }
}