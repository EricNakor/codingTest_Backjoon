package algorythm202502_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1316 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int groupWordCount = 0;

        // 1. 입력 처리 및 그룹 단어 판별
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (isGroupWord(word)) {
                groupWordCount++;
            }
        }

        // 2. 결과 출력
        System.out.println(groupWordCount);
    }

    // 그룹 단어 판별 함수
    private static boolean isGroupWord(String word) {
        // 알파벳 26자 사용 여부 체크
        boolean[] used = new boolean[26];

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 1. 현재 문자가 이전 문자와 다르고
            // 2. 이미 사용된 적이 있는 경우
            if (/*i > 0 && */word.charAt(i - 1) != c && used[c - 'a']) {
                return false;
            }
            used[c - 'a'] = true;
        }
        return true;
    }
}
