package algo2507.algo2507_1;

import java.io.*;
import java.util.StringTokenizer;

public class problem1011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 총 이동해야 할 거리
            int distance = y - x;
            // 작동 횟수
            int count = 0;

            // 최대 이동거리 n 계산 (distance 제곱)
            int max = (int) Math.sqrt(distance);

            if (max * max == distance) {
                // distance가 완전제곱수인 경우
                count = max * 2 - 1;
            } else if (distance <= max * max + max) {
                // n^2 < distance ≤ n^2 + n인 경우
                count = max * 2;
            } else {
                // n^2 + n < distance ≤ n^2 + 2n인 경우
                count = max * 2 + 1;
            }

            System.out.println(count);
        }
    }
}
