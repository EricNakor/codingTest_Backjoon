package algo2508.algo2508_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class problem7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (command.equals("I")) {
                    // 삽입: 해당 값의 개수 증가
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else if (command.equals("D")) {
                    if (!map.isEmpty()) {
                        int target;
                        if (num == 1) {
                            // 최댓값 삭제
                            target = map.lastKey();
                        } else {
                            // 최솟값 삭제
                            target = map.firstKey();
                        }

                        // 개수 감소
                        map.put(target, map.get(target) - 1);

                        // 개수가 0이 되면 키 제거
                        if (map.get(target) == 0) {
                            map.remove(target);
                        }
                    }
                }
            }

            // 결과 출력
            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }
        }

        System.out.print(sb);
    }
}
