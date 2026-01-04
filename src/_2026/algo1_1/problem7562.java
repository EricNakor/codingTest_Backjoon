package _2026.algo1_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem7562 {

    // 나이트의 8가지 이동 방향
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    static int I; // 체스판 크기
    static int startX, startY;
    static int targetX, targetY;
    static int[][] visited; // 방문 여부 및 이동 횟수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        while (T-- > 0) {
            I = Integer.parseInt(br.readLine());
            visited = new int[I][I]; // 각 케이스마다 초기화

            // 시작점
            StringTokenizer st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());

            // 목표점
            st = new StringTokenizer(br.readLine());
            targetX = Integer.parseInt(st.nextToken());
            targetY = Integer.parseInt(st.nextToken());

            // BFS 수행 및 결과 저장
            sb.append(bfs()).append("\n");
        }

        System.out.print(sb);
    }

    static int bfs() {
        // 시작과 끝이 같으면 0 반환
        if (startX == targetX && startY == targetY) return 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});
        visited[startX][startY] = 1; // 방문 처리 (횟수 계산 편의상 1부터 시작하고 나중에 -1)

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 8; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 1. 체스판 범위 내에 있고
                // 2. 아직 방문하지 않았다면
                if (nx >= 0 && nx < I && ny >= 0 && ny < I && visited[nx][ny] == 0) {
                    visited[nx][ny] = visited[cx][cy] + 1;
                    q.add(new int[]{nx, ny});

                    // 목표 지점에 도달했으면 즉시 반환
                    if (nx == targetX && ny == targetY) {
                        return visited[nx][ny] - 1; // 시작을 1로 했으므로 1을 빼줌
                    }
                }
            }
        }
        return 0; // 도달할 수 없는 경우는 없으므로(체스판) 실행되지 않음
    }
}
