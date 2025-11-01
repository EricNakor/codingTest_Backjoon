package algo2503.algo2503_1;

import java.io.*;
import java.util.*;

public class problem1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> notHeard = new HashSet<>();
        List<String> result = new ArrayList<>();

        // 듣도 못한 사람 명단 저장
        for (int i = 0; i < N; i++) {
            notHeard.add(br.readLine());
        }

        // 보도 못한 사람 명단과 비교
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (notHeard.contains(name)) {
                result.add(name);
            }
        }

        // 결과 정렬 및 출력
        Collections.sort(result);

        bw.write(result.size() + "\n");
        for (String name : result) {
            bw.write(name + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
