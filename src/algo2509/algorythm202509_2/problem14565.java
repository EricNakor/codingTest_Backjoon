package algo2509.algorythm202509_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem14565 {
    // 확장 유클리드 호제법
    // a*s + b*t = gcd(a, b) 를 만족하는 [gcd, s, t] 배열 반환
    public static long[] extendedGcd(long a, long b) {
        if (b == 0) {
            return new long[]{a, 1, 0};
        }

        long[] vals = extendedGcd(b, a % b);
        long gcd = vals[0];
        long s = vals[2];
        long t = vals[1] - (a / b) * vals[2];
        return new long[]{gcd, s, t};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long A = Long.parseLong(st.nextToken());

        // 1. 덧셈의 역원 (x)
        // A + x = 0 (mod N) -> x = -A (mod N)
        // 0 < x < N 범위이므로 x = N - A
        long additiveInverse = N - A;

        // 2. 곱셈의 역원 (y)
        // A * y = 1 (mod N)
        // 확장 유클리드 호제법 사용
        long[] result = extendedGcd(A, N);
        long s = result[1];

        // 결과 s가 음수일 수 있으므로 (0, N) 범위로 변환
        long multiplicativeInverse = (s % N + N) % N;

        System.out.println(additiveInverse + " " + multiplicativeInverse);
    }
}
