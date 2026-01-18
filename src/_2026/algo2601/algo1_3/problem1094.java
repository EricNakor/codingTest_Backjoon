package _2026.algo2601.algo1_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class problem1094 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 0; i < 7; i++) {
            // i번째 비트가 1인지 확인 (1, 2, 4, 8, 16, 32, 64)
            if ((X & (1 << i)) > 0) {
                count++;
            }
        }

        System.out.println(count);
    }
}
