package algo2503.algo2503_3;

import java.io.*;

public class problem1436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 입력받은 N
        int count = 0; // 종말의 숫자 개수
        int number = 666; // 첫 번째 종말의 숫자부터 시작

        while (true) {
            // 현재 숫자에 "666"이 포함되어 있는지 확인
            if (String.valueOf(number).contains("666")) {
                count++; // 종말의 숫자 개수 증가
            }

            // N번째 종말의 숫자를 찾으면 출력 후 종료
            if (count == N) {
                bw.write(number + "\n");
                break;
            }

            number++; // 다음 숫자로 이동
        }

        bw.flush();
        bw.close();
    }
}