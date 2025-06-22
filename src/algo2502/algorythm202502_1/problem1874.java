package algo2502.algorythm202502_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class problem1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] sequence = new int[N];

        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        int pushIndex = 1;

        for (int target : sequence) {
            while (stack.isEmpty() || stack.peek() < target) {
                stack.push(pushIndex);
                result.append("+\n");
                pushIndex++;
            }

            if (stack.isEmpty() || stack.peek() != target) {
                System.out.println("NO");
                return;
            }

            stack.pop();
            result.append("-\n");
        }

        System.out.println(result.toString());
    }
}
