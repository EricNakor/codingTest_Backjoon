package _2026.algo1_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class problem11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 우선순위 큐 생성 및 정렬 기준 재정의
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);

            // 1. 절댓값이 같으면 실제 값 비교 (오름차순: 음수가 앞으로 옴)
            if (abs1 == abs2) {
                return o1 - o2; // o1 > o2 이면 양수 반환 -> 순서 바꿈
            }
            // 2. 절댓값이 다르면 절댓값 비교 (오름차순)
            return abs1 - abs2;
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                // 제거 연산
                if (pq.isEmpty()) {
                    sb.append("0\n");
                } else {
                    sb.append(pq.poll()).append("\n");
                }
            } else {
                // 삽입 연산
                pq.add(x);
            }
        }

        System.out.print(sb);
    }
}