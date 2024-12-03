package BalancedSearchTree;

class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int item) {
        key = item;
        left = right = null;
    }
}

class AVLTree {
    private TreeNode root;
    private int rotations; // Count of rotations
    private int splits;    // Count of splits

    public AVLTree() {
        this.root = null;
        this.rotations = 0;
        this.splits = 0;
    }

    // Insert a new node with given key
    public void insert(int key) {
        root = insert(root, key);
    }

    private TreeNode insert(TreeNode node, int key) {
        // Step 1: Perform normal BST insert
        if (node == null) {
            splits++; // Count the split for a new node
            return new TreeNode(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            // Duplicate keys are not allowed in BST
            return node;
        }

        // Step 2: Update the balance factor and balance the tree
        return balance(node, key);
    }

    private TreeNode balance(TreeNode node, int key) {
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            rotations++; // Count the rotation
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            rotations++; // Count the rotation
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            rotations++; // Count the rotation
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            rotations++; // Count the rotation
            return leftRotate(node);
        }

        return node; // Return the (unchanged) node pointer
    }

    // Get the balance factor of a node
    private int getBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Get the height of a node
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Perform right rotation
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        return x;
    }

    // Perform left rotation
    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        return y;
    }

    // Method to print the tree in order
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    // Get the count of rotations and splits
    public int getRotations() {
        return rotations;
    }

    public int getSplits() {
        return splits;
    }
}

// Main class to test the AVL Tree
public class AVLTreeDemo {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Insert nodes into the AVL tree
        int[] keys = {10, 20, 30, 40, 50, 25};
        for (int key : keys) {
            tree.insert(key);
        }

        // Print in order traversal of the AVL tree
        System.out.println("In order traversal of the constructed AVL tree is:");
        tree.inOrder();

        // Print the number of rotations and splits
        System.out.println("\nTotal Rotations: " + tree.getRotations());
        System.out.println("Total Splits: " + tree.getSplits());
    }
}
