package algo2504.algo2504_4;

import java.io.*;
import java.util.StringTokenizer;

public class problem25206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        double sumNumxScore = 0;
        double sumNum = 0;

        for (int i = 0; i < 20; i++) {
            st = new StringTokenizer(br.readLine());
            String object = st.nextToken();
            double num = Double.parseDouble(st.nextToken());
            String alphabet = st.nextToken();
            double score = 0;

            if (!(alphabet.equals("P"))) {
                switch (alphabet) {
                    case "A+":
                        score = 4.5;
                        break;
                    case "A0":
                        score = 4.0;
                        break;
                    case "B+":
                        score = 3.5;
                        break;
                    case "B0":
                        score = 3.0;
                        break;
                    case "C+":
                        score = 2.5;
                        break;
                    case "C0":
                        score = 2.0;
                        break;
                    case "D+":
                        score = 1.5;
                        break;
                    case "D0":
                        score = 1.0;
                        break;
                    case "F":
                        score = 0.0;
                        break;
                }

                sumNumxScore += (num * score);
                sumNum += num;
            }
        }

        System.out.printf("%.6f", sumNumxScore / sumNum);
        br.close();
    }
}
