import java.util.*;

public class exp2 {
    public static void main(String[] args) {
        Set<String> keywords = Set.of("if", "else", "while", "for", "int", "float", "double", "char", "String", "boolean");
        Set<String> operators = Set.of("+", "-", "*", "/", "=", ">", "<", "!", "&", "|", "++", "--");
        Set<String> delimiters = Set.of("(", ")", "{", "}", "[", "]", ",", ";");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter program with single spaces:");
        String input = sc.nextLine();

        // Using an enhanced for-each loop for token processing
        String[] tokens = input.split(" ");
        for (String token : tokens) {
            String type;

            if (keywords.contains(token)) {
                type = "keyword";
            } else if (operators.contains(token)) {
                type = "operator";
            } else if (delimiters.contains(token)) {
                type = "delimiter";
            } else if (isNumeric(token)) {
                type = "literal";
            } else if (isValidIdentifier(token)) {
                type = "identifier";
            } else {
                type = "unknown";
            }
            System.out.println(token + ": " + type);
        }
        sc.close();
    }

    // Simplified isValidIdentifier
    public static boolean isValidIdentifier(String str) {
        return str.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }

    // Simplified isNumeric
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str); // Works for both integers and decimals
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}