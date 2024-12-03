import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface UnionFind {
    void union(int p, int q);
    boolean connected(int p, int q);
    int count();
}

class QuickFindUF implements UnionFind {
    private int[] id;
    private int count;

    public QuickFindUF(int N) {
        id = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    @Override
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        if (pid == qid) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
        count--;
    }

    @Override
    public int count() {
        return count;
    }
}

class QuickUnionUF implements UnionFind {
    private int[] parent;
    private int count;

    public QuickUnionUF(int N) {
        parent = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }

    @Override
    public int count() {
        return count;
    }
}

class WeightedQuickUnionUF implements UnionFind {
    private int[] parent;
    private int[] size;
    private int count;

    public WeightedQuickUnionUF(int N) {
        parent = new int[N];
        size = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]); // Path compression
        }
        return parent[p];
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // Union by size
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    @Override
    public int count() {
        return count;
    }
}

class GridUnionFindPerformanceTest {
    public static ConnectionResult generateConnections(int N, UnionFind uf) {
        Random rand = new Random();
        int connections = 0;
        List<int[]> connectionList = new ArrayList<>();

        // Use an N-by-N grid and create random connections
        int totalSites = N * N;
        while (uf.count() > 1) {
            int p = rand.nextInt(totalSites);
            int q = rand.nextInt(totalSites);

            int pRow = p / N;
            int pCol = p % N;
            int qRow = q / N;
            int qCol = q % N;

            if (Math.abs(pRow - qRow) <= 1 && Math.abs(pCol - qCol) <= 1 && !uf.connected(p, q)) {
                uf.union(p, q);
                connectionList.add(new int[]{p, q});
            }
            connections++;
        }

        return new ConnectionResult(connections, connectionList);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java GridUnionFindPerformanceTest <T> <N>");
            System.out.println("Where:");
            System.out.println("  T - Number of trials");
            System.out.println("  N - Grid size (N x N grid)");
            return;
        }

        int T;
        int N;

        try {
            T = Integer.parseInt(args[0]);
            N = Integer.parseInt(args[1]);
            if (T <= 0 || N <= 0) {
                System.out.println("T and N must be positive integers.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter positive integers for T and N.");
            return;
        }

        // Header for output
        System.out.println("Trial\tGridSize\tAvgConnections\tTimeRatio(QuickFind/QuickUnion)\tTimeRatio(WeightedQuickUnion)");

        for (int trial = 1; trial <= T; trial++) {
            // Generate random connections using the different UnionFind algorithms
            UnionFind quickFind = new QuickFindUF(N * N);
            UnionFind quickUnion = new QuickUnionUF(N * N);
            UnionFind weightedQuickUnion = new WeightedQuickUnionUF(N * N);

            // Quick-Find Test
            long startTimeFind = System.nanoTime();
            ConnectionResult resultFind = generateConnections(N, quickFind);
            long endTimeFind = System.nanoTime();
            double timeFind = (endTimeFind - startTimeFind) / 1e6; // Convert to milliseconds

            // Quick-Union Test
            long startTimeUnion = System.nanoTime();
            ConnectionResult resultUnion = generateConnections(N, quickUnion);
            long endTimeUnion = System.nanoTime();
            double timeUnion = (endTimeUnion - startTimeUnion) / 1e6; // Convert to milliseconds

            // Weighted Quick-Union Test
            long startTimeWQU = System.nanoTime();
            ConnectionResult resultWQU = generateConnections(N, weightedQuickUnion);
            long endTimeWQU = System.nanoTime();
            double timeWQU = (endTimeWQU - startTimeWQU) / 1e6; // Convert to milliseconds

            // Step 4: Calculate ratios and output the result
            double ratioQuickFind = timeFind / timeUnion;
            double ratioWQU = timeUnion / timeWQU;

            // Output trial information
            System.out.printf("%d\t%d\t%d\t%.2f\t%.2f%n", trial, N, resultFind.getConnections(), ratioQuickFind, ratioWQU);
        }
    }
}
