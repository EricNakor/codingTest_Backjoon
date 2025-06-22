package algo2504.algorythm202504_5;

import java.io.*;
import java.util.*;

public class problem15686 {
    static int N, M, result = Integer.MAX_VALUE;
    static List<int[]> homeList = new ArrayList<>();      // 집 좌표 저장 리스트
    static List<int[]> chickenList = new ArrayList<>();   // 치킨집 좌표 저장 리스트
    static boolean[] visit;                               // 치킨집 선택 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 도시 정보 입력 및 집, 치킨집 좌표 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) homeList.add(new int[]{i, j});      // 집 좌표 저장
                else if (val == 2) chickenList.add(new int[]{i, j}); // 치킨집 좌표 저장
            }
        }

        visit = new boolean[chickenList.size()];
        backtracking(0, 0); // 백트래킹 시작

        System.out.println(result); // 결과 출력
    }

    // 치킨집 조합을 선택하는 백트래킹 함수
    static void backtracking(int idx, int cnt) {
        // M개 치킨집을 모두 선택한 경우
        if (cnt == M) {
            int cityDist = getCityChickenDistance();
            result = Math.min(result, cityDist); // 최소값 갱신
            return;
        }
        // 치킨집 조합 선택
        for (int i = idx; i < chickenList.size(); i++) {
            visit[i] = true;
            backtracking(i + 1, cnt + 1);
            visit[i] = false;
        }
    }

    // 선택된 치킨집들로 도시 치킨 거리 계산
    static int getCityChickenDistance() {
        int sum = 0;
        for (int[] home : homeList) {
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < chickenList.size(); i++) {
                if (visit[i]) {
                    int[] chicken = chickenList.get(i);
                    int dist = Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
                    minDist = Math.min(minDist, dist); // 집에서 가장 가까운 치킨집 거리
                }
            }
            sum += minDist; // 모든 집의 치킨 거리 합산
        }
        return sum;
    }
}
