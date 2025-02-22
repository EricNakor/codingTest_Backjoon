package algorythm202502_3;

import java.io.*;

public class problem1065 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int count = countHansu(N);

        bw.write(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // 한수의 개수를 세는 함수
    static int countHansu(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (isHansu(i)) {
                count++;
            }
        }
        return count;
    }

    // 한수인지 체크하는 함수
    static boolean isHansu(int num) {
        String strNum = String.valueOf(num);
        int len = strNum.length();

        if (len == 1) return true; // 한 자리 수는 모두 한수
        if (len == 2) return true; // 두 자리 수는 모두 한수

        int diff = strNum.charAt(1) - strNum.charAt(0);
        for (int i = 2; i < len; i++) {
            if (strNum.charAt(i) - strNum.charAt(i - 1) != diff) {
                return false;
            }
        }
        return true;
    }
}
