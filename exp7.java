import java.util.Scanner;

public class exp7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input, stack = "";
        int ruleCount;
        System.out.println("Enter the number of production rules: ");
        ruleCount = sc.nextInt();
        sc.nextLine();
        
        String[] left = new String[ruleCount];
        String[] right = new String[ruleCount];
        System.out.println("Enter the production rules (in the form 'left->right'):");
        for (int i = 0; i < ruleCount; i++) {
            String[] parts = sc.nextLine().split("->");
            left[i] = parts[0].trim();
            right[i] = parts[1].trim();
        }
        
        System.out.println("Enter the input string: ");
        input = sc.nextLine();
        int i = 0;
        System.out.println("Stack\tInputBuffer\tAction");
        
        while (true) {
            if (i < input.length()) {
                char ch = input.charAt(i++);
                stack += ch;
                System.out.println(stack + "\t" + input.substring(i) + "\t\tShift " + ch);
            }
            for (int j = 0; j < ruleCount; j++) {
                int idx = stack.indexOf(right[j]);
                if (idx != -1) {
                    stack = stack.substring(0, idx) + left[j];
                    System.out.println(stack + "\t" + input.substring(i) + "\t\tReduce " + left[j] + "->" + right[j]);
                    j = -1; 
                }
            }
            if (stack.equals(left[0]) && i == input.length()) {
                System.out.println("\nAccepted");
                break;
            }
            if (i == input.length()) {
                System.out.println("\nNot Accepted");
                break;
            }
        }
        sc.close();
    }
}
