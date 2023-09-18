package Day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Day18 {
    private static final String FILE = "./Input/Test";

    public static void main(String[] args) {
        Node n = parseLine("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]");
//        Node n = parseLine("[[[[0,7],4],[7,[[8,4],9]]],[1,1]]");
        explode(n);

//        LinkedList<Node> lines = new LinkedList<>();
//
//        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
//            String line = in.readLine();
//            while (line != null) {
//                lines.add(parseLine(line));
//                line = in.readLine();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        Node sum = linesToSum(lines);
//        sum.print();
    }

    public static Node findFirst5th(Node node, int level) {
        if (level == 4) return node;
        Node n = null;
        if (node.left.value == null) n = findFirst5th(node.left, level + 1);
        if (n == null && node.right.value == null) n = findFirst5th(node.right, level + 1);
        return n;
    }

    public static boolean explode(Node root) {
        Node n = findFirst5th(root, 0);
        if (n == null) return false;

        int toLeft = n.left.value;
        int toRight = n.right.value;
        Node neu = new Node(n.parent, 0);
        if (Objects.equals(n.parent.left, n)) n.parent.setLeft(neu);
        else n.parent.setRight(neu);

        done = false;
        System.out.println(wouldPrintAt(root, neu));
        root.print();
        // left
//        for (int i = printAt; i > 0; i++) {
//
//        }


        return true;
    }

    static boolean done = false;

    public static int wouldPrintAt(Node root, Node node) {
        if (Objects.equals(root, node)) {
            done = true;
            return 0;
        }
        if (root.value != null) {
            return 1;
        }
        int i = 1;
        i += wouldPrintAt(root.left, node);
        if (done) return i;
        i++;
        i += wouldPrintAt(root.right, node);
        if (done) return i;
        return i + 1;
    }

    public static boolean split(Node root) {
        return false;
    }

    public static void reduce(Node root) {
        do {
        } while (explode(root) || split(root));
    }

    public static Node linesToSum(LinkedList<Node> lines) {
        Node[] arr = lines.toArray(new Node[0]);
        Node root = new Node(null);
        root.setLeft(arr[0]);
        root.setRight(arr[1]);
        reduce(root);
        for (int i = 2; i < arr.length; i++) {
            Node tmp = new Node(null);
            tmp.setLeft(root);
            tmp.setRight(arr[i]);
            root = tmp;
            reduce(root);
        }
        return root;
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
                int num = Integer.parseInt("" + c);
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

        public void setLeft(Node node) {
            left = node;
            node.parent = this;
        }

        public void setRight(Node node) {
            right = node;
            node.parent = this;
        }

        public void print() {
            printRek();
            System.out.println();
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
