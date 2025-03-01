package algorythm202502_4;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class problem1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 포켓몬 개수
        int M = Integer.parseInt(st.nextToken()); // 문제 개수

        Map<String, Integer> nameToNumber = new HashMap<>();
        String[] numberToName = new String[N + 1]; // 1부터 시작하므로 N+1 크기로 생성

        // 포켓몬 정보 입력
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            nameToNumber.put(name, i);
            numberToName[i] = name;
        }

        // 문제 해결
        for (int i = 0; i < M; i++) {
            String query = br.readLine();
            try {
                int number = Integer.parseInt(query); // 숫자인지 확인
                if (number >= 1 && number <= N) {
                    bw.write(numberToName[number] + "\n"); // 숫자에 해당하는 이름 출력
                } else {
                    bw.write("Invalid number\n"); // 예외 처리
                }
            } catch (NumberFormatException e) {
                if (nameToNumber.containsKey(query)) {
                    bw.write(nameToNumber.get(query) + "\n"); // 이름에 해당하는 숫자 출력
                } else {
                    bw.write("Pokemon not found\n"); // 예외 처리
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
