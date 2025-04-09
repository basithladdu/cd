    import java.util.Scanner;

    public class exp3 {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of productions: ");
        
        int num = sc.nextInt();
        sc.nextLine(); 

        while (num-- > 0) {
            System.out.print("Enter production (A -> Aa / b): ");
            String[] parts = sc.nextLine().split("->");
            char nt = parts[0].trim().charAt(0);
            String[] choices = parts[1].split("/");

            if (choices[0].trim().startsWith(nt + "")) { 
                System.out.println(nt + " -> " + choices[1].trim() + nt + "'");
                System.out.println(nt + "' -> " + choices[0].trim().substring(1) + nt + "' / ε");
            } else {
                System.out.println(nt + " is not left recursive.");
            }
        }
        sc.close();
    }
}


