package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
//        bad();
        better();
    }

    public static void better() {
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day6"))) {
            String line = in.readLine();
            long[] fish = new long[9];
            int anz = (int) ((line.length() + 1) * 0.5);
            while (line != null) {
                line = line.replaceAll(",", " ");
                Scanner sc = new Scanner(line);
                for (int i = 0; i < anz; i++) {
                    fish[sc.nextInt()]++;
                }

                line = in.readLine();
            }
            System.out.println(Arrays.toString(fish));
            for (int day = 0; day < 256; day++) {
                long[] newFish = new long[9];
                newFish[0] = fish[1];
                newFish[1] = fish[2];
                newFish[2] = fish[3];
                newFish[3] = fish[4];
                newFish[4] = fish[5];
                newFish[5] = fish[6];
                newFish[6] = fish[7] + fish[0];
                newFish[7] = fish[8];
                newFish[8] = fish[0];
                fish = newFish;
                if (day == 79) System.out.println("sum: " + Arrays.stream(fish).sum());
            }
            System.out.println("sum: " + Arrays.stream(fish).sum());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bad() {
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day6"))) {
            String line = in.readLine();
            int[] fish = new int[1000000];
            int anz = (int) ((line.length() + 1) * 0.5);
            while (line != null) {
                line = line.replaceAll(",", " ");
                Scanner sc = new Scanner(line);
                for (int i = 0; i < anz; i++) {
                    fish[i] = sc.nextInt();
                }

                line = in.readLine();
            }
            System.out.println(Arrays.toString(fish));
            for (int day = 0; day < 80; day++) {
                int anzNew = anz;
                for (int f = 0; f < anz; f++) {
                    fish[f]--;
                    if (fish[f] < 0) {
                        fish[f] = 6;
                        fish[anzNew] = 8;
                        anzNew++;
                    }
                }
                anz = anzNew;
            }
            System.out.println("anzahl: " + anz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
