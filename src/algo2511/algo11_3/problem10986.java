package algo2511.algo11_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // cnt[k]: 누적 합을 M으로 나눈 나머지가 k인 인덱스의 수
        long[] cnt = new long[M];
        long sum = 0; // 누적 합

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            sum = (sum + num) % M; // 누적 합의 나머지를 바로 계산

            cnt[(int) sum]++; // 해당 나머지의 개수 증가
        }

        // 1. 나머지가 0인 경우, 그 자체로 정답 하나가 됨 (0번 인덱스부터의 구간)
        long answer = cnt[0];

        // 2. 나머지가 같은 두 인덱스를 뽑는 경우의 수 (Combination: nC2)
        for (int i = 0; i < M; i++) {
            if (cnt[i] > 1) {
                answer += (cnt[i] * (cnt[i] - 1)) / 2;
            }
        }

        System.out.println(answer);
    }
}
