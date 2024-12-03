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
            parent[p] = parent[parent[p]]; // Path compression (optional)
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

class ConnectionResult {
    private int connections;
    private List<int[]> connectionList;

    public ConnectionResult(int connections, List<int[]> connectionList) {
        this.connections = connections;
        this.connectionList = connectionList;
    }

    public int getConnections() {
        return connections;
    }

    public List<int[]> getConnectionList() {
        return connectionList;
    }
}

public class UFPerformanceTest {
    public static ConnectionResult generateConnections(int N) {
        UnionFind uf = new QuickUnionUF(N);
        Random rand = new Random();
        int connections = 0;
        List<int[]> connectionList = new ArrayList<>();

        while (uf.count() > 1) {
            int p = rand.nextInt(N);
            int q = rand.nextInt(N);
            connections++;
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                connectionList.add(new int[]{p, q});
            }
        }

        return new ConnectionResult(connections, connectionList);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java UFPerformanceTest <T> <N>");
            System.out.println("Where:");
            System.out.println("  T - Number of trials");
            System.out.println("  N - Number of sites");
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
        System.out.println("Trial\tTimeRatio(QuickFind/QuickUnion)");

        for (int trial = 1; trial <= T; trial++) {
            // Step 1: Generate random connections using ErdosRenyi
            ConnectionResult result = generateConnections(N);
            List<int[]> connections = result.getConnectionList();

            // Step 2: Apply Quick-Find
            UnionFind quickFind = new QuickFindUF(N);
            long startTimeFind = System.nanoTime();
            for (int[] pair : connections) {
                quickFind.union(pair[0], pair[1]);
            }
            long endTimeFind = System.nanoTime();
            double timeFind = (endTimeFind - startTimeFind) / 1e6; // Convert to milliseconds

            // Step 3: Apply Quick-Union
            UnionFind quickUnion = new QuickUnionUF(N);
            long startTimeUnion = System.nanoTime();
            for (int[] pair : connections) {
                quickUnion.union(pair[0], pair[1]);
            }
            long endTimeUnion = System.nanoTime();
            double timeUnion = (endTimeUnion - startTimeUnion) / 1e6; // Convert to milliseconds

            // Step 4: Calculate ratio
            double ratio = timeFind / timeUnion;

            // Step 5: Output the result
            System.out.printf("%d\t%.2f%n", trial, ratio);
        }
    }
}
