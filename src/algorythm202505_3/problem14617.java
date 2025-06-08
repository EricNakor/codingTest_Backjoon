package algorythm202505_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class problem14617 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 중복 제거를 위한 Set 사용
        Set<BigInteger> uniqueValues = new HashSet<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            BigInteger A = new BigInteger(st.nextToken());
            BigInteger B = new BigInteger(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            BigInteger power = BigInteger.ONE; // B^0 = 1

            // A×B^0부터 A×B^C까지 계산
            for (int j = 0; j <= C; j++) {
                BigInteger result = A.multiply(power);
                uniqueValues.add(result);
                power = power.multiply(B); // 다음 거듭제곱 준비
            }
        }

        // 서로 다른 값의 개수 출력
        System.out.println(uniqueValues.size());
    }
}
