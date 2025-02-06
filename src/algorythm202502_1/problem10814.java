package algorythm202502_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem10814 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Member> members = new ArrayList<>();

        // 1. 입력 처리
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            members.add(new Member(age, name, i));
        }

        // 2. 정렬 수행
        members.sort((m1, m2) -> {
            if (m1.age == m2.age) {
                return Integer.compare(m1.joinOrder, m2.joinOrder);
            }
            return Integer.compare(m1.age, m2.age);
        });

        // 3. 결과 출력
        for (Member member : members) {
            System.out.println(member.age + " " + member.name);
        }
    }

    static class Member {
        int age;
        String name;
        int joinOrder;

        public Member(int age, String name, int joinOrder) {
            this.age = age;
            this.name = name;
            this.joinOrder = joinOrder;
        }
    }
}
