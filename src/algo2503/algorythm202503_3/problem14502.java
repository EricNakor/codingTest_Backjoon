package algo2503.algorythm202503_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem14502 {
    static int N, M;
    static int[][] map;
    static int[][] tempMap;
    static List<int[]> emptySpaces = new ArrayList<>();
    static List<int[]> virusPositions = new ArrayList<>();
    static int maxSafeArea = 0;

    // 방향 벡터 (상하좌우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 입력 처리 및 빈 칸/바이러스 위치 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) emptySpaces.add(new int[]{i, j});
                if (map[i][j] == 2) virusPositions.add(new int[]{i, j});
            }
        }

        // 벽 세우기 조합 생성 및 처리
        combination(0, 0);

        System.out.println(maxSafeArea);
    }

    // 조합으로 벽 세우기
    public static void combination(int start, int count) {
        if (count == 3) {
            simulate();
            return;
        }

        for (int i = start; i < emptySpaces.size(); i++) {
            int[] pos = emptySpaces.get(i);
            map[pos[0]][pos[1]] = 1; // 벽 세우기
            combination(i + 1, count + 1);
            map[pos[0]][pos[1]] = 0; // 원상복구
        }
    }

    // 시뮬레이션 실행 (BFS로 바이러스 확산)
    public static void simulate() {
        tempMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            tempMap[i] = map[i].clone();
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int[] pos : virusPositions) {
            queue.add(pos);
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && tempMap[nx][ny] == 0) {
                    tempMap[nx][ny] = 2;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        calculateSafeArea();
    }

    // 안전 영역 계산
    public static void calculateSafeArea() {
        int safeAreaCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempMap[i][j] == 0) safeAreaCount++;
            }
        }
        maxSafeArea = Math.max(maxSafeArea, safeAreaCount);
    }
}
