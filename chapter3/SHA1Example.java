package chapter3;

import java.security.MessageDigest; //This import provides the MessageDigest class, which allows us to compute hash values using various algorithms like SHA-1.
import java.security.NoSuchAlgorithmException; //This import provides the NoSuchAlgorithmException class, which is thrown if the requested cryptographic algorithm is not available in the environment.
import java.util.Scanner;

public class SHA1Example {

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Plaintext: ");
		String plaintext = scanner.nextLine();
		scanner.close();
        try {
        	//Calls the generateSHA1 method to generate the SHA-1 hash of the plaintext string
            String hash = generateSHA1(plaintext);
            System.out.println("SHA-1 Hash: " + hash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String generateSHA1(String input) throws NoSuchAlgorithmException {
        //Creates a MessageDigest instance for the SHA-1 algorithm. If SHA-1 is not available, this line throws a NoSuchAlgorithmException.
    	MessageDigest md = MessageDigest.getInstance("SHA-1");
        
    	//Computes the SHA-1 hash of the input string by first converting the string to bytes and then passing those bytes to the digest method.
    	byte[] messageDigest = md.digest(input.getBytes());

        // Convert byte array into signum representation
        StringBuilder sb = new StringBuilder();
        
        //Iterates over each byte in the messageDigest array, formats it as a two-digit hexadecimal string, and appends it to the StringBuilder
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString(); // Converts the StringBuilder content to a string and returns it.
    }
}
