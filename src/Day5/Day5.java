package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Day5 {
    static final boolean teil2 = true;

    public static void main(String[] args) {
        int[][] point = new int[1000][1000];
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day5"))) {
            String line = in.readLine();
            while (line != null) {
                line = line.replace(",", " ");
                line = line.replace("-", " ");
                line = line.replace(">", " ");
                Scanner sc = new Scanner(line);
                int xFrom = sc.nextInt();
                int yFrom = sc.nextInt();
                int xTo = sc.nextInt();
                int yTo = sc.nextInt();
                if (xFrom == xTo) {
                    if (yFrom < yTo) {
                        for (int y = yFrom; y <= yTo; y++) {
                            point[xFrom][y]++;
                        }
                    } else {
                        for (int y = yFrom; y >= yTo; y--) {
                            point[xFrom][y]++;
                        }
                    }
                } else if (yFrom == yTo) {
                    if (xFrom < xTo) {
                        for (int x = xFrom; x <= xTo; x++) {
                            point[x][yFrom]++;
                        }
                    } else {
                        for (int x = xFrom; x >= xTo; x--) {
                            point[x][yFrom]++;
                        }
                    }
                } else if (teil2) {
                    int xPlus = +1;
                    if (xFrom > xTo) xPlus = -1;
                    int yPlus = +1;
                    if (yFrom > yTo) yPlus = -1;

                    int dif = Math.abs(xTo - xFrom);
                    for (int i = 0; i <= dif; i++) {
                        point[xFrom + xPlus * i][yFrom + yPlus * i]++;
                    }
                }

                line = in.readLine();
            }
            int sum = 0;
            for (int x = 0; x < 1000; x++) {
                for (int y = 0; y < 1000; y++) {
                    if (point[x][y] > 1) sum++;
                }
            }
            System.out.println("sum: " + sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
