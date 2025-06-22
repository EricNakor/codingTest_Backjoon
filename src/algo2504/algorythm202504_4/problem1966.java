package algo2504.algorythm202504_4;

import java.io.*;
import java.util.*;

public class problem1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<int[]> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                // [인덱스, 중요도]
                queue.offer(new int[]{i, Integer.parseInt(st.nextToken())});
            }

            int count = 0;

            while (!queue.isEmpty()) {
                int[] front = queue.poll();
                boolean isMax = true;

                // 현재 문서보다 중요도가 높은 문서가 있는지 확인
                for (int[] doc : queue) {
                    if (doc[1] > front[1]) {
                        isMax = false;
                        break;
                    }
                }

                if (isMax) {
                    count++;
                    if (front[0] == M) {
                        break;
                    }
                } else {
                    queue.offer(front);
                }
            }

            System.out.println(count);
        }
    }
}
