package algo2504.algo2504_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 사람 수
        int[][] people = new int[N][2]; // [몸무게, 키] 저장 배열

        // 입력 처리
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            people[i][0] = Integer.parseInt(st.nextToken()); // 몸무게
            people[i][1] = Integer.parseInt(st.nextToken()); // 키
        }

        StringBuilder sb = new StringBuilder();

        // 모든 사람에 대해 등수 계산
        for (int i = 0; i < N; i++) {
            int rank = 1; // 초기 등수는 1
            for (int j = 0; j < N; j++) {
                if (i != j && people[j][0] > people[i][0] && people[j][1] > people[i][1]) {
                    rank++; // 자신보다 큰 사람이 있으면 등수 증가
                }
            }
            sb.append(rank).append(" "); // 결과 저장
        }

        System.out.println(sb.toString().trim()); // 결과 출력
    }
}
