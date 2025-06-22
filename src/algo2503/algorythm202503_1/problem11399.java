package algo2503.algorythm202503_1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] P = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(P);

        int sum = 0;
        int total = 0;

        for (int i = 0; i < N; i++) {
            sum += P[i];
            total += sum;
        }

        bw.write(String.valueOf(total));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
