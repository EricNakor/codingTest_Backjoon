package algo2512.algo12_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 투 포인터 사용을 위한 정렬
        Arrays.sort(arr);

        int count = 0;

        // 모든 수를 타겟으로 하여 탐색
        for (int i = 0; i < N; i++) {
            long target = arr[i];
            int left = 0;
            int right = N - 1;

            while (left < right) {
                long sum = arr[left] + arr[right];

                if (sum == target) {
                    // 본인 자신을 포함하지 않는 서로 다른 두 수여야 함
                    if (left != i && right != i) {
                        count++;
                        break; // 하나라도 찾으면 Good Number임
                    } else if (left == i) {
                        left++;
                    } else if (right == i) {
                        right--;
                    }
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}
