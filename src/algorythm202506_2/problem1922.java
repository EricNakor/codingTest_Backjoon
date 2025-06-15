package algorythm202506_2;

import java.io.*;
import java.util.*;

public class problem1922 {
    static int[] parent; // Union-Find를 위한 부모 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 컴퓨터 수
        int M = Integer.parseInt(br.readLine()); // 간선 수

        Edge[] edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, cost);
        }

        // 1. 간선을 비용 기준으로 오름차순 정렬
        Arrays.sort(edges);

        // 2. Union-Find를 위한 parent 배열 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        long totalCost = 0;
        int edgeCount = 0;

        // 3. 비용이 낮은 간선부터 순회하며 MST 구성
        for (Edge edge : edges) {
            // 두 정점이 연결되어 있지 않으면 (사이클이 생기지 않으면)
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to); // 두 정점을 연결
                totalCost += edge.cost;   // 비용 추가
                edgeCount++;
                // N-1개의 간선이 선택되면 모든 컴퓨터가 연결됨
                if (edgeCount == N - 1) break;
            }
        }

        System.out.println(totalCost);
    }

    // 특정 원소가 속한 집합의 루트를 찾는 함수
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    // 두 원소가 속한 집합을 합치는 함수
    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}

// 간선 정보를 저장하는 클래스
class Edge implements Comparable<Edge> {
    int from, to, cost;

    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    // 비용(cost) 기준으로 오름차순 정렬
    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}