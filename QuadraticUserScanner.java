import java.util.Scanner;

public class QuadraticUserScanner {

    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner input = new Scanner(System.in);

        System.out.println("--- Quadratic Equation Solver ---");
        System.out.println("Form: ax² + bx + c = 0\n");

        // Loop to safely get coefficient 'a'
        double a = 0;
        while (true) {
            System.out.print("Enter coefficient a (cannot be 0): ");
            if (input.hasNextDouble()) {
                a = input.nextDouble();
                if (a != 0) {
                    break; 
                } else {
                    System.out.println("Error: 'a' cannot be zero in a quadratic equation.");
                }
            } else {
                System.out.println("Error: Please enter a valid number.");
                input.next(); // Clear the invalid input
            }
        }

        // Get coefficient 'b'
        double b = getValidDouble(input, "Enter coefficient b: ");

        // Get coefficient 'c'
        double c = getValidDouble(input, "Enter coefficient c: ");

        // Display the user's equation
        System.out.println("\nSolving: " + a + "x² + " + b + "x + " + c + " = 0");

        // Calculate the discriminant
        double discriminant = (b * b) - (4 * a * c);

        // Calculate and print the roots based on the discriminant
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.printf("Two distinct real roots: x1 = %.4f, x2 = %.4f%n", root1, root2);
        } 
        else if (discriminant == 0) {
            double root = -b / (2 * a);
            System.out.printf("One repeated real root: x = %.4f%n", root);
        } 
        else {
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            System.out.printf("Complex roots: x1 = %.4f + %.4fi, x2 = %.4f - %.4fi%n", 
                realPart, imaginaryPart, realPart, imaginaryPart);
        }

        // Close the scanner resource
        input.close();
    }

    // Helper method to make sure the user inputs a valid number for b and c
    private static double getValidDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Error: Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}
