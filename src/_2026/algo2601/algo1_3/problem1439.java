package _2026.algo2601.algo1_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class problem1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int zeroBlocks = 0; // 0으로 이루어진 덩어리의 개수
        int oneBlocks = 0;  // 1로 이루어진 덩어리의 개수

        // 첫 번째 문자 처리
        if (s.charAt(0) == '0') {
            zeroBlocks++;
        } else {
            oneBlocks++;
        }

        // 두 번째 문자부터 끝까지 순회
        for (int i = 1; i < s.length(); i++) {
            // 이전 문자와 다르면 새로운 덩어리가 시작된 것
            if (s.charAt(i) != s.charAt(i - 1)) {
                if (s.charAt(i) == '0') {
                    zeroBlocks++;
                } else {
                    oneBlocks++;
                }
            }
        }

        // 둘 중 더 적은 횟수의 덩어리를 뒤집으면 됨
        System.out.println(Math.min(zeroBlocks, oneBlocks));
    }
}
