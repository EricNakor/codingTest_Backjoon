package algo2510.algo2510_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem1004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int n = Integer.parseInt(br.readLine());
            int count = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int cx = Integer.parseInt(st.nextToken());
                int cy = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                // 1. 출발점이 원 안에 있는지 확인
                boolean isStartInside = isInside(x1, y1, cx, cy, r);
                // 2. 도착점이 원 안에 있는지 확인
                boolean isEndInside = isInside(x2, y2, cx, cy, r);

                // 3. 두 점의 상태가 다를 때만(XOR) 카운트 증가
                if (isStartInside != isEndInside) {
                    count++;
                }
            }
            sb.append(count).append('\n');
        }
        System.out.print(sb);
    }

    // 원 내부에 있는지 판별하는 함수
    public static boolean isInside(int x, int y, int cx, int cy, int r) {
        // (x - cx)^2 + (y - cy)^2 < r^2
        // 제곱 계산 시 int 범위를 넘을 수 있으므로 long 사용 고려
        return (long) Math.pow(x - cx, 2) + (long) Math.pow(y - cy, 2) < (long) Math.pow(r, 2);
    }
}
