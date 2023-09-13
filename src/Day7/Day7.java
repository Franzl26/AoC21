package Day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day7"))) {
            ArrayList<Integer> list = new ArrayList<>();

            String line = in.readLine();
            line = line.replaceAll(",", " ");
            Scanner sc = new Scanner(line);
            while (sc.hasNextInt()) list.add(sc.nextInt());
            Integer[] sub = new Integer[list.size()];
            list.toArray(sub);
            long best = -1, anz = -1;
            for (int pos = 0; pos < 1500; pos++) {
                long sum = 0;
                for (int s : sub) {
                    sum += Math.abs(s - pos);
                }
                if (anz == -1 || sum < anz) {
                    anz = sum;
                    best = pos;
                }
            }
            System.out.println("best: " + best + " anz: " + anz);
            best = -1;
            anz = -1;
            for (int pos = 0; pos < 1500; pos++) {
                long sum = 0;
                for (int s : sub) {
                    int temp = Math.abs(s - pos);
                    sum += Math.round(0.5 * (temp * (temp + 1)));
                }
                if (anz == -1 || sum < anz) {
                    anz = sum;
                    best = pos;
                }
            }
            System.out.println("best: " + best + " anz: " + anz);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
