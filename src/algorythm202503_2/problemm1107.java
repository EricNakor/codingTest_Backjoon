package algorythm202503_2;

import java.io.*;
import java.util.StringTokenizer;

public class problemm1107 {
    static boolean[] broken = new boolean[10]; // 고장난 버튼 체크 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 목표 채널
        int M = Integer.parseInt(br.readLine()); // 고장난 버튼 개수

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true; // 고장난 버튼 체크
            }
        }

        int ans = Math.abs(N - 100); // 초기값: 현재 채널(100)에서 +, -만 사용한 경우

        // 가능한 모든 채널 탐색 (0부터 999,999까지)
        for (int i = 0; i <= 999999; i++) {
            int len = possible(i); // 숫자 버튼으로 채널 i를 누를 수 있는지 확인
            if (len > 0) { // 숫자 버튼으로 이동 가능하다면
                int press = Math.abs(i - N); // +, - 버튼으로 이동한 횟수 계산
                ans = Math.min(ans, len + press); // 최소 클릭 횟수 갱신
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    // 숫자 버튼으로 채널 c를 누를 수 있는지 확인하는 함수
    static int possible(int c) {
        if (c == 0) { // 예외 처리: 채널이 0일 때
            return broken[0] ? 0 : 1;
        }
        int len = 0;
        while (c > 0) {
            if (broken[c % 10]) { // 고장난 버튼이면 불가능
                return 0;
            }
            len++;
            c /= 10;
        }
        return len; // 누를 수 있는 숫자 길이 반환
    }
}
