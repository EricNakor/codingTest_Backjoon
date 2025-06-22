package algo2503.algorythm202503_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class problem13549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] visited = new int[100001];
        Arrays.fill(visited, -1);

        pq.offer(new int[]{0, N});
        visited[N] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0];
            int position = current[1];

            if (position == K) {
                System.out.println(time);
                return;
            }

            if (position * 2 <= 100000 && (visited[position * 2] == -1 || visited[position * 2] > time)) {
                pq.offer(new int[]{time, position * 2});
                visited[position * 2] = time;
            }

            if (position + 1 <= 100000 && (visited[position + 1] == -1 || visited[position + 1] > time + 1)) {
                pq.offer(new int[]{time + 1, position + 1});
                visited[position + 1] = time + 1;
            }

            if (position - 1 >= 0 && (visited[position - 1] == -1 || visited[position - 1] > time + 1)) {
                pq.offer(new int[]{time + 1, position - 1});
                visited[position - 1] = time + 1;
            }
        }
    }
}