package algo2501.algo2501_2;

import java.io.*;
import java.util.Stack;

public class problem9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String result = isVPS(br.readLine()) ? "YES" : "NO";
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isVPS(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
