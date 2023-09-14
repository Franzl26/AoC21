package Day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day16 {
    private static final String FILE = "./Input/Test";

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            String line = in.readLine();
            while (line != null) {


                line = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
