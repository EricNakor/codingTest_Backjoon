package algo2507.algo2507_1;

import java.io.*;
public class problem9020 {
    public static boolean[] isPrime = new boolean[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 에라토스테네스의 체로 소수 판별
        sieve();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            // n/2부터 시작해서 아래로 내려가면서 탐색
            for (int j = n / 2; j >= 2; j--) {
                if (isPrime[j] && isPrime[n - j]) {
                    System.out.println(j + " " + (n - j));
                    break;
                }
            }
        }
    }

    // 에라토스테네스의 체
    public static void sieve() {
        // 초기값: 모든 수를 소수로 가정
        for (int i = 2; i <= 10000; i++) {
            isPrime[i] = true;
        }

        // 에라토스테네스의 체 알고리즘
        for (int i = 2; i * i <= 10000; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= 10000; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}