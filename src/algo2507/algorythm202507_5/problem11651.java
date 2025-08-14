package algo2507.algorythm202507_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem11651 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // N개의 좌표를 저장할 2차원 배열
        int[][] coords = new int[N][2];

        // 좌표 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coords[i][0] = Integer.parseInt(st.nextToken()); // x 좌표
            coords[i][1] = Integer.parseInt(st.nextToken()); // y 좌표
        }

        // 사용자 정의 규칙에 따라 배열 정렬
        Arrays.sort(coords, (p1, p2) -> {
            // y좌표가 같으면 x좌표를 비교
            if (p1[1] == p2[1]) {
                return p1[0] - p2[0];
            }
            // y좌표가 다르면 y좌표를 비교
            return p1[1] - p2[1];
        });

        // 빠른 출력을 위해 StringBuilder 사용
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(coords[i][0]).append(" ").append(coords[i][1]).append("\n");
        }

        System.out.print(sb);
    }
}
