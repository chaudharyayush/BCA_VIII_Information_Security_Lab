package chapter2;


import java.util.Scanner;

public class CaesarCipher {
    public static String caesarCipher(String text, int shift) {
    	//The StringBuilder class in Java creates a mutable sequence of characters, allowing modifications (appending, inserting, deleting) without creating new objects, unlike the immutable String class.
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char charAt = text.charAt(i);
            if (Character.isLetter(charAt)) {
                if (Character.isUpperCase(charAt)) {
                    char shiftedChar = (char) (((charAt - 'A' + shift + 26) % 26) + 'A');
                    //charAt = 'C'
                    //'C' - 'A' = 67 - 65 = 2
                    //2 + 3(key) = 5
                    //5 + 26 = 31 To handle negative shifts, not necessary here but included for completeness
                    //31 % 26 = 5
                    //5 + 'A' = 5 + 65 = 70 (ASCII value of 'F')
                    //(char) 70 = 'F'
                    result.append(shiftedChar);
                } else {
                    char shiftedChar = (char) (((charAt - 'a' + shift + 26) % 26) + 'a');
                    result.append(shiftedChar);
                }
            } else {
                result.append(charAt);//If the character is not a letter (e.g., a space or punctuation), it is appended to the result without any changes.
            }
        }
        return result.toString();//This line converts the StringBuilder object to a String and returns it.
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plain text: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the number of shift: ");
        int shift = scanner.nextInt();
        scanner.close();

        // Encrypt the plaintext using Caesar cipher
        String encryptedText = caesarCipher(plaintext, shift);
        System.out.println("Encrypted: " + encryptedText);

        // Decrypt the encrypted text using Caesar cipher
        String decryptedText = caesarCipher(encryptedText, -shift);
        System.out.println("Decrypted: " + decryptedText);
    }
}
