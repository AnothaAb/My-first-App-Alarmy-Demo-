package edu.bhcc;
/**
 * @author A.b Almasri
 * Date: 12/14/2023
 * @version
 * 2.0
 * 
*/
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;

/**
 * LoginForm: Represents the login form for the Train Alarm System application.
 */
public class LoginForm extends WelcomeForm {

    //The list of user profiles loaded from the file.
    private ArrayList<UserProfile> userProfiles;

    /**
    * Constructor for the LoginForm class.
    * Initializes the LoginForm and attempts to load user profiles from the user
    * profiles file which should act as a data base.
    * If an IOException occurs during the loading process, it invokes the method to handle the exception.
    *
    * @throws IOException If an I/O error occurs during the loading of user profiles.
    */
    public LoginForm() {
        try {
        // Initialize user profiles from the file during the LoginForm creation
        this.userProfiles = loadUserProfilesFromFile("AbAlmasri.txt");
        } catch (IOException e) {
        handleUserProfileLoadException(e);
        }
}

    /**
     * display: Displays the login form.
     *
     * @param primaryStage The primary stage for the application.
     */
    public void display(Stage primaryStage) {
        // Create a new GridPane for the login form
        GridPane loginRoot = createLoginFormLayout(primaryStage);
        primaryStage.setTitle("Login - Train Alarm System");
    
        // Showing the scene
        Scene scene = new Scene(loginRoot, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * createLoginFormLayout: Creates a new form layout for the login form.
     *
     * @param primaryStage The primary stage for the application.
     * @return The GridPane representing the login form layout.
     */
    private GridPane createLoginFormLayout(Stage primaryStage) {
        // Creating the pane
        GridPane loginRoot = new GridPane();
        loginRoot.setStyle("-fx-background-color: ALICEBLUE;");
    
        // Setting constraints to center the content
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.CENTER);
        loginRoot.getColumnConstraints().addAll(columnConstraints);
    
        // Creating welcome title and adjusting the size and color
        Text welcomeTitle = new Text("Login to Alarmy");
        welcomeTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: BURLYWOOD;");
        Text subText = new Text("Enter your credentials to log in");
        subText.setStyle("-fx-font-size: 20px; -fx-fill: BURLYWOOD;");
        
        // Adding elements
        GridPane.setColumnSpan(welcomeTitle, 2);
        loginRoot.add(welcomeTitle, 0, 0);
        GridPane.setColumnSpan(subText, 2);
        loginRoot.add(subText, 0, 1);

        // Moving the title text up for better formatting
        welcomeTitle.setTranslateY(-100); 
        subText.setTranslateY(-80);
        
        // Username and Password fields
        Text usernameLabel = new Text("Username:");
        usernameLabel.setStyle("-fx-font-size: 18px; -fx-fill: BURLYWOOD;");
        loginRoot.add(usernameLabel, 0, 2);
        TextField usernameField = new TextField();
        usernameField.setPrefColumnCount(20);  
        loginRoot.add(usernameField, 1, 2);
    
        Text passwordLabel = new Text("Password:");
        passwordLabel.setStyle("-fx-font-size: 18px; -fx-fill: BURLYWOOD;");
        loginRoot.add(passwordLabel, 0, 3);
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefColumnCount(20);
        loginRoot.add(passwordField, 1, 3);
    
        // Create the login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText(), primaryStage));
        loginRoot.add(loginButton, 1, 4);
        GridPane.setColumnSpan(loginButton, 2);
        GridPane.setHalignment(loginButton, HPos.CENTER);

        // Create the sign-up button
        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> start(primaryStage));
        loginRoot.add(signUpButton, 1, 5);
        GridPane.setColumnSpan(signUpButton, 2);
        GridPane.setHalignment(signUpButton, HPos.CENTER);
    
        // Centering elements
        loginRoot.setAlignment(Pos.CENTER);
        loginRoot.setVgap(5);
    
        return loginRoot;
    }

    /**
     * handleLogin: Handles the login process.
     *
     * @param enteredUsername The username entered by the user.
     * @param enteredPassword The password entered by the user.
     * @param primaryStage    The primary stage for the application.
     */
    private void handleLogin(String enteredUsername, String enteredPassword, Stage primaryStage) {
        // Validate fields and perform login logic
        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            showAlert("Login Error", "Please enter both username and password.");
        } else {
            // Perform login logic
            if (authenticateUser(enteredUsername, enteredPassword, primaryStage)) {
                showAlert("Login Success", "Welcome, " + enteredUsername + "!");
                // Handle successful login (e.g., navigate to another screen)
            } else {
                showAlert("Login Error", "Invalid username or password.");
            }
        }
    }

    /**
     * loadUserProfilesFromFile: Loads user profiles from a file.
     *
     * @param fileName The name of the file containing user profiles.
     * @return An ArrayList of UserProfile objects loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private ArrayList<UserProfile> loadUserProfilesFromFile(String fileName) throws IOException {
        ArrayList<UserProfile> profiles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                /**
                 * LINE USED FOR DEBUGGING
                 * System.out.println("Loaded line: " + line);
                 * 
                 */
                
                // Splitting the line into components
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String firstName = parts[0].trim();
                    String lastName = parts[1].trim();
                    String userName = parts[2].trim();
                    String password = parts[3].trim();
                    // Create a UserProfile object and add it to the list
                    profiles.add(new UserProfile(firstName, lastName, userName, password));
                }
            }
        } catch (IOException e) {
            handleUserProfileLoadException(e);
        }

        return profiles;
    }

    /**
     * authenticateUser: Authenticates the user based on the entered username and password.
     *
     * @param enteredUsername The username entered by the user.
     * @param enteredPassword The password entered by the user.
     * @param primaryStage    The primary stage for the application.
     * @return True if the authentication is successful, false otherwise.
     */
    private boolean authenticateUser(String enteredUsername, String enteredPassword, Stage primaryStage) {
        for (UserProfile profile : userProfiles) {
            /**
             * LINE UED FOR DEBUGGING
             * System.out.println("Comparing: " + profile.getUserName() + " with " + enteredUsername);
             * System.out.println("Comparing: " + profile.getPassword() + " with " + enteredPassword);
            */
            if (profile.getUserName().equals(enteredUsername) && profile.getPassword().equals(enteredPassword)) {
                // Displaying the map selection page
                MapSelectionForm.display(primaryStage);
                /**
                 * LINE UED FOR DEBUGGING
                 * 
                 * System.out.println("Authentication successful for: " + enteredUsername);
                 */
                return true;
            }
        }
        System.out.println("Authentication failed for: " + enteredUsername);
        return false;
    }

    /**
     * handleUserProfileLoadException: Handles exceptions that occur during user profile loading.
     *
     * @param e The exception that occurred.
     */
    private void handleUserProfileLoadException(IOException e) {
        // Show an alert to the user
        showAlert("Error", "Unable to load user profiles. Please try again later.");
        // You can also print the exception details for debugging
        e.printStackTrace();
    }
}
