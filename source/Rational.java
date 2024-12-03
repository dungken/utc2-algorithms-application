public class Rational {
    private final long numerator;
    private final long denominator;

    // Constructor
    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }

        // Normalize the sign
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        // Reduce to simplest form
        long gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    // Add two rational numbers
    public Rational plus(Rational b) {
        long newNumerator = this.numerator * b.denominator + b.numerator * this.denominator;
        long newDenominator = this.denominator * b.denominator;
        return new Rational((int) newNumerator, (int) newDenominator);
    }

    // Subtract two rational numbers
    public Rational minus(Rational b) {
        long newNumerator = this.numerator * b.denominator - b.numerator * this.denominator;
        long newDenominator = this.denominator * b.denominator;
        return new Rational((int) newNumerator, (int) newDenominator);
    }

    // Multiply two rational numbers
    public Rational times(Rational b) {
        long newNumerator = this.numerator * b.numerator;
        long newDenominator = this.denominator * b.denominator;
        return new Rational((int) newNumerator, (int) newDenominator);
    }

    // Divide two rational numbers
    public Rational divides(Rational b) {
        if (b.numerator == 0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }
        long newNumerator = this.numerator * b.denominator;
        long newDenominator = this.denominator * b.numerator;
        return new Rational((int) newNumerator, (int) newDenominator);
    }

    // Check equality
    public boolean equals(Rational that) {
        return this.numerator == that.numerator && this.denominator == that.denominator;
    }

    // String representation
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    // Helper method to find the greatest common divisor
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Test client
    public static void main(String[] args) {
        Rational r1 = new Rational(1, 2); // 1/2
        Rational r2 = new Rational(3, 4); // 3/4

        System.out.println("r1: " + r1);
        System.out.println("r2: " + r2);

        System.out.println("r1 + r2 = " + r1.plus(r2));
        System.out.println("r1 - r2 = " + r1.minus(r2));
        System.out.println("r1 * r2 = " + r1.times(r2));
        System.out.println("r1 / r2 = " + r1.divides(r2));

        // Testing equality
        Rational r3 = new Rational(2, 4); // Equivalent to 1/2
        System.out.println("r1 equals r3: " + r1.equals(r3)); // true
    }
}
