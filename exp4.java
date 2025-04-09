import java.util.*;

public class exp4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get the number of productions
        System.out.print("Enter number of productions: ");
        int n = sc.nextInt();
        sc.nextLine();  // Handle the newline after the integer input

        // Step 2: Input the productions
        String[] productions = new String[n];
        System.out.println("Enter the productions (e.g., A->ab|ac|d):");
        for (int i = 0; i < n; i++) {
            productions[i] = sc.nextLine();
        }

        // Step 3: Process each production
        for (String prod : productions) {
            // Split the production into LHS and RHS
            String[] parts = prod.split("->");
            String lhs = parts[0];
            String[] rhs = parts[1].split("\\|");

            // Find the common prefix among RHS alternatives
            String prefix = findCommonPrefix(rhs);

            // Check if left factoring is needed
            if (prefix.isEmpty()) {
                System.out.println(prod + " (No left factoring needed)");
                continue;
            }

            // Rewrite the production with left factoring
            System.out.println(lhs + " -> " + prefix + lhs + "'");
            System.out.print(lhs + "' -> ");

            // Print the modified RHS alternatives
            for (String r : rhs) {
                if (r.startsWith(prefix)) {
                    System.out.print(r.substring(prefix.length()) + "|");
                } else {
                    System.out.print(r + "|");
                }
            }
            System.out.println("ε");  // Add epsilon for empty alternatives
        }

        sc.close();  // Close the scanner
    }

    // Helper function to find the longest common prefix
    private static String findCommonPrefix(String[] rhs) {
        // Initialize the prefix to the first RHS alternative
        String prefix = rhs[0];

        // Iterate through all RHS alternatives to find the common prefix
        for (String r : rhs) {
            // While the current alternative doesn't start with the prefix
            while (!r.startsWith(prefix)) {
                // Reduce the prefix by removing the last character
                prefix = prefix.substring(0, prefix.length() - 1);

                // If the prefix becomes empty, return an empty string
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;  // Return the longest common prefix
    }
}
