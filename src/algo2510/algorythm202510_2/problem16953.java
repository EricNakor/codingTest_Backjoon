package algo2510.algorythm202510_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int count = 1;

        while (B > A) {
            // B의 마지막 자리가 1인 경우
            if (B % 10 == 1) {
                B /= 10;
            }
            // B가 짝수인 경우
            else if (B % 2 == 0) {
                B /= 2;
            }
            // 두 경우 모두 해당하지 않으면 변환 불가
            else {
                count = -1;
                break;
            }
            count++;
        }

        // 루프 종료 후 A와 B가 다르면 변환 불가
        if (count != -1 && B != A) {
            count = -1;
        }

        System.out.println(count);
    }
}
