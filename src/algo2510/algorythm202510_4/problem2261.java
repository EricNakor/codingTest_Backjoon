package algo2510.algorythm202510_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem2261 {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int getDistSq(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    // 브루트포스: 작은 크기의 문제 해결
    public static int bruteForce(Point[] points, int start, int end) {
        int minDist = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                minDist = Math.min(minDist, getDistSq(points[i], points[j]));
            }
        }
        return minDist;
    }

    public static int solve(Point[] points, int start, int end) {
        int n = end - start + 1;
        if (n <= 3) {
            return bruteForce(points, start, end);
        }

        int mid = (start + end) / 2;
        int d = Math.min(solve(points, start, mid), solve(points, mid + 1, end));

        List<Point> strip = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            int dx = points[i].x - points[mid].x;
            if (dx * dx < d) {
                strip.add(points[i]);
            }
        }

        strip.sort(Comparator.comparingInt(p -> p.y));

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size(); j++) {
                int dy = strip.get(j).y - strip.get(i).y;
                if (dy * dy >= d) {
                    break;
                }
                d = Math.min(d, getDistSq(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // x좌표 기준 정렬
        Arrays.sort(points, Comparator.comparingInt(p -> p.x));

        System.out.println(solve(points, 0, N - 1));
    }
}
