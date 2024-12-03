package BalancedSearchTree;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class RedBlackBSTSearchTime<Key extends Comparable<Key>, Value> {

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

    // Standard red-black BST insertion methods
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

    // Utility methods for red-black tree
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
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

    // Calculate the internal path length (sum of the path lengths to all nodes)
    private int calculateInternalPathLength(Node node, int depth) {
        if (node == null) return 0;
        return depth + calculateInternalPathLength(node.left, depth + 1) +
                calculateInternalPathLength(node.right, depth + 1);
    }

    // Get the average path length in the current tree
    public double getAveragePathLength() {
        if (root == null) return 0;
        int internalPathLength = calculateInternalPathLength(root, 0);
        return (double) internalPathLength / size(root); // Average path length
    }

    // Run trials for each N and return the average path lengths for each trial
    public List<Double> runTrials(int N, int trials) {
        Random random = new Random();
        List<Double> averagePathLengths = new ArrayList<>();
        for (int t = 0; t < trials; t++) {
            RedBlackBSTSearchTime<Integer, String> tree = new RedBlackBSTSearchTime<>();
            for (int i = 0; i < N; i++) {
                tree.put(random.nextInt(), "Value");
            }
            averagePathLengths.add(tree.getAveragePathLength());
        }
        return averagePathLengths;
    }

    // Calculate the mean of a list of values
    public double calculateMean(List<Double> values) {
        double sum = 0;
        for (double v : values) {
            sum += v;
        }
        return sum / values.size();
    }

    // Calculate the standard deviation of a list of values
    public double calculateStandardDeviation(List<Double> values, double mean) {
        double sum = 0;
        for (double v : values) {
            sum += Math.pow(v - mean, 2);
        }
        return Math.sqrt(sum / values.size());
    }

    public static void main(String[] args) {
        RedBlackBSTSearchTime<Integer, String> experiment = new RedBlackBSTSearchTime<>();
        int[] treeSizes = {10, 100, 1000, 10000};
        int trials = 1000;

        for (int N : treeSizes) {
            System.out.println("Running trials for N = " + N);
            List<Double> averagePathLengths = experiment.runTrials(N, trials);
            double mean = experiment.calculateMean(averagePathLengths);
            double stdDev = experiment.calculateStandardDeviation(averagePathLengths, mean);
            System.out.println("N = " + N + ", Mean path length: " + mean + ", Std Dev: " + stdDev);
        }
    }
}
