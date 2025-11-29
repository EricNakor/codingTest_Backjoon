package algo2511.algo11_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem2477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 1m^2당 참외 개수 K
        int K = Integer.parseInt(br.readLine());

        int[] lengths = new int[6];
        int maxW = 0, maxWIdx = 0;
        int maxH = 0, maxHIdx = 0;

        // 2. 6개의 변 정보 입력
        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            lengths[i] = len;

            // 방향이 1(동), 2(서)이면 가로 길이
            if (dir == 1 || dir == 2) {
                if (len > maxW) {
                    maxW = len;
                    maxWIdx = i;
                }
            }
            // 방향이 3(남), 4(북)이면 세로 길이
            else {
                if (len > maxH) {
                    maxH = len;
                    maxHIdx = i;
                }
            }
        }

        // 3. 빈 사각형의 가로, 세로 길이 구하기
        // 가장 긴 변의 인덱스에서 +3 위치(반대편)에 있는 변이 빈 사각형의 변입니다.
        // 순환 구조이므로 % 6을 사용합니다.
        int smallW = lengths[(maxHIdx + 3) % 6]; // maxH와 짝이 되는 빈 사각형의 가로
        int smallH = lengths[(maxWIdx + 3) % 6]; // maxW와 짝이 되는 빈 사각형의 세로

        // 4. 전체 면적 계산 (큰 사각형 - 작은 사각형)
        int area = (maxW * maxH) - (smallW * smallH);

        // 5. 총 참외 개수 출력
        System.out.println(area * K);
    }
}
