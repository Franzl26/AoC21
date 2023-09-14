package Day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class Day10 {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("./Input/Day10"))) {
            int error = 0;
            ArrayList<Long> list = new ArrayList<>();

            String line = in.readLine();
            while (line != null) {
                Stack<Character> stack = new Stack<>();
                boolean err = false;
                for (int i=0;i<line.length(); i++) {
                    char c = line.charAt(i);
                    if (c == '(' || c == '[' || c == '{' || c == '<') stack.push(c);
                    else {
                        switch (c) {
                            case ')' -> {
                                if (stack.pop() == '(') continue;
                                else {
                                    error += 3;
                                    err = true;
                                }
                            }
                            case ']' -> {
                                if (stack.pop() == '[') continue;
                                else {
                                    error += 57;
                                    err = true;
                                }
                            }
                            case '}' -> {
                                if (stack.pop() == '{') continue;
                                else {
                                    error += 1197;
                                    err = true;
                                }
                            }
                            case '>' -> {
                                if (stack.pop() == '<') continue;
                                else {
                                    error += 25137;
                                    err = true;
                                }
                            }
                        }
                        if (err) break;
                    }
                }
                long score = 0;
                if (!err) {
                    while (!stack.empty()) {
                        score *= 5;
                        score += switch (stack.pop()) {
                            case '(' -> 1;
                            case '[' -> 2;
                            case '{' -> 3;
                            case '<' -> 4;
                            default -> throw new RuntimeException();
                        };
                    }
                    list.add(score);
                }

                line = in.readLine();
            }

            System.out.println("error: " + error);
            Long[] arr = new Long[list.size()];
            list.toArray(arr);
            Object[] objects = Arrays.stream(arr).sorted().toArray();
            System.out.println("score: " + objects[(objects.length - 1) / 2]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
