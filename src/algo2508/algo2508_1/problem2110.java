package algo2508.algo2508_1;

import java.io.*;
import java.util.*;

public class problem2110 {
    static int N, C;
    static int[] houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        // 집 좌표 정렬 (이분 탐색의 전제 조건)
        Arrays.sort(houses);

        int answer = binarySearch();
        System.out.println(answer);
    }

    static int binarySearch() {
        int left = 1;                        // 최소 가능 거리
        int right = houses[N - 1] - houses[0]; // 최대 가능 거리
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canInstall(mid)) {
                // mid 거리로 C개 설치 가능 → 더 큰 거리 시도
                answer = mid;
                left = mid + 1;
            } else {
                // mid 거리로 C개 설치 불가능 → 더 작은 거리 시도
                right = mid - 1;
            }
        }

        return answer;
    }

    static boolean canInstall(int distance) {
        int count = 1;                // 첫 번째 집에 설치
        int lastInstalled = houses[0]; // 마지막 설치 위치

        for (int i = 1; i < N; i++) {
            // 마지막 설치 위치에서 distance 이상 떨어진 경우
            if (houses[i] - lastInstalled >= distance) {
                count++;
                lastInstalled = houses[i];

                // C개 설치 완료 시 조기 종료
                if (count >= C) {
                    return true;
                }
            }
        }

        return count >= C;
    }
}
