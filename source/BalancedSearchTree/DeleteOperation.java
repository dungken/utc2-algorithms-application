package BalancedSearchTree;

public class DeleteOperation<Key extends Comparable<Key>, Value> {

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

    // Check if a node is red
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // Rotate left
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

    // Rotate right
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

    // Flip colors
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // Return the size of the tree
    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    // Public method to get the size of the tree
    public int size() {
        return size(root);
    }

    // Public method to delete a key from the tree
    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    // Recursive method to delete a node with a specific key
    private Node delete(Node h, Key key) {
        // If the key is smaller, move left
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            // Move right if needed
            if (isRed(h.left)) h = rotateRight(h);

            // If the key is found and it's the rightmost node, delete it
            if (key.compareTo(h.key) == 0 && h.right == null) return null;

            // Ensure the right child is not a 2-node
            if (!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h);
            }

            // If the key is found, replace it with its successor and delete the minimum in the right subtree
            if (key.compareTo(h.key) == 0) {
                Node min = min(h.right);
                h.key = min.key;
                h.val = min.val;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    // Move red link to the left to ensure the left child is not a 2-node
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    // Move red link to the right to ensure the right child is not a 2-node
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
        }
        return h;
    }

    // Balance the tree after deletion
    private Node balance(Node h) {
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    // Find the minimum node
    private Node min(Node h) {
        if (h.left == null) return h;
        else return min(h.left);
    }

    // Delete the minimum element from the tree
    private Node deleteMin(Node h) {
        if (h.left == null) return null;

        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }

        h.left = deleteMin(h.left);
        return balance(h);
    }

    // Public method to insert a new key-value pair
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    // Recursive method to insert a key-value pair
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

    // Check if the tree is empty
    public boolean isEmpty() {
        return root == null;
    }
}
