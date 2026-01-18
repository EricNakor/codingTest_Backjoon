package _2026.algo2601.algo1_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem1062 {
    static int N, K;
    static int[] words; // 각 단어를 비트마스크로 저장
    static int maxCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 1. 기본 글자 5개도 못 배우면 0개
        if (K < 5) {
            System.out.println(0);
            return;
        }
        // 2. 모든 글자를 배울 수 있으면 N개
        if (K == 26) {
            System.out.println(N);
            return;
        }

        words = new int[N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int mask = 0;
            for (char c : str.toCharArray()) {
                mask |= (1 << (c - 'a')); // 각 알파벳 위치의 비트를 켬
            }
            words[i] = mask;
        }

        // 기본 5글자('a', 'c', 'i', 'n', 't') 세팅
        int baseMask = 0;
        baseMask |= (1 << ('a' - 'a'));
        baseMask |= (1 << ('c' - 'a'));
        baseMask |= (1 << ('i' - 'a'));
        baseMask |= (1 << ('n' - 'a'));
        baseMask |= (1 << ('t' - 'a'));

        // 0번 알파벳('a')부터 시작, 현재까지 뽑은 추가 글자 수는 0개
        // baseMask를 들고 탐색 시작
        dfs(0, 0, baseMask);

        System.out.println(maxCount);
    }

    // index: 현재 탐색 중인 알파벳 인덱스 (0~25)
    // count: 지금까지 추가로 배운 글자의 수 (목표: K-5)
    // learnedMask: 현재까지 배운 글자들의 비트마스크
    static void dfs(int index, int count, int learnedMask) {
        // 목표 개수만큼 글자를 다 뽑았으면 검증
        if (count == K - 5) {
            int readable = 0;
            for (int word : words) {
                // 단어의 모든 글자가 배운 글자 안에 포함되는지 확인
                // (word & learnedMask) == word : word가 learnedMask의 부분집합인가?
                if ((word & learnedMask) == word) {
                    readable++;
                }
            }
            maxCount = Math.max(maxCount, readable);
            return;
        }

        // 알파벳 탐색 (index부터 25까지)
        for (int i = index; i < 26; i++) {
            // 이미 배운 글자(a, c, i, n, t)는 건너뜀
            if ((learnedMask & (1 << i)) != 0) continue;

            // i번째 알파벳을 배우고 다음 단계로 (백트래킹)
            dfs(i + 1, count + 1, learnedMask | (1 << i));
        }
    }
}
