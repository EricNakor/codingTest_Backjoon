package algo2511.algo11_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class problem10610 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();

        char[] digits = N.toCharArray();

        long sum = 0;
        boolean hasZero = false;

        for (char d : digits) {
            int num = d - '0';
            sum += num;
            if (num == 0) {
                hasZero = true;
            }
        }

        // 1. 10의 배수(0 포함)이 아니거나
        // 2. 3의 배수(자릿수 합)가 아니면
        // 30의 배수를 만들 수 없음
        if (!hasZero || sum % 3 != 0) {
            System.out.println(-1);
            return;
        }

        // 3. 30의 배수를 만들 수 있다면, 가장 큰 수를 만들기 위해 내림차순 정렬
        Arrays.sort(digits);

        StringBuilder sb = new StringBuilder();
        // 정렬된 배열을 역순으로(내림차순) append
        for (int i = digits.length - 1; i >= 0; i--) {
            sb.append(digits[i]);
        }

        System.out.println(sb.toString());
    }
}
