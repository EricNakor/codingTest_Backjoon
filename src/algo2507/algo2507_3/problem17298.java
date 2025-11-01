package algo2507.algo2507_3;

import java.io.*;
import java.util.*;

public class problem17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] answer = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
            // 기본값 -1로 초기화
            answer[i] = -1;
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                answer[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        for (int val : answer) sb.append(val).append(" ");
        System.out.println(sb);
    }
}