package Accumulator;

public class AccumulatorTest {
    public static void main(String[] args) {
        Accumulator acc = new Accumulator();

        // Adding test values
        acc.addDataValue(10);
        acc.addDataValue(20);
        acc.addDataValue(30);

        // Expected calculations
        System.out.println("Mean: " + acc.mean()); // Expected: 20.0
        System.out.println("Variance: " + acc.var()); // Expected: 100.0 = ((10-20)^2 + (20-20)^2 + (30-20)^2) / 2)
        System.out.println("Standard Deviation: " + acc.stddev()); // Expected: 10.0

        // More values for additional testing
        acc.addDataValue(40);
        acc.addDataValue(50);

        // New expected calculations after adding more values
        System.out.println("Mean after more values: " + acc.mean()); // Should reflect the new mean
        System.out.println("Variance after more values: " + acc.var()); // Should reflect the updated variance
        System.out.println("Standard Deviation after more values: " + acc.stddev()); // Should reflect the updated stddev
    }
}

/* OUTPUT:
Mean: 20.0
Variance: 100.0
Standard Deviation: 10.0
Mean after more values: 30.0
Variance after more values: 250.0
Standard Deviation after more values: 15.811388300841896
 */
