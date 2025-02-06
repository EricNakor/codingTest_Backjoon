package algorythm202502_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// 틀림: 시간초과, 런타임에러, 시간초과
public class problem5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            Deque<Integer> deque = new LinkedList<>();

            // 1. 배열 초기화
            String[] nums = br.readLine().replace("[", "").replace("]", "").split(",");
            for (String num : nums) {
                if (!num.isEmpty()) {
                    deque.offerLast(Integer.parseInt(num));
                }
            }

            boolean error = false;

            // 2. 함수 수행
            for (char func : p.toCharArray()) {
                if (func == 'R') {
                    // 뒤집기
                    Deque<Integer> temp = new LinkedList<>();
                    while (!deque.isEmpty()) {
                        temp.offerFirst(deque.pollLast());
                    }
                    deque = temp;
                } else if (func == 'D') {
                    // 첫 번째 요소 제거
                    if (deque.isEmpty()) {
                        error = true;
                        break;
                    }
                    deque.pollFirst();
                }
            }

            // 3. 결과 출력
            if (error) {
                System.out.println("error");
            } else {
                StringBuilder sb = new StringBuilder("[");
                while (!deque.isEmpty()) {
                    sb.append(deque.pollFirst());
                    if (!deque.isEmpty()) {
                        sb.append(",");
                    }
                }
                sb.append("]");
                System.out.println(sb.toString());
            }
        }
    }
}
