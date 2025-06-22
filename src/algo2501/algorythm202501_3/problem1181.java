package algo2501.algorythm202501_3;

import java.io.*;
import java.util.TreeSet;

public class problem1181 {
    // 틀림
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        TreeSet<Word> words = new TreeSet<>((a, b) -> {
            if (a.length != b.length) {
                return a.length - b.length;
            }
            return a.str.compareTo(b.str);
        });

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words.add(new Word(word, word.length()));
        }

        for (Word word : words) {
            bw.write(word.str);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static class Word {
        String str;
        int length;

        Word(String str, int length) {
            this.str = str;
            this.length = length;
        }
    }
}
