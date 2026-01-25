package _2026.algo2601.algo1_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem12851 {
    static int N, K;
    static int[] time = new int[100001]; // 각 위치별 최소 도달 시간 저장
    static int minTime = Integer.MAX_VALUE;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 예외 처리: 시작과 끝이 같은 경우
        if (N == K) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        bfs();

        System.out.println(minTime - 1); // 시작을 1초로 했으므로 -1
        System.out.println(count);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        time[N] = 1; // 방문 처리 및 시간 기록 (0은 미방문으로 간주하기 위해 1부터 시작)

        while (!q.isEmpty()) {
            int now = q.poll();

            // 현재 시간이 이미 구한 최단 시간보다 길다면 탐색 중단 (가지치기)
            if (minTime < time[now]) return;

            // 3가지 이동 방법 탐색
            int[] nextMoves = {now - 1, now + 1, now * 2};

            for (int next : nextMoves) {
                // 범위 체크
                if (next < 0 || next > 100000) continue;

                // 목표 도달 확인
                if (next == K) {
                    // 처음 도착한 경우 (최단 시간 갱신)
                    if (minTime == Integer.MAX_VALUE) {
                        minTime = time[now] + 1;
                        count = 1;
                    }
                    // 이미 찾은 최단 시간과 동일하게 도착한 경우 (카운트 증가)
                    else if (minTime == time[now] + 1) {
                        count++;
                    }
                }

                // 방문 조건:
                // 1. 처음 방문하거나 (time[next] == 0)
                // 2. 이미 방문했지만, 현재 경로를 통해 도달하는 시간이 기존 최단 시간과 같은 경우 (time[next] == time[now] + 1)
                if (time[next] == 0 || time[next] == time[now] + 1) {
                    time[next] = time[now] + 1;
                    q.add(next);
                }
            }
        }
    }
}