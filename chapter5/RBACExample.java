package chapter5;

import java.util.*;

//Enum for defining roles
//An enum (short for "enumeration") in Java is a special data type that represents a group of constants (fixed set of related values). It is used to define a variable that can hold a predefined set of values. Enums are useful when you need a variable to have one of a specific set of predefined values, rather than just any value.
enum Role {
 ADMIN,
 MANAGER,
 EMPLOYEE
}

//Class to represent User with roles
class User {
 private String username;
 
 //A Set of Role enums representing the roles assigned to the user.
 private Set<Role> roles;

 //Constructor that initializes the username and creates an empty HashSet for roles.
 public User(String username) {
     this.username = username;
     this.roles = new HashSet<>();
 }

 //Returns the username of the user.
 public String getUsername() {
     return username;
 }

 //Returns the set of roles assigned to the user.
 public Set<Role> getRoles() {
     return roles;
 }

 //Adds a role to the user’s set of roles
 public void addRole(Role role) {
     roles.add(role);
 }

 //Removes a role from the user’s set of roles
 public void removeRole(Role role) {
     roles.remove(role);
 }
}

//Class to manage access control
class AccessControlManager {

	//A map that associates each Role with a set of permissions (as strings) that the role has.
 private Map<Role, Set<String>> permissions;

 //Constructor that initializes the permissions map with predefined permissions for each role
 public AccessControlManager() {
     permissions = new HashMap<>();
     permissions.put(Role.ADMIN, new HashSet<>(Arrays.asList("read", "write", "delete")));
     permissions.put(Role.MANAGER, new HashSet<>(Arrays.asList("read", "write")));
     permissions.put(Role.EMPLOYEE, new HashSet<>(Collections.singletonList("read")));
 }

 //Checks if a user has a specific permission
 public boolean hasAccess(User user, String permission) {
	 
	 //Retrieves the user's roles
     Set<Role> userRoles = user.getRoles();
     
     //Iterates through the user's roles.
     //For each role, checks if the role has the specified permission.
     //Returns true if any role has the permission, otherwise false.
     for (Role role : userRoles) {
         Set<String> rolePermissions = permissions.get(role);
         if (rolePermissions != null && rolePermissions.contains(permission)) {
             return true;
         }
     }
     return false;
 }
}

public class RBACExample {
 public static void main(String[] args) {
     // Create users and assign roles
     User adminUser = new User("admin");
     adminUser.addRole(Role.ADMIN);

     User managerUser = new User("manager");
     managerUser.addRole(Role.MANAGER);

     User employeeUser = new User("employee");
     employeeUser.addRole(Role.EMPLOYEE);

     // Access control manager instance
     AccessControlManager acManager = new AccessControlManager();

     // Test access
     testAccess(acManager, adminUser);
     testAccess(acManager, managerUser);
     testAccess(acManager, employeeUser);
 }

 public static void testAccess(AccessControlManager acManager, User user) {
     System.out.println("User: " + user.getUsername());
     System.out.println("Has access to delete? " + acManager.hasAccess(user, "delete"));
     System.out.println("Has access to write? " + acManager.hasAccess(user, "write"));
     System.out.println("Has access to read? " + acManager.hasAccess(user, "read"));
     System.out.println();
 }
}
