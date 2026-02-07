package _2026.algo2602.algo2_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class problem1035 {
    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static ArrayList<Point> stars = new ArrayList<>(); // 초기 조각 위치
    static int K; // 조각의 개수
    static int minTotalMoves = Integer.MAX_VALUE;
    static int[] selectedIndices; // 조합으로 선택된 25칸 중의 인덱스 (0~24)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력 처리 및 조각 위치 저장
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                if (line.charAt(j) == '*') {
                    stars.add(new Point(i, j));
                }
            }
        }

        K = stars.size();
        selectedIndices = new int[K];

        // 2. 25개 칸 중 K개를 뽑는 모든 조합 탐색
        combination(0, 0);

        System.out.println(minTotalMoves);
    }

    // 조합 생성: index(0~24)부터 시작해서 count개가 될 때까지 선택
    static void combination(int index, int count) {
        if (count == K) {
            // K개를 모두 뽑았다면, 연결되어 있는지 확인
            if (isConnected()) {
                // 연결되어 있다면 최소 이동 비용 계산
                solveMatching(0, 0, 0);
            }
            return;
        }

        for (int i = index; i < 25; i++) {
            selectedIndices[count] = i;
            combination(i + 1, count + 1);
        }
    }

    // 선택된 K개의 위치가 하나로 연결되어 있는지 BFS로 확인
    static boolean isConnected() {
        if (K <= 1) return true;

        boolean[][] map = new boolean[5][5];
        Point startNode = null;

        // 선택된 인덱스를 좌표로 변환하여 맵에 표시
        for (int idx : selectedIndices) {
            int r = idx / 5;
            int c = idx % 5;
            map[r][c] = true;
            if (startNode == null) startNode = new Point(r, c);
        }

        // BFS 탐색
        Queue<Point> q = new LinkedList<>();
        q.add(startNode);
        boolean[][] visited = new boolean[5][5];
        visited[startNode.r][startNode.c] = true;

        int connectedCount = 1;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                // 맵 범위 안이고, 선택된 칸(map[nr][nc])이며, 방문하지 않았다면
                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && map[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    connectedCount++;
                    q.add(new Point(nr, nc));
                }
            }
        }

        // 방문한 칸의 개수가 전체 조각 개수와 같으면 모두 연결된 것
        return connectedCount == K;
    }

    // 초기 조각(stars)들을 선택된 위치(selectedIndices)로 이동시키는 최소 비용 계산
    // starIdx: 현재 매칭할 조각 인덱스
    // usedMask: 선택된 위치 중 이미 사용한 위치 비트마스크
    // currentSum: 현재까지 누적 이동 거리
    static void solveMatching(int starIdx, int usedMask, int currentSum) {
        // 가지치기 (Pruning): 이미 찾은 최소값보다 크면 중단
        if (currentSum >= minTotalMoves) return;

        if (starIdx == K) {
            minTotalMoves = currentSum;
            return;
        }

        Point star = stars.get(starIdx);

        // 현재 조각(star)을 아직 사용되지 않은 목표 위치(selectedIndices[i])에 매칭 시도
        for (int i = 0; i < K; i++) {
            if ((usedMask & (1 << i)) == 0) {
                int targetIdx = selectedIndices[i];
                int tr = targetIdx / 5;
                int tc = targetIdx % 5;

                // 맨해튼 거리 계산
                int dist = Math.abs(star.r - tr) + Math.abs(star.c - tc);

                solveMatching(starIdx + 1, usedMask | (1 << i), currentSum + dist);
            }
        }
    }
}
