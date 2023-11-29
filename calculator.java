import java.util.Scanner;

public class Calculator {
    private static double result = 0;
    private static double lastNumber = 0;
    private static char lastOperation = ' ';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayResult();

            System.out.println("Enter a digit (0-9), operation (+, -, /, =), 'C', 'AC', '+/-' (to change sign), or '.' (for decimal point): ");
            String input = scanner.next();

            processInput(input);
        }
    }

    private static void processInput(String input) {
        char inputChar = input.charAt(0);

        if (Character.isDigit(inputChar)) {
            handleDigitInput(Character.getNumericValue(inputChar));
        } else {
            switch (inputChar) {
                case '+':
                case '-':
                case '/':
                    handleOperation(inputChar);
                    break;
                case '=':
                    calculateResult();
                    break;
                case 'C':
                    clearLastEntry();
                    break;
                case 'A':
                    clearAll();
                    break;
                case 'S':
                    changeSign();
                    break;
                case '.':
                    // Handle decimal point input
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void handleDigitInput(int digit) {
        if (result == 0) {
            result = digit;
        } else {
            result = result * 10 + digit;
        }
    }

    private static void handleOperation(char operation) {
        lastNumber = result;
        lastOperation = operation;
        result = 0;
    }

    private static void calculateResult() {
        switch (lastOperation) {
            case '+':
                result = lastNumber + result;
                break;
            case '-':
                result = lastNumber - result;
                break;
            case '/':
                if (result != 0) {
                    result = lastNumber / result;
                } else {
                    System.out.println("Cannot divide by zero. Please try again.");
                    clearAll();
                }
                break;
            default:
                // Do nothing for '=' or unsupported operations
        }
        lastOperation = ' ';
        lastNumber = 0;
    }

    private static void clearLastEntry() {
        result = lastNumber;
        lastOperation = ' ';
        lastNumber = 0;
    }

    private static void clearAll() {
        result = 0;
        lastOperation = ' ';
        lastNumber = 0;
    }

    private static void changeSign() {
        result = -result;
    }

    private static void displayResult() {
        System.out.println("Result: " + result);
    }
}

