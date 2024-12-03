import java.util.Random;

public class ErdosRenyi {
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

        // Find the root of the component/set  
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
}