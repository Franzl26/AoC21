package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        LinkedList<Integer> numbers = new LinkedList<>();
        LinkedList<Board> boards = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day4"))) {
            String line = in.readLine();

            String[] ns = line.split(",");
            for (String s : ns) numbers.add(Integer.parseInt(s));

            line = in.readLine();
            while (line != null) {
                Board b = new Board();
                for (int x = 0; x < 5; x++) {
                    line = in.readLine();
                    Scanner sc = new Scanner(line);
                    for (int y = 0; y < 5 ; y++) {
                        b.grid[x][y] = sc.nextInt();
                    }
                }
                boards.add(b);

                line = in.readLine();
            }
            boolean first = false;
            int total = boards.size();
            int full = 0;
            for (int i : numbers) {
                System.out.println("adding: " + i);
                for (Board b : boards) {
                    b.mark(i);
                    boolean f = b.finished;
                    if (b.checkFinished() && !first) {
                        first = true;
                        System.out.println("found");
                        System.out.println(i);
                        System.out.println("sum: " + b.calcValue() + " mult: " + b.calcValue() * i);
                    }
                    if (!f && b.checkFinished()) full++;
                    if (full == total) {
                        System.out.println("sum: " + b.calcValue() + " mult: " + b.calcValue() * i);
                        return;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class Board {
        int[][] grid = new int[5][5];
        boolean[][] marked = new boolean[5][5];
        boolean finished = false;

        void mark(int n) {
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    if (grid[x][y] == n) marked[x][y] = true;
                }
            }
        }

        boolean checkFinished() {
            if (finished) return true;
            // -
            for (int x = 0; x < 5; x++) {
                if (marked[x][0] && marked[x][1] && marked[x][2] && marked[x][3] && marked[x][4]){
                    finished = true;
                    return true;
                }
            }
            // |
            for (int y = 0; y < 5; y++) {
                if (marked[0][y] && marked[1][y] && marked[2][y] && marked[3][y] && marked[4][y]) {
                    finished = true;
                    return true;
                }
            }
            return false;
        }

        void print() {
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    System.out.print(grid[x][y] + " ");
                }
                System.out.println();
            }
        }

        int calcValue() {
            int sum = 0;
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    if (!marked[x][y]) sum += grid[x][y];
                }
            }
            return sum;
        }
    }
}
