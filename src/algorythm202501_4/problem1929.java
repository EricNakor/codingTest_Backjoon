package algorythm202501_4;

import java.io.*;
import java.util.StringTokenizer;

public class problem1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        for (int i = M; i <= N; i++) {
            if (isPrime(i)) {
                bw.write(Integer.toString(i));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // 에라토스테네스의 체 >> 자연수 1 제거, 2의 배수 제거, 3의 배수 제거
    // 제곱하여 소수가 아닌 것은 모두 제거하여 코드 효율화
    static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
