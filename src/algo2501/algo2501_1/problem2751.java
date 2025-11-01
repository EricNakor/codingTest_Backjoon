package algo2501.algo2501_1;

import java.io.*;
import java.util.Arrays;

public class problem2751 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        // O(n^2)
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        // O(nlog n)
        Arrays.sort(arr);

        // O(n)
        for (int i = 0; i < N; i++) {
            bw.write(arr[i] + "\n");
        }

        // O(n^2 + nlog n + n)
        // O(nlog n) : 최종 시간 복잡도
        // 구현한 코드에서 가장 큰 시간 복잡도만 남기면 최종 시간 복잡도 이다
        bw.flush();
        bw.close();
    }
}
