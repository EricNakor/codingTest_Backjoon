package algorythm202505_3;

import java.io.*;
import java.util.*;

public class problem6086 {
    static final int SIZE = 52; // A~Z(0~25), a~z(26~51)
    static int[][] capacity = new int[SIZE][SIZE];
    static int[][] flow = new int[SIZE][SIZE];
    static ArrayList<Integer>[] adj = new ArrayList[SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        for (int i = 0; i < SIZE; i++) adj[i] = new ArrayList<>();

        // 입력 및 용량 누적
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            char a = line.charAt(0);
            char b = line.charAt(2);
            int c = Integer.parseInt(line.substring(4).trim());
            int u = toIndex(a);
            int v = toIndex(b);
            if (!adj[u].contains(v)) adj[u].add(v);
            if (!adj[v].contains(u)) adj[v].add(u);
            capacity[u][v] += c;
            capacity[v][u] += c; // 양방향
        }

        System.out.println(edmondsKarp(0, 25)); // A=0, Z=25
    }

    // 에드몬드-카프 알고리즘(BFS 기반)
    static int edmondsKarp(int source, int sink) {
        int result = 0;
        while (true) {
            int[] parent = new int[SIZE];
            Arrays.fill(parent, -1);
            Queue<Integer> q = new LinkedList<>();
            q.add(source);
            parent[source] = source;

            // BFS로 증가 경로 탐색
            while (!q.isEmpty() && parent[sink] == -1) {
                int cur = q.poll();
                for (int next : adj[cur]) {
                    if (capacity[cur][next] - flow[cur][next] > 0 && parent[next] == -1) {
                        parent[next] = cur;
                        q.add(next);
                    }
                }
            }
            if (parent[sink] == -1) break; // 증가 경로 없음

            // 경로의 최소 잔여 용량(flow) 계산
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v] - flow[u][v]);
            }
            // 경로를 따라 유량 갱신
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += pathFlow;
                flow[v][u] -= pathFlow;
            }
            result += pathFlow;
        }
        return result;
    }

    // 문자→정수 인덱스 변환 함수
    static int toIndex(char c) {
        if ('A' <= c && c <= 'Z') return c - 'A';
        else return c - 'a' + 26;
    }
}
