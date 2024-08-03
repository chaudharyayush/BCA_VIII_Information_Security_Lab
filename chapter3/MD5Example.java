package chapter3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5Example {

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Plaintext: ");
		String plaintext = scanner.nextLine();
		scanner.close();
		try {
            String md5Hash = calculateMD5(plaintext);
            System.out.println("MD5 Hash: " + md5Hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    public static String calculateMD5(String input) throws NoSuchAlgorithmException {
        // Create MD5 digest instance
        MessageDigest md = MessageDigest.getInstance("MD5");
        
        // Compute the hash
        byte[] hashBytes = md.digest(input.getBytes());
        
        // Convert byte array to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        
        return sb.toString();
    }
}

