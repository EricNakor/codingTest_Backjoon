package algo2510.algorythm202510_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class problem14425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> s = new HashSet<>();
        for (int i = 0; i < N; i++) {
            s.add(br.readLine());
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            if (s.contains(br.readLine())) {
                count++;
            }
        }

        System.out.println(count);
    }
}
