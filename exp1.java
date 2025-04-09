import java.util.Scanner;

public class exp1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String s = sc.next();
        
        if (dfa(s)) {
            System.out.println("String is accepted.");
        } else {
            System.out.println("String is rejected.");
        }
        sc.close();
    }
//abc
    static boolean dfa(String s) {
        int state = 0;
        System.out.print("-> q0");
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); 
            if (c != 'a' && c != 'b' && c != 'c') {
                System.out.println("\nInvalid String");
                return false;
            }
            switch (state) {
                case 0:
                    if (c == 'a') {
                        state = 1;
                        System.out.print(" -> q1");
                    }
                    break;
                case 1:
                    if (c == 'b') {
                        state = 2;
                        System.out.print(" -> q2");
                    }
                    break;
                case 2:
                    if (c == 'c') {
                        state = 3;
                        System.out.print(" -> q3");
                    }
                    break;
                default:
                    if (c == 'a') {
                        state = 1;
                        System.out.print(" -> q1");
                    } else if (c == 'b' || c == 'c') {
                        state = 0;
                        System.out.print(" -> q0");
                    }
                    break;
            }
        }
        System.out.println(); 
        return state == 3; 
    }
}
