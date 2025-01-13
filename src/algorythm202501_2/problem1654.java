package algorythm202501_2;

import java.io.*;
import java.util.StringTokenizer;

public class problem1654 {
    public static void main(String[] args) throws IOException {
        // 틀림 _ 이진탐색
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가지고 있는 랜선의 개수
        int k = Integer.parseInt(st.nextToken());
        // 필요한 랜선의 개수
        int n = Integer.parseInt(st.nextToken());

        long[] cables = new long[k];
        long max = 0;

        for (int i = 0; i < k; i++) {
            cables[i] = Long.parseLong(br.readLine());
            max = Math.max(max, cables[i]);
        }

        long result = findMaxCableLength(cables, n, max);

        bw.write(String.valueOf(result));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    static long findMaxCableLength(long[] cables, int N, long max) {
        long left = 1;
        long right = max;
        long result = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long count = countCables(cables, mid);

            if (count >= N) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    static long countCables(long[] cables, long length) {
        long count = 0;
        for (long cable : cables) {
            count += cable / length;
        }
        return count;
    }
}
