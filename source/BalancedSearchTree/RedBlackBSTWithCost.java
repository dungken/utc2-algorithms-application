package BalancedSearchTree;

import java.util.ArrayList;
import java.util.List;

public class RedBlackBSTWithCost<Key extends Comparable<Key>, Value> {

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
    private int comparisons;
    private int rotations;

    // To store the cost of each put operation
    private List<Integer> costs = new ArrayList<>();

    // Put method tracking the cost of each insertion
    public void put(Key key, Value val) {
        comparisons = 0;  // Reset comparison count for this operation
        rotations = 0;    // Reset rotation count
        root = put(root, key, val);
        root.color = BLACK;
        costs.add(comparisons + rotations);
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            comparisons++; // Count comparison
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(h.key);
        comparisons++; // Count comparison

        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
            rotations++; // Count rotation
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
            rotations++; // Count rotation
        }
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    // Rest of the red-black tree methods go here...

    public void plotCosts() {
        for (int i = 0; i < costs.size(); i++) {
            System.out.println("Operation " + (i + 1) + " cost: " + costs.get(i));
        }
        // Plotting code can be added here using a plotting library
    }



    public static void main(String[] args) {
        RedBlackBSTWithCost<Integer, String> tree = new RedBlackBSTWithCost<>();
        for (int i = 0; i < 1000; i++) {
            tree.put(i, "Value");
        }
        tree.plotCosts(); // Outputs the cost of each operation
    }
}
