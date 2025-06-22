package algo2503.algorythm202503_1;

import java.io.*;
import java.util.StringTokenizer;

public class problem2468 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1}; // 상하좌우 이동
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 받기
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int maxHeight = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int maxSafeZone = 0;

        // 비의 높이를 0부터 maxHeight까지 시뮬레이션
        for (int rainHeight = 0; rainHeight <= maxHeight; rainHeight++) {
            visited = new boolean[N][N];
            int safeZoneCount = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 방문하지 않았고 현재 높이가 물에 잠기지 않는다면
                    if (!visited[i][j] && map[i][j] > rainHeight) {
                        dfs(i, j, rainHeight);
                        safeZoneCount++;
                    }
                }
            }

            maxSafeZone = Math.max(maxSafeZone, safeZoneCount);
        }

        // 결과 출력
        bw.write(maxSafeZone + "\n");
        bw.flush();
        bw.close();
    }

    // DFS를 사용하여 연결된 안전 영역 탐색
    private static void dfs(int x, int y, int rainHeight) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) { // 배열 범위 체크
                if (!visited[nx][ny] && map[nx][ny] > rainHeight) {
                    dfs(nx, ny, rainHeight);
                }
            }
        }
    }
}
