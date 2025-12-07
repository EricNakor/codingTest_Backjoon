package algo2512.algo12_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problem1068 {
    static ArrayList<Integer>[] tree;
    static int deleteNode;
    static int leafCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        int root = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                tree[parent].add(i);
            }
        }

        deleteNode = Integer.parseInt(br.readLine());

        // 예외 처리: 루트 노드를 삭제하는 경우
        if (deleteNode == root) {
            System.out.println(0);
            return;
        }

        dfs(root);
        System.out.println(leafCount);
    }

    static void dfs(int node) {
        boolean isLeaf = true;

        for (int child : tree[node]) {
            // 삭제된 노드라면 건너뜀 (연결 끊김 효과)
            if (child == deleteNode) continue;

            // 유효한 자식이 하나라도 있으면 리프 노드가 아님
            isLeaf = false;
            dfs(child);
        }

        // 유효한 자식이 없으면 리프 노드
        if (isLeaf) {
            leafCount++;
        }
    }
}
