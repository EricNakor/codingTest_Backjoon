package _2026.algo2602.algo2_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 그래프의 간선 정보 (연결된 노드와 비율 p:q)
class Edge {
    int to;
    int p;
    int q;

    public Edge(int to, int p, int q) {
        this.to = to;
        this.p = p;
        this.q = q;
    }
}

public class problem1033 {
    static int N;
    static ArrayList<Edge>[] adj;
    static long[] mass; // 각 재료의 질량을 저장 (long 사용)
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N];
        mass = new long[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            // 비율을 기약분수로 만들어 저장 (계산 간소화)
            int common = gcd(p, q);
            p /= common;
            q /= common;

            // a : b = p : q  =>  mass[b] = mass[a] * q / p
            adj[a].add(new Edge(b, p, q));
            // b : a = q : p  =>  mass[a] = mass[b] * p / q
            adj[b].add(new Edge(a, q, p));
        }

        // 0번 노드의 초기값을 1로 설정하고 DFS 시작
        mass[0] = 1;
        dfs(0);

        // 결과값들이 서로소가 아닐 수 있으므로, 전체 GCD로 나누어 줌
        long totalGcd = mass[0];
        for (int i = 1; i < N; i++) {
            totalGcd = gcd(totalGcd, mass[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(mass[i] / totalGcd).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static void dfs(int u) {
        visited[u] = true;

        for (Edge e : adj[u]) {
            int v = e.to;
            if (!visited[v]) {
                // 목표: mass[v] = mass[u] * e.q / e.p
                // 조건: mass[v]가 정수가 되려면 (mass[u] * e.q)가 e.p로 나누어떨어져야 함.
                // e.p와 e.q는 서로소이므로, mass[u]가 e.p의 배수가 되어야 함.

                long currentMass = mass[u];

                // 현재 질량(mass[u])을 e.p의 배수로 만들기 위해 필요한 값을 계산
                // 필요한 값 = e.p / (mass[u]와 e.p의 최대공약수)
                long commonDiv = gcd(currentMass, e.p);
                long factor = e.p / commonDiv;

                // factor가 1보다 크다면, 현재까지 구한 모든 재료의 질량에 factor를 곱해줌 (비율 유지)
                if (factor > 1) {
                    for (int i = 0; i < N; i++) {
                        mass[i] *= factor;
                    }
                }

                // 이제 mass[u]는 e.p로 나누어떨어지므로, 안전하게 mass[v] 계산
                mass[v] = mass[u] * e.q / e.p;

                dfs(v);
            }
        }
    }

    // 최대공약수 (GCD) 함수 - 유클리드 호제법
    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    // int형 오버로딩
    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
