package Day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day20 {
    private static final String FILE = "./Input/Day20";

    public static void main(String[] args) {

        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            // read
            String line = in.readLine();
            boolean[] replace = new boolean[line.length()];
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#') replace[i] = true;
            }

            in.readLine();
            line = in.readLine();
            boolean[][] map = new boolean[line.length() + 104][line.length() + 104];
            for (int y = 0; y < 100; y++) {
                for (int x = 0; x < line.length(); x++) {
                    if (line.charAt(x) == '#') map[y + 52][x + 52] = true;
                }
                line = in.readLine();
            }

            print(replace, map);
            for (int i = 0; i < 50; i++) {
                map = step(replace, map);
//                print(replace, map);
                if (i == 1) System.out.println("part 1: " + count(map));
            }
            print(replace, map);
            System.out.println("part 2: " + count(map));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void print(boolean[] replace, boolean[][] map) {
        for (boolean b : replace) {
            if (b) System.out.print('#');
            else System.out.print('.');
        }
        System.out.println();
        System.out.println();
        for (boolean[] booleans : map) {
            for (boolean b : booleans) {
                if (b) System.out.print('#');
                else System.out.print('.');
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean[][] step(boolean[] replace, boolean[][] map) {
        boolean[][] step = new boolean[map.length][map[0].length];

        for (int y = 1; y < map.length - 1; y++) {
            for (int x = 1; x < map[0].length - 1; x++) {
                int bin = 0;
                if (map[y - 1][x - 1]) bin += 256;
                if (map[y - 1][x]) bin += 128;
                if (map[y - 1][x + 1]) bin += 64;
                if (map[y][x - 1]) bin += 32;
                if (map[y][x]) bin += 16;
                if (map[y][x + 1]) bin += 8;
                if (map[y + 1][x - 1]) bin += 4;
                if (map[y + 1][x]) bin += 2;
                if (map[y + 1][x + 1]) bin += 1;
                step[y][x] = replace[bin];
            }
        }

        if (replace[0]) {
            for (int y = 0; y < map.length; y++) {
                step[y][0] = !map[y][0];
                step[y][map[0].length - 1] = !map[y][map[0].length - 1];
            }
            for (int x = 0; x < map[0].length; x++) {
                step[0][x] = !map[0][x];
                step[map.length - 1][x] = !map[map.length - 1][x];
            }
        }
        return step;
    }

    public static int count(boolean[][] map) {
        int count = 0;
        for (boolean[] booleans : map) {
            for (boolean b : booleans) {
                if (b) count++;
            }
        }
        return count;
    }
}
