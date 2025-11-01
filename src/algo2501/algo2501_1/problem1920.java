package algo2501.algo2501_1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem1920 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 배열 크기
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        // 요소 넣기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 정렬
        Arrays.sort(arr);

        // 찾을 값
        int m = Integer.parseInt(br.readLine());

        // 찾을 값 이진 탐색
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            int result = Arrays.binarySearch(arr, target);
            bw.write((result >= 0 ? 1 : 0 )+ "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    // 시간초과
//        int N = Integer.parseInt(br.readLine());
//        int[] A = new int[N];
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        // O(n^2)
//        for (int i = 0; i < N; i++) {
//            A[i] = Integer.parseInt(st.nextToken());
//        }
//
//        int M = Integer.parseInt(br.readLine());
//
//        st = new StringTokenizer(br.readLine());
//
//        // O(n^2)
//        for (int i = 0; i < M; i++) {
//            int target = Integer.parseInt(st.nextToken());
//            bw.write(linearSearch(A, target) ? "1\n" : "0\n");
//        }
//
//        bw.flush();
//        bw.close();
//        br.close();
//    }
//
//    static boolean linearSearch(int[] arr, int target) {
//        // O(n)
//        for (int num : arr) {
//            if (num == target) {
//                return true;
//            }
//        }
//        return false;
//    }
}
