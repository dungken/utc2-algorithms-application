package BalancedSearchTree;
import java.util.Random;

public class RedNodeCounter<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        boolean color;
        int N;

        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private Node root;

    // Count the number of red nodes
    private int countRedNodes(Node h) {
        if (h == null) return 0;
        int count = 0;
        if (isRed(h)) count++;
        count += countRedNodes(h.left);
        count += countRedNodes(h.right);
        return count;
    }

    // Count total nodes
    private int countTotalNodes(Node h) {
        if (h == null) return 0;
        return 1 + countTotalNodes(h.left) + countTotalNodes(h.right);
    }

    // Check if the node is red
    private boolean isRed(Node h) {
        if (h == null) return false;
        return h.color == RED;
    }

    // Insert a key-value pair (same as the earlier put method)
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public void runTrials(int N, int trials) {
        Random random = new Random();
        for (int t = 0; t < trials; t++) {
            RedNodeCounter<Integer, String> tree = new RedNodeCounter<>();
            for (int i = 0; i < N; i++) {
                tree.put(random.nextInt(), "Value");
            }
            int redNodes = tree.countRedNodes(tree.root);
            int totalNodes = tree.countTotalNodes(tree.root);
            double percentage = (double) redNodes / totalNodes * 100;
            System.out.println("Trial " + (t + 1) + ": " + percentage + "% red nodes");
        }
    }

    public static void main(String[] args) {
        RedNodeCounter<Integer, String> tree = new RedNodeCounter<>();
        int N = 10000;  // You can change this to 10^5 or 10^6 as needed.
        tree.runTrials(N, 100);
    }
}
