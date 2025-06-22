package algo2504.algorythm202504_4;

import java.io.*;
import java.util.*;

public class problem11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();

        // 1부터 N까지의 숫자를 큐에 추가
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        StringBuilder result = new StringBuilder("<");

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            // K-1번 앞에서 빼서 뒤로 보냄
            for (int i = 0; i < K - 1; i++) {
                queue.offer(queue.poll());
            }

            // K번째 원소를 결과에 추가
            result.append(queue.poll());

            // 큐가 비어있지 않으면 쉼표 추가
            if (!queue.isEmpty()) {
                result.append(", ");
            }
        }

        result.append(">");
        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
