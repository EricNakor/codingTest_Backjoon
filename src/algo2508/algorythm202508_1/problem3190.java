package algo2508.algorythm202508_1;

import java.io.*;
import java.util.*;

public class problem3190 {
    static int N;
    static int[][] board;
    static Deque<int[]> snake = new ArrayDeque<>();
    static Map<Integer, Character> directions = new HashMap<>();

    // 방향: 오른쪽, 아래, 왼쪽, 위 (시계방향)
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 보드 크기 입력
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1]; // 1-based indexing

        // 사과 개수 및 위치 입력
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 2; // 사과 표시
        }

        // 방향 전환 횟수 및 정보 입력
        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            directions.put(time, dir);
        }

        // 게임 시뮬레이션 실행
        int result = simulate();
        System.out.println(result);
    }

    static int simulate() {
        // 초기 설정: 뱀을 (1,1)에 위치, 오른쪽 방향
        snake.addFirst(new int[]{1, 1});
        board[1][1] = 1; // 뱀의 몸 표시

        int time = 0;
        int direction = 0; // 0: 오른쪽

        while (true) {
            time++; // 시간 1초 증가

            // 현재 머리 위치
            int[] head = snake.peekFirst();
            int currentX = head[0];
            int currentY = head[1];

            // 다음 머리 위치 계산
            int nextX = currentX + dx[direction];
            int nextY = currentY + dy[direction];

            // 벽 충돌 검사
            if (nextX < 1 || nextX > N || nextY < 1 || nextY > N) {
                return time; // 게임 종료
            }

            // 자기 몸 충돌 검사
            if (board[nextX][nextY] == 1) {
                return time; // 게임 종료
            }

            // 새로운 머리 추가
            snake.addFirst(new int[]{nextX, nextY});

            // 사과 확인 및 처리
            if (board[nextX][nextY] == 2) {
                // 사과가 있는 경우: 사과 제거, 꼬리 유지 (길이 증가)
                board[nextX][nextY] = 1; // 뱀의 몸으로 변경
            } else {
                // 사과가 없는 경우: 꼬리 제거 (길이 유지)
                int[] tail = snake.removeLast();
                board[tail[0]][tail[1]] = 0; // 꼬리 위치를 빈 칸으로
                board[nextX][nextY] = 1; // 새로운 머리를 뱀의 몸으로
            }

            // 방향 전환 확인
            if (directions.containsKey(time)) {
                char turn = directions.get(time);
                if (turn == 'L') {
                    // 왼쪽으로 90도 회전
                    direction = (direction + 3) % 4;
                } else if (turn == 'D') {
                    // 오른쪽으로 90도 회전
                    direction = (direction + 1) % 4;
                }
            }
        }
    }
}
