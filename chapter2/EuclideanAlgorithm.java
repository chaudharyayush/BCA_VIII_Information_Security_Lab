package chapter2;

import java.util.Scanner;

public class EuclideanAlgorithm {

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the First number: ");
		int number1 = scanner.nextInt();
		System.out.println("Enter the Second number: ");
		int number2 = scanner.nextInt();
		scanner.close();

        int gcd = gcd(number1, number2);
        System.out.println("GCD of " + number1 + " and " + number2 + " is: " + gcd);
    }

    //The function gcd(int a, int b) uses the iterative form of the Euclidean Algorithm.
    //While b is not 0, it repeatedly updates a and b such that a becomes b and b becomes the remainder of a divided by b.
    //When b becomes 0, a contains the GCD of the original a and b.
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

