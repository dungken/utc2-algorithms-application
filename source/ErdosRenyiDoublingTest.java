import java.util.Random;

public class ErdosRenyiDoublingTest {
    private static class UnionFind {
        private int[] parent;
        private int[] size;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]]; // Path compression
                p = parent[p];
            }
            return p;
        }

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
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
    }

    // Count method from the previous implementation
    public static int count(int N) {
        if (N <= 0) throw new IllegalArgumentException("N must be greater than 0");
        UnionFind uf = new UnionFind(N);
        Random random = new Random();
        int connections = 0;
        int numberOfComponents = N;

        while (numberOfComponents > 1) {
            int p = random.nextInt(N);
            int q = random.nextInt(N);
            connections++;
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                numberOfComponents--;
            }
        }

        return connections;
    }

    // Method to perform the doubling test
    public static void doublingTest(int T) {
        int initialN = 1; // Starting size
        for (int i = 0; i < T; i++) {
            int N = initialN * (1 << i); // Double the size for each trial
            long totalConnections = 0;
            long totalTime = 0;

            // Perform multiple trials for each N
            int trials = 10; // Number of trials per size
            for (int j = 0; j < trials; j++) {
                long startTime = System.nanoTime();
                int connections = count(N);
                long endTime = System.nanoTime();

                totalConnections += connections;
                totalTime += (endTime - startTime);
            }

            double averageConnections = (double) totalConnections / trials;
            double averageTime = (double) totalTime / trials; // in nanoseconds
            double ratio = i == 0 ? 0 : averageTime / ((double) totalTime / trials); // Prevent division by zero for the first run

            System.out.printf("N: %d, Avg Connections: %.2f, Avg Time (ns): %.2f, Time Ratio: %.2f%n", N, averageConnections, averageTime, ratio);
        }
    }
}
