package algo2507.algorythm202507_3;

import java.io.*;

public class proble1676 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 0;

        // N!에서 5의 지수 개수 계산
        for (int i = 5; i <= N; i *= 5) {
            count += N / i;
        }

        System.out.println(count);
    }
}