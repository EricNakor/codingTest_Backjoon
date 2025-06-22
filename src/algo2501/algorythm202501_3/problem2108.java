package algo2501.algorythm202501_3;

import java.io.*;
import java.util.*;


public class problem2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int sum = 0;
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            sum += numbers[i];
            frequency.put(numbers[i], frequency.getOrDefault(numbers[i], 0) + 1);
        }

        Arrays.sort(numbers);

        // 산술평균
        int mean = (int) Math.round((double) sum / N);

        // 중앙값
        int median = numbers[N / 2];

        // 최빈값
        int mode = getModeValue(frequency);

        // 범위
        int range = numbers[N - 1] - numbers[0];

        bw.write(mean + "\n");
        bw.write(median + "\n");
        bw.write(mode + "\n");
        bw.write(range + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static int getModeValue(Map<Integer, Integer> frequency) {
        int maxFrequency = Collections.max(frequency.values());
        List<Integer> modes = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modes.add(entry.getKey());
            }
        }

        Collections.sort(modes);
        return modes.size() > 1 ? modes.get(1) : modes.get(0);
    }
}
