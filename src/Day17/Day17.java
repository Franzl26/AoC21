package Day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Day17 {
    private static final String FILE = "./Input/Test";

    public static void main(String[] args) {
//        int xFrom = 20, xTo = 30, yFrom = -5, yTo = -10; // Test
        int xFrom = 211, xTo = 232, yFrom = -69, yTo = -124; // real
        HashMap<Character, Integer> max = new HashMap<>();
        max.put('x', -1);
        max.put('y', -1);
        max.put('h', -1);

        int count = 0;

        for (int dx = 0; dx < 1500; dx++) {
            for (int dy = -1500; dy < 1500; dy++) {
                int dxTmp = dx;
                int dyTmp = dy;
                int x = 0, y = 0;
                int h = -1;
                while (true) {
                    x += dxTmp;
                    y += dyTmp;
                    if (dxTmp > 0) dxTmp--;
                    if (dxTmp < 0) dxTmp++;
                    dyTmp--;
                    if (dyTmp == 0) h = y;
                    if (x > xTo || y < yTo) break;
                    if (xFrom <= x && x <= xTo && yTo <= y && y <= yFrom) {
                        count++;
                        if (h > max.get('h')) {
                            max.put('x', dx);
                            max.put('y', dy);
                            max.put('h', h);
                        }
                        break;
                    }
                }
            }
        }

        System.out.printf("Teil 1:  x: %d,  y: %d,  h: %d\n", max.get('x'), max.get('y'), max.get('h'));
        System.out.println("Teil 2: " + count);
    }
}
