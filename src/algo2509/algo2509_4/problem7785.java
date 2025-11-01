package algo2509.algo2509_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem7785 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 현재 회사에 있는 사람을 기록할 HashSet
        Set<String> peopleInCompany = new HashSet<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String action = st.nextToken();

            if (action.equals("enter")) {
                peopleInCompany.add(name);
            } else if (action.equals("leave")) {
                peopleInCompany.remove(name);
            }
        }

        // 결과를 정렬하기 위해 리스트로 변환
        List<String> result = new ArrayList<>(peopleInCompany);

        // 사전 순의 역순(내림차순)으로 정렬
        result.sort(Collections.reverseOrder());

        // 빠른 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();
        for (String person : result) {
            sb.append(person).append('\n');
        }

        System.out.print(sb);
    }
}