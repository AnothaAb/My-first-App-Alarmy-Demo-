package edu.bhcc;
/**
 * @author A.b Almasri
 * Date: 12/14/2023
 * @version
 * 2.0
 * 
 * Class: CSC-239-04H
*/

/**
 * Represents an authenticatable entity, such as a user profile.
 */
public interface Authenticatable {

    /**
     * Authenticates the entity based on the entered username and password.
     *
     * @param enteredUserName The username entered by the user.
     * @param enteredPassword The password entered by the user.
     * @return True if the authentication is successful, false otherwise.
     */
    boolean authenticate(String enteredUserName, String enteredPassword);
}
