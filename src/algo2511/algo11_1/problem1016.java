package algo2511.algo11_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem1016 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        int range = (int) (max - min + 1);
        boolean[] isNotSquareFree = new boolean[range];

        // 1. 2부터 sqrt(max)까지의 수 i에 대해
        for (long i = 2; i * i <= max; i++) {
            long square = i * i;

            // 2. min 이상인 첫 번째 square의 배수를 찾음
            // (min / square)가 몫.
            // 나머지가 0이 아니면 몫+1 (올림)한 후 square를 곱함
            long startNum;
            if (min % square == 0) {
                startNum = min;
            } else {
                startNum = (min / square + 1) * square;
            }

            // 3. min~max 범위 내의 square 배수들을 체킹
            for (long num = startNum; num <= max; num += square) {
                isNotSquareFree[(int) (num - min)] = true;
            }
        }

        // 4. 제곱 ㄴㄴ 수 개수 카운트
        int count = 0;
        for (int i = 0; i < range; i++) {
            if (!isNotSquareFree[i]) {
                count++;
            }
        }

        System.out.println(count);
    }
}
