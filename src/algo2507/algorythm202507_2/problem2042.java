package algo2507.algorythm202507_2;

import java.io.*;
import java.util.StringTokenizer;

public class problem2042 {
    static int N;
    static long[] tree, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        tree = new long[getTreeSize()];

        // 배열 입력
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 세그먼트 트리 초기화
        init(0, N - 1, 1);

        // 쿼리 처리
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 1) {
                // 값 변경
                int b = Integer.parseInt(st.nextToken()) - 1;
                long c = Long.parseLong(st.nextToken());
                long diff = c - arr[b];
                update(0, N - 1, 1, b, diff);
                arr[b] = c;  // 원본 배열도 업데이트
            } else {
                // 구간 합 구하기
                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                long sum = query(0, N - 1, 1, b, c);
                sb.append(sum).append("\n");
            }
        }

        System.out.print(sb);
    }

    // 트리 크기 계산
    static int getTreeSize() {
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        return (int) Math.pow(2, h) - 1;
    }

    // 세그먼트 트리 초기화
    static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // 값 업데이트
    static void update(int start, int end, int node, int idx, long diff) {
        if (start <= idx && idx <= end) {
            tree[node] += diff;
        } else {
            return;
        }

        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, diff);
        update(mid + 1, end, node * 2 + 1, idx, diff);
    }

    // 구간 합 쿼리
    static long query(int start, int end, int node, int left, int right) {
        if (right < start || left > end) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return query(start, mid, node * 2, left, right) +
                query(mid + 1, end, node * 2 + 1, left, right);
    }

}
