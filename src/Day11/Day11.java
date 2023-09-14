package Day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day11 {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day11"))) {
            int[][] field = new int[10][10];
            int[][] field2 = new int[10][10];

            String line;
            for (int y = 0; y < 10; y++) {
                line = in.readLine();
                for (int x = 0; x < 10; x++) {
                    field[x][y] = Integer.parseInt(line.substring(x, x + 1));
                    field2[x][y] = Integer.parseInt(line.substring(x, x + 1));
                }
            }


            long flashes = 0;
            for (int i = 0; i < 100; i++) {
                flashes += evolve(field);
            }
            System.out.println("flashed: " + flashes);

            int step = 1;
            while (evolve(field2) != 100) step++;

            System.out.println("steps: " + step);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int evolve(int[][] field) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                field[x][y]++;
            }
        }

        int flashes = 0;
        boolean flash = false;
        boolean[][] flashed = new boolean[10][10];
        do {
            flash = false;
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    if (field[x][y] > 9 && !flashed[x][y]) {
                        flash = true;
                        flashed[x][y] = true;
                        flashes++;
                        if (x > 0) field[x - 1][y]++;
                        if (x > 0 && y > 0) field[x - 1][y - 1]++;
                        if (y > 0) field[x][y - 1]++;
                        if (x < 9 && y > 0) field[x + 1][y - 1]++;
                        if (x < 9) field[x + 1][y]++;
                        if (x < 9 && y < 9) field[x + 1][y + 1]++;
                        if (y < 9) field[x][y + 1]++;
                        if (x > 0 && y < 9) field[x - 1][y + 1]++;
                    }
                }
            }
        } while (flash);
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (flashed[x][y]) field[x][y] = 0;
            }
        }
        return flashes;
    }
}
