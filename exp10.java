import java.util.Stack;
import java.util.Scanner;

public class exp10 {

    public static void generateIntermediateCode(String expression) {
        Stack<String> operands = new Stack<>();
        Stack<String> operators = new Stack<>();
        String[] tokens = expression.split(" ");
        int tempCount = 0;
        
        for (String token : tokens) {
            if (isOperator(token)) {
                operators.push(token);
            } else {
                operands.push(token);
                
                // When you have two operands and at least one operator,
                // combine them into a temporary variable.
                if (operands.size() >= 2 && operators.size() >= 1) {
                    String operand2 = operands.pop();
                    String operand1 = operands.pop();
                    String operator = operators.pop();
                    
                    String tempVar = "t" + tempCount++;
                    System.out.println(tempVar + " = " + operand1 + " " + operator + " " + operand2);
                    
                    // Push the temporary variable back as the new operand.
                    operands.push(tempVar);
                }
            }
        }
    }

    public static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
            System.out.print("Enter an arithmetic expression (tokens separated by single space): ");
            String expression = sc.nextLine().trim();
           
            System.out.println("Intermediate Code:");
            generateIntermediateCode(expression);
            System.out.println();
        
        
    }
}