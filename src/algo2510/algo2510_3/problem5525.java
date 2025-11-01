package algo2510.algo2510_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] S = br.readLine().toCharArray();

        int result = 0;
        int patternCount = 0;

        for (int i = 1; i < M - 1; i++) {
            if (S[i - 1] == 'I' && S[i] == 'O' && S[i + 1] == 'I') {
                patternCount++;
                if (patternCount == N) {
                    result++;
                    patternCount--; // 슬라이딩 윈도우처럼 한 칸 이동
                }
                i++; // IO'I' 다음으로 바로 점프
            } else {
                patternCount = 0;
            }
        }

        System.out.println(result);
    }
}
