package chapter2;

import java.util.Scanner;

public class RailFenceCipher {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plain text: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the number of key");
        int key = scanner.nextInt(); //key represents the number of rails (rows) to use in the Rail Fence cipher
        scanner.close();
         
        String ciphertext = encrypt(plaintext, key);//The encrypt method is called with plaintext and key as arguments. 
        System.out.println("Ciphertext: " + ciphertext);
        

    }
    //This line declares a static method named encrypt that takes a string text and an integer key as parameters and returns an encrypted string.
    public static String encrypt(String text, int key) {
        if (key <= 1) return text;

        //An array of StringBuilder objects is created with a size equal to the key. Each StringBuilder will hold characters for one rail.
        StringBuilder[] rail = new StringBuilder[key];
        
        //This loop initializes each element of the rail array with a new StringBuilder instance.
        for (int i = 0; i < key; i++) rail[i] = new StringBuilder();

        //Two integers are declared and initialized: dir is set to 1 to represent the direction (downwards initially), and row is set to 0 to represent the current rail.
        int dir = 1, row = 0;
        //This loop iterates over each character c in the input text
        for (char c : text.toCharArray()) {
            rail[row].append(c);//The character c is appended to the current rail (rail[row])
            if (row == 0) dir = 1;//If the current row is 0, the direction (dir) is set to 1 (moving downwards)
            if (row == key - 1) dir = -1;//If the current row is the last row (key - 1), the direction (dir) is set to -1 (moving upwards)
            row += dir;//The row is updated by adding the direction (dir), moving to the next rail
        }

        StringBuilder result = new StringBuilder();//A StringBuilder named result is created to hold the final encrypted text
        for (StringBuilder sb : rail) result.append(sb);//This loop concatenates all the StringBuilder objects in the rail array into the result
        return result.toString();//The encrypted text (result) is converted to a string and returned
    }
}

    