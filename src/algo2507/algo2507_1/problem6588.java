package algo2507.algo2507_1;
import java.io.*;

public class problem6588 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 에라토스테네스의 체로 소수 판별 배열 생성
        boolean[] isPrime = new boolean[1000001];
        // 초기값을 true로 설정 (소수로 가정)
        for (int i = 2; i <= 1000000; i++) {
            isPrime[i] = true;
        }

        // 에라토스테네스의 체 알고리즘
        for (int i = 2; i * i <= 1000000; i++) {
            if (isPrime[i]) {
                // i의 배수들을 모두 소수가 아님으로 표시
                for (int j = i * i; j <= 1000000; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int n;
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            boolean found = false;

            // 3부터 n/2까지 홀수만 확인 (b-a가 최대가 되도록)
            for (int i = 3; i <= n / 2; i += 2) {
                if (isPrime[i] && isPrime[n - i]) {
                    bw.write(n + " = " + i + " + " + (n - i) + "\n");
                    found = true;
                    break;
                }
            }

            if (!found) {
                bw.write("Goldbach's conjecture is wrong.\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
