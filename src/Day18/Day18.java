package Day18;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Day18 {
    private static final String FILE = "./Input/Day18";

    public static void main(String[] args) {
//        Node n = parseLine("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]");
//        Node n = parseLine("[[[[0,7],4],[15,[0,13]]],[1,1]]");
//        split(n).print();

//        Node n = parseLine("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]");
//        reduce(n).print();

//        Node n = parseLine("[[[[4,0],[5,4]],[[7,0],[15,5]]],[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]]");
//        n.print();
//        deepCopy(n).print();
//        explode(n).print();
//        System.out.println("\n\n\n");

        LinkedList<Node> lines = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            String line = in.readLine();
            while (line != null) {
                lines.add(parseLine(line));
                line = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Node sum = linesToSum(lines);
//        sum.print();
        System.out.println("part 1: " + magnitude(sum));


        lines = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            String line = in.readLine();
            while (line != null) {
                lines.add(parseLine(line));
                line = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("part 2: "+part2(lines));

    }

    public static Node findFirst5th(Node node, int level) {
        if (level == 4) return node;
        Node n = null;
        if (node.left.value == null) n = findFirst5th(node.left, level + 1);
        if (n == null && node.right.value == null) n = findFirst5th(node.right, level + 1);
        return n;
    }

    public static Node explode(Node root) {
        Node n = findFirst5th(root, 0);
        if (n == null) return null;

        int toLeft = n.left.value;
        int toRight = n.right.value;
        Node neu = new Node(n.parent, 0);
        if (Objects.equals(n.parent.left, n)) n.parent.setLeft(neu);
        else n.parent.setRight(neu);
        int printAt = wouldPrintAt(root, neu);

        done = false;
        StringBuilder build = new StringBuilder();
        buildString(root, build);
        String s = build.toString();
        // left
        for (int i = printAt - 1; i > 0; i--) {
            char c = s.charAt(i);
            if ('0' <= c && c <= '9') {
                char c2 = s.charAt(i - 1);
                int val;
                if ('0' <= c2 && c2 <= '9') {
                    val = Integer.parseInt("" + c2 + c);
                    val += toLeft;
                    s = s.substring(0, i - 1) + val + s.substring(i + 1);
                } else {
                    val = Integer.parseInt("" + c);
                    val += toLeft;
                    s = s.substring(0, i) + val + s.substring(i + 1);
                }
                if (val > 9) printAt++;
                break;
            }
        }
        // right
        for (int i = printAt + 2; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('0' <= c && c <= '9') {
                char c2 = s.charAt(i + 1);
                int val;
                if ('0' <= c2 && c2 <= '9') {
                    val = Integer.parseInt("" + c + c2);
                    val += toRight;
                    s = s.substring(0, i) + val + s.substring(i + 2);
                } else {
                    val = Integer.parseInt("" + c);
                    val += toRight;
                    s = s.substring(0, i) + val + s.substring(i + 1);
                }
                break;
            }
        }
        return parseLine(s);
    }

    static boolean done = false;

    public static int wouldPrintAt(Node root, Node node) {
        if (Objects.equals(root, node)) {
            done = true;
            return 0;
        }
        if (root.value != null) {
            if (root.value < 10) return 1;
            else return 2;
        }
        int i = 1;
        i += wouldPrintAt(root.left, node);
        if (done) return i;
        i++;
        i += wouldPrintAt(root.right, node);
        if (done) return i;
        return i + 1;
    }

    public static void buildString(Node root, StringBuilder build) {
        if (root.value != null) {
            build.append(root.value);
            return;
        }
        build.append('[');
        buildString(root.left, build);
        build.append(',');
        buildString(root.right, build);
        build.append(']');
    }

    public static Node findFirstSplit(Node node) {
        if (node.value != null) {
            if (node.value > 9) return node;
            return null;
        }
        Node n = null;
        n = findFirstSplit(node.left);
        if (n == null) n = findFirstSplit(node.right);
        return n;
    }

    public static Node split(Node root) {
        Node n = findFirstSplit(root);
        if (n == null) return null;

        int val = n.value;
        int left = Math.floorDiv(val, 2);
        int right = Math.ceilDiv(val, 2);

        Node neu = new Node(n.parent);
        neu.setLeft(new Node(neu, left));
        neu.setRight(new Node(neu, right));

        if (Objects.equals(n.parent.left, n)) {
            n.parent.setLeft(neu);
        } else {
            n.parent.setRight(neu);
        }


        return root;
    }

    public static Node reduce(Node root) {
        Node tmp = root;
        Node ret;
        do {
            ret = explode(tmp);
            if (ret == null) ret = split(tmp);
            if (ret != null) tmp = ret;
        } while (ret != null);
        return tmp;
    }

    public static long magnitude(Node node) {
        if (node.value != null) return node.value;
        long n = 0;
        n += magnitude(node.left) * 3;
        n += magnitude(node.right) * 2;
        return n;
    }

    public static Node linesToSum(LinkedList<Node> lines) {
        Node[] arr = lines.toArray(new Node[0]);
        Node root = new Node(null);
        root.setLeft(arr[0]);
        root.setRight(arr[1]);
        root.print();
        root = reduce(root);
        for (int i = 2; i < arr.length; i++) {
            root.print();
            Node tmp = new Node(null);
            tmp.setLeft(root);
            tmp.setRight(arr[i]);
            root = tmp;
            root = reduce(root);
        }
        return root;
    }

    public static long part2(LinkedList<Node> lines) {
        Node[] arr = lines.toArray(new Node[0]);
        long max = 0;
//        System.out.println(arr.length);
        for (int i = 0; i < arr.length; i++) {
//            System.out.println(i);
            for (int j = i + 1; j < arr.length; j++) {
//                System.out.println(j);
                long l = magnitude(reduce(new Node(null).setLeft(deepCopy(arr[i])).setRight(deepCopy(arr[j])).print()).print());
                long l2 = magnitude(reduce(new Node(null).setLeft(deepCopy(arr[j])).setRight(deepCopy(arr[i])).print()).print());
                if (l > max) max = l;
                if (l2 > max) max = l2;
            }
        }
        return max;
    }

    public static Node deepCopy(Node old) {
        if (old.value != null) return new Node(null, old.value);
        Node n = new Node(null);
        n.setLeft(deepCopy(old.left));
        n.setRight(deepCopy(old.right));
        return n;
    }

    public static Node parseLine(String line) {
        Node root = new Node(null);
        Node cur = root;
        boolean right = false;
        for (int i = 1; i < line.length() - 1; i++) {
            char c = line.charAt(i);
            if (c == '[') {
                Node neu = new Node(cur);
                if (right) cur.right = neu;
                else cur.left = neu;
                cur = neu;
                right = false;
            } else if (c == ',') {
                right = true;
            } else if (c == ']') {
                cur = cur.parent;
            } else if ('0' <= c && c <= '9') {
                char c2 = line.charAt(i + 1);
                int num;
                if ('0' <= c2 && c2 <= '9') {
                    i++;
                    num = Integer.parseInt("" + c + c2);
                } else {
                    num = Integer.parseInt("" + c);
                }
                if (right) cur.right = new Node(cur, num);
                else cur.left = new Node(cur, num);
            } else {
                throw new RuntimeException();
            }
        }
        return root;
    }

    public static class Node {
        Integer value = null;
        Node parent;
        Node left = null;
        Node right = null;

        public Node(Node parent) {
            this.parent = parent;
        }

        public Node(Node parent, Integer value) {
            this.value = value;
            this.parent = parent;
        }

        public Node setLeft(Node node) {
            left = node;
            node.parent = this;
            return this;
        }

        public Node setRight(Node node) {
            right = node;
            node.parent = this;
            return this;
        }

        public Node print() {
            printRek();
            System.out.println();
            return this;
        }

        private void printRek() {
            if (value != null) {
                System.out.print(value);
                return;
            }
            System.out.print('[');
            left.printRek();
            System.out.print(',');
            right.printRek();
            System.out.print(']');
        }
    }
}
