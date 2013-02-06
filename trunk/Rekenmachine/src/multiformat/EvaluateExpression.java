package multiformat;

import java.util.Stack;

public class EvaluateExpression {
 
	public EvaluateExpression(String expression) {
		// Check number of arguments passed
		try{
			this.evaluateExpression(expression);
		}
		catch (Exception ex) {
			System.out.println("Wrong expression");
		}
	}

	/** Evaluate an expression */
	public static Rational evaluateExpression(String expression) {
		// Create operandStack to store operands
		Stack<Rational> operandStack = new Stack<Rational>();

		// Create operatorStack to store operators
		Stack<Character> operatorStack = new Stack<Character>();

		// Extract operands and operators
		java.util.StringTokenizer tokens = new java.util.StringTokenizer(expression, "()+-/*", true) ;

		// Phase 1: Scan tokens
		while(tokens.hasMoreTokens()){
	
			String token = tokens.nextToken().trim(); // Extract a token
			if (token.length() == 0)// Blank space
				continue; // Back to the while loop to extract the next token
			else if (token.charAt(0) == '+' || token.charAt(0) == '-'){
		
				// Process all +, -, *, / in the top of the operator stack
				while (!operatorStack.isEmpty() &&
						(operatorStack.peek() == '+' ||
						operatorStack.peek() == '-' ||
						operatorStack.peek() == '*' ||
						operatorStack.peek() == '/')) {
					processAnOperator(operandStack, operatorStack);
				}
				
				// Push the + or - operator into the operator stack
				operatorStack.push(token.charAt(0));
			} 
			else if (token.charAt(0) == '*' || token.charAt(0) == '/'){
				// Process all *, / in the top of the operator stack
				while (!operatorStack.isEmpty() &&
						(operatorStack.peek() == '*' ||
						operatorStack.peek() == '/')) {
					processAnOperator(operandStack, operatorStack);
				}

				// Push the * or / operator into the operator stack
				operatorStack.push(token.charAt(0));
			}else if (token.trim().charAt(0) == '(') {

				operatorStack.push('('); // Push '(' to stack
			}
			else if (token.trim().charAt(0) == ')') {
				// Process all the operators in the stack until seeing '('
				while (operatorStack.peek() != '(') {
					processAnOperator(operandStack, operatorStack);
				}

				operatorStack.pop(); // Pop the '(' symbol from the stack
			}
			else { // An operand scanned
				// Push an operand to the stack
				 double number = Double.parseDouble(token);
				operandStack.push(new Rational(number));
			}
		}

		// Phase 2: process all the remaining operators in the stack
		while (!operatorStack.isEmpty()) {
			processAnOperator(operandStack, operatorStack);
		}

		// Return the result
		return operandStack.pop();
		}
	
		/** Process one operator: Take an operator from operatorStack and
		 * apply it on the operands in the operandStack 
		 */
	
	public static void processAnOperator(Stack<Rational> operandStack, Stack<Character> operatorStack) {
		char op = operatorStack.pop();
		Rational op1 = operandStack.pop();
		Rational op2 = operandStack.pop();

		if (op == '+')
			operandStack.push(op2.plus(op1));
		else if (op == '-')
			operandStack.push(op2.minus(op1));
		else if (op == '*')
			operandStack.push(op2.mul(op1));
		else if (op == '/')
			operandStack.push(op2.div(op1));
		}
}