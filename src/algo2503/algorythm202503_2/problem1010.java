package algo2503.algorythm202503_2;

import java.io.*;

public class problem1010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int t = 0; t < T; t++) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);

            // 조합 계산 및 출력
            bw.write(combination(M, N) + "\n");
        }

        bw.flush();
        bw.close();
    }

    // 조합 계산 함수 >> nCm
    private static long combination(int m, int n) {
        long result = 1;

        // 조합 계산: C(m, n) = m! / (n! * (m-n)!)
        for (int i = 0; i < n; i++) {
            result *= (m - i);
            result /= (i + 1);
        }

        return result;
    }
}
