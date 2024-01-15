package edu.bhcc;
/**
 * @author A.b Almasri
 * Date: 12/14/2023
 * @version
 * 2.0
 *
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * A class to Represent a user profile with basic information.
 */
public class UserProfile implements Authenticatable {

  private static ArrayList<UserProfile> userProfiles = new ArrayList<>();

  // needed members
  private String firstName;
  private String lastName;
  private String userName;
  private String password;

  /**
   * UserProfile: Constructs a new UserProfile with the specified information and prints the profile to a file.
   *
   * @param firstName The first name of the user.
   * @param lastName  The last name of the user.
   * @param userName  The username chosen by the user.
   * @param password  The password chosen by the user.
   */
  public UserProfile(
    String firstName,
    String lastName,
    String userName,
    String password
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;

    // Check if the user already exists
    if (!userExists(userName)) {
      // Add the created profile to the list
      userProfiles.add(this);
      // Print the user profile to a file
      printUserProfileToFile();
    } else {
      // USED FOR DEBUGGING
      System.out.println("User already exists!");
    }
  }

  /**
   * printUserProfileToFile: Prints the user profile information to file.
   *
   * Note: I want this to act as my data base of user profiles
   *
   * @throws IOException If an I/O error occurs while writing to the file.
   */
  private void printUserProfileToFile() {
    String fileName = "AbAlmasri.txt";

    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
      // Append user profile details to the file
      writer.write(formatUserProfileDetails());
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * formatUserProfileDetails: Formats the user profile details.
   *
   * @return A formatted string containing user profile information.
   */
  private String formatUserProfileDetails() {
    StringBuilder formattedDetails = new StringBuilder();
    formattedDetails.append(firstName).append(",");
    formattedDetails.append(lastName).append(",");
    formattedDetails.append(userName).append(",");
    formattedDetails.append(password).append("\n");
    return formattedDetails.toString();
  }

  /**
   * authenticate: Authenticates the entity based on the entered username and password.
   *
   * @param enteredUserName The username entered by the user.
   * @param enteredPassword The password entered by the user.
   * @return True if the authentication is successful, false otherwise.
   */
  @Override
  public boolean authenticate(String enteredUserName, String enteredPassword) {
    // Implement authentication logic here
    return userName.equals(enteredUserName) && password.equals(enteredPassword);
  }

  /**
   * userExists: Checks if a user with the given username already exists.
   *
   * @param userName The username to check.
   * @return True if the user exists, false otherwise.
   */
  private boolean userExists(String userName) {
    for (UserProfile profile : userProfiles) {
      if (profile.getUserName().equals(userName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * getFirstName: Returns the first name of the user.
   *
   * @return The first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * getLastName: Returns the last name of the user.
   *
   * @return The last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * getUserName: Returns the username chosen by the user.
   *
   * @return The username.
   */
  public String getUserName() {
    return userName;
  }

  /**
   * getPassword: Returns the password chosen by the user.
   *
   * @return The password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * getUserProfiles: Returns the list of all user profiles.
   *
   * @return ArrayList<UserProfile> The list of user profiles.
   */
  public static ArrayList<UserProfile> getUserProfiles() {
    return userProfiles;
  }
}
