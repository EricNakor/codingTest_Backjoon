package algo2502.algorythm202502_4;

import java.io.*;

public class problem1193 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        int X = Integer.parseInt(br.readLine());
//
//        int cross = 1;
//        while (X > cross) {
//            X -= cross;
//            cross++;
//        }
//
//        int son = X;
//        int mom = cross - X + 1;
//
//        if ((cross % 2) == 0) {
//            int temp = son;
//            son = mom;
//            mom = temp;
//        }
//
//        bw.write(son + "/" + mom + "\n");
//        bw.flush();
//        bw.close();
//        br.close();
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int X = Integer.parseInt(br.readLine());

        int diagonal = 1; // 대각선 번호
        int start = 1;    // 대각선 시작 번호

        // X가 속한 대각선 찾기
        while (X > start + diagonal - 1) {
            start = start + diagonal;
            diagonal++;
        }

        int numerator;   // 분자
        int denominator; // 분모

        // 분자/분모 계산
        if (diagonal % 2 == 1) { // 홀수
            numerator = diagonal - (X - start);
            denominator = X - start + 1;
        } else { // 짝수
            numerator = X - start + 1;
            denominator = diagonal - (X - start);
        }

        // 결과 출력
        bw.write(numerator + "/" + denominator + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
