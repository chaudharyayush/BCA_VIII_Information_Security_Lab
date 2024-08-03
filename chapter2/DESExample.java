package chapter2;

import javax.crypto.Cipher; //Provides the functionality of a cryptographic cipher for encryption and decryption.
import javax.crypto.KeyGenerator; //Provides the functionality of generating keys.
import javax.crypto.SecretKey; //Represents the secret (symmetric) key
//import javax.crypto.spec.SecretKeySpec; //Used to build the secret key from a byte array

import java.util.Base64; //java.util.Base64 is a utility class in Java that provides Base64 encoding and decoding schemes. 
//Base64 is a binary-to-text encoding scheme that represents binary data in an ASCII string format by translating it into a radix-64 representation. 
//It is commonly used for encoding binary data, such as images, into text so that it can be easily transmitted over text-based protocols like HTTP.

import java.util.Scanner; //for input

public class DESExample {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Plaintext: ");
		String plaintext = scanner.nextLine();
		scanner.close();

		// Generate a key
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		SecretKey secretKey = keyGen.generateKey();

		// Encrypt the plaintext
		String ciphertext = encrypt(plaintext, secretKey);
		System.out.println("Ciphertext: " + ciphertext);

		// Decrypt the ciphertext
		String decryptedText = decrypt(ciphertext, secretKey);
		System.out.println("Decrypted text: " + decryptedText);
	}

	// This method is public and static, meaning it can be called without creating an instance of the class.
	// It takes a plaintext string and a SecretKey object as parameters.
	// It throws a generic Exception to handle various potential issues (e.g., invalid key, cipher issues).

	public static String encrypt(String plaintext, SecretKey secretKey) throws Exception {
		// Cipher is a class provided by Java's cryptography library that provides encryption and decryption functionality.
		// getInstance("DES") creates a Cipher object configured to use the DESalgorithm.
		Cipher cipher = Cipher.getInstance("DES");

		// cipher.init initializes the cipher in encryption mode (ENCRYPT_MODE) using the provided secretKey.
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		// plaintext.getBytes() converts the plaintext string into a byte array.
		// cipher.doFinal processes the byte array and performs the encryption,
		// returning the encrypted byte array (encryptedBytes).
		byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());

		// encodes the encrypted byte array into a Base64 string.
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public static String decrypt(String ciphertext, SecretKey secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
		return new String(decryptedBytes);
	}
}
