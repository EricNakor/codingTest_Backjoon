package algo2504.algorythm202504_4;

import java.io.*;
import java.util.StringTokenizer;

public class problem2563 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 100x100 도화지, 색종이가 붙은 곳은 true
        boolean[][] paper = new boolean[100][100];

        int n = Integer.parseInt(br.readLine()); // 색종이 개수 입력

        for (int k = 0; k < n; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 색종이의 10x10 영역을 true로 표시
            for (int i = x; i < x + 10; i++) {
                for (int j = y; j < y + 10; j++) {
                    paper[i][j] = true;
                }
            }
        }

        // 색종이가 붙은 영역(=true)의 개수 세기
        int area = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (paper[i][j]) area++;
            }
        }

        bw.write(area + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
