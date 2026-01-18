package _2026.algo2601.algo1_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem1991 {

    static class Node {
        char val;
        Node left;
        Node right;

        public Node(char val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // A~Z까지의 노드를 저장할 배열
    static Node[] tree = new Node[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 1. 노드 미리 생성 (A ~ Z)
        // (미리 생성해두지 않고 입력받을 때 생성해도 되지만, 배열에 넣어두면 관리가 편함)
        /* 이번 풀이에서는 입력받을 때 동적으로 연결하는 방식을 사용하겠습니다.
           하지만 편의상 tree 배열에 'A'~'Z' 객체를 모두 넣어두고 시작하는 방법도 많이 쓰입니다.
           여기서는 tree 배열에 해당 문자가 들어갈 위치를 직접 new Node로 채우는 방식을 씁니다.
        */
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parentVal = st.nextToken().charAt(0);
            char leftVal = st.nextToken().charAt(0);
            char rightVal = st.nextToken().charAt(0);

            // 부모 노드가 배열에 없으면 생성
            if (tree[parentVal - 'A'] == null) {
                tree[parentVal - 'A'] = new Node(parentVal);
            }
            Node parent = tree[parentVal - 'A'];

            // 왼쪽 자식 연결
            if (leftVal != '.') {
                tree[leftVal - 'A'] = new Node(leftVal); // 자식 노드 생성 및 배열 등록
                parent.left = tree[leftVal - 'A'];
            }

            // 오른쪽 자식 연결
            if (rightVal != '.') {
                tree[rightVal - 'A'] = new Node(rightVal);
                parent.right = tree[rightVal - 'A'];
            }
        }

        // 항상 A가 루트 노드
        Node root = tree[0];

        // 2. 순회 실행
        preOrder(root);
        System.out.println();

        inOrder(root);
        System.out.println();

        postOrder(root);
        System.out.println();
    }

    // 전위 순회: 루트 -> 좌 -> 우
    static void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 중위 순회: 좌 -> 루트 -> 우
    static void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.val);
        inOrder(node.right);
    }

    // 후위 순회: 좌 -> 우 -> 루트
    static void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val);
    }
}
