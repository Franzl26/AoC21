package Day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day8 {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day8"))) {
            int anz = 0;

            String line = in.readLine();
            while (line != null) {
                String[] strings = line.split(" ");
                if (strings[11].length() == 2 || strings[11].length() == 3 || strings[11].length() == 4 || strings[11].length() == 7) anz++;
                if (strings[12].length() == 2 || strings[12].length() == 3 || strings[12].length() == 4 || strings[12].length() == 7) anz++;
                if (strings[13].length() == 2 || strings[13].length() == 3 || strings[13].length() == 4 || strings[13].length() == 7) anz++;
                if (strings[14].length() == 2 || strings[14].length() == 3 || strings[14].length() == 4 || strings[14].length() == 7) anz++;
                line = in.readLine();
            }
            System.out.println("anz: " + anz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // -----------------------------------------------------------------

        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day8"))) {
            long sum = 0;

            String line = in.readLine();
            while (line != null) {
                String[] strings = line.split(" ");
                int[] sForNum = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
                int[] numInS = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
                // get 1,4,7,8
                for (int i = 0; i < 10; i++) {
                    if (strings[i].length() == 2) {
                        sForNum[1] = i;
                        numInS[i] = 1;
                    } else if (strings[i].length() == 3) {
                        sForNum[7] = i;
                        numInS[i] = 7;
                    } else if (strings[i].length() == 4) {
                        sForNum[4] = i;
                        numInS[i] = 4;
                    } else if (strings[i].length() == 7) {
                        sForNum[8] = i;
                        numInS[i] = 8;
                    }
                }
                // get 3, 6
                for (int i = 0; i < 10; i++) {
                    String c1 = strings[sForNum[1]].substring(0, 1);
                    String c2 = strings[sForNum[1]].substring(1, 2);
                    if (strings[i].length() == 5 && strings[i].contains(c1) && strings[i].contains(c2)) {
                        sForNum[3] = i;
                        numInS[i] = 3;
                    }
                    if (strings[i].length() == 6 && !(strings[i].contains(c1) && strings[i].contains(c2))) {
                        sForNum[6] = i;
                        numInS[i] = 6;
                    }
                }
                // get top-left segment
                String topLeft = "";
                String three = strings[sForNum[3]];
                String four = strings[sForNum[4]];
                for (int i = 0; i < 4; i++) {
                    String c = four.substring(i, i + 1);
                    if (!three.contains(c)) {
                        topLeft = c;
                        break;
                    }
                }
                // get 2, 5
                for (int i = 0; i <10;i++) {
                    if (strings[i].length() == 5 && numInS[i] == -1) {
                        if (strings[i].contains(topLeft)) {
                            sForNum[5] = i;
                            numInS[i] = 5;
                        } else {
                            sForNum[2] = i;
                            numInS[i] = 2;
                        }
                    }
                }
                // get 0, 9
                for (int i = 0; i <10;i++) {
                    String s = strings[i];
                    if (s.length() == 6 && numInS[i] == -1) {
                        if (s.contains(four.substring(0, 1)) && s.contains(four.substring(1, 2)) && s.contains(four.substring(2, 3)) && s.contains(four.substring(3, 4))) {
                            sForNum[9] = i;
                            numInS[i] = 9;
                        } else {
                            sForNum[0] = i;
                            numInS[i] = 0;
                        }
                    }
                }
                int number = 0;
                for (int i = 11; i < 15; i++) {
                    for (int s = 0; s < 10; s++) {
                        if (stringMatch(strings[i], strings[s])) number += Math.pow(10, 14 - i) * numInS[s];
                    }
                }
                sum += number;

                line = in.readLine();
            }
            System.out.println("sum: " + sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean stringMatch(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        for (int i = 0; i < s1.length(); i++) {
            if (!s2.contains(s1.substring(i, i+1))) return false;
        }
        return true;
    }
}
