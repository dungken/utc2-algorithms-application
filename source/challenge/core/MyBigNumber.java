package challenge.core;

import java.util.logging.Logger;

public class MyBigNumber {
    private static final Logger logger = Logger.getLogger(MyBigNumber.class.getName());

    public String sum(String stn1, String stn2) {
        StringBuilder result = new StringBuilder();
        int len1 = stn1.length();
        int len2 = stn2.length();
        int carry = 0;  // Memory variable
        int maxLen = Math.max(len1, len2);  // Length of the largest string

        // Loop through each digit of the two strings
        for (int i = 0; i < maxLen; i++) {
            // Get the digit at the current position (from right to left) of each string
            // If the position is out of range, use 0 instead
            int digit1 = (i < len1) ? stn1.charAt(len1 - 1 - i) - '0' : 0;
            int digit2 = (i < len2) ? stn2.charAt(len2 - 1 - i) - '0' : 0;

            // Calculate the sum of the two digits and the carry
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;  // Calculate the carry
            int currentDigit = sum % 10;  // Get the current digit of the result

            // Log the calculation
            logger.info("Step " + (i + 1) + ": " + digit1 + " + " + digit2 + " + " + carry + " = " + sum
                    + " (carry: " + carry + ", current digit: " + currentDigit + ")");

            // Add the current digit to the result
            result.append(currentDigit);
        }

        // If there is a carry left, add it to the result
        if (carry > 0) {
            result.append(carry);
            logger.info("Final carry: " + carry);
        }

        // Reverse the result and return it
        return result.reverse().toString();
    }
}
