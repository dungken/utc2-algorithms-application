import java.util.Arrays;

public class Vector implements Comparable<Vector> {
    private final int[] components;
    public Vector(int... components) {
        this.components = components;
    }

    @Override
    public int compareTo(Vector other) {
        for (int i = 0; i < this.components.length; i++) {
            if (this.components[i] < other.components[i]) return -1;
            if (this.components[i] > other.components[i]) return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(components);
    }

    //Test
    public static void main(String[] args) {
        Vector[] vectors = {
                new Vector(3, 2, 1),
                new Vector(1, 2, 3),
                new Vector(2, 1, 3),
                new Vector(3, 1, 2)
        };

        Arrays.sort(vectors);

        for (Vector v : vectors) {
            System.out.println(v);
        }
    }
}
