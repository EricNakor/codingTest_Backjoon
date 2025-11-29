package algo2511.algo11_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class problem1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();
        int zeroCount = 0;
        int oneCount = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                plus.add(num);
            } else if (num == 1) {
                oneCount++;
            } else if (num == 0) {
                zeroCount++;
            } else {
                minus.add(num);
            }
        }

        // 양수는 큰 수부터 묶기 위해 내림차순 정렬
        Collections.sort(plus, Collections.reverseOrder());
        // 음수는 작은 수(절대값이 큰 수)부터 묶기 위해 오름차순 정렬
        Collections.sort(minus);

        long sum = 0;

        // 양수 처리
        int i = 0;
        while (i < plus.size()) {
            if (i + 1 < plus.size()) {
                sum += (long) plus.get(i) * plus.get(i + 1);
                i += 2;
            } else {
                sum += plus.get(i);
                i++;
            }
        }

        // 음수 처리
        i = 0;
        while (i < minus.size()) {
            if (i + 1 < minus.size()) {
                sum += (long) minus.get(i) * minus.get(i + 1);
                i += 2;
            } else {
                // 남은 음수가 있고, 0이 없다면 더해줌 (0이 있으면 곱해서 0이 되므로 더하지 않음)
                if (zeroCount == 0) {
                    sum += minus.get(i);
                }
                i++;
            }
        }

        // 1은 그냥 더해줌
        sum += oneCount;

        System.out.println(sum);
    }
}
