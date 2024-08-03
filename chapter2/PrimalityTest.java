package chapter2;

import java.util.Scanner;

public class PrimalityTest {

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number Which you want to test: ");
		int number = scanner.nextInt();
		scanner.close();
        boolean isPrime = isPrime(number);
        System.out.println(number + " is prime? " + isPrime);
    }
    
    public static boolean isPrime(int number) {
        // Edge cases- handle very small numbers
        if (number <= 1) return false;
        if (number <= 3) return true; // 2 and 3 are prime
        
        // Simple Cases- Eliminate even numbers and multiples of 3
        if (number % 2 == 0 || number % 3 == 0) return false;
        
        // Check divisibility up to the square root of the number
        //Efficient Checking further ensures that only potential prime candidates are tested, making the algorithm faster for larger numbers.
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        
        return true;
    }
}

