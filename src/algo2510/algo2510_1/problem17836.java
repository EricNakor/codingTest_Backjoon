package algo2510.algo2510_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem17836 {
    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    // CCW 함수
    public static int ccw(Point p1, Point p2, Point p3) {
        long result = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) -
                (p1.y * p2.x + p2.y * p3.x + p3.y * p1.y);
        if (result > 0) return 1;   // 반시계
        if (result < 0) return -1;  // 시계
        return 0;                   // 일직선
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        Point p1 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point p2 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        Point p3 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point p4 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

        int ccw1 = ccw(p1, p2, p3);
        int ccw2 = ccw(p1, p2, p4);
        int ccw3 = ccw(p3, p4, p1);
        int ccw4 = ccw(p3, p4, p2);

        // 두 선분이 서로를 가로지르는 경우 (한쪽이라도 0이 아니어야 함)
        if (ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0) {
            // 네 점이 모두 한 직선 위에 있는 경우
            if (ccw1 * ccw2 == 0 && ccw3 * ccw4 == 0) {
                // p1 < p2, p3 < p4가 되도록 정렬
                if (Math.max(p1.x, p2.x) < Math.min(p3.x, p4.x) ||
                        Math.max(p3.x, p4.x) < Math.min(p1.x, p2.x) ||
                        Math.max(p1.y, p2.y) < Math.min(p3.y, p4.y) ||
                        Math.max(p3.y, p4.y) < Math.min(p1.y, p2.y)) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
            } else {
                System.out.println(1);
            }
        } else {
            System.out.println(0);
        }
    }
}
