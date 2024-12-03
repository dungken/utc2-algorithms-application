import java.math.BigInteger;

public class RobustRational {
    private final BigInteger numerator;
    private final BigInteger denominator;

    // Constructor
    public RobustRational(int numerator, int denominator) {
        assert denominator != 0 : "Denominator cannot be zero.";

        // Normalize the sign
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        // Reduce to simplest form
        BigInteger gcd = gcd(BigInteger.valueOf(Math.abs(numerator)), BigInteger.valueOf(Math.abs(denominator)));
        this.numerator = BigInteger.valueOf(numerator).divide(gcd);
        this.denominator = BigInteger.valueOf(denominator).divide(gcd);
    }

    // Add two rational numbers
    public RobustRational plus(RobustRational b) {
        BigInteger newNumerator = this.numerator.multiply(b.denominator).add(b.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(b.denominator);
        return new RobustRational(newNumerator.intValue(), newDenominator.intValue());
    }

    // Subtract two rational numbers
    public RobustRational minus(RobustRational b) {
        BigInteger newNumerator = this.numerator.multiply(b.denominator).subtract(b.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(b.denominator);
        return new RobustRational(newNumerator.intValue(), newDenominator.intValue());
    }

    // Multiply two rational numbers
    public RobustRational times(RobustRational b) {
        BigInteger newNumerator = this.numerator.multiply(b.numerator);
        BigInteger newDenominator = this.denominator.multiply(b.denominator);
        return new RobustRational(newNumerator.intValue(), newDenominator.intValue());
    }

    // Divide two rational numbers
    public RobustRational divides(RobustRational b) {
        assert !b.numerator.equals(BigInteger.ZERO) : "Cannot divide by zero.";
        BigInteger newNumerator = this.numerator.multiply(b.denominator);
        BigInteger newDenominator = this.denominator.multiply(b.numerator);
        return new RobustRational(newNumerator.intValue(), newDenominator.intValue());
    }

    // Check equality
    public boolean equals(RobustRational that) {
        return this.numerator.equals(that.numerator) && this.denominator.equals(that.denominator);
    }

    // String representation
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    // Helper method to find the greatest common divisor
    private BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    // Test client
    public static void main(String[] args) {
        RobustRational r1 = new RobustRational(1, 2); // 1/2
        RobustRational r2 = new RobustRational(3, 4); // 3/4
        RobustRational largeR1 = new RobustRational(Integer.MAX_VALUE, Integer.MAX_VALUE - 1); // Large values
        RobustRational largeR2 = new RobustRational(Integer.MAX_VALUE - 1, Integer.MAX_VALUE); // Large values

        System.out.println("r1: " + r1);
        System.out.println("r2: " + r2);

        // Basic operations
        System.out.println("r1 + r2 = " + r1.plus(r2));
        System.out.println("r1 - r2 = " + r1.minus(r2));
        System.out.println("r1 * r2 = " + r1.times(r2));
        System.out.println("r1 / r2 = " + r1.divides(r2));

        // Testing equality
        RobustRational r3 = new RobustRational(2, 4); // Equivalent to 1/2
        System.out.println("r1 equals r3: " + r1.equals(r3)); // true

        // Tests with large numbers
        System.out.println("largeR1: " + largeR1);
        System.out.println("largeR2: " + largeR2);

        System.out.println("largeR1 + largeR2 = " + largeR1.plus(largeR2));
        System.out.println("largeR1 - largeR2 = " + largeR1.minus(largeR2));
        System.out.println("largeR1 * largeR2 = " + largeR1.times(largeR2));
        System.out.println("largeR1 / largeR2 = " + largeR1.divides(largeR2));
    }

}
