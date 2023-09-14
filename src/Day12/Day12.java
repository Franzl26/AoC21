package Day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day12 {
    private static int LINES = 0;

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day12"))) {
            String line = in.readLine();
            while (line != null) {
                LINES++;
                line = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day12"))) {
            String[][] paths = new String[LINES][2];

            for (int i = 0; i < LINES; i++) {
                String line = in.readLine();
                String[] l = line.split("-");
                paths[i][0] = l[0];
                paths[i][1] = l[1];
            }

            int ways = findWay(paths, "start", "start");
            System.out.println("ways: " + ways);
            ways = findWayDouble(paths, "start", "start", false);
            System.out.println("ways double: " + ways);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int findWay(String[][] paths, String current, String way) {
        if (current.equals("end")) {
//            System.out.println(way);
            return 1;
        }
        int ways = 0;

        for (int i = 0; i < LINES; i++) {
            if (paths[i][0].equals(current)) {
                if ((paths[i][1].charAt(0) >= 'A' && paths[i][1].charAt(0) <= 'Z') || !way.contains(paths[i][1])) {
                    ways += findWay(paths, paths[i][1], way + "," + paths[i][1]);
                }
            }
            if (paths[i][1].equals(current)) {
                if ((paths[i][0].charAt(0) >= 'A' && paths[i][0].charAt(0) <= 'Z') || !way.contains(paths[i][0])) {
                    ways += findWay(paths, paths[i][0], way + "," + paths[i][0]);
                }
            }
        }

        return ways;
    }

    public static int findWayDouble(String[][] paths, String current, String way, boolean doubleUsed) {
        if (current.equals("end")) {
//            System.out.println(way);
            return 1;
        }
        int ways = 0;

        for (int i = 0; i < LINES; i++) {
            if (paths[i][0].equals(current)) {
                if ((paths[i][1].charAt(0) >= 'A' && paths[i][1].charAt(0) <= 'Z')) {
                    ways += findWayDouble(paths, paths[i][1], way + "," + paths[i][1], doubleUsed);
                } else if (!way.contains(paths[i][1])) {
                    ways += findWayDouble(paths, paths[i][1], way + "," + paths[i][1], doubleUsed);
                } else if (!doubleUsed && !paths[i][1].equals("start")) {
                    ways += findWayDouble(paths, paths[i][1], way + "," + paths[i][1], true);
                }
            }
            if (paths[i][1].equals(current)) {
                if ((paths[i][0].charAt(0) >= 'A' && paths[i][0].charAt(0) <= 'Z')) {
                    ways += findWayDouble(paths, paths[i][0], way + "," + paths[i][0], doubleUsed);
                } else if (!way.contains(paths[i][0])) {
                    ways += findWayDouble(paths, paths[i][0], way + "," + paths[i][0], doubleUsed);
                } else if (!doubleUsed && !paths[i][0].equals("start")) {
                    ways += findWayDouble(paths, paths[i][0], way + "," + paths[i][0], true);
                }
            }

        }

        return ways;
    }
}
