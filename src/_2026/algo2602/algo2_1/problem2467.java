package _2026.algo2602.algo2_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N]; // 10억 + 10억은 int 범위를 넘지 않지만, 안전하게 long 사용

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 투 포인터 초기화
        int left = 0;
        int right = N - 1;

        long minDiff = Long.MAX_VALUE;
        long ans1 = 0, ans2 = 0;

        while (left < right) {
            long sum = arr[left] + arr[right];

            // 1. 현재 합의 절댓값이 최소인지 확인 후 갱신
            if (Math.abs(sum) < minDiff) {
                minDiff = Math.abs(sum);
                ans1 = arr[left];
                ans2 = arr[right];
            }

            // 2. 합이 0이면 최적의 해이므로 종료
            if (sum == 0) {
                break;
            }

            // 3. 포인터 이동
            if (sum < 0) {
                left++; // 합을 키워야 함 -> 왼쪽 포인터 이동
            } else {
                right--; // 합을 줄여야 함 -> 오른쪽 포인터 이동
            }
        }

        System.out.println(ans1 + " " + ans2);
    }
}
