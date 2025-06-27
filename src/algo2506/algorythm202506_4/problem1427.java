package algo2506.algorythm202506_4;

import java.io.*;
import java.util.Arrays;

public class problem1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력받은 숫자를 문자 배열로 변환
        char[] arr = br.readLine().toCharArray();

        // 내림차순 정렬
        Arrays.sort(arr);

        // 역순으로 출력
        for (int i = arr.length - 1; i >= 0; i--) {
            bw.write(arr[i]);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
