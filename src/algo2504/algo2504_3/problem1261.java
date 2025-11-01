package algo2504.algo2504_3;

import java.io.*;
import java.util.*;

public class problem1261 {
    static int N, M;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Node implements Comparable<Node> {
        int x, y, cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        public int compareTo(Node o) {
            return this.cnt - o.cnt; // 부순 벽의 개수 오름차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.x == N - 1 && now.y == M - 1) {
                System.out.println(now.cnt);
                return;
            }
            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                int nextCnt = now.cnt + map[nx][ny];
                if (dist[nx][ny] > nextCnt) {
                    dist[nx][ny] = nextCnt;
                    pq.offer(new Node(nx, ny, nextCnt));
                }
            }
        }
    }
}
