package algo2510.algo2510_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class problem1655 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // Java의 PriorityQueue는 기본이 최소 힙
        // 최대 힙을 만들기 위해 Collections.reverseOrder() 사용
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            // 1. 두 힙의 크기를 같거나, maxHeap이 하나 더 많게 유지
            if (maxHeap.size() == minHeap.size()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }

            // 2. 값의 순서가 잘못된 경우 (maxHeap의 top > minHeap의 top) 교환
            if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int temp = maxHeap.poll();
                maxHeap.add(minHeap.poll());
                minHeap.add(temp);
            }

            // 중간값은 항상 maxHeap의 top
            sb.append(maxHeap.peek()).append('\n');
        }

        System.out.print(sb);
    }
}