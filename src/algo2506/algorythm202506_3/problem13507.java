package algo2506.algorythm202506_3;

import java.io.*;
import java.util.*;

public class problem13507 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String goodStr = br.readLine();
        int K = Integer.parseInt(br.readLine());

        // 나쁜 문자 판별을 위한 Set 생성
        Set<Character> badChars = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            if (goodStr.charAt(i) == '0') {
                badChars.add((char) ('a' + i));
            }
        }

        // 누적합 배열 생성 (나쁜 문자의 개수)
        int[] prefixSum = new int[S.length() + 1];
        for (int i = 0; i < S.length(); i++) {
            prefixSum[i + 1] = prefixSum[i];
            if (badChars.contains(S.charAt(i))) {
                prefixSum[i + 1]++;
            }
        }

        // 좋은 부분 문자열을 저장할 Set (중복 제거)
        Set<String> goodSubstrings = new HashSet<>();

        // 모든 부분 문자열 확인
        for (int start = 0; start < S.length(); start++) {
            for (int end = start + 1; end <= S.length(); end++) {
                // start부터 end-1까지의 나쁜 문자 개수
                int badCount = prefixSum[end] - prefixSum[start];
                if (badCount <= K) {
                    goodSubstrings.add(S.substring(start, end));
                } else {
                    break; // 더 긴 부분 문자열은 나쁜 문자가 더 많아짐
                }
            }
        }

        System.out.println(goodSubstrings.size());
    }
}