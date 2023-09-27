package Day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day22 {
    private static final String FILE = "./Input/Day22";
    private static final Pattern pattern = Pattern.compile("o([nf])f? x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)");

    public static void main(String[] args) {
        boolean[][][] cube = new boolean[101][101][101];

        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            String line = in.readLine();
            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                if (!matcher.matches()) throw new RuntimeException();
                int xFrom = Integer.parseInt(matcher.group(2));
                if (xFrom > 50 || xFrom < -50) break;
                int xTo = Integer.parseInt(matcher.group(3));
                int yFrom = Integer.parseInt(matcher.group(4));
                int yTo = Integer.parseInt(matcher.group(5));
                int zFrom = Integer.parseInt(matcher.group(6));
                int zTo = Integer.parseInt(matcher.group(7));
                boolean on = matcher.group(1).equals("n");

                boolean xInc = (xFrom < xTo);
                boolean yInc = (yFrom < yTo);

                int zCur = zFrom;
                while (true) {
                    boolean zInc = (zFrom < zTo);
                    int yCur = yFrom;
                    while (true) {
                        int xCur = xFrom;
                        while (true) {
//                            System.out.printf("x=%d,y=%d,z=%d\n", xCur,yCur,zCur);
                            cube[zCur+50][yCur+50][xCur+50] = on;
                            if (xCur == xTo) break;
                            if (xInc) xCur++;
                            else xCur--;
                        }
                        if (yCur == yTo) break;
                        if (yInc) yCur++;
                        else yCur--;
                    }

                    if (zCur == zTo) break;
                    if (zInc) zCur++;
                    else zCur--;
                }

                line = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long count = 0;
        for (boolean[][] yx : cube) {
            for (boolean[] x : yx) {
                for (boolean f : x) {
                    if (f) count++;
                }
            }
        }
        System.out.println(count);
    }
}
