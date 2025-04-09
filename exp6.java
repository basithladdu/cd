import java.util.*;

public class exp6 {
    static Map<Character, List<String>> grammar = new HashMap<>();
    static String input;
    static int i = 0;

    static boolean parse(char nt) {
        int backtrack = i;
        for (String prod : grammar.get(nt)) {
            i = backtrack;
            boolean success = true;
            for (char symbol : prod.toCharArray()) {
                if (symbol == '@') // Epsilon
                    continue;
                else if (Character.isUpperCase(symbol)) // Non-terminal
                    success &= parse(symbol);
                else if (i < input.length() && input.charAt(i) == symbol) // Terminal matches input
                    i++;
                else {
                    success = false;
                    break;
                }
            }
            if (success) return true; // Production successfully matches
        }
        return false; // No production matches
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter num of productions: ");
        int n = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Enter productions: ");
        for (int j = 0; j < n; j++) {
            String[] rule = sc.nextLine().split("->");
            grammar.put(rule[0].charAt(0), Arrays.asList(rule[1].split("\\|")));
        }
        System.out.println("Enter input string:");
        input = sc.next() + "$"; 
        System.out.println(parse('E') && i == input.length() - 1 ? "String is Accepted" : "String is rejected");
        sc.close();
    }
}