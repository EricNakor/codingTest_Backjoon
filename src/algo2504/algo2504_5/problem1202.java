package algo2504.algo2504_5;

import java.io.*;
import java.util.*;

public class problem1202 {
    static class Jewel implements Comparable<Jewel> {
        int weight, value;

        Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.weight - o.weight; // 무게 오름차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m, v);
        }
        Arrays.sort(jewels); // 무게 오름차순, 무게 같으면 가격 내림차순(compareTo 수정 필요)

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags); // 가방 오름차순 정렬

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 가격 내림차순
        long answer = 0;
        int j = 0; // 보석 인덱스
        for (int i = 0; i < K; i++) {
            // 현재 가방에 담을 수 있는 보석 모두 큐에 추가
            while (j < N && jewels[j].weight <= bags[i]) {
                pq.add(jewels[j].value);
                j++;
            }
            // 큐에서 가장 비싼 보석 꺼내기
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }
        System.out.println(answer);
    }
}
