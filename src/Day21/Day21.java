package Day21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

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
//        p1Rem = p1 = 3;
//        p2Rem = p2 = 7;
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
        Game game = new Game(p1Rem, p2Rem, 0, 0, true);
        long[] arr = play(game);
        System.out.println(Arrays.toString(arr));
        if (arr[0] > arr[1]) {
            System.out.println("Player 1: " + arr[0]);
        } else {
            System.out.println("Player 2: " + arr[1]);

        }
    }

    public static long[] play(Game game) {
        if (game.p1Sum >= 21) return new long[]{1, 0};
        if (game.p2Sum >= 21) return new long[]{0, 1};

        long[] newScore = new long[2];
        int add;
        long[] tmp = new long[2];
        if (game.p1Turn) {
            add = 3;
            tmp = play(new Game((game.p1 + add) % 10, game.p2, game.p1Sum + ((game.p1 + add) % 10)+1, game.p2Sum, false));
            newScore[0] += tmp[0];
            newScore[1] += tmp[1];
            add = 4;
            tmp = play(new Game((game.p1 + add) % 10, game.p2, game.p1Sum + ((game.p1 + add) % 10)+1, game.p2Sum, false));
            newScore[0] += 3 * tmp[0];
            newScore[1] += 3 * tmp[1];
            add = 5;
            tmp = play(new Game((game.p1 + add) % 10, game.p2, game.p1Sum + ((game.p1 + add) % 10)+1, game.p2Sum, false));
            newScore[0] += 6 * tmp[0];
            newScore[1] += 6 * tmp[1];
            add = 6;
            tmp = play(new Game((game.p1 + add) % 10, game.p2, game.p1Sum + ((game.p1 + add) % 10)+1, game.p2Sum, false));
            newScore[0] += 7 * tmp[0];
            newScore[1] += 7 * tmp[1];
            add = 7;
            tmp = play(new Game((game.p1 + add) % 10, game.p2, game.p1Sum + ((game.p1 + add) % 10)+1, game.p2Sum, false));
            newScore[0] += 6 * tmp[0];
            newScore[1] += 6 * tmp[1];
            add = 8;
            tmp = play(new Game((game.p1 + add) % 10, game.p2, game.p1Sum + ((game.p1 + add) % 10)+1, game.p2Sum, false));
            newScore[0] += 3 * tmp[0];
            newScore[1] += 3 * tmp[1];
            add = 9;
            tmp = play(new Game((game.p1 + add) % 10, game.p2, game.p1Sum + ((game.p1 + add) % 10)+1, game.p2Sum, false));
            newScore[0] += tmp[0];
            newScore[1] += tmp[1];
        } else {
            add = 3;
            tmp = play(new Game(game.p1, (game.p2 + add) % 10, game.p1Sum, game.p2Sum + ((game.p2 + add) % 10)+1, true));
            newScore[0] += tmp[0];
            newScore[1] += tmp[1];
            add = 4;
            tmp = play(new Game(game.p1, (game.p2 + add) % 10, game.p1Sum, game.p2Sum + ((game.p2 + add) % 10)+1, true));
            newScore[0] += 3 * tmp[0];
            newScore[1] += 3 * tmp[1];
            add = 5;
            tmp = play(new Game(game.p1, (game.p2 + add) % 10, game.p1Sum, game.p2Sum + ((game.p2 + add) % 10)+1, true));
            newScore[0] += 6 * tmp[0];
            newScore[1] += 6 * tmp[1];
            add = 6;
            tmp = play(new Game(game.p1, (game.p2 + add) % 10, game.p1Sum, game.p2Sum + ((game.p2 + add) % 10)+1, true));
            newScore[0] += 7 * tmp[0];
            newScore[1] += 7 * tmp[1];
            add = 7;
            tmp = play(new Game(game.p1, (game.p2 + add) % 10, game.p1Sum, game.p2Sum + ((game.p2 + add) % 10)+1, true));
            newScore[0] += 6 * tmp[0];
            newScore[1] += 6 * tmp[1];
            add = 8;
            tmp = play(new Game(game.p1, (game.p2 + add) % 10, game.p1Sum, game.p2Sum + ((game.p2 + add) % 10)+1, true));
            newScore[0] += 3 * tmp[0];
            newScore[1] += 3 * tmp[1];
            add = 9;
            tmp = play(new Game(game.p1, (game.p2 + add) % 10, game.p1Sum, game.p2Sum + ((game.p2 + add) % 10)+1, true));
            newScore[0] += tmp[0];
            newScore[1] += tmp[1];
        }
        return newScore;
    }

    public static class Game {
        int p1;
        int p2;
        int p1Sum;
        int p2Sum;
        boolean p1Turn;

        public Game(int p1, int p2, int p1Sum, int p2Sum, boolean p1Turn) {
            this.p1 = p1;
            this.p2 = p2;
            this.p1Sum = p1Sum;
            this.p2Sum = p2Sum;
            this.p1Turn = p1Turn;
        }
    }
}
