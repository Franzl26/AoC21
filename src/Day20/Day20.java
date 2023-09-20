package Day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day20 {
    private static final String FILE = "./Input/Test";

    public static void main(String[] args) {

        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            String line = in.readLine();
            boolean[] replace = new boolean[line.length()];
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#') replace[i] = true;
            }

            in.readLine();
            line = in.readLine();
            boolean[][] map = new boolean[line.length()][line.length()];
            for (int y=0;y<100;y++) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '#') map[i][y] = true;
                }
                line = in.readLine();
            }

            for (int i=0;i<replace.length;i++) {
                if (replace[i]) System.out.print('#');
                else System.out.print('.');
            }
            System.out.println();
            System.out.println();
            for (int y=0;y<100;y++) {
                for (int i = 0; i < map.length; i++) {
                    if (map[i][y]) System.out.print('#');
                    else System.out.print('.');
                }
                System.out.println();
            }
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
