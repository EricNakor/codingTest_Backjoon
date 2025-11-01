package algo2505.algo2505_3;

import java.io.*;
import java.util.*;

public class problem13507 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String goodStr = br.readLine();
        int K = Integer.parseInt(br.readLine());

        // 나쁜 문자 판별을 위한 배열 생성
        boolean[] isBad = new boolean[26];
        for (int i = 0; i < 26; i++) {
            if (goodStr.charAt(i) == '0') {
                isBad[i] = true;
            }
        }

        // 누적합 배열 생성 (나쁜 문자의 개수)
        int[] prefixSum = new int[S.length() + 1];
        for (int i = 0; i < S.length(); i++) {
            prefixSum[i + 1] = prefixSum[i];
            if (isBad[S.charAt(i) - 'a']) {
                prefixSum[i + 1]++;
            }
        }

        // 해시(HashSet)를 이용해 좋은 부분 문자열을 저장 (중복 제거)
        Set<String> goodSubstrings = new HashSet<>();

        // 모든 부분 문자열 확인
        for (int start = 0; start < S.length(); start++) {
            for (int end = start + 1; end <= S.length(); end++) {
                // start부터 end-1까지의 나쁜 문자 개수
                int badCount = prefixSum[end] - prefixSum[start];
                if (badCount <= K) {
                    // 조건을 만족하면 해시에 추가 (중복 자동 제거)
                    goodSubstrings.add(S.substring(start, end));
                } else {
                    break; // 더 긴 부분 문자열은 나쁜 문자가 더 많아짐
                }
            }
        }

        // 해시의 크기 = 중복 제거된 좋은 부분 문자열의 개수
        System.out.println(goodSubstrings.size());
    }
}
