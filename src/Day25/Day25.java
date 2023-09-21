package Day25;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day25 {
    private static final String FILE = "./Input/Day25";

    public static void main(String[] args) {
        char[][] map = new char[137][];

        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            for (int y = 0; y < map.length; y++) {
                String line = in.readLine();
                map[y] = line.toCharArray();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        print(map);
        int[] move = new int[1];
        for (int i = 0; i < 1000; i++) {
            map = move(map, move);
            if (move[0] == 0) {
                System.out.println("part 1: " + (i + 1));
//                print(map);
                break;
            }
        }
    }

    public static void print(char[][] map) {
        for (char[] line : map) {
            System.out.println(line);
        }
        System.out.println();
    }

    public static char[][] move(char[][] old, int[] moves) {
        char[][] step = new char[old.length][old[0].length];
        int move = 0;

        for (int y = 0; y < old.length; y++) {
            for (int x = 0; x < old[0].length; x++) {
                step[y][x] = '.';
            }
        }

        for (int y = 0; y < old.length; y++) {
            for (int x = 0; x < old[0].length; x++) {
                if (old[y][x] == 'v') {
                    step[y][x] = 'v';
                    continue;
                }
                if (old[y][x] == '.') continue;
                if (old[y][(x + 1) % old[0].length] == '.') {
                    step[y][(x + 1) % old[0].length] = '>';
                    move++;
                } else step[y][x] = '>';
            }
        }

        char[][] step2 = new char[old.length][old[0].length];
        for (int y = 0; y < old.length; y++) {
            for (int x = 0; x < old[0].length; x++) {
                if (step[y][x] == 'v') step2[y][x] = '.';
                else step2[y][x] = step[y][x];
            }
        }

        for (int y = 0; y < old.length; y++) {
            for (int x = 0; x < old[0].length; x++) {
                if (old[y][x] == '.' || old[y][x] == '>') continue;
                if (step[(y + 1) % old.length][x] == '.') {
                    step2[(y + 1) % old.length][x] = 'v';
                    move++;
                }
                else step2[y][x] = 'v';
            }
        }

        moves[0] = move;
        return step2;
    }
}
