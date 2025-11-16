package algo2511.algo11_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class problem11000 {
    static class ClassTime implements Comparable<ClassTime> {
        int start, end;

        public ClassTime(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(ClassTime o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ClassTime[] classes = new ClassTime[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            classes[i] = new ClassTime(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 1. 시작 시간 기준 오름차순 정렬
        Arrays.sort(classes);

        // 2. 종료 시간을 저장할 최소 힙(PriorityQueue)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 3. 정렬된 수업 순회
        for (int i = 0; i < N; i++) {
            int startTime = classes[i].start;
            int endTime = classes[i].end;

            // 4. 재사용 가능한 강의실 확인
            // (가장 빨리 끝나는 시간이 현재 시작 시간보다 작거나 같다면)
            if (!pq.isEmpty() && pq.peek() <= startTime) {
                pq.poll(); // 기존 수업을 빼고
            }

            // 5. 현재 수업의 종료 시간을 큐에 추가
            pq.add(endTime);
        }

        // 6. 큐의 크기가 필요한 최소 강의실 수
        System.out.println(pq.size());
    }
}
