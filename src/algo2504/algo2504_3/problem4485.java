package algo2504.algo2504_3;

import java.io.*;
import java.util.*;

public class problem4485 {
    static class Node implements Comparable<Node> {
        int y, x, cost;

        Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N;
    static int[][] map;
    static int[][] dist;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int problemNum = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            map = new int[N][N];
            dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                    dist[i][j] = INF;
                }
            }

            dijkstra();

            System.out.println("Problem " + problemNum + ": " + dist[N - 1][N - 1]);
            problemNum++;
        }
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0][0] = map[0][0];
        pq.offer(new Node(0, 0, dist[0][0]));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.y][now.x] < now.cost) continue;
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                int nextCost = now.cost + map[ny][nx];
                if (dist[ny][nx] > nextCost) {
                    dist[ny][nx] = nextCost;
                    pq.offer(new Node(ny, nx, nextCost));
                }
            }
        }
    }
}
