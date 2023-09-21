package Day21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day21 {
    private static final String FILE = "./Input/Day21";

    public static void main(String[] args) {
        int p1, p1Rem;
        int p2, p2Rem;
        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            p1 = p1Rem = Integer.parseInt(in.readLine().split(" ")[4]) - 1;
            p2 = p2Rem = Integer.parseInt(in.readLine().split(" ")[4]) - 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        p1 = 3;
//        p2 = 7;
        int dice = 1;
        int p1Sum = 0;
        int p2Sum = 0;
        boolean player1 = true;
        while (true) {
//            System.out.printf("p1: %d, p1Sum: %d, p2: %d, p2Sum: %d\n", p1, p1Sum, p2, p2Sum);
            int add = dice * 3 + 3;
            if (player1) {
                p1 = (p1 + add) % 10;
                if (p1Sum + p1 + 1 >= 1000) {
                    System.out.println("part 1: " + ((dice + 2) * p2Sum));
                    break;
                }
                p1Sum += p1 + 1;
            } else {
                p2 = (p2 + add) % 10;
                if (p2Sum + p2 + 1 >= 1000) {
                    System.out.println("part 1: " + (p1Sum * (dice + 2)));
                    break;
                }
                p2Sum += p2 + 1;
            }
            dice += 3;
            player1 = !player1;
        }
        Game game = new Game(p1Rem, p2Rem, 0, 0);
    }

    public static long play(Game game) {

    }

    public static class Game {
        int p1;
        int p2;
        int p1Sum;
        int p2Sum;

        public Game(int p1, int p2, int p1Sum, int p2Sum) {
            this.p1 = p1;
            this.p2 = p2;
            this.p1Sum = p1Sum;
            this.p2Sum = p2Sum;
        }
    }
}
