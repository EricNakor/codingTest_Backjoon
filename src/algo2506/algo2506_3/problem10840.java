package algo2506.algo2506_3;

import java.io.*;
import java.util.*;

public class problem10840 {
    static int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();

        int maxLen = Math.min(A.length(), B.length());

        // 최대 길이부터 1까지 순차 탐색
        for (int len = maxLen; len >= 1; len--) {
            if (hasCommonSubstring(A, B, len)) {
                System.out.println(len);
                return;
            }
        }

        System.out.println(0);
    }

    // 길이 len인 부분 문자열 중 같은 성분을 가진 것이 있는지 확인
    static boolean hasCommonSubstring(String A, String B, int len) {
        Set<Long> hashSetA = new HashSet<>();
        Set<Long> hashSetB = new HashSet<>();

        // A 문자열의 모든 길이 len 부분 문자열 해시값 계산
        for (int i = 0; i <= A.length() - len; i++) {
            long hash = calculateHash(A, i, i + len - 1);
            hashSetA.add(hash);
        }

        // B 문자열의 모든 길이 len 부분 문자열 해시값 계산
        for (int i = 0; i <= B.length() - len; i++) {
            long hash = calculateHash(B, i, i + len - 1);
            hashSetB.add(hash);
        }

        // 교집합이 있는지 확인
        for (long hash : hashSetA) {
            if (hashSetB.contains(hash)) {
                return true;
            }
        }

        return false;
    }

    // 부분 문자열의 해시값 계산 (알파벳 개수 기반)
    static long calculateHash(String str, int start, int end) {
        int[] count = new int[26];

        // 각 알파벳 개수 세기
        for (int i = start; i <= end; i++) {
            count[str.charAt(i) - 'a']++;
        }

        // 해시값 계산 (각 알파벳 개수 × 해당 소수)
        long hash = 0;
        for (int i = 0; i < 26; i++) {
            hash += (long) count[i] * prime[i];
        }

        return hash;
    }
}