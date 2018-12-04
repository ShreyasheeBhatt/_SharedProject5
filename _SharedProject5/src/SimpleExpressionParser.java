/**
 * Starter code to implement an ExpressionParser. Your parser methods should use the following grammar:
 * E := A | X
 * A := A+M | M
 * M := M*M | X
 * X := (E) | L
 * L := [0-9]+ | [a-z]
 */
public class SimpleExpressionParser implements ExpressionParser {

	/** Attempts to create an expression tree -- flattened as much as possible -- from the specified String.
         * Throws a ExpressionParseException if the specified string cannot be parsed.
	 * @param str the string to parse into an expression tree
	 * @param withJavaFXControls you can just ignore this variable for R1
	 * @return the Expression object representing the parsed expression tree
	 */
	public Expression parse (String str, boolean withJavaFXControls) throws ExpressionParseException {
		// Remove spaces -- this simplifies the parsing logic
		str = str.replaceAll(" ", "");
		Expression expression = parseExpression(str);
		if (expression == null) {
			// If we couldn't parse the string, then raise an error
			throw new ExpressionParseException("Cannot parse expression: " + str);
		}

		// Flatten the expression before returning
		expression.flatten();
		return expression;
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	protected Expression parseExpression (String str) {
		Expression expression = null;
		if(!isE(str))
			return null;
		else if(isA(str)) {
			expression = new AdditiveExpression();
			expression = getA(str);
		}
		else if(isM(str)) {
			expression = new MultiplicativeExpression();
			expression = getM(str);
		}
		else if(isX(str) && !isL(str)) {
			expression = new ParentheticalExpression();
			expression = parseExpression(str.substring(1,str.length()-1));
		}
		else if(isL(str)) {
			expression = new LiteralExpression(str);
		}
		return expression;
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	private boolean isE(String str) {
		return (isA(str) || isX(str));
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	private boolean isA(String str) {
		boolean isA = false;
		if(isM(str))
			isA = true;
		else {
			if (str.contains("+")) {
				String str1 = str.substring(0, str.indexOf("+"));
				String str2 = str.substring(str.indexOf("+") + 1);
				if (isA(str1) && isM(str2))
					isA = true;
			}
		}
		return isA;
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	private Expression getA(String str) {
		CompoundExpression expression = new AdditiveExpression();
		if(str.indexOf("+") > str.indexOf(")")) {
			String str1 = str.substring(0,str.indexOf("+"));
			String str2 = str.substring(str.indexOf("+")+1);

			Expression sub1 = parseExpression(str1);
			sub1.setParent(expression);
			Expression sub2 = parseExpression(str2);
			sub2.setParent(expression);

			expression.addSubexpression(sub1);
			expression.addSubexpression(sub2);
		}
		else
			getA(str.substring(str.indexOf(")")+1));
		return expression;
	}


	/**
	 *
	 * @param str
	 * @return
	 */
	private boolean isM(String str) {
		boolean isM = false;
		if(isX(str))
			isM = true;
		else {
			if (str.contains("*")) {
				String str1 = str.substring(0, str.indexOf("*"));
				String str2 = str.substring(str.indexOf("*") + 1);
				if (isM(str1) && isM(str2))
					isM = true;
			}
		}
		return isM;
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	private Expression getM(String str) {
		CompoundExpression expression = new MultiplicativeExpression();
		if (str.indexOf("*") > str.indexOf(")")) {
			String str1 = str.substring(0, str.indexOf("*"));
			String str2 = str.substring(str.indexOf("*") + 1);

			Expression sub1 = parseExpression(str1);
			sub1.setParent(expression);
			Expression sub2 = parseExpression(str2);
			sub2.setParent(expression);

			expression.addSubexpression(sub1);
			expression.addSubexpression(sub2);
		}
		else
			getM(str.substring(str.indexOf(")") + 1));
		return expression;
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	private boolean isX(String str) {
		boolean isX = false;
		if(str.charAt(0) == '(' && isE(str.substring(1,str.length()-1)) && str.charAt(str.length()-1) == ')')
			isX = true;
		return (isL(str) || isX);
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	private boolean isL(String str) {
		boolean isL = true;
		for(int n=0; n<str.length(); n++) {
			if(!(Character.isDigit(str.charAt(n)) || Character.isLetter(str.charAt(n))))
				isL = false;
		}
		return isL;
	}
}
