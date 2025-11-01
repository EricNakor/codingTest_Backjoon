package algo2505.algo2505_1;

import java.io.*;
import java.util.*;

public class problem1759 {
    static int L, C;
    static char[] chars;
    static char[] password;
    static final char[] vowels = {'a', 'e', 'i', 'o', 'u'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        chars = new char[C];
        password = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            chars[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(chars); // 사전순 출력을 위해 정렬

        dfs(0, 0, 0, 0); // 백트래킹 시작
    }

    // idx: chars에서 선택할 시작 인덱스
    // depth: 현재 암호의 길이
    // vowel: 모음 개수
    // consonant: 자음 개수
    static void dfs(int idx, int depth, int vowel, int consonant) {
        if (depth == L) {
            // 모음 1개 이상, 자음 2개 이상
            if (vowel >= 1 && consonant >= 2) {
                System.out.println(new String(password));
            }
            return;
        }
        for (int i = idx; i < C; i++) {
            password[depth] = chars[i];
            if (isVowel(chars[i])) {
                dfs(i + 1, depth + 1, vowel + 1, consonant);
            } else {
                dfs(i + 1, depth + 1, vowel, consonant + 1);
            }
        }
    }

    // 해당 문자가 모음인지 판별
    static boolean isVowel(char c) {
        for (char v : vowels) {
            if (c == v) return true;
        }
        return false;
    }
}
