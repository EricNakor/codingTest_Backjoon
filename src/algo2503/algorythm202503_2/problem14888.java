package algo2503.algorythm202503_2;

import java.io.*;
import java.util.StringTokenizer;

public class problem14888 {
    static int N;
    static int[] numbers;
    static int[] operators; // +, -, *, /
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        operators = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        // DFS 시작
        dfs(numbers[0], 1);

        // 결과 출력
        bw.write(MAX + "\n");
        bw.write(MIN + "\n");
        bw.flush();
        bw.close();
    }

    public static void dfs(int current, int idx) {
        if (idx == N) {
            MAX = Math.max(MAX, current);
            MIN = Math.min(MIN, current);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;

                switch (i) {
                    case 0:
                        dfs(current + numbers[idx], idx + 1);
                        break;
                    case 1:
                        dfs(current - numbers[idx], idx + 1);
                        break;
                    case 2:
                        dfs(current * numbers[idx], idx + 1);
                        break;
                    case 3:
                        dfs(current / numbers[idx], idx + 1);
                        break;
                }

                operators[i]++;
            }
        }
    }
}
