package Day1;

import java.io.*;

public class Day1 {
    public static void main(String[] args) {
        int old = -1;
        int count = 0;
        int[] zahlen = new int[2001];
        int count2 = 0;
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day1"))) {
            String line = in.readLine();
            while (line != null) {
                int neu = Integer.parseInt(line);
                zahlen[count2] = neu;
                count2++;
                if (old == -1) {
                    old = neu;
                    continue;
                }
                if (neu > old) count++;
                old = neu;
                line = in.readLine();
            }
            System.out.println("1: " + count);
            count = 0;
            for (int i = 1; i + 2 < 2000; i++) {
                int c1 = zahlen[i - 1] + zahlen[i] + zahlen[i+1];
                int c2 = zahlen[i] + zahlen[i+1] + zahlen[i+2];
                if (c1 < c2) count++;
            }
            System.out.println("2: " + count);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
