package HistogramExample;

import java.util.Scanner;

public class Histogram {

    public static void main(String[] args) {
        // Check for correct number of command line arguments
        if (args.length != 3) {
            System.out.println("Usage: java HistogramExample.Histogram <N> <l> <r>");
            return;
        }

        // Parse command line arguments
        int N = Integer.parseInt(args[0]);
        double l = Double.parseDouble(args[1]);
        double r = Double.parseDouble(args[2]);

        // Check for valid input
        if (N <= 0 || l >= r) {
            System.out.println("Error: N must be positive and l must be less than r.");
            return;
        }

        // Create an array to hold counts for each interval
        int[] counts = new int[N];
        double intervalSize = (r - l) / N;

        // Read double values from standard input
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextDouble()) {
            double value = scanner.nextDouble();
            // Check which interval the value falls into
            if (value >= l && value < r) {
                int index = (int) ((value - l) / intervalSize);
                if (index >= N) {
                    index = N - 1; // Handle edge case for r
                }
                counts[index]++;
            }
        }
        scanner.close();

        // Set up the drawing canvas
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(l, r);
        StdDraw.setYscale(0, getMaxCount(counts) + 1);
        StdDraw.clear();

        // Draw the histogram
        for (int i = 0; i < N; i++) {
            double x = l + i * intervalSize;
            double width = intervalSize;
            double height = counts[i];
            StdDraw.filledRectangle(x + width / 2, height / 2, width / 2, height / 2);
        }

        // Add labels
        StdDraw.text((l + r) / 2, getMaxCount(counts) + 0.5, "HistogramExample.Histogram");
        StdDraw.text((l + r) / 2, -0.5, "Value Ranges");
        StdDraw.text(-0.5, getMaxCount(counts) / 2, "Count", 90);
    }

    // Helper function to get the maximum count
    private static int getMaxCount(int[] counts) {
        int max = 0;
        for (int count : counts) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }
}
