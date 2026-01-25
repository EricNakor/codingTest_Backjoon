package _2026.algo2601.algo1_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem11004 {
    public static void main(String[] args) throws IOException {
        // 대량의 입력을 빠르게 처리하기 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄 읽기 (N, K)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 500만 개의 데이터를 담을 배열
        int[] arr = new int[N];

        // 둘째 줄 데이터 읽기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬 (Dual-Pivot Quicksort, 평균 O(N log N))
        Arrays.sort(arr);

        // K번째 수 출력 (인덱스는 0부터 시작하므로 K-1)
        System.out.println(arr[K - 1]);
    }
}