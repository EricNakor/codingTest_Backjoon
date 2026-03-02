package _2026.algo2602.algo2_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem2644 {
    static ArrayList<Integer>[] familyTree;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 사람의 수 N
        int n = Integer.parseInt(br.readLine());

        // 2. 촌수를 계산할 두 사람
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 3. 관계의 개수 M
        int m = Integer.parseInt(br.readLine());

        // 그래프(가계도) 초기화
        familyTree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            familyTree[i] = new ArrayList<>();
        }

        // 부모-자식 관계 입력 (양방향 연결)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            // 서로가 1촌 관계이므로 양쪽에 모두 추가해 줍니다.
            familyTree[parent].add(child);
            familyTree[child].add(parent);
        }

        // 촌수(거리) 배열 초기화. -1은 '방문하지 않음'이자 '연결 안 됨'을 의미
        dist = new int[n + 1];
        Arrays.fill(dist, -1);

        // BFS 탐색 시작
        bfs(start, end);

        // 결과 출력 (도달했다면 촌수가, 못했다면 초기값인 -1이 출력됨)
        System.out.println(dist[end]);
    }

    static void bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0; // 시작점의 촌수는 0

        while (!q.isEmpty()) {
            int current = q.poll();

            // 목표 인물에 도달했다면 더 이상 탐색할 필요가 없습니다.
            if (current == end) {
                return;
            }

            // 현재 사람과 1촌 관계인(연결된) 모든 사람들을 확인
            for (int next : familyTree[current]) {
                // 아직 촌수를 계산하지 않은 사람이라면
                if (dist[next] == -1) {
                    // 현재 사람의 촌수 + 1 을 기록하고 큐에 넣음
                    dist[next] = dist[current] + 1;
                    q.add(next);
                }
            }
        }
    }
}