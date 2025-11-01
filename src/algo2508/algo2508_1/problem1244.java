package algo2508.algo2508_1;

import java.io.*;
import java.util.*;

public class problem1244 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 스위치 개수 입력
        int n = Integer.parseInt(br.readLine());

        // 스위치 상태 입력 (1번부터 시작하므로 크기 n+1)
        int[] switches = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        // 학생 수 입력
        int studentCount = Integer.parseInt(br.readLine());

        // 각 학생 처리
        for (int i = 0; i < studentCount; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            if (gender == 1) {
                // 남학생: 받은 번호의 배수 스위치 상태 변경
                for (int j = number; j <= n; j += number) {
                    switches[j] = switches[j] == 0 ? 1 : 0;
                }
            } else {
                // 여학생: 받은 번호를 중심으로 대칭 구간 상태 변경
                switches[number] = switches[number] == 0 ? 1 : 0;

                int left = number - 1;
                int right = number + 1;

                // 좌우 대칭 확인하며 확장
                while (left >= 1 && right <= n && switches[left] == switches[right]) {
                    switches[left] = switches[left] == 0 ? 1 : 0;
                    switches[right] = switches[right] == 0 ? 1 : 0;
                    left--;
                    right++;
                }
            }
        }

        // 결과 출력 (20개씩 한 줄에)
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(switches[i]).append(" ");
            if (i % 20 == 0) {
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }
}
