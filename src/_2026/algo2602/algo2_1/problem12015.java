package _2026.algo2602.algo2_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;

public class problem12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // LIS 길이를 구하기 위한 리스트 (실제 LIS가 아님에 주의)
        List<Integer> lis = new ArrayList<>();

        // 첫 번째 원소 삽입
        lis.add(A[0]);

        for (int i = 1; i < N; i++) {
            int key = A[i];

            // 1. 현재 값이 LIS 리스트의 마지막 값보다 크면 추가
            if (key > lis.get(lis.size() - 1)) {
                lis.add(key);
            }
            // 2. 작거나 같으면 이분 탐색으로 들어갈 자리(Lower Bound)를 찾아 교체
            else {
                int lo = 0;
                int hi = lis.size() - 1;

                while (lo < hi) {
                    int mid = (lo + hi) / 2;
                    if (lis.get(mid) >= key) {
                        hi = mid;
                    } else {
                        lo = mid + 1;
                    }
                }
                // lo(또는 hi)가 key보다 크거나 같은 첫 번째 위치임
                lis.set(hi, key);
            }
        }

        System.out.println(lis.size());
    }
}
