
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
@SuppressWarnings("PMD.CyclomaticComplexity")
public class EZCalculator {

	private static String expression = "";
	private static String tempExpression = "";
	private static String result = "";
	private static double resultNumber = 0;

	// Constructor
	public EZCalculator() {
		createAndShowGUI();
	}

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

		Font expressionFont = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
		Font resultFont = new Font(Font.SANS_SERIF, Font.BOLD, 22);

		// Create text fields and position them on the top of the frame
		GridBagConstraints cF1 = new GridBagConstraints();
		JTextField expressionField = new JTextField();
		expressionField.setFont(expressionFont);
		expressionField.setDisabledTextColor(Color.black);

		expressionField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();
				if ((c < '0') || (c > '9')) { // (c < '*') if we want to use '+', '-', '*' and '/'
					e.consume(); // ignore event
				}

			}
		});
		cF1.gridwidth = 6;
		positionTextField(pane, expressionField, cF1, 0, 20, 0.0, new Insets(6, 4, 4, 2), 0, 0);

		GridBagConstraints cF2 = new GridBagConstraints();
		JTextField resultField = new JTextField();
		resultField.setFont(resultFont);
		resultField.setDisabledTextColor(Color.BLACK);
		resultField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				e.consume(); // ignore event
			}
		});
		cF2.gridwidth = 6;
		positionTextField(pane, resultField, cF2, 0, 20, 0.0, new Insets(2, 4, 4, 2), 0, 1);

		// Create all buttons and position them in the frame
		GridBagConstraints cB7 = new GridBagConstraints();
		JButton b7 = new JButton("7");
		positionButton(pane, b7, cB7, 10, 10, 0.5, insetsLeft, 0, 2);

		GridBagConstraints cB8 = new GridBagConstraints();
		JButton b8 = new JButton("8");
		positionButton(pane, b8, cB8, 10, 10, 0.5, insetsMiddle, 1, 2);

		GridBagConstraints cB9 = new GridBagConstraints();
		JButton b9 = new JButton("9");
		positionButton(pane, b9, cB9, 10, 10, 0.5, insetsMiddle, 2, 2);

		GridBagConstraints cBDivide = new GridBagConstraints();
		JButton bDivide = new JButton("/");
		positionButton(pane, bDivide, cBDivide, 15, 10, 0.5, insetsMiddle, 3, 2);

		GridBagConstraints cBBackspace = new GridBagConstraints();
		JButton bBackspace = new JButton("\u2b05");
		positionButton(pane, bBackspace, cBBackspace, 0, 10, 0.5, insetsMiddle, 4, 2);

		GridBagConstraints cBCancel = new GridBagConstraints();
		JButton bCancel = new JButton("C");
		positionButton(pane, bCancel, cBCancel, 10, 10, 0.5, insetsRight, 5, 2);

		GridBagConstraints cB4 = new GridBagConstraints();
		JButton b4 = new JButton("4");
		positionButton(pane, b4, cB4, 10, 10, 0.5, insetsLeft, 0, 3);

		GridBagConstraints cB5 = new GridBagConstraints();
		JButton b5 = new JButton("5");
		positionButton(pane, b5, cB5, 10, 10, 0.5, insetsMiddle, 1, 3);

		GridBagConstraints cB6 = new GridBagConstraints();
		JButton b6 = new JButton("6");
		positionButton(pane, b6, cB6, 10, 10, 0.5, insetsMiddle, 2, 3);

		GridBagConstraints cBMultiply = new GridBagConstraints();
		JButton bMultiply = new JButton("*");
		positionButton(pane, bMultiply, cBMultiply, 0, 10, 0.5, insetsMiddle, 3, 3);

		GridBagConstraints cBFactorial = new GridBagConstraints();
		JButton bFactorial = new JButton("n!");
		positionButton(pane, bFactorial, cBFactorial, 0, 10, 0.5, insetsMiddle, 4, 3);

		GridBagConstraints cBLogarithm = new GridBagConstraints();
		JButton bLogarithm = new JButton("log");
		positionButton(pane, bLogarithm, cBLogarithm, 0, 10, 0.5, insetsRight, 5, 3);

		GridBagConstraints cB1 = new GridBagConstraints();
		JButton b1 = new JButton("1");
		positionButton(pane, b1, cB1, 10, 10, 0.5, insetsLeft, 0, 4);

		GridBagConstraints cB2 = new GridBagConstraints();
		JButton b2 = new JButton("2");
		positionButton(pane, b2, cB2, 10, 10, 0.5, insetsMiddle, 1, 4);

		GridBagConstraints cB3 = new GridBagConstraints();
		JButton b3 = new JButton("3");
		positionButton(pane, b3, cB3, 10, 10, 0.5, insetsMiddle, 2, 4);

		GridBagConstraints cbSubtract = new GridBagConstraints();
		JButton bSubtract = new JButton("-");
		positionButton(pane, bSubtract, cbSubtract, 10, 10, 0.5, insetsMiddle, 3, 4);

		GridBagConstraints cbSecondPower = new GridBagConstraints();
		JButton bSecondPower = new JButton("x\u00B2");
		positionButton(pane, bSecondPower, cbSecondPower, 0, 10, 0.5, insetsMiddle, 4, 4);

		GridBagConstraints cBSquareRoot = new GridBagConstraints();
		JButton bSquareRoot = new JButton("\u221A");
		positionButton(pane, bSquareRoot, cBSquareRoot, 0, 10, 0.5, insetsRight, 5, 4);

		GridBagConstraints cB0 = new GridBagConstraints();
		JButton b0 = new JButton("0");
		positionButton(pane, b0, cB0, 10, 10, 0.5, new Insets(2, 4, 4, 2), 0, 5);

		GridBagConstraints cBDot = new GridBagConstraints();
		JButton bDot = new JButton(".");
		positionButton(pane, bDot, cBDot, 10, 10, 0.5, insetsBottom, 1, 5);

		GridBagConstraints cBPercent = new GridBagConstraints();
		JButton bPercent = new JButton("%");
		positionButton(pane, bPercent, cBPercent, 10, 10, 0.5, insetsBottom, 2, 5);

		GridBagConstraints cbAdd = new GridBagConstraints();
		JButton bAdd = new JButton("+");
		positionButton(pane, bAdd, cbAdd, 0, 10, 0.5, insetsBottom, 3, 5);

		GridBagConstraints cBEquals = new GridBagConstraints();
		JButton bEquals = new JButton("=");
		cBEquals.gridwidth = 2;
		positionButton(pane, bEquals, cBEquals, 0, 10, 0.5, new Insets(2, 2, 4, 4), 4, 5);

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
				throw new RuntimeException("Unknown operator" + operator);// catch this somewhere???
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

	// Add signs representing math function like second power, square root,
	// logarithm and factorial.
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

	private static void positionButton(Container pane, JButton b, GridBagConstraints gbc, int ipadx, int ipady,
			double weightx, Insets insets, int gridx, int gridy) {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		gbc.weightx = weightx;
		gbc.insets = insets;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		pane.add(b, gbc);
	}

	private static void positionTextField(Container pane, JTextField tf, GridBagConstraints gbc, int ipadx, int ipady,
			double weightx, Insets insets, int gridx, int gridy) {
		gbc.fill = GridBagConstraints.HORIZONTAL;
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
