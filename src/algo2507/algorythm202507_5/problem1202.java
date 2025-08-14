package algo2507.algorythm202507_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Jewel implements Comparable<Jewel> {
    int weight;
    int value;

    public Jewel(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    // 무게를 기준으로 오름차순 정렬
    @Override
    public int compareTo(Jewel other) {
        return this.weight - other.weight;
    }
}

public class problem1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 보석 정보 입력 및 정렬
        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(M, V);
        }
        Arrays.sort(jewels); // 무게 오름차순 정렬

        // 가방 정보 입력 및 정렬
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags); // 용량 오름차순 정렬

        // 가격을 기준으로 한 최대 힙 (PriorityQueue)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long totalValue = 0;
        int jewelIndex = 0;

        // 용량이 작은 가방부터 순회
        for (int i = 0; i < K; i++) {
            int bagCapacity = bags[i];

            // 현재 가방에 담을 수 있는 모든 보석을 우선순위 큐에 추가
            while (jewelIndex < N && jewels[jewelIndex].weight <= bagCapacity) {
                pq.offer(jewels[jewelIndex].value);
                jewelIndex++;
            }

            // 큐에 보석이 있다면, 가장 가치가 높은 보석을 선택 (훔치기)
            if (!pq.isEmpty()) {
                totalValue += pq.poll();
            }
        }

        System.out.println(totalValue);
    }
}
