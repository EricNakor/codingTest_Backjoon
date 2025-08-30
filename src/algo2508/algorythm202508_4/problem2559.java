package algo2508.algorythm202508_4;

import java.io.*;
import java.util.*;

public class problem2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] preSum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + arr[i - 1];
        }

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i <= N - K; i++) {
            int sum = preSum[i + K] - preSum[i];
            if (sum > maxSum) maxSum = sum;
        }

        System.out.println(maxSum);
    }
}
