package chapter4;

//HashMap and Map are used to store user credentials
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AuthenticationSystem {
	//Declares a Map named users to store username-password pairs. 
    private Map<String, String> users;

    //Constructor:
    //Initializes the users field with a new HashMap.
    //Adds predefined username-password pairs to the map:
   // "admin" with password "password123".
    //"user1" with password "java".
    public AuthenticationSystem() {
        users = new HashMap<>();
        users.put("admin", "password123");
        users.put("user1", "java");
    }

    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public static void main(String[] args) {
    	//Creates an instance of AuthenticationSystem.
        AuthenticationSystem auth = new AuthenticationSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        scanner.close();

        if (auth.authenticate(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}