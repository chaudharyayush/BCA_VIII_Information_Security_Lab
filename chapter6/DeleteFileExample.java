package chapter6;

/**
 * This program is an example of malicious logic. It demonstrates how a file-deleting operation
 * could be used in malware to remove files from a system. In a malicious context, such a program
 * could be used to intentionally delete critical files or data, leading to disruption and data loss.
 */

import java.io.File;
import java.io.IOException;

public class DeleteFileExample {
    public static void main(String[] args) {
        String fileToDelete = "H:\\Teaching\\BCA NOTE\\Information Security\\file_TO_delete"; // Specify the path to the file

        File file = new File(fileToDelete);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
            	System.out.println("Failed to delete the file.");
                System.out.println("File path: " + fileToDelete);
                System.out.println("File exists: " + file.exists());
                System.out.println("Is file: " + file.isFile());
                System.out.println("Is directory: " + file.isDirectory());
            }
        } else {
            System.out.println("The file does not exist or the path is incorrect.");
        }

        // Pause the console to see the output
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

