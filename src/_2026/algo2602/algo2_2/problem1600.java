package _2026.algo2602.algo2_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem1600 {
    // 큐에 넣을 원숭이의 상태 정보 클래스
    static class Node {
        int r, c, k, moves;

        public Node(int r, int c, int k, int moves) {
            this.r = r;
            this.c = c;
            this.k = k;         // 사용한 특별 점프권(말 이동) 횟수
            this.moves = moves; // 총 이동 횟수
        }
    }

    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;

    // 상하좌우 (원숭이의 일반 이동)
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    // 체스 나이트의 8방향 (말의 이동)
    static int[] hr = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] hc = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        // visited[r][c][k]: 점프권을 k번 쓰고 (r, c)에 방문했는지 여부
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        // 시작점과 도착점이 같은 경우 (예외 처리)
        if (W == 1 && H == 1) return 0;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node current = q.poll();

            // 목적지에 도착한 경우 바로 종료 (BFS이므로 가장 먼저 도착한 것이 최소 횟수)
            if (current.r == H - 1 && current.c == W - 1) {
                return current.moves;
            }

            // 1. 일반 이동 (상하좌우 4방향)
            for (int i = 0; i < 4; i++) {
                int nr = current.r + dr[i];
                int nc = current.c + dc[i];

                // 맵을 벗어나지 않고, 장애물이 아니며, 해당 점프권 사용 횟수로 방문한 적이 없는 경우
                if (nr >= 0 && nr < H && nc >= 0 && nc < W) {
                    if (map[nr][nc] == 0 && !visited[nr][nc][current.k]) {
                        visited[nr][nc][current.k] = true;
                        q.add(new Node(nr, nc, current.k, current.moves + 1));
                    }
                }
            }

            // 2. 말의 이동 (특별 점프권 사용, K번 미만으로 사용했을 때만 가능)
            if (current.k < K) {
                for (int i = 0; i < 8; i++) {
                    int nr = current.r + hr[i];
                    int nc = current.c + hc[i];

                    // 맵을 벗어나지 않고, 장애물이 아니며, 점프권을 한 번 더 쓴 상태로 방문한 적이 없는 경우
                    if (nr >= 0 && nr < H && nc >= 0 && nc < W) {
                        if (map[nr][nc] == 0 && !visited[nr][nc][current.k + 1]) {
                            visited[nr][nc][current.k + 1] = true;
                            // 점프권을 썼으므로 k를 1 증가시켜 큐에 넣음
                            q.add(new Node(nr, nc, current.k + 1, current.moves + 1));
                        }
                    }
                }
            }
        }

        // 큐가 다 빌 때까지 목적지에 도달하지 못했다면
        return -1;
    }
}