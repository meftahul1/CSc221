import jdk.jshell.spi.ExecutionControl;
import java.util.Scanner;

public class Assignment1 {
    // defined a class that contains the 5 different tasks as well as the bonus task.
    public void task1() {
        int a = 5; // a is an integer that has been assigned the value of 5
        double b = 5.6; // b is a double that has been assigned the value of 5.6
        String message = "Hello Professor!"; // message of String type contains value of "Hello world!"
        char c = 'c'; // char c contains only one character which is c
        System.out.println("Task1");
        System.out.println("Integer: " + a);
        System.out.println("Double: " + b);
        System.out.println("String Message: " + message);
        System.out.println("Character: " + c);
    }

    public void task2(int age) {
        System.out.println("Task 2: Control Flow and Functions");
        if (age >= 18) {
            System.out.println("You are an adult.");
        }
        else {
            System.out.println("You are a minor.");
        }
    }

    public void task3() {
        System.out.println("Task 3: Conditional Statements and Loops (Even numbers)");
        int i;
        for (i = 1; i <= 20; i++) { // loops through 1 to 20
            if (i % 2 == 0) { // checks for even number, if the modulo is 0, it is even
                System.out.println(i);
            }
        }
    }

    public int calculateArea(int length, int width) {
        int area = length * width;
        return area;
    }

    public static long calculateFactorial(int n) { // static long contains larger values than int and double
        if (n == 0 || n == 1) { // base case
            return 1;
        }
        else { // recursion
            return n * calculateFactorial(n - 1);
        }
    }

    public int calculator(int a, int b, char c) {
        int result = 0;
        char operator = c;
        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b != 0) {
                    result = a / b;
                } else {
                    System.out.println("Error. Enter a second integer > 0");
                }
                break;
        }
        return result;

    }



    public static void main(String[] args) {
        Assignment1 assignment = new Assignment1(); // Create an instance of Assignment1
        assignment.task1(); // Call task1 on the instance
        System.out.println("Enter an age: "); // asks user to input an age
        Scanner scnr = new Scanner(System.in); // system takes in the input
        int age = scnr.nextInt(); // age equals to the value user inserted

        assignment.task2(age);

        assignment.task3();

        System.out.println("Task 4: Defining and Calculating Function (Calculate Area)");
        int area1 = assignment.calculateArea(4, 4);
        // calculates the area of the first instance, given length = 4 and width = 4

        int area2 = assignment.calculateArea(6, 3);
        int area3 = assignment.calculateArea(12,2);
        System.out.println(area1);
        System.out.println(area2);
        System.out.println(area3);

//        int factorial = ;
        System.out.println("Task 5: Parameter Passing and Return Values (calculateFactorial)");
        System.out.println(assignment.calculateFactorial(20));

//      bonus

        System.out.println("Bonus Task: Building a calculator");

        System.out.println("Enter an integer: "); // asking user to input an integer
        Scanner calculateA = new Scanner(System.in); // takes in the input
        int a = calculateA.nextInt(); //  saves input into a
        System.out.println("Enter another integer: "); // asks user to input another integer
        Scanner calculateB = new Scanner(System.in); // taking input for b
        int b = calculateB.nextInt(); // saves input in b
        System.out.println("Enter an operator (+,-,*,/)"); // asks for an operator
        Scanner operator = new Scanner(System.in);
        char c = operator.next().charAt(0);
        int bonus = assignment.calculator(a, b, c);
        System.out.println(bonus);


    }

}

