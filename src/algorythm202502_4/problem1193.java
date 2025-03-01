package algorythm202502_4;

import java.io.*;

public class problem1193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int X = Integer.parseInt(br.readLine());

        int cross = 1;
        while (X > cross) {
            X -= cross;
            cross++;
        }

        int son = X;
        int mom = cross - X + 1;

        if ((cross % 2) == 0) {
            int temp = son;
            son = mom;
            mom = temp;
        }

        bw.write(son + "/" + mom + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
