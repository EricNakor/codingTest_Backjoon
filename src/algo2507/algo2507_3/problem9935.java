package algo2507.algo2507_3;

import java.io.*;
import java.util.*;

public class problem9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        int tLen = T.length();

        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            stack.push(c);
            // 최근 폭발 문자열 길이만큼만 검사
            if (stack.size() >= tLen) {
                boolean isBomb = true;
                for (int i = 0; i < tLen; i++) {
                    if (stack.get(stack.size() - tLen + i) != T.charAt(i)) {
                        isBomb = false;
                        break;
                    }
                }
                if (isBomb) {
                    for (int i = 0; i < tLen; i++)
                        stack.pop();
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (char c : stack) sb.append(c);
            System.out.println(sb);
        }
    }
}