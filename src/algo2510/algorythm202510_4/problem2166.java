package algo2510.algorythm202510_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2166 {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        long sumX = 0; // xᵢ * yᵢ₊₁ 들의 합
        long sumY = 0; // yᵢ * xᵢ₊₁ 들의 합

        for (int i = 0; i < N; i++) {
            Point currentPoint = points[i];
            Point nextPoint = points[(i + 1) % N]; // 마지막 점은 첫 번째 점과 연결

            sumX += (long) currentPoint.x * nextPoint.y;
            sumY += (long) currentPoint.y * nextPoint.x;
        }

        // 넓이 = 0.5 * | (x₁y₂ + ...) - (y₁x₂ + ...) |
        double area = Math.abs(sumX - sumY) / 2.0;

        System.out.printf("%.1f\n", area);
    }
}
