package algo2507.algorythm202507_2;

import java.io.*;
import java.util.*;

public class problem1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                // 최솟값 출력 및 제거
                if (minHeap.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(minHeap.poll()).append("\n");
                }
            } else {
                // 자연수 x를 힙에 추가
                minHeap.offer(x);
            }
        }

        System.out.print(sb);
    }
}
