import java.util.Arrays;

public class Insertion {
    public static int[] indirectSort(Comparable[] a) {
        int N = a.length;
        Integer[] index = new Integer[N];

        for (int i = 0; i < N; i++) {
            index[i] = i;
        }

        //Insertion Sort
        for (int i = 1; i < N; i++) {
            Comparable key = a[index[i]];
            int j = i - 1;
            while (j >= 0 && a[index[j]].compareTo(key) > 0) {
                index[j + 1] = index[j];
                j--;
            }
            index[j + 1] = i;
        }

        return Arrays.stream(index).mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Comparable[] a = {5, 2, 8, 3, 1};
        int[] sortedIndices = indirectSort(a);

        System.out.println("Sorted indices: " + Arrays.toString(sortedIndices));
        System.out.println("Sorted values: ");
        for (int index : sortedIndices) {
            System.out.print(a[index] + " ");
        }
    }
}
