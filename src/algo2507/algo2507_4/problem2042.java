package algo2507.algo2507_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2042 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];

        // 배열 초기화
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 쿼리 처리
        while (true) {
            if (m == 0 && k == 0) break;

            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                // 값 변경
                int idx = Integer.parseInt(st.nextToken()) - 1;
                long num = Long.parseLong(st.nextToken());
                arr[idx] = num;
                m--;
            } else {
                // 구간 합 계산
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken());
                long sum = 0;

                for (int i = start; i < end; i++) {
                    sum += arr[i];
                }

                sb.append(sum + "\n");
                k--;
            }
        }

        System.out.println(sb.toString());
    }
}
