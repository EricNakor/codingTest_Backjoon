package algo2510.algo2510_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem11694 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nimSum = 0;
        for (int i = 0; i < N; i++) {
            int pile = Integer.parseInt(st.nextToken());
            nimSum ^= pile; // XOR 연산을 누적
        }

        // 님 합이 0이 아니면 선공 필승
        if (nimSum != 0) {
            System.out.println("koosaga");
        } else { // 님 합이 0이면 후공 필승
            System.out.println("cubelover");
        }
    }
}
