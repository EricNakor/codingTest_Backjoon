package algo2502.algorythm202502_2;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class problem2206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 처리
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // BFS
        int[][][] visited = new int[N][M][2]; // [x][y][broken]
        Deque<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 1, false));
        visited[0][0][0] = 1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            // 목적지 도착한 경우
            if (p.x == N - 1 && p.y == M - 1) {
                bw.write(String.valueOf(p.dist));
                bw.flush();
                return;
            }

            // 인접 칸 탐색
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // 경계 체크
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                // 벽이고 아직 부수지 않은 경우
                if (map[nx][ny] == 1 && p.broken == 0 && visited[nx][ny][1] == 0) {
                    visited[nx][ny][1] = p.dist + 1;
                    queue.offer(new Point(nx, ny, p.dist + 1, true));
                }
                // 벽이 아니고 아직 방문하지 않은 경우
                else if (map[nx][ny] == 0 && visited[nx][ny][p.broken] == 0) {
                    visited[nx][ny][p.broken] = p.dist + 1;
                    queue.offer(new Point(nx, ny, p.dist + 1, p.broken == 1));
                }
            }
        }

        // 목적지에 도달할 수 없는 경우
        bw.write("-1");
        bw.flush();
    }

    static class Point {
        int x, y, dist, broken;

        public Point(int x, int y, int dist, boolean broken) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broken = broken ? 1 : 0;
        }
    }
}
