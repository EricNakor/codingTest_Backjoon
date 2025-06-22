package algo2503.algorythm202503_3;

import java.io.*;

public class problem4948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 최대 범위 설정
        int maxRange = 246912;
        boolean[] isPrime = new boolean[maxRange + 1];

        // 에라토스테네스의 체 -> 소수 판별
        for (int i = 2; i <= maxRange; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= maxRange; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= maxRange; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            // n보다 크고, 2n보다 작거나 같은 소수 개수 카운트
            int count = 0;
            for (int i = n + 1; i <= 2 * n; i++) {
                if (isPrime[i]) {
                    count++;
                }
            }

            // 결과 출력
            bw.write(count + "\n");
        }

        bw.flush();
        bw.close();
    }
}
