package algo2504.algorythm202504_1;

import java.io.*;
import java.util.StringTokenizer;

public class problem11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] A = new int[N + 1][N + 1]; // 원본 배열
        int[][] D = new int[N + 1][N + 1]; // 누적합 배열

        // 원본 배열 입력받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 누적합 배열 생성
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                D[i][j] = A[i][j] + D[i - 1][j] + D[i][j - 1] - D[i - 1][j - 1];
            }
        }

        // 쿼리 처리 및 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int q = 0; q < M; q++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int result = D[x2][y2]
                    - (x1 > 0 ? D[x1 - 1][y2] : 0)
                    - (y1 > 0 ? D[x2][y1 - 1] : 0)
                    + ((x1 > 0 && y1 > 0) ? D[x1 - 1][y1 - 1] : 0);

            bw.write(result + "\n");
        }
        bw.flush();
    }
}
