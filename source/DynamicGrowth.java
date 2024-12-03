public class DynamicGrowth {
    private int[] parent; // parent[i] points to the parent of i
    private int[] size;   // size[i] represents the size of the tree rooted at i
    private final int count;    // number of components
    private int nextSite; // next available site (identifier)

    public DynamicGrowth() {
        parent = new int[2]; // starting with a small array
        size = new int[2];
        count = 2;
        for (int i = 0; i < 2; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        nextSite = 2; // the next available site index
    }

    private void resize(int capacity) {
        int[] tempParent = new int[capacity];
        int[] tempSize = new int[capacity];
        for (int i = 0; i < parent.length; i++) {
            tempParent[i] = parent[i];
            tempSize[i] = size[i];
        }
        parent = tempParent;
        size = tempSize;
    }

    public int newSite() {
        if (nextSite == parent.length) {
            resize(parent.length * 2); // Double the array size if needed
        }
        parent[nextSite] = nextSite; // New site points to itself
        size[nextSite] = 1;          // Initially, the size of the tree is 1
        return nextSite++;
    }

    // Find the root of the site
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // path compression
            p = parent[p];
        }
        return p;
    }

    // Union the components containing p and q
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // Weighted quick union: attach smaller tree to larger tree
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    // Check if p and q are connected (in the same component)
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // Returns the number of components (for completeness)
    public int count() {
        return count;
    }

    // Nested Connection class
    private static class Connection {
        int p;
        int q;
        public Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }
    }

    public static void main(String[] args) {
        DynamicGrowth uf = new DynamicGrowth();

        // Adding new sites dynamically
        int id1 = uf.newSite();
        int id2 = uf.newSite();
        int id3 = uf.newSite();

        // Perform some unions and checks
        uf.union(id1, id2);
        System.out.println(uf.connected(id1, id2)); // Should print true
        System.out.println(uf.connected(id1, id3)); // Should print false

        // Add more sites and continue operations
        int id4 = uf.newSite();
        uf.union(id3, id4);
        System.out.println(uf.connected(id3, id4)); // Should print true
    }
}
