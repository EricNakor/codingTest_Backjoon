package algo2510.algo2510_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class problem1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[][] applicants = new int[N][2];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                applicants[i][0] = Integer.parseInt(st.nextToken()); // 서류 성적
                applicants[i][1] = Integer.parseInt(st.nextToken()); // 면접 성적
            }

            // 1. 서류 성적(0번 인덱스) 기준으로 오름차순 정렬
            Arrays.sort(applicants, (a, b) -> Integer.compare(a[0], b[0]));

            int count = 1; // 서류 1등은 무조건 합격
            int minInterviewRank = applicants[0][1]; // 합격자 중 최고 면접 등수 (서류 1등의 면접 등수)

            // 2. 서류 2등부터 탐색
            for (int i = 1; i < N; i++) {
                // 3. 면접 성적이 이전 합격자들의 최고 성적보다 좋다면 합격
                if (applicants[i][1] < minInterviewRank) {
                    count++;
                    minInterviewRank = applicants[i][1]; // 최고 면접 등수 갱신
                }
            }
            sb.append(count).append('\n');
        }
        System.out.print(sb);
    }
}
