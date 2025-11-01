package algo2506.algo2506_4;

import java.io.*;
import java.util.*;

public class problem9019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트 케이스의 개수 입력
        int T = Integer.parseInt(br.readLine());

        // 각 테스트 케이스에 대해 실행
        while (T-- > 0) {
            // A와 B 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // 방문 여부를 체크할 배열
            boolean[] visited = new boolean[10000];
            // 각 숫자에 대한 명령어를 저장할 배열
            String[] command = new String[10000];

            // BFS를 위한 큐
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(A);
            visited[A] = true;
            command[A] = "";

            // BFS 실행
            while (!queue.isEmpty() && !visited[B]) {
                int current = queue.poll();

                // D 명령어: 2n mod 10000
                int D = (current * 2) % 10000;
                if (!visited[D]) {
                    visited[D] = true;
                    command[D] = command[current] + "D";
                    queue.offer(D);
                }

                // S 명령어: n-1 (0이면 9999)
                int S = (current == 0) ? 9999 : current - 1;
                if (!visited[S]) {
                    visited[S] = true;
                    command[S] = command[current] + "S";
                    queue.offer(S);
                }

                // L 명령어: 왼쪽으로 회전
                int L = (current % 1000) * 10 + current / 1000;
                if (!visited[L]) {
                    visited[L] = true;
                    command[L] = command[current] + "L";
                    queue.offer(L);
                }

                // R 명령어: 오른쪽으로 회전
                int R = (current % 10) * 1000 + current / 10;
                if (!visited[R]) {
                    visited[R] = true;
                    command[R] = command[current] + "R";
                    queue.offer(R);
                }
            }

            bw.write(command[B] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
