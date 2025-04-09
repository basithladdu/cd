import java.util.Scanner;

public class Exp9 {
    static int n, top = -1;
    static int s[];

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of stack:");
        n = sc.nextInt();
        if (n <= 0) {
            System.out.println("Invalid stack size.");
            return;
        }
        s = new int[n]; // Initialize stack with user-defined size

        System.out.println("\nStack Operations:\n1. Push\n2. Pop\n3. Display\n4. EXIT");
        int ch;

        do {
            System.out.println("Enter your choice:");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    push(sc);
                    break;
                case 2:
                    pop();
                    break;
                case 3:
                    display();
                    break;
                case 4:
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        } while (ch != 4); // Corrected exit condition
        sc.close();
    }

    static void push(Scanner sc) {
        if (top >= n - 1) {
            System.out.println("Stack Overflow");
        } else {
            System.out.println("Enter the value to push:");
            s[++top] = sc.nextInt();
        }
    }

    static void pop() {
        if (top == -1) {
            System.out.println("Underflow");
        } else {
            System.out.println("Popped " + s[top--]);
        }
    }

    static void display() {
        if (top == -1) {
            System.out.println("Empty");
        } else {
            System.out.println("The elements in the stack are:");
            for (int i = top; i >= 0; i--) {
                System.out.println(s[i]);
            }
        }
    }
}
