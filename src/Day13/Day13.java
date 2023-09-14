package Day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Day13 {
    private static final String FILE = "./Input/Day13";
    public static void main(String[] args) {
        int maxX = 0, maxY = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            String line = in.readLine();
            while (line != null) {
                if (line.length() < 2) break;
                String[] s = line.split(",");
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                if (x > maxX) maxX = x;
                if (y > maxY) maxY = y;
                line = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("maxX: " + maxX + " maxY: " + maxY);

        boolean[][] feld = new boolean[maxX + 1][maxY + 1];
        LinkedList<String> fold = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            String line = in.readLine();
            while (line != null) {
                if (line.length() < 2) break;
                String[] s = line.split(",");
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                feld[x][y] = true;
                line = in.readLine();
            }
            line = in.readLine();
            while (line != null) {
                fold.add(line);
                line = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println(Arrays.toString(fold.toArray()));
        int anz = fold.size();
        for (int i = 0; i < anz; i++) {
            String s = fold.remove();
            int value = Integer.parseInt(s.split("=")[1]);
            if (s.contains("x")) {
                feld = foldVer(feld, value);
            }
            if (s.contains("y")) {
                feld = foldHor(feld, value);
            }
            if (i == 0) System.out.println("count after 1: " + count(feld));
        }
        print(feld);


    }

    public static void print(boolean[][] feld) {
        for (int y = 0; y < feld[0].length; y++) {
            for (int x = 0; x < feld.length; x++) {
                if (feld[x][y]) System.out.print("#");
                else System.out.print(".");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int count(boolean[][] feld) {
        int count = 0;
        for (int y = 0; y < feld[0].length; y++) {
            for (int x = 0; x < feld.length; x++) {
                if (feld[x][y]) count++;
            }
        }
        return count;
    }

    public static boolean[][] foldHor(boolean[][] feld, int foldAt) {
        boolean[][] neu = new boolean[feld.length][foldAt];

        for (int x = 0; x < feld.length; x++) {
            for (int y = 0; y < foldAt; y++) {
                neu[x][y] = feld[x][y];
            }
        }
        for (int x = 0; x < feld.length; x++) {
            for (int i = 1; i <= foldAt; i++) {
                if (feld[x][foldAt + i]) neu[x][foldAt - i] = true;
            }
        }

        return neu;
    }

    public static boolean[][] foldVer(boolean[][] feld, int foldAt) {
        boolean[][] neu = new boolean[foldAt][feld[0].length];

        for (int x = 0; x < foldAt; x++) {
            for (int y = 0; y < feld[0].length; y++) {
                neu[x][y] = feld[x][y];
            }
        }
        for (int i = 1; i <= foldAt; i++) {
            for (int y = 0; y < feld[0].length; y++) {
                if (feld[foldAt + i][y]) neu[foldAt - i][y] = true;
            }
        }

        return neu;
    }
}
