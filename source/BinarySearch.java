import java.util.Arrays;

public class BinarySearch {
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if(key < a[mid]) hi = mid - 1;
            else if(key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] whitelist = {1, 0, 0, -1, 4, 2, 9, 10, 12, 8, 12};
        Arrays.sort(whitelist);
        for(int e : whitelist) {
            System.out.print(e + " ");
        }
        System.out.println();
        int key = -2;
        int returnIndex = rank(key, whitelist);
        if(returnIndex != -1) {
            System.out.println("Index at key " + key + " is: " + returnIndex);
        } else {
            System.out.println("Not exist key " + key);
        }
    }
}
