package Day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Day15 {
    private static final String FILE = "./Input/Day15";

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader(FILE))) {
            String line = in.readLine();
            int end = line.length();
            int[][] map = new int[end][end];
            for (int y = 0; line != null; y++) {
                for (int x = 0; x < end; x++) {
                    map[x][y] = Integer.parseInt(line.substring(x, x + 1));
                }
                line = in.readLine();
            }

            boolean[][] expanded = new boolean[map.length][map.length];
            PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.minRisk));
            Node root = new Node(0, 0, 0, end, end);
            queue.add(root);
            while (true) {
                if (queue.isEmpty()) throw new RuntimeException();
                Node n = queue.remove();
                ArrayList<Node> list = expand(n, map, expanded);
                if (list == null) {
                    System.out.println("teil 1: " + n.pathRisk);
                    break;
                }
                queue.addAll(list);
            }
            // ----------------------- teil 2

            int[][] mapOld = map;
            map = new int[5 * mapOld.length][5 * mapOld.length];
            for (int xMult = 0; xMult < 5; xMult++) {
                for (int x = 0; x < end; x++) {
                    for (int yMult = 0; yMult < 5; yMult++) {
                        for (int y = 0; y < end; y++) {
                            int val = mapOld[x][y] + xMult + yMult;
                            if (val > 9) val -= 9;
                            map[x + end * xMult][y + end * yMult] = val;
                        }
                    }
                }
            }


            expanded = new boolean[map.length][map.length];
            queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.minRisk));
            root = new Node(0, 0, 0, end, end);
            queue.add(root);
            while (true) {
                if (queue.isEmpty()) throw new RuntimeException();
                Node n = queue.remove();
                ArrayList<Node> list = expand(n, map, expanded);
                if (list == null) {
                    System.out.println("Teil2: " + n.pathRisk);
                    break;
                }
                queue.addAll(list);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Node> expand(Node node, int[][] map, boolean[][] expanded) {
        if (node.posX == map.length - 1 && node.posY == map.length - 1) return null;

        expanded[node.posX][node.posY] = true;
        ArrayList<Node> list = new ArrayList<>();
        if (node.posX > 0 && !expanded[node.posX - 1][node.posY]) {
            list.add(new Node(node.posX - 1, node.posY, node.pathRisk + map[node.posX - 1][node.posY], map.length, map.length));
        }
        if (node.posX < map.length - 1 && !expanded[node.posX + 1][node.posY]) {
            list.add(new Node(node.posX + 1, node.posY, node.pathRisk + map[node.posX + 1][node.posY], map.length, map.length));
        }
        if (node.posY > 0 && !expanded[node.posX][node.posY - 1]) {
            list.add(new Node(node.posX, node.posY - 1, node.pathRisk + map[node.posX][node.posY - 1], map.length, map.length));
        }
        if (node.posY < map.length - 1 && !expanded[node.posX][node.posY + 1]) {
            list.add(new Node(node.posX, node.posY + 1, node.pathRisk + map[node.posX][node.posY + 1], map.length, map.length));
        }
        return list;
    }

    public static class Node {
        int posX;
        int posY;
        int pathRisk;
        int minRisk;

        public Node(int posX, int posY, int pathRisk, int toX, int toY) {
            this.posX = posX;
            this.posY = posY;
            this.pathRisk = pathRisk;
            this.minRisk = pathRisk + Math.abs(posX - toX) + Math.abs(posY - toY);
        }
    }
}
