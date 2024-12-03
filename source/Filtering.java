import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Filtering {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N
        System.out.print("Enter the number of values (N): ");
        int N = scanner.nextInt();
        double[] numbers = new double[N];

        // Read N numbers
        System.out.println("Enter " + N + " real numbers between 0 and 1:");
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextDouble();
        }

        // Call the required functions
        printMaxMin(numbers);
        System.out.println("Median: " + findMedian(numbers));
        System.out.println("Enter k for k-th smallest value (k < 100): ");
        int k = scanner.nextInt();
        System.out.println(k + "-th smallest value: " + findKthSmallest(numbers, k));
        System.out.println("Sum of squares: " + sumOfSquares(numbers));
        System.out.println("Average: " + average(numbers));
        System.out.println("Percentage of numbers greater than average: " + percentageAboveAverage(numbers) + "%");
        printSortedNumbers(numbers);
        printRandomOrder(numbers);

        scanner.close();
    }

    // Print the maximum and minimum numbers
    static void printMaxMin(double[] numbers) {
        double max = numbers[0];
        double min = numbers[0];
        for (double num : numbers) {
            if (num > max) max = num;
            if (num < min) min = num;
        }
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
    }

    // Find the median
    static double findMedian(double[] numbers) {
        double[] sorted = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sorted);
        int n = sorted.length;
        if (n % 2 == 0) {
            return (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0;
        } else {
            return sorted[n / 2];
        }
    }

    // Find the k-th smallest value
    static double findKthSmallest(double[] numbers, int k) {
        if (k <= 0 || k > numbers.length) {
            throw new IllegalArgumentException("k must be between 1 and " + numbers.length);
        }
        double[] sorted = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sorted);
        return sorted[k - 1]; // k is 1-based index
    }

    // Calculate the sum of squares
    static double sumOfSquares(double[] numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num * num;
        }
        return sum;
    }

    // Calculate the average
    static double average(double[] numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.length;
    }

    // Calculate percentage of numbers greater than the average
    static double percentageAboveAverage(double[] numbers) {
        double avg = average(numbers);
        int count = 0;
        for (double num : numbers) {
            if (num > avg) count++;
        }
        return (count / (double) numbers.length) * 100;
    }

    // Print numbers in sorted order
    static void printSortedNumbers(double[] numbers) {
        double[] sorted = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sorted);
        System.out.println("Sorted numbers: " + Arrays.toString(sorted));
    }

    // Print numbers in random order
    static void printRandomOrder(double[] numbers) {
        double[] shuffled = Arrays.copyOf(numbers, numbers.length);
        Random rand = new Random();
        for (int i = shuffled.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Swap shuffled[i] with the element at random index
            double temp = shuffled[i];
            shuffled[i] = shuffled[j];
            shuffled[j] = temp;
        }
        System.out.println("Random order: " + Arrays.toString(shuffled));
    }
}
