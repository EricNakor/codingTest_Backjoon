package algorythm202501_4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 회의 시작, 끝나는 시간을 2차원 배열로 생성
        int[][] meetings = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 시작 시간
            meetings[i][0] = Integer.parseInt(st.nextToken());
            // 끝나는 시간
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }

        // 끝나는 시간 기준 정렬
        // 끝나는 시간이 같을 때 시작 시간으로 정렬
        Arrays.sort(meetings, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int count = 0;
        int endTime = 0;

        for (int[] meeting : meetings) {
            if (meeting[0] >= endTime) {
                endTime = meeting[1];
                count++;
            }
        }

        bw.write(String.valueOf(count));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
