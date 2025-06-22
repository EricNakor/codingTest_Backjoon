package algo2505.algorythm202505_1;

import java.io.*;
import java.util.*;

public class problem15649 {
    static int N, M;
    static int[] arr;           // 현재 수열 저장
    static boolean[] visited;   // 숫자 사용 여부 체크

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        visited = new boolean[N + 1];

        back(0); // 백트래킹 시작
    }

    // depth: 현재 수열의 길이
    static void back(int depth) {
        if (depth == M) {
            // M개를 모두 골랐으면 출력
            for (int i = 0; i < M; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        // 1부터 N까지 순서대로 시도
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) { // 아직 사용하지 않은 숫자라면
                visited[i] = true;   // 사용 표시
                arr[depth] = i;      // 현재 위치에 숫자 저장
                back(depth + 1);     // 다음 자리로 재귀 호출
                visited[i] = false;  // 사용 해제(백트래킹)
            }
        }
    }
}
