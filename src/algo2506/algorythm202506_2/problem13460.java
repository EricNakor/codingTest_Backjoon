package algo2506.algorythm202506_2;

import java.io.*;
import java.util.*;

public class problem13460 {
    static int N, M;
    static char[][] board;
    static boolean[][][][] visited;
    // 이동 방향: 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M][N][M];
        int startRx = 0, startRy = 0, startBx = 0, startBy = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    startRx = i;
                    startRy = j;
                } else if (board[i][j] == 'B') {
                    startBx = i;
                    startBy = j;
                }
            }
        }

        bfs(new Marble(startRx, startRy, startBx, startBy, 0));
    }

    static void bfs(Marble start) {
        Queue<Marble> queue = new LinkedList<>();
        queue.add(start);
        visited[start.rx][start.ry][start.bx][start.by] = true;

        while (!queue.isEmpty()) {
            Marble current = queue.poll();

            // 10번을 초과한 경우 더 이상 탐색하지 않음
            if (current.count >= 10) continue;

            for (int i = 0; i < 4; i++) {
                // 현재 구슬 위치에서 기울이기 시작
                int nrx = current.rx;
                int nry = current.ry;
                int nbx = current.bx;
                int nby = current.by;

                // 빨간 구슬 이동
                int rMoveCount = 0;
                while (board[nrx + dx[i]][nry + dy[i]] != '#' && board[nrx][nry] != 'O') {
                    nrx += dx[i];
                    nry += dy[i];
                    rMoveCount++;
                }

                // 파란 구슬 이동
                int bMoveCount = 0;
                while (board[nbx + dx[i]][nby + dy[i]] != '#' && board[nbx][nby] != 'O') {
                    nbx += dx[i];
                    nby += dy[i];
                    bMoveCount++;
                }

                // 파란 구슬이 구멍에 빠지면 실패. 이 경우는 무시함
                if (board[nbx][nby] == 'O') continue;

                // 빨간 구슬만 구멍에 빠지면 성공. 횟수 출력 후 종료
                if (board[nrx][nry] == 'O') {
                    System.out.println(current.count + 1);
                    return;
                }

                // 두 구슬이 같은 위치에 있을 경우 위치 조정
                if (nrx == nbx && nry == nby) {
                    if (rMoveCount > bMoveCount) { // 더 많이 움직인 구슬이 뒤에 있었음
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }

                // 방문하지 않은 새로운 상태라면 큐에 추가
                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    queue.add(new Marble(nrx, nry, nbx, nby, current.count + 1));
                }
            }
        }

        // 큐가 비워질 때까지 성공하지 못하면 -1 출력
        System.out.println(-1);
    }
}

// 구슬 상태 저장
class Marble {
    int rx, ry, bx, by, count;

    Marble(int rx, int ry, int bx, int by, int count) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.count = count;
    }
}
