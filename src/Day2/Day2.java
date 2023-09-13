package Day2;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {
    public static void main(String[] args) {
        int x = 0, x2 = 0;
        int depth = 0, depth2 = 0;
        int aim = 0;
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day2"))) {
            String line = in.readLine();
            while (line != null) {
                String[] l = line.split(" ");
                // 1
                if ("forward".equals(l[0])) x += Integer.parseInt(l[1]);
                else if ("down".equals(l[0])) depth += Integer.parseInt(l[1]);
                else if ("up".equals(l[0])) depth -= Integer.parseInt(l[1]);
                else {
                    System.out.println(l[0]);
                    throw new RuntimeException();
                }
                // 2
                if ("forward".equals(l[0])) {
                    x2 += Integer.parseInt(l[1]);
                    depth2 += aim * Integer.parseInt(l[1]);
                } else if ("down".equals(l[0])) {
                    aim += Integer.parseInt(l[1]);
                } else if ("up".equals(l[0])) {
                    aim -= Integer.parseInt(l[1]);
                } else {
                    System.out.println(l[0]);
                    throw new RuntimeException();
                }

                line = in.readLine();
            }
            System.out.printf("x: %d, depth: %d, mult: %d\n", x, depth, x * depth);
            System.out.printf("x: %d, depth: %d, mult: %d\n", x2, depth2, x2 * depth2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
