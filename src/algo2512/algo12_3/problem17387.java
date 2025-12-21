package algo2512.algo12_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem17387 {
    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) return Long.compare(this.y, o.y);
            return Long.compare(this.x, o.x);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        Point p1 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point p2 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        Point p3 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point p4 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

        System.out.println(isIntersect(p1, p2, p3, p4) ? 1 : 0);
    }

    // CCW: (x2-x1)(y3-y1) - (x3-x1)(y2-y1)
    public static int ccw(Point p1, Point p2, Point p3) {
        long val = (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
        if (val > 0) return 1;
        else if (val < 0) return -1;
        else return 0;
    }

    public static boolean isIntersect(Point p1, Point p2, Point p3, Point p4) {
        int ccw1 = ccw(p1, p2, p3);
        int ccw2 = ccw(p1, p2, p4);
        int ccw3 = ccw(p3, p4, p1);
        int ccw4 = ccw(p3, p4, p2);

        // 일직선 상에 있거나 끝점이 겹치는 등의 특수 케이스 가능성
        if (ccw1 * ccw2 == 0 && ccw3 * ccw4 == 0) {
            // 비교를 위해 점들을 정렬 (x좌표 우선, y좌표 차선)
            // p1 <= p2, p3 <= p4 가 되도록 설정
            if (p1.compareTo(p2) > 0) {
                Point temp = p1;
                p1 = p2;
                p2 = temp;
            }
            if (p3.compareTo(p4) > 0) {
                Point temp = p3;
                p3 = p4;
                p4 = temp;
            }

            // 두 선분이 포개져 있는지 확인
            // p1-------p2
            //      p3-------p4  (겹침)
            // p1---p2 p3---p4   (안겹침)
            return p1.compareTo(p4) <= 0 && p3.compareTo(p2) <= 0;
        }

        // 일반적인 교차 (X자 형태)
        return ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0;
    }
}
