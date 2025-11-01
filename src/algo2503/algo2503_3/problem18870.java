package algo2503.algo2503_3;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class problem18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 좌표 개수 입력받기
        int[] coords = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            coords[i] = Integer.parseInt(st.nextToken());
        }

        // 중복 제거 및 정렬
        int[] sortedCoords = Arrays.stream(coords).distinct().sorted().toArray();

        // 압축된 값 매핑
        Map<Integer, Integer> coordToIndex = new HashMap<>();
        for (int i = 0; i < sortedCoords.length; i++) {
            coordToIndex.put(sortedCoords[i], i);
        }

        // 좌표 압축 결과 생성 및 출력
        for (int coord : coords) {
            bw.write(coordToIndex.get(coord) + " ");
        }

        bw.flush();
        bw.close();
    }
}
