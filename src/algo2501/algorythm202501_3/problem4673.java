package algo2501.algorythm202501_3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class problem4673 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean[] isNotSelfNumber = new boolean[10001];

        for (int i = 1; i <= 10000; i++) {
            int n = d(i);
            if (n <= 10000) {
                isNotSelfNumber[n] = true;
            }
        }

        for (int i = 1; i <= 10000; i++) {
            if (!isNotSelfNumber[i]) {
                bw.write(Integer.toString(i));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
    }

    public static int d(int n) {
        int sum = n;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
