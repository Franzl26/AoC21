package Day9;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day9 {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day9"))) {
            int[][] map = new int[100][100];
            boolean[][] marked = new boolean[100][100];
            LinkedList<Point> lows = new LinkedList<>();

            for (int y = 0; y < 100; y++) {
                String line = in.readLine();
                for (int x = 0; x < 100; x++) {
                    map[x][y] = Integer.parseInt(line.substring(x, x + 1));
                }
            }

            long risk = 0;

            for (int y = 0; y < 100; y++) {
                for (int x = 0; x < 100; x++) {
                    if ((x == 0 || map[x][y] < map[x - 1][y]) && (x == 99 || map[x][y] < map[x + 1][y]) && (y == 0 || map[x][y] < map[x][y - 1]) && (y == 99 || map[x][y] < map[x][y+1])) {
                        risk += map[x][y] + 1;
                        lows.add(new Point(x, y));
                    }
                }
            }
            System.out.println("risk: "+ risk);
            ArrayList<Integer> list = new ArrayList<>();
            for (Point p : lows) {
                if (!marked[p.x][p.y]) {
                    int a = floodFill(map, marked, p.x, p.y);
                    list.add(a);
                    //System.out.println("area: " + a);
                }
            }
            Integer[] sort = new Integer[list.size()];
            list.toArray(sort);
            Object[] arr = Arrays.stream(sort).sorted().toArray();
            System.out.println("value: " + (int)arr[arr.length - 1] * (int)arr[arr.length - 2] * (int)arr[arr.length - 3]);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int floodFill(int[][] map, boolean[][] marked, int x, int y) {
        if (marked[x][y]) return 0;
        marked[x][y] = true;
        int ret = 1;
        if (x > 0 && map[x - 1][y] < 9) ret += floodFill(map, marked, x - 1, y);
        if (x < 99 && map[x + 1][y] < 9) ret += floodFill(map, marked, x + 1, y);
        if (y > 0 && map[x][y - 1] < 9) ret += floodFill(map, marked, x, y - 1);
        if (y < 99 && map[x][y + 1] < 9) ret += floodFill(map, marked, x, y + 1);
        return ret;
    }

    public static class Point {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

//for (int y=0;y<100;y++) {
//        for (int x = 0; x < 100; x++) {
//        System.out.print(map[x][y]);
//        }
//        System.out.println();
//        }