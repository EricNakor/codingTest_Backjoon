package algo2502.algorythm202502_4;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class problem10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> sanggeunSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            sanggeunSet.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (sanggeunSet.contains(num)) {
                sb.append(1).append(" ");
            } else {
                sb.append(0).append(" ");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
