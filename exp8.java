import java.util.Scanner;
public class exp8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] stack = new char[20], ip;
        char[][] opt = new char[10][10]; // precedence table
        char[] ter = new char[10];
        int i, j, k, n, top = 0, col = 0, row = 0;

        System.out.print("Enter the no. of terminals:");
        n = sc.nextInt();
        System.out.print("\nEnter the terminals:");
        ter = sc.next().toCharArray();

        System.out.println("\nEnter the table values:");
        for(i = 0; i < n; i++) {
            for(j = 0; j < n; j++) {
                System.out.printf("\nEnter the value for %c %c:", ter[i], ter[j]);
                opt[i][j] = sc.next().toCharArray()[0];
            }
        }
        
        System.out.println("\nOPERATOR PRECEDENCE TABLE:");
        System.out.print("\t");
        for(i = 0; i < n; i++) System.out.print(ter[i] + "\t");
        System.out.println();
        for(i = 0; i < n; i++) {
            System.out.print(ter[i]);
            for(j = 0; j < n; j++) {
                System.out.print("\t" + opt[i][j]);
            }
            System.out.println();
        }
        
        stack[top] = '$';
        System.out.print("\nEnter the input string:");
        String input = sc.next();
        ip = input.toCharArray();
        i = 0;
        System.out.println("\nSTACK\t\t\tINPUT STRING\t\t\tACTION");
        System.out.print(new String(stack, 0, top+1) + "\t" + input + "\t\t");
        
        while (i <= input.length()) {
            // Find the table indices for stack[top] and current input symbol
            for (k = 0; k < n; k++) {
                if (stack[top] == ter[k])
                    col = k;
                if (i < input.length() && ip[i] == ter[k])
                    row = k;
            }
            if ((stack[top] == '$') && (i < input.length() && ip[i] == '$')) {
                System.out.println("String is accepted");
                break;
            }
            else if (i < input.length() && (opt[col][row]=='<' || opt[col][row]=='=')) {
                stack[++top] = opt[col][row];
                stack[++top] = ip[i];
                System.out.println("Shift " + ip[i]);
                i++;
            }
            else if (i < input.length() && opt[col][row]=='>') {
                while (stack[top] != '<') {
                    top--;
                }
                top--; // remove '<'
                System.out.println("Reduce");
            }
            else {
                System.out.println("\nString is not accepted");
                break;
            }
            System.out.println();
            for (k = 0; k <= top; k++)
                System.out.print(stack[k]);
            System.out.print("\t\t\t");
            for (k = i; k < input.length(); k++)
                System.out.print(ip[k]);
            System.out.print("\t\t\t");
        }
        sc.close();
    }
}
