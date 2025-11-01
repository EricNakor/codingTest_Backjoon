package algo2501.algo2501_2;

import java.io.*;
import java.util.StringTokenizer;

public class problem1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            bw.write(calcIntersectionPoints(x1, y1, r1, x2, y2, r2) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int calcIntersectionPoints(int x1, int y1, int r1, int x2, int y2, int r2) {
        // 피타고라스
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        if (distance == 0 && r1 == r2) return -1;
        if (distance > r1 + r2) return 0;
        if (distance < Math.abs(r1 - r2)) return 0;
        if (distance == r1 + r2) return 1;
        if (distance == Math.abs(r1 - r2)) return 1;
        if (Math.abs(r1 - r2) < distance && distance < r1 + r2) return 2;

        return 0;
    }
}
