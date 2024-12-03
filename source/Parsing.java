public class Parsing {
    public static class Date {
        private int month;
        private int day;
        private int year;

        // Parse constructor
        public Date(String date) {
            String[] fields = date.split("/");
            month = Integer.parseInt(fields[0]);
            day = Integer.parseInt(fields[1]);
            year = Integer.parseInt(fields[2]);

            validateDate();
        }

        private void validateDate() {
            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("Month must be between 1 and 12.");
            }
            if (day < 1 || day > 31) {
                throw new IllegalArgumentException("Day must be between 1 and 31.");
            }
        }

        @Override
        public String toString() {
            return month + "/" + day + "/" + year;
        }

    }
    public static class Transaction {
        private String customer;
        private Date date;
        private double amount;

        // Parse constructor
        public Transaction(String transaction) {
            String[] fields = transaction.split("\\s+"); // Split by whitespace
            customer = fields[0];
            date = new Date(fields[1]); // Create Date object
            amount = Double.parseDouble(fields[2]);
        }

        @Override
        public String toString() {
            return customer + " " + date + " " + amount;
        }
    }

    public static void main(String[] args) {
        // Test Date
        Date date = new Date("5/22/1939");
        System.out.println("Parsed Date: " + date);

        // Test Transaction
        Transaction transaction = new Transaction("Turing         5/22/1939       11.99");
        System.out.println("Parsed Transaction: " + transaction);
    }
}
