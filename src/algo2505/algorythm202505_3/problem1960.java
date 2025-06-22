package algo2505.algorythm202505_3;
import java.io.*;
import java.util.*;
public class problem1960 {
    static int n, S, T;
    static int[][] capacity, flow;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] rowSum = new int[n];
        int[] colSum = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalRow = 0, totalCol = 0;
        for (int i = 0; i < n; i++) {
            rowSum[i] = Integer.parseInt(st.nextToken());
            totalRow += rowSum[i];
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            colSum[i] = Integer.parseInt(st.nextToken());
            totalCol += colSum[i];
        }
        if (totalRow != totalCol) {
            System.out.println(-1);
            return;
        }

        // 정점 번호: 0=source, 1~n=행, n+1~2n=열, 2n+1=sink
        S = 0;
        T = 2 * n + 1;
        int size = 2 * n + 2;
        capacity = new int[size][size];
        flow = new int[size][size];
        adj = new ArrayList[size];
        for (int i = 0; i < size; i++) adj[i] = new ArrayList<>();

        // source→행
        for (int i = 1; i <= n; i++) {
            capacity[S][i] = rowSum[i - 1];
            adj[S].add(i); adj[i].add(S);
        }
        // 열→sink
        for (int i = 1; i <= n; i++) {
            capacity[n + i][T] = colSum[i - 1];
            adj[n + i].add(T); adj[T].add(n + i);
        }
        // 행→열 (각 칸은 0 또는 1)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                capacity[i][n + j] = 1;
                adj[i].add(n + j); adj[n + j].add(i);
            }
        }

        int maxFlow = 0;
        while (true) {
            int[] parent = new int[size];
            Arrays.fill(parent, -1);
            Queue<Integer> q = new LinkedList<>();
            q.add(S);
            parent[S] = S;
            while (!q.isEmpty() && parent[T] == -1) {
                int cur = q.poll();
                for (int next : adj[cur]) {
                    if (capacity[cur][next] - flow[cur][next] > 0 && parent[next] == -1) {
                        parent[next] = cur;
                        q.add(next);
                    }
                }
            }
            if (parent[T] == -1) break; // 증가 경로 없음

            // 경로의 최소 잔여 용량 계산
            int pathFlow = Integer.MAX_VALUE;
            for (int v = T; v != S; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v] - flow[u][v]);
            }
            // 경로를 따라 유량 갱신
            for (int v = T; v != S; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += pathFlow;
                flow[v][u] -= pathFlow;
            }
            maxFlow += pathFlow;
        }

        if (maxFlow != totalRow) {
            System.out.println(-1);
            return;
        }

        // 행렬 복원
        System.out.println(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(flow[i][n + j] == 1 ? "1" : "0");
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
