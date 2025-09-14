public class Hex {
    public static void main(String[] args) {
        int number;
        if (args.length > 0) {
            try {
                number = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid integer.");
                return;
            }
        } else {
            number = 255; // default value
        }

        System.out.println("Decimal: " + number);
        System.out.println("Hexadecimal: " + Integer.toHexString(number).toUpperCase());
    }
}
