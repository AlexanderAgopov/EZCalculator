
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * The class EZCalculator builds a GUI calculator which can perform the
 * following math functions: 1) add; 2) subtract; 3) multiply; 4) divide; 5)
 * calculate a percentage; 6) raise to second power; 7) calculate a square root;
 * 8) calculate a natural logarithm; 9) calculate a factorial.
 * 
 * @author a
 *
 */

public class EZCalculator {

	private static String expression = "";
	private static String tempExpression = "";
	private static String result = "";
	private static double resultNumber = 0;

	/**
	 * Create and add all components to pane.
	 * 
	 * @param pane - the pane to be used
	 */
	private static void createAndAddComponentsToPane(Container pane) {

		pane.setLayout(new GridBagLayout());

		// Create Insets templates to be used for buttons on the left side, on the right
		// side, on the bottom and in the middle of the frame
		Insets insetsMiddle = new Insets(2, 2, 2, 2);
		Insets insetsLeft = new Insets(2, 4, 2, 2);
		Insets insetsRight = new Insets(2, 2, 2, 4);
		Insets insetsBottom = new Insets(2, 2, 4, 2);

		// Create text fields and position them on the top of the frame
		JTextField expressionField = new JTextField();
		expressionField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		expressionField.setDisabledTextColor(Color.black);

		expressionField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if ((e.getKeyChar() < '0') || (e.getKeyChar() > '9')) { // (c < '*') allows usage of '+', '-', '*','/'
					e.consume(); // ignore event
				}
			}
		});
		positionTextField(pane, expressionField, new GridBagConstraints(), 6, 0, 20, 0.0, new Insets(6, 4, 4, 2), 0, 0);

		JTextField resultField = new JTextField();
		resultField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		resultField.setDisabledTextColor(Color.BLACK);
		resultField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				e.consume(); // ignore event
			}
		});
		positionTextField(pane, resultField, new GridBagConstraints(), 6, 0, 20, 0.0, new Insets(2, 4, 4, 2), 0, 1);

		// Create all buttons and position them in the frame
		JButton b7 = new JButton("7");
		positionButton(pane, b7, new GridBagConstraints(), 1, 10, 10, 0.5, insetsLeft, 0, 2);

		JButton b8 = new JButton("8");
		positionButton(pane, b8, new GridBagConstraints(), 1, 10, 10, 0.5, insetsMiddle, 1, 2);

		JButton b9 = new JButton("9");
		positionButton(pane, b9, new GridBagConstraints(), 1, 10, 10, 0.5, insetsMiddle, 2, 2);

		JButton bDivide = new JButton("/");
		positionButton(pane, bDivide, new GridBagConstraints(), 1, 15, 10, 0.5, insetsMiddle, 3, 2);

		JButton bBackspace = new JButton("\u2b05");
		positionButton(pane, bBackspace, new GridBagConstraints(), 1, 0, 10, 0.5, insetsMiddle, 4, 2);

		JButton bCancel = new JButton("C");
		positionButton(pane, bCancel, new GridBagConstraints(), 1, 10, 10, 0.5, insetsRight, 5, 2);

		JButton b4 = new JButton("4");
		positionButton(pane, b4, new GridBagConstraints(), 1, 10, 10, 0.5, insetsLeft, 0, 3);

		JButton b5 = new JButton("5");
		positionButton(pane, b5, new GridBagConstraints(), 1, 10, 10, 0.5, insetsMiddle, 1, 3);

		JButton b6 = new JButton("6");
		positionButton(pane, b6, new GridBagConstraints(), 1, 10, 10, 0.5, insetsMiddle, 2, 3);

		JButton bMultiply = new JButton("*");
		positionButton(pane, bMultiply, new GridBagConstraints(), 1, 0, 10, 0.5, insetsMiddle, 3, 3);

		JButton bFactorial = new JButton("n!");
		positionButton(pane, bFactorial, new GridBagConstraints(), 1, 0, 10, 0.5, insetsMiddle, 4, 3);

		JButton bLogarithm = new JButton("log");
		positionButton(pane, bLogarithm, new GridBagConstraints(), 1, 0, 10, 0.5, insetsRight, 5, 3);

		JButton b1 = new JButton("1");
		positionButton(pane, b1, new GridBagConstraints(), 1, 10, 10, 0.5, insetsLeft, 0, 4);

		JButton b2 = new JButton("2");
		positionButton(pane, b2, new GridBagConstraints(), 1, 10, 10, 0.5, insetsMiddle, 1, 4);

		JButton b3 = new JButton("3");
		positionButton(pane, b3, new GridBagConstraints(), 1, 10, 10, 0.5, insetsMiddle, 2, 4);

		JButton bSubtract = new JButton("-");
		positionButton(pane, bSubtract, new GridBagConstraints(), 1, 10, 10, 0.5, insetsMiddle, 3, 4);

		JButton bSecondPower = new JButton("x\u00B2");
		positionButton(pane, bSecondPower, new GridBagConstraints(), 1, 0, 10, 0.5, insetsMiddle, 4, 4);

		JButton bSquareRoot = new JButton("\u221A");
		positionButton(pane, bSquareRoot, new GridBagConstraints(), 1, 0, 10, 0.5, insetsRight, 5, 4);

		JButton b0 = new JButton("0");
		positionButton(pane, b0, new GridBagConstraints(), 1, 10, 10, 0.5, new Insets(2, 4, 4, 2), 0, 5);

		JButton bDot = new JButton(".");
		positionButton(pane, bDot, new GridBagConstraints(), 1, 10, 10, 0.5, insetsBottom, 1, 5);

		JButton bPercent = new JButton("%");
		positionButton(pane, bPercent, new GridBagConstraints(), 1, 10, 10, 0.5, insetsBottom, 2, 5);

		JButton bAdd = new JButton("+");
		positionButton(pane, bAdd, new GridBagConstraints(), 1, 0, 10, 0.5, insetsBottom, 3, 5);

		JButton bEquals = new JButton("=");
		positionButton(pane, bEquals, new GridBagConstraints(), 2, 0, 10, 0.5, new Insets(2, 2, 4, 4), 4, 5);

		// Set button operations
		b0.addActionListener((ActionEvent e) -> {
			printDigit(expressionField, "0");
		});

		b1.addActionListener((ActionEvent e) -> {
			printDigit(expressionField, "1");
		});

		b2.addActionListener((ActionEvent e) -> {
			printDigit(expressionField, "2");
		});

		b3.addActionListener((ActionEvent e) -> {
			printDigit(expressionField, "3");
		});

		b4.addActionListener((ActionEvent e) -> {
			printDigit(expressionField, "4");
		});

		b5.addActionListener((ActionEvent e) -> {
			printDigit(expressionField, "5");
		});

		b6.addActionListener((ActionEvent e) -> {
			printDigit(expressionField, "6");
		});

		b7.addActionListener((ActionEvent e) -> {
			printDigit(expressionField, "7");
		});

		b8.addActionListener((ActionEvent e) -> {
			printDigit(expressionField, "8");
		});

		b9.addActionListener((ActionEvent e) -> {
			printDigit(expressionField, "9");
		});

		bDot.addActionListener((ActionEvent e) -> {
			printDigit(expressionField, ".");
		});

		bAdd.addActionListener((ActionEvent e) -> {
			add(expressionField, resultField);
		});

		bSubtract.addActionListener((ActionEvent e) -> {
			subtract(expressionField, resultField);
		});

		bMultiply.addActionListener((ActionEvent e) -> {
			multiply(expressionField, resultField);
		});

		bDivide.addActionListener((ActionEvent e) -> {
			divide(expressionField, resultField);
		});

		bPercent.addActionListener((ActionEvent e) -> {
			calculatePercentage(expressionField, resultField);
		});

		bSecondPower.addActionListener((ActionEvent e) -> {
			raiseToSecondPower(expressionField, resultField);
		});

		bSquareRoot.addActionListener((ActionEvent e) -> {
			sqrt(expressionField, resultField);
		});

		bLogarithm.addActionListener((ActionEvent e) -> {
			log(expressionField, resultField);
		});

		bFactorial.addActionListener((ActionEvent e) -> {
			calculateFactorial(expressionField, resultField);
		});

		bBackspace.addActionListener((ActionEvent e) -> {
			if (expression.charAt(expression.length() - 1) == ' ') {
				expression = "";
				expressionField.setText(expression);
				result = "";
				resultField.setText(result);
			} else {
				expression = expression.substring(0, expression.length() - 1);
				expressionField.setText(expression);
			}
		});

		bCancel.addActionListener((ActionEvent e) -> {
			expression = "";
			expressionField.setText(expression);
			result = "";
			resultField.setText(result);
		});

		bEquals.addActionListener((ActionEvent e) -> {
			calculate(expressionField, resultField);
		});

	}

	private static void printDigit(JTextField expressionField, String digit) {
		expression += digit;
		expressionField.setText(expression);
	}

	/**
	 * The method add(JTextField expressionField, JTextField resultField) performs
	 * the math operation "addition". The first text field contains the expression
	 * which could be formed by multiple mathematical operations and functions
	 * defined by this class. The second text field contains the result.
	 * 
	 * 
	 * @param expressionField - contains the math expression
	 * @param resultField     - contains the result
	 */
	private static void add(JTextField expressionField, JTextField resultField) {
		if (noCalculationYet()) {
			if (expressionField.getText() == "") {
				addBasicOperator(expressionField, "+");
				resultNumber = 0;
				showResult(resultField);
			} else {
				addFirstOperatorInExpression(expressionField, resultField, "+");
			}
		} else {
			if (isOnlyOneNumberPresent()) {
				addFirstOperatorInExpression(expressionField, resultField, "+");
			} else {
				if (isPrecedingMathFunctionSimple()) {
					doPrecedingSimpleMathFunction(expressionField, resultField);
					expression = expressionField.getText();
					addBasicOperator(expressionField, "+");
				} else {
					addBasicOperator(expressionField, "+");
				}
			}

		}
	}

	/**
	 * The method subtract(JTextField expressionField, JTextField resultField)
	 * performs the math operation "subtraction". The parameters of the method are
	 * two objects of class JTextField. The first text field contains the expression
	 * which could be formed by multiple mathematical operations and functions
	 * defined by this class. The second text field contains the result.
	 * 
	 * 
	 * @param expressionField - contains the math expression
	 * @param resultField     - contains the result
	 */
	private static void subtract(JTextField expressionField, JTextField resultField) {
		if (noCalculationYet()) {
			if (expression == "") {
				resultNumber = 0;
				addBasicOperator(expressionField, "-");
			} else {
				addFirstOperatorInExpression(expressionField, resultField, "-");
			}

		} else {
			if (isOnlyOneNumberPresent()) {
				addFirstOperatorInExpression(expressionField, resultField, "-");
			} else {
				if (isPrecedingMathFunctionSimple()) {
					doPrecedingSimpleMathFunction(expressionField, resultField);
					addBasicOperator(expressionField, "-");
				} else {
					addBasicOperator(expressionField, "-");
				}
			}

		}
	}

	/**
	 * The method multiply(JTextField expressionField, JTextField resultField)
	 * performs the math operation "multiplication". The parameters of the method
	 * are two objects of class JTextField. The first text field contains the
	 * expression which could be formed by multiple mathematical operations and
	 * functions defined by this class. The second text field contains the result.
	 * 
	 * 
	 * @param expressionField - contains the math expression
	 * @param resultField     - contains the result
	 */
	private static void multiply(JTextField expressionField, JTextField resultField) {
		if (noCalculationYet()) {
			if (expression == "") {
				addBasicOperator(expressionField, "*");
				resultNumber = 0;
				showResult(resultField);
			} else {
				addFirstOperatorInExpression(expressionField, resultField, "*");
			}
		} else {
			if (isOnlyOneNumberPresent()) {
				addFirstOperatorInExpression(expressionField, resultField, "*");
			} else {
				if (isPrecedingMathFunctionSimple()) {
					doPrecedingSimpleMathFunction(expressionField, resultField);
					addBasicOperator(expressionField, "*");
				} else {
					addBasicOperator(expressionField, "*");
				}
			}

		}
	}

	/**
	 * The method divide(JTextField expressionField, JTextField resultField)
	 * performs the math operation "division". The parameters of the method are two
	 * objects of class JTextField. The first text field contains the expression
	 * which could be formed by multiple mathematical operations and functions
	 * defined by this class. The second text field contains the result.
	 * 
	 * @param expressionField - contains the math expression
	 * @param resultField     - contains the result
	 */
	private static void divide(JTextField expressionField, JTextField resultField) {
		if (noCalculationYet()) {
			if (expression == "") {
				addBasicOperator(expressionField, "/");
				resultNumber = 0;
				showResult(resultField);
			} else {
				addFirstOperatorInExpression(expressionField, resultField, "/");
			}
		} else {
			if (isOnlyOneNumberPresent()) {
				addFirstOperatorInExpression(expressionField, resultField, "/");
			} else {
				if (isPrecedingMathFunctionSimple()) {
					doPrecedingSimpleMathFunction(expressionField, resultField);
					addBasicOperator(expressionField, "/");
				} else {
					addBasicOperator(expressionField, "/");
				}
			}

		}
	}

	/**
	 * The method calculatePercentage(JTextField expressionField, JTextField
	 * resultField) calculates the percentage of a given number (or the percentage
	 * of a given expression). The parameters of the method are two objects of class
	 * JTextField. The first text field contains the expression which could be
	 * formed by multiple mathematical operations and functions defined by this
	 * class. The second text field contains the result.
	 * 
	 * 
	 * @param expressionField - contains the math expression
	 * @param resultField     - contains the result
	 */
	private static void calculatePercentage(JTextField expressionField, JTextField resultField) {
		if (noCalculationYet()) {
			if (expression == "") {
				addBasicOperator(expressionField, "%");
				resultNumber = 0;
				showResult(resultField);
			} else {
				addFirstOperatorInExpression(expressionField, resultField, "%");
			}
		} else {
			if (isOnlyOneNumberPresent()) {
				addFirstOperatorInExpression(expressionField, resultField, "%");
			} else {
				if (isPrecedingMathFunctionSimple()) {
					doPrecedingSimpleMathFunction(expressionField, resultField);
					addBasicOperator(expressionField, "%");
				} else {
					addBasicOperator(expressionField, "%");
				}
			}

		}
	}

	/**
	 * The method raiseToSecondPower(JTextField expressionField, JTextField
	 * resultField) raises a given number (or a given expression) to second power.
	 * The parameters of the method are two objects of class JTextField. The first
	 * text field contains the expression which could be formed by multiple
	 * mathematical operations and functions defined by this class. The second text
	 * field contains the result.
	 * 
	 * 
	 * @param expressionField - contains the math expression
	 * @param resultField     - contains the result
	 */
	private static void raiseToSecondPower(JTextField expressionField, JTextField resultField) {
		if (noCalculationYet()) {
			if (expression == "") {
				resultNumber = 0;
				expression = "( 0 )\u00B2";
				expressionField.setText(expression);
				showResult(resultField);
			} else {
				resultNumber = Double.parseDouble(expressionField.getText())
						* Double.parseDouble(expressionField.getText());
				addAdvancedOperator(expressionField, "( ", " )\u00B2");
				showResult(resultField);
			}
		} else {
			if (isOnlyOneNumberPresent()) {
				resultNumber = Double.parseDouble(expressionField.getText())
						* Double.parseDouble(expressionField.getText());
				addAdvancedOperator(expressionField, "( ", " )\u00B2");
				showResult(resultField);
			} else {
				if (isPrecedingMathFunctionSimple()) {
					doPrecedingSimpleMathFunction(expressionField, resultField);
					addAdvancedOperator(expressionField, "( ", " )\u00B2");
					resultNumber = resultNumber * resultNumber;
					showResult(resultField);

				} else {
					addAdvancedOperator(expressionField, "( ", " )\u00B2");
					resultNumber = resultNumber * resultNumber;
					showResult(resultField);
				}
			}

		}
	}

	/**
	 * The method sqrt(JTextField expressionField, JTextField resultField) finds the
	 * square root of a given number (or a given expression). The parameters of the
	 * method are two objects of class JTextField. The first text field contains the
	 * expression which could be formed by multiple mathematical operations and
	 * functions defined by this class. The second text field contains the result.
	 * 
	 * 
	 * @param expressionField - contains the math expression
	 * @param resultField     - contains the result
	 */
	private static void sqrt(JTextField expressionField, JTextField resultField) {
		if (noCalculationYet()) {
			if (expression == "") {
				resultNumber = 0;
				expression = "\u221A( 0 )";
				expressionField.setText(expression);
				showResult(resultField);
			} else {
				resultNumber = Math.sqrt(Double.parseDouble(expressionField.getText()));
				addAdvancedOperator(expressionField, "\u221A( ", " )");
				showResult(resultField);
			}
		} else {
			if (isOnlyOneNumberPresent()) {
				resultNumber = Math.sqrt(Double.parseDouble(expressionField.getText()));
				addAdvancedOperator(expressionField, "\u221A( ", " )");
				showResult(resultField);
			} else {
				if (isPrecedingMathFunctionSimple()) {
					doPrecedingSimpleMathFunction(expressionField, resultField);
					addAdvancedOperator(expressionField, "\u221A( ", " )");
					resultNumber = Math.sqrt(resultNumber);
					showResult(resultField);
				} else {
					addAdvancedOperator(expressionField, "\u221A( ", " )");
					resultNumber = Math.sqrt(resultNumber);
					showResult(resultField);
				}
			}

		}
	}

	/**
	 * The method log(JTextField expressionField, JTextField resultField) finds the
	 * natural logarithm of a given number (or a given expression). The parameters
	 * of the method are two objects of class JTextField. The first text field
	 * contains the expression which could be formed by multiple mathematical
	 * operations and functions defined by this class. The second text field
	 * contains the result.
	 * 
	 * 
	 * @param expressionField - contains the math expression
	 * @param resultField     - contains the result
	 */
	private static void log(JTextField expressionField, JTextField resultField) {
		if (noCalculationYet()) {
			if (expression == "") {
				resultNumber = 0;
				expression = "log( 0 )";
				expressionField.setText(expression);
				resultNumber = Math.log(resultNumber);
				showResult(resultField);
			} else {
				resultNumber = Math.log(Double.parseDouble(expressionField.getText()));
				addAdvancedOperator(expressionField, "log( ", " )");
				showResult(resultField);
			}
		} else {
			if (isOnlyOneNumberPresent()) {
				resultNumber = Math.log(Double.parseDouble(expressionField.getText()));
				addAdvancedOperator(expressionField, "log( ", " )");
				showResult(resultField);
			} else {
				if (isPrecedingMathFunctionSimple()) {
					doPrecedingSimpleMathFunction(expressionField, resultField);
					addAdvancedOperator(expressionField, "log( ", " )");
					resultNumber = Math.log(resultNumber);
					showResult(resultField);

				} else {
					addAdvancedOperator(expressionField, "log( ", " )");
					resultNumber = Math.log(resultNumber);
					showResult(resultField);
				}
			}

		}
	}

	/**
	 * The method calculateFactorial(JTextField expressionField, JTextField
	 * resultField) finds the product of all positive integers less than a given
	 * number (or a given expression). The parameters of the method are two objects
	 * of class JTextField. The first text field contains the expression which could
	 * be formed by multiple mathematical operations and functions defined by this
	 * class. The second text field contains the result.
	 * 
	 * 
	 * @param expressionField - contains the math expression
	 * @param resultField     - contains the result
	 */
	private static void calculateFactorial(JTextField expressionField, JTextField resultField) {
		if (noCalculationYet()) {
			if (expression == "") {
				resultNumber = 1;
				expression = "( 0 )!";
				expressionField.setText(expression);
				showResult(resultField);
			} else {
				resultNumber = factorial(Double.parseDouble(expressionField.getText()));
				addAdvancedOperator(expressionField, "( ", " )!");
				showResult(resultField);
			}
		} else {
			if (isOnlyOneNumberPresent()) {
				resultNumber = factorial(Double.parseDouble(expressionField.getText()));
				addAdvancedOperator(expressionField, "( ", " )!");
				showResult(resultField);
			} else {
				if (isPrecedingMathFunctionSimple()) {
					doPrecedingSimpleMathFunction(expressionField, resultField);
					addAdvancedOperator(expressionField, "( ", " )!");
					resultNumber = factorial(resultNumber);
					showResult(resultField);

				} else {
					addAdvancedOperator(expressionField, "( ", " )!");
					resultNumber = factorial(resultNumber);
					showResult(resultField);
				}
			}

		}
	}

	/**
	 * The method calculate(JTextField expressionField, JTextField resultField)
	 * evaluates a given expression. The parameters of the method are two objects of
	 * class JTextField. The first text field contains the expression which could be
	 * formed by multiple mathematical operations and functions defined by this
	 * class. The second text field contains the result.
	 * 
	 * 
	 * @param expressionField - contains the math expression
	 * @param resultField     - contains the result
	 */
	private static void calculate(JTextField expressionField, JTextField resultField) {
		if (noCalculationYet()) {
			if (expression == "") {
				resultNumber = 0;
				expression = "0";
				expressionField.setText(expression);
				showResult(resultField);
			} else {
				resultNumber = Double.parseDouble(expressionField.getText());
				showResult(resultField);
			}
		} else {
			if (isOnlyOneNumberPresent()) {
				resultNumber = Double.parseDouble(expressionField.getText());
				showResult(resultField);
			} else {
				if (areMultipleNumbersPresent()) {
					doPrecedingSimpleMathFunction(expressionField, resultField);
					expression = result;
					expressionField.setText(expression);
				} else {
					showResult(resultField);
					expression = result;
					expressionField.setText(expression);
				}
			}
		}
	}

	/**
	 * The method doBasicMath(String expression) uses switch construction to define
	 * the math basic operations performed by the EZ Calculator. The calculation of
	 * percentage is also included.
	 * 
	 * @param expression - the math expression written in the expression field
	 * @return result of math function
	 */
	private static double doBasicMath(String expression) {
		double result = 0;
		if (expression.isEmpty() == true) {
			return result;
		} else {
			double firstValue = resultNumber;
			double secondValue = Double.parseDouble(tempExpression.substring(tempExpression.lastIndexOf(" ")));
			String operator = tempExpression.substring(tempExpression.indexOf(" ") + 1,
					tempExpression.lastIndexOf(" "));

			switch (operator) {
			case "+":
				return firstValue + secondValue;
			case "-":
				return firstValue - secondValue;
			case "*":
				return firstValue * secondValue;
			case "/":
				return firstValue / secondValue;
			case "%":
				return (firstValue * secondValue) / 100;
			default:
				throw new RuntimeException("Unknown operator" + operator);
			}
		}
	}

	/**
	 * The method factorial(double value) evaluates the factorial of the integer
	 * number closest to but less than the real number given to the method as a
	 * parameter.
	 * 
	 * @param value - variable of type double, real number
	 * @return the factorial of the integer number closest to but less than the real
	 *         number given to the method as a parameter.
	 */
	private static double factorial(double value) {
		long fact = 1L;
		int intValue = (int) value;
		for (int i = 1; i <= intValue; i++) {
			fact = fact * i;
		}
		return fact;
	}

	/**
	 * Create the GUI and show it.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("EZ Calculator");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createAndAddComponentsToPane(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}

	private static void showResult(JTextField resultField) {
		result = Double.toString(resultNumber);
		resultField.setText(result);
	}

	// Add "+", "-", "*", "/" or "%"
	private static void addBasicOperator(JTextField expressionField, String operator) {
		expression += " " + operator + " ";
		expressionField.setText(expression);
	}

	// Add signs representing advanced math functions
	private static void addAdvancedOperator(JTextField expressionField, String firstPart, String secondPart) {
		expression = firstPart + expression + secondPart;
		expressionField.setText(expression);
	}

	private static void doPrecedingSimpleMathFunction(JTextField expressionField, JTextField resultField) {
		tempExpression = result + expressionField.getText().substring(expressionField.getText().lastIndexOf(" ") - 2);
		resultNumber = doBasicMath(tempExpression);
		showResult(resultField);
	}

	// This method checks if the preceding operator is '+', '-', '*', '/' or '%'.
	private static boolean isPrecedingMathFunctionSimple() {
		return expression.charAt(expression.lastIndexOf(" ") - 1) == '+'
				|| expression.charAt(expression.lastIndexOf(" ") - 1) == '-'
				|| expression.charAt(expression.lastIndexOf(" ") - 1) == '*'
				|| expression.charAt(expression.lastIndexOf(" ") - 1) == '/'
				|| expression.charAt(expression.lastIndexOf(" ") - 1) == '%';
	}

	private static boolean isLastEntryANumber() {

		return Character.isDigit(expression.charAt(expression.length() - 1));
	}

	private static boolean areMultipleNumbersPresent() {
		return isLastEntryANumber() && isPrecedingMathFunctionSimple();

	}

	private static boolean isOnlyOneNumberPresent() {
		return isLastEntryANumber() && expression.indexOf(" ") == -1;
	}

	private static boolean noCalculationYet() {
		return result == "" && expression.indexOf(" ") == -1;
	}

	private static void addFirstOperatorInExpression(JTextField expressionField, JTextField resultField,
			String operator) {
		double expressionNumber = Double.parseDouble(expressionField.getText());
		expression = expression + " " + operator + " ";
		expressionField.setText(expression);
		resultNumber = expressionNumber;
		showResult(resultField);
	}

	private static void positionButton(Container pane, JButton b, GridBagConstraints gbc, int fill, int ipadx,
			int ipady, double weightx, Insets insets, int gridx, int gridy) {
		gbc.gridwidth = fill;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		gbc.weightx = weightx;
		gbc.insets = insets;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		pane.add(b, gbc);
	}

	private static void positionTextField(Container pane, JTextField tf, GridBagConstraints gbc, int fill, int ipadx,
			int ipady, double weightx, Insets insets, int gridx, int gridy) {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = fill;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		gbc.weightx = weightx;
		gbc.insets = insets;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		pane.add(tf, gbc);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
