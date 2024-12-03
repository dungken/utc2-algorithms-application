package FileInput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileInput {
    public static int[] readInts(String name) {
        try {
            // Read all lines from the file as a single String
            String input = new String(Files.readAllBytes(Paths.get(name)));

            // Split the input string into individual words (numbers)
            String[] words = input.split("\\s+");

            // Create an array to hold the integers
            int[] ints = new int[words.length];

            // Parse each word to an integer
            for (int i = 0; i < words.length; i++) {
                try {
                    ints[i] = Integer.parseInt(words[i]);
                } catch (NumberFormatException e) {
                    System.err.println("Warning: '" + words[i] + "' is not a valid integer and will be skipped.");
                }
            }

            // Return the array of integers
            return ints;

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return new int[0]; // Return an empty array in case of an error
        }
    }

    public static void main(String[] args) {
        int[] numbers = FileInput.readInts("C:\\Workspace\\algorithms-utc2\\FileInput\\input.txt");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }

}
