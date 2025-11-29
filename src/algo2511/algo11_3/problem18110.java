package algo2511.algo11_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class problem18110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 의견이 하나도 없으면 0 출력
        if (n == 0) {
            System.out.println(0);
            return;
        }

        int[] ratings = new int[n];
        for (int i = 0; i < n; i++) {
            ratings[i] = Integer.parseInt(br.readLine());
        }

        // 절사평균을 위해 정렬
        Arrays.sort(ratings);

        // 위, 아래에서 제외할 인원 수 계산 (반올림)
        int cutoff = (int) Math.round(n * 0.15);

        double sum = 0;
        // 제외된 인원을 뺀 나머지 합계 계산
        for (int i = cutoff; i < n - cutoff; i++) {
            sum += ratings[i];
        }

        // 반영된 인원 수
        int count = n - 2 * cutoff;

        // 평균 계산 후 반올림
        int result = (int) Math.round(sum / count);

        System.out.println(result);
    }
}
