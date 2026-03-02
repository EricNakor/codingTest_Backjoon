package _2026.algo2602.algo2_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem6603 {
    static int k;
    static int[] S;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            // 입력이 0이면 무한 루프 종료
            if (k == 0) {
                break;
            }

            S = new int[k];
            result = new int[6]; // 로또 번호 6개를 담을 배열

            for (int i = 0; i < k; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            // 백트래킹 시작: S 배열의 0번 인덱스부터, 고른 개수는 0개로 시작
            dfs(0, 0);

            // 테스트 케이스 사이에 빈 줄 추가
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    // start: 탐색을 시작할 인덱스 (조합을 위해 사용)
    // depth: 현재까지 선택한 숫자의 개수
    static void dfs(int start, int depth) {
        // 1. 종료 조건: 6개를 모두 골랐을 때
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 2. 탐색 및 재귀 호출
        // i는 start부터 시작하므로, 이전에 고른 숫자보다 항상 뒤에 있는 숫자만 고르게 됨
        for (int i = start; i < k; i++) {
            result[depth] = S[i];         // 현재 깊이(빈칸)에 숫자를 넣음
            dfs(i + 1, depth + 1); // 다음 빈칸을 채우기 위해 재귀 호출

            // 재귀가 끝나고 돌아왔다는 것은, 방금 넣었던 숫자로 할 수 있는 모든 경우를 다 봤다는 뜻입니다.
            // for문이 다음 바퀴를 돌면서 새로운 숫자를 result[depth] 자리에 덮어쓰게 되므로
            // 명시적으로 숫자를 지우는(pop) 작업은 배열 특성상 생략해도 자연스럽게 백트래킹이 이루어집니다.
        }
    }
}