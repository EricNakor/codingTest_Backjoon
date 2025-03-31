package algorythm202503_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열의 길이
        int S = Integer.parseInt(st.nextToken()); // 목표값

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터 초기화
        int start = 0, end = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while (end <= N) {
            if (sum >= S) { // 현재 부분합이 목표값 이상인 경우
                minLength = Math.min(minLength, end - start); // 최소 길이 갱신
                sum -= arr[start]; // 시작 포인터 이동하며 값 제거
                start++;
            } else { // 현재 부분합이 목표값보다 작은 경우
                if (end < N) {
                    sum += arr[end]; // 끝 포인터 이동하며 값 추가
                }
                end++;
            }
        }
        // 결과 출력
        if (minLength == Integer.MAX_VALUE) {
            System.out.println(0); // 조건을 만족하는 부분합 없음
        } else {
            System.out.println(minLength);
        }
    }
}