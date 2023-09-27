import Day21.Day21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {
    private static final Pattern pattern = Pattern.compile("o([nf])f? x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)");
    public static void main(String[] args) {
        Matcher matcher = pattern.matcher("on x=-1..48,y=-16..38,z=-20..25");
        System.out.println(matcher.matches());
        System.out.println(matcher.groupCount());
        for (int i=0;i<=matcher.groupCount();i++) {
            System.out.println(matcher.group(i));
        }
    }
}
