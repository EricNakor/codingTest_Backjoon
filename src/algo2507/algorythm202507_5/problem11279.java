package algo2507.algorythm202507_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class problem11279 {
    public static void main(String[] args) throws IOException {
        // 빠른 입출력을 위한 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 최대 힙으로 동작하도록 우선순위 큐 생성
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                // x가 0일 때: 최댓값 출력 및 제거
                if (maxHeap.isEmpty()) {
                    // 힙이 비어있으면 0 출력
                    sb.append(0).append("\n");
                } else {
                    // 힙이 비어있지 않으면 최댓값(루트)을 꺼내서 출력
                    sb.append(maxHeap.poll()).append("\n");
                }
            } else {
                // x가 자연수일 때: 힙에 추가
                maxHeap.offer(x);
            }
        }

        // 결과 한 번에 출력
        System.out.print(sb);
    }
}
