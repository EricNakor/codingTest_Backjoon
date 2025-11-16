package algo2511.algo11_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class problem16235 {
    static class Tree implements Comparable<Tree> {
        int r, c, age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    static int N, M, K;
    static int[][] A; // 겨울에 추가될 양분
    static int[][] nutrients; // 현재 양분
    static LinkedList<Tree>[][] treeMap; // 나무 맵

    // 8방향
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        nutrients = new int[N][N];
        treeMap = new LinkedList[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                nutrients[i][j] = 5; // 초기 양분 5
                treeMap[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int c = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int age = Integer.parseInt(st.nextToken());
            treeMap[r][c].add(new Tree(r, c, age));
        }

        // 초기 정렬 (한 번만 수행)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Collections.sort(treeMap[i][j]);
            }
        }

        // K년 시뮬레이션
        while (K-- > 0) {
            springAndSummer();
            fall();
            winter();
        }

        // 살아있는 나무 개수 세기
        int aliveCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                aliveCount += treeMap[i][j].size();
            }
        }
        System.out.println(aliveCount);
    }

    // 봄: 나무가 양분 먹고 나이 증가, 못 먹으면 죽음
    // 여름: 죽은 나무가 양분이 됨
    static void springAndSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                LinkedList<Tree> currentTrees = treeMap[i][j];
                LinkedList<Tree> survivors = new LinkedList<>();
                int deadNutrient = 0;

                while (!currentTrees.isEmpty()) {
                    Tree tree = currentTrees.pollFirst();
                    if (nutrients[i][j] >= tree.age) {
                        nutrients[i][j] -= tree.age;
                        tree.age++;
                        survivors.addLast(tree);
                    } else {
                        deadNutrient += tree.age / 2;
                    }
                }

                treeMap[i][j] = survivors; // 살아남은 나무로 갱신
                nutrients[i][j] += deadNutrient; // 여름
            }
        }
    }

    // 가을: 나무 번식
    static void fall() {
        List<Tree> newBorn = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Tree tree : treeMap[i][j]) {
                    if (tree.age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int nr = i + dr[d];
                            int nc = j + dc[d];
                            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                                newBorn.add(new Tree(nr, nc, 1));
                            }
                        }
                    }
                }
            }
        }
        // 번식은 동시에 일어나므로, 모았다가 한 번에 추가
        for (Tree baby : newBorn) {
            treeMap[baby.r][baby.c].addFirst(baby); // 나이 1살, 맨 앞에 추가
        }
    }

    // 겨울: 양분 추가
    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nutrients[i][j] += A[i][j];
            }
        }
    }
}
