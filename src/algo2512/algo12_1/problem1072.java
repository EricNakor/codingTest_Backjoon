package algo2512.algo12_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem1072 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        long Z = (Y * 100) / X;

        // 승률이 99% 이상이면 더 이상 오를 수 없음 (절대 100%가 될 수 없거나 이미 100%)
        if (Z >= 99) {
            System.out.println(-1);
            return;
        }

        long left = 0;
        long right = 1_000_000_000; // 충분히 큰 값
        long answer = -1;

        while (left <= right) {
            long mid = (left + right) / 2;

            // mid판을 더 이겼을 때의 새로운 승률
            long newZ = ((Y + mid) * 100) / (X + mid);

            if (newZ > Z) {
                // 승률이 올랐다면, 최소 횟수를 찾기 위해 더 작은 범위를 탐색
                answer = mid;
                right = mid - 1;
            } else {
                // 승률이 그대로라면, 더 많은 게임이 필요함
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
