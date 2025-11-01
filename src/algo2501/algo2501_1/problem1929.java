package algo2501.algo2501_1;

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

    static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // 에라토스테네스의 체 ???
}
