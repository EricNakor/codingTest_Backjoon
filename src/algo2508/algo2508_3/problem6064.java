package algo2508.algo2508_3;

import java.io.*;
import java.util.*;

public class problem6064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 최대공약수를 이용해 최소공배수 계산
            // 카잉 달력의 주기는 M과 N의 최소공배수
            int lcm = M * N / gcd(M, N);

            // 결과를 찾았는지 확인하는 플래그
            boolean found = false;

            // 핵심 최적화: x를 고정하고 M씩 건너뛰며 탐색
            // x, x+M, x+2M, x+3M, ... 형태로 년도 생성
            for (int year = x; year <= lcm; year += M) {
                // year를 N으로 나눈 나머지가 y와 같은지 확인
                // 나머지가 0인 경우는 N으로 처리 (1-based 인덱싱)
                if ((year - 1) % N + 1 == y) {
                    System.out.println(year);
                    found = true;
                    break;
                }
            }

            // 최소공배수까지 탐색해도 찾지 못한 경우
            if (!found) {
                System.out.println(-1);
            }
        }
    }

    // 최대공약수를 구하는 유클리드 호제법
    // 최소공배수 계산에 필요
    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
