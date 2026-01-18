package _2026.algo2601.algo1_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class problem5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int ans = 0;
        int count = 0; // 연속된 'IOI' 패턴의 수 (정확히는 'OI' 덩어리의 수)

        // i는 0부터 M-2까지만 확인 (i+2 인덱스 참조 때문)
        for (int i = 0; i < M - 2; i++) {
            // "IOI" 패턴 확인
            if (S.charAt(i) == 'I' && S.charAt(i + 1) == 'O' && S.charAt(i + 2) == 'I') {
                count++;

                // 연속된 "OI"가 N개가 되면 P_N 완성
                if (count == N) {
                    ans++;
                    count--; // 가장 앞의 IOI 하나를 버리고, 뒤에서 이어지는지 확인하기 위해 1 감소
                }

                // "IOI"를 확인했으므로 두 칸 점프 (for문의 i++와 합쳐져서 실제로 다음 i는 +2 된 위치)
                i++;
            } else {
                // 패턴이 깨지면 초기화
                count = 0;
            }
        }

        System.out.println(ans);
    }
}
