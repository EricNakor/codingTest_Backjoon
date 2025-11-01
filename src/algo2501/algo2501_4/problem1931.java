package algo2501.algo2501_4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class problem1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<int[]> meetings = new ArrayList<>();

        // 1. 입력 처리
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new int[]{start, end});
        }

        // 2. 정렬 (종료 시간 → 시작 시간 기준)
        meetings.sort((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // 3. 그리디 선택
        int count = 0;
        int lastEnd = 0;
        for (int[] m : meetings) {
            if (m[0] >= lastEnd) {
                count++;
                lastEnd = m[1];
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
