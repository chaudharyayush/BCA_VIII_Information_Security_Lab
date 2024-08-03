package chapter2;

import java.util.Scanner;

//import java.util.Scanner;

public class PlayFairCipher {
    private char[][] keyMatrix;

    private void generateKeyMatrix(String key) {
        keyMatrix = new char[5][5];
        key = sanitizeKey(key.toUpperCase());

        int index = 0;
        boolean[] used = new boolean[26];

        // Fill in the key
        for (char c : key.toCharArray()) {
            if (c != 'J' && !used[c - 'A']) {
                keyMatrix[index / 5][index % 5] = c;
                used[c - 'A'] = true;
                index++;
            }
        }

        // Fill in the remaining alphabets
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && !used[c - 'A']) {
                keyMatrix[index / 5][index % 5] = c;
                index++;
            }
        }
    }

    private String sanitizeKey(String key) {
        StringBuilder sanitizedKey = new StringBuilder();
        boolean[] visited = new boolean[26];

        for (char c : key.toCharArray()) {
            if (c == 'J') {
                continue;
            }
            if (!visited[c - 'A']) {
                sanitizedKey.append(c);
                visited[c - 'A'] = true;
            }
        }

        return sanitizedKey.toString();
    }

    private String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        for (int i = 0; i < plaintext.length(); i += 2) {
            char a = plaintext.charAt(i);
            char b = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';

            int row1 = -1, col1 = -1, row2 = -1, col2 = -1;
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (keyMatrix[row][col] == a) {
                        row1 = row;
                        col1 = col;
                    } else if (keyMatrix[row][col] == b) {
                        row2 = row;
                        col2 = col;
                    }
                }
            }

            if (row1 == row2) {
                ciphertext.append(keyMatrix[row1][(col1 + 1) % 5]);
                ciphertext.append(keyMatrix[row2][(col2 + 1) % 5]);
            } else if (col1 == col2) {
                ciphertext.append(keyMatrix[(row1 + 1) % 5][col1]);
                ciphertext.append(keyMatrix[(row2 + 1) % 5][col2]);
            } else {
                ciphertext.append(keyMatrix[row1][col2]);
                ciphertext.append(keyMatrix[row2][col1]);
            }
        }

        return ciphertext.toString();
    }

    private String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);

            int row1 = -1, col1 = -1, row2 = -1, col2 = -1;
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (keyMatrix[row][col] == a) {
                        row1 = row;
                        col1 = col;
                    } else if (keyMatrix[row][col] == b) {
                        row2 = row;
                        col2 = col;
                    }
                }
            }

            if (row1 == row2) {
                // Same row, move backward
                plaintext.append(keyMatrix[row1][(col1 + 4) % 5]);
                plaintext.append(keyMatrix[row2][(col2 + 4) % 5]);
            } else if (col1 == col2) {
                // Same column, move backward
                plaintext.append(keyMatrix[(row1 + 4) % 5][col1]);
                plaintext.append(keyMatrix[(row2 + 4) % 5][col2]);
            } else {
                // Different row and column, use the other corners of the rectangle
                plaintext.append(keyMatrix[row1][col2]);
                plaintext.append(keyMatrix[row2][col1]);
            }
        }

        // Remove any trailing 'X' padding characters
        if (plaintext.charAt(plaintext.length() - 1) == 'X') {
            plaintext.deleteCharAt(plaintext.length() - 1);
        }

        return plaintext.toString();
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlayFairCipher playfairCipher = new PlayFairCipher();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();
        playfairCipher.generateKeyMatrix(key);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        String ciphertext = playfairCipher.encrypt(plaintext);
        System.out.println("Encrypted text: " + ciphertext);

        String decryptedText = playfairCipher.decrypt(ciphertext);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}
