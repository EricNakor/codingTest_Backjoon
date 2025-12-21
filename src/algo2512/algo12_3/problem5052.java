package algo2512.algo12_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class problem5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] phoneNumbers = new String[n];

            for (int i = 0; i < n; i++) {
                phoneNumbers[i] = br.readLine();
            }

            // 1. 전화번호 목록을 사전순으로 정렬
            Arrays.sort(phoneNumbers);

            boolean isConsistent = true;

            // 2. 인접한 두 번호만 비교
            for (int i = 0; i < n - 1; i++) {
                // 뒷 번호가 앞 번호로 시작하는지 확인 (접두어 관계)
                if (phoneNumbers[i + 1].startsWith(phoneNumbers[i])) {
                    isConsistent = false;
                    break;
                }
            }

            if (isConsistent) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.print(sb);
    }
}
