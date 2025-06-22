package algo2502.algorythm202502_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class problem10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 1. 카드 개수 계산
        Map<Integer, Integer> cardCount = new HashMap<>();
        String[] cards = br.readLine().split(" ");
        for (String card : cards) {
            int num = Integer.parseInt(card);
            cardCount.put(num, cardCount.getOrDefault(num, 0) + 1);
        }

        // 2. 결과 출력
        int M = Integer.parseInt(br.readLine());
        String[] targets = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String target : targets) {
            int count = cardCount.getOrDefault(Integer.parseInt(target), 0);
            sb.append(count).append(' ');
        }
        System.out.println(sb.toString().trim());
    }
}
