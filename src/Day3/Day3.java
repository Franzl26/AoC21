package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Day3 {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day3"))) {
            int[] nullen = new int[12];
            int[] einsen = new int[12];
            String line = in.readLine();
            while (line != null) {
                for (int i = 0; i < 12; i++) {
                    if (line.charAt(i) == '1') einsen[i]++;
                    else nullen[i]++;
                }

                line = in.readLine();
            }
            int gamma = 0;
            int epsilon = 0;
            int[] array = new int[]{2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
            for (int i = 0; i < 12; i++) {
                if (nullen[i] < einsen[i]) {
                    gamma += array[i];
                } else {
                    epsilon += array[i];
                }
            }
            System.out.printf("gamma: %d, epsilon: %d, sum: %d, mult: %d\n", gamma, epsilon, gamma + epsilon, gamma * epsilon);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day3"))) {

            LinkedList<String> slines = new LinkedList<>();
            for (int i = 0; i < 1000; i++) slines.add(in.readLine());
            LinkedList<String> glines = (LinkedList<String>) slines.clone();

            int[] nullen = new int[12];
            int[] einsen = new int[12];
            for (int i = 0; i < 12 ;i++) {
                for (String s : glines) {
                    if (s.charAt(i) == '1') einsen[i]++;
                    else nullen[i]++;
                }
                LinkedList<String> neu = (LinkedList<String>) glines.clone();
                if (einsen[i] >= nullen[i]) {
                    for (String s : glines) {
                        if (s.charAt(i) == '0') neu.remove(s);
                    }
                } else {
                    for (String s : glines) {
                        if (s.charAt(i) == '1') neu.remove(s);
                    }
                }
                glines = neu;
                if (glines.size() < 2) break;
            }
            nullen = new int[12];
            einsen = new int[12];
            for (int i = 0; i < 12 ;i++) {
                for (String s : slines) {
                    if (s.charAt(i) == '1') einsen[i]++;
                    else nullen[i]++;
                }
                LinkedList<String> neu = (LinkedList<String>) slines.clone();
                if (einsen[i] >= nullen[i]) {
                    for (String s : slines) {
                        if (s.charAt(i) == '1') neu.remove(s);
                    }
                } else {
                    for (String s : slines) {
                        if (s.charAt(i) == '0') neu.remove(s);
                    }
                }
                slines = neu;
                if (slines.size() < 2) break;
            }
//            System.out.println("g: " + glines);
//            System.out.println("s: " + slines);

            int scrub = Integer.parseInt(slines.remove(), 2);
            int gen = Integer.parseInt(glines.remove(), 2);

            System.out.printf("scrub: %d, gen: %d, sum: %d, mult: %d\n", scrub, gen, scrub + gen, scrub * gen);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
