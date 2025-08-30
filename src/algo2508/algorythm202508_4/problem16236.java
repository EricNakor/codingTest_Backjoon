package algo2508.algorythm202508_4;

import java.io.*;
import java.util.*;

public class problem16236 {
    static int N, sharkX, sharkY, sharkSize, eatCount, answer;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    static class Fish implements Comparable<Fish> {
        int x, y, dist;

        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public int compareTo(Fish f) {
            // 거리→행→열 순 우선
            if (this.dist != f.dist) return this.dist - f.dist;
            if (this.x != f.x) return this.x - f.x;
            return this.y - f.y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }
        sharkSize = 2;
        eatCount = 0;
        answer = 0;

        while (true) {
            Fish nextFish = bfs();
            if (nextFish == null) break;

            // 먹이 위치, 거리 반영
            sharkX = nextFish.x;
            sharkY = nextFish.y;
            answer += nextFish.dist;
            map[sharkX][sharkY] = 0;
            eatCount++;

            // 사이즈 증가
            if (eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
        }
        System.out.println(answer);
    }

    static Fish bfs() {
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sharkX, sharkY, 0});
        visited[sharkX][sharkY] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2];
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] || map[nx][ny] > sharkSize) continue;

                visited[nx][ny] = true;
                if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
                    pq.offer(new Fish(nx, ny, dist + 1));
                }
                q.offer(new int[]{nx, ny, dist + 1});
            }
        }
        return pq.isEmpty() ? null : pq.poll();
    }
}
