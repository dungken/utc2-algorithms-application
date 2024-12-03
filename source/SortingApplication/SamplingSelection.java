import java.util.Arrays;
import java.util.Random;

public class SamplingSelection {
    public static int selectWithSampling(int[] arr, int k, int sampleSize) {
        int[] sample = new int[sampleSize];
        Random rand = new Random();

        for (int i = 0; i < sampleSize; i++) {
            sample[i] = arr[rand.nextInt(arr.length)];
        }

        Arrays.sort(sample);
        int median = sample[sampleSize / 2];

        int[] less = Arrays.stream(arr).filter(x -> x < median).toArray();
        int[] greater = Arrays.stream(arr).filter(x -> x > median).toArray();

        // Tìm phần tử thứ k
        if (k < less.length) {
            return selectWithSampling(less, k, sampleSize);
        } else if (k < arr.length - greater.length) {
            return median;
        } else {
            return selectWithSampling(greater, k - (arr.length - greater.length), sampleSize);
        }
    }

    // Test
    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 2;  // Tìm phần tử lớn thứ 2
        int sampleSize = 3;

        int result = selectWithSampling(arr, k, sampleSize);
        System.out.println("Phần tử tại chỉ số " + k + " là: " + result);
    }
}
