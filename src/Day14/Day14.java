package Day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day14 {
    private static final String FILE = "./Input/Day14";
    private static int LINES = 0;

    public static void main(String[] args) {
//        old();
        better();
    }

    public static void better() {
        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            String line = in.readLine();
            while (line != null) {
                LINES++;
                line = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LINES -= 2;

        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            Map<String, Long> count = new HashMap<>();
            Map<String, String> replace = new HashMap<>();
            String poly;

            poly = in.readLine();
            in.readLine();
            for (int i = 0; i < LINES; i++) {
                String line = in.readLine();
                String[] l = line.split(" -> ");
                count.put(l[0], 0L);
                replace.put(l[0], l[1]);
            }
            for (int i = 0; i < poly.length() - 1; i++) {
                String sub = poly.substring(i, i + 2);
                Long value = count.get(sub);
                value++;
                count.put(sub, value);
            }
                System.out.println(count);
            for (int i = 0; i < 40; i++) {
                count = evolvePoly(count, replace);
                if (i == 9) System.out.println("10: " + calcPoly(count));
            }

            System.out.println("40: " + calcPoly(count));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Map<String, Long> evolvePoly(Map<String, Long> count, Map<String, String> replace) {
        Map<String, Long> neu = new HashMap<>();
        for (String s : count.keySet()) {
            neu.put(s, 0L);
        }
        for (String s : count.keySet()) {
            Long value = count.get(s);
            String left = s.charAt(0) + replace.get(s);
            String right = replace.get(s) + s.charAt(1);
            Long vLeft = neu.get(left) + value;
            Long vRight = neu.get(right) + value;
            neu.put(left, vLeft);
            neu.put(right, vRight);
        }


        return neu;
    }

    public static Long calcPoly(Map<String, Long> count) {
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        for (char c = 'A'; c <= 'Z'; c++) {
            long anz = 0;

            for (String s : count.keySet()) {
                if (s.charAt(0) == c) anz += count.get(s);
                //if (s.charAt(1) == c) anz += count.get(s);
            }

            if (anz > max) max = anz;
            if (anz < min && anz != 0) min = anz;
        }
        return max - min;
    }

    public static void old() {
        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            String line = in.readLine();
            while (line != null) {
                LINES++;
                line = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LINES -= 2;

        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            String[][] replace = new String[LINES][2];
            String poly = in.readLine();
            in.readLine();

            for (int i = 0; i < LINES; i++) {
                String line = in.readLine();
                String[] l = line.split(" -> ");
                replace[i][0] = l[0];
                replace[i][1] = l[1];
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                poly = newPoly(replace, poly);
//                System.out.println(poly);
            }
            int polyLength = poly.length();
            long max = Long.MIN_VALUE;
            long min = Long.MAX_VALUE;
            for (char c = 'A'; c <= 'Z'; c++) {
                long anz = 0;
                for (int i = 0; i < polyLength; i++) {
                    if (poly.charAt(i) == c) anz++;
                }
                if (anz > max) max = anz;
                if (anz < min && anz != 0) min = anz;
            }
            System.out.println("max - min: " + (max - min));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String newPoly(String[][] replace, String polyIn) {
        int anz = polyIn.length() - 1;
        StringBuilder polyNew = new StringBuilder();
        for (int i = 0; i < anz; i++) {
            polyNew.append(polyIn.charAt(i));
            String sub = polyIn.substring(i, i + 2);
            for (int j = 0; j < LINES; j++) {
                if (replace[j][0].equals(sub)) polyNew.append(replace[j][1]);
            }
        }
        polyNew.append(polyIn.charAt(polyIn.length() - 1));
        return polyNew.toString();
    }
}
