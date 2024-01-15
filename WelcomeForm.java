package edu.bhcc;
/**
 * @author A.b Almasri
 * Date: 12/14/2023
 * @version
 * .0
 * 
 * Class: CSC-239-04H
*/

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * WelcomeForm: Represents the main application for the Route Alarm System.
 * Which allows users to sign up and start their journey.
 */
public class WelcomeForm extends Application {

    /** The user profile associated with the current application. */
    private UserProfile userProfile;

    /**
     * The main for the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method for the program.
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Train Alarm System");

        // Creating sign-up form
        GridPane root = createSignUpForm(primaryStage);

        // Setting alignment to center
        root.setAlignment(Pos.CENTER);

        // Disabling window resizing
        primaryStage.setResizable(false);
        // Showing the scene
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * createSignUpForm: Creates the sign-up form page.
     *
     * @param primaryStage The primary stage for the application.
     * @return The GridPane representing the sign-up form layout.
     */
    private GridPane createSignUpForm(Stage primaryStage) {

        // Creating the pane
        GridPane root = new GridPane();

        // Setting background color
        root.setStyle("-fx-background-color: ALICEBLUE;");

        // Setting constraints to center the content
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.CENTER);

        // Adding the elements to the layout
        root.getColumnConstraints().addAll(columnConstraints);

        // Creating welcome title and adjusting the size to make it bigger and the
        // color to fit the theme
        Text welcomeTitle = new Text("Welcome to Alarmy,\n your personal travel companion");
        welcomeTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: BURLYWOOD;"); 
        Text subText = new Text("Start your journey by signing up!");
        subText.setStyle("-fx-font-size: 20px; -fx-fill: BURLYWOOD;");

        // Adding elements
        GridPane.setColumnSpan(welcomeTitle, 2);
        root.add(welcomeTitle, 0, 0);
        GridPane.setColumnSpan(subText, 2);
        root.add(subText, 0, 1);
         

        // Moving the title text up for better formatting
        welcomeTitle.setTranslateY(-100); 
        subText.setTranslateY(-80);
        
        
        Text firstNameLabel = new Text("First Name:");
        firstNameLabel.setStyle("-fx-font-size: 18px; -fx-fill: BURLYWOOD;");
        root.add(firstNameLabel, 0, 2);
        TextField firstNameField = new TextField(); 
        root.add(firstNameField, 1, 2);

        Text lastNameLabel = new Text("Last Name:");
        lastNameLabel.setStyle("-fx-font-size: 18px; -fx-fill: BURLYWOOD;");
        root.add(lastNameLabel, 0, 3);
        TextField lastNameField = new TextField();
        root.add(lastNameField, 1, 3);

        // Username, Password, and Sign Up button below
        Text usernameLabel = new Text("Username:");
        usernameLabel.setStyle("-fx-font-size: 18px; -fx-fill: BURLYWOOD;");
        root.add(usernameLabel, 0, 4);
        TextField usernameField = new TextField();
        root.add(usernameField, 1, 4);

        Text passwordLabel = new Text("Password:");
        passwordLabel.setStyle("-fx-font-size: 18px; -fx-fill: BURLYWOOD;");
        root.add(passwordLabel, 0, 5);
        PasswordField passwordField = new PasswordField();
        root.add(passwordField, 1, 5);

        

        // Creating the sign up button
        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> {
            // Validating the fields
            if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                    usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                showAlert("Error", "Please fill in all the fields.");
            } else {
                // Creating a user profile with entered information
                userProfile = new UserProfile(
                        firstNameField.getText(),
                        lastNameField.getText(),
                        usernameField.getText(),
                        passwordField.getText()
                );

                // Displaying the map selection page
                MapSelectionForm.display(primaryStage);
            }
        });

    // Creating the login button
    Button loginButton = new Button("Login");
    loginButton.setOnAction(e -> {
    // Handle login button click
    LoginForm loginForm = new LoginForm();
    loginForm.display(primaryStage);
    });

    // Adding login button
    root.add(loginButton, 1, 7);
    GridPane.setColumnSpan(loginButton, 2);
    GridPane.setHalignment(loginButton, HPos.CENTER);
        
        root.add(signUpButton, 1, 6);
        GridPane.setColumnSpan(signUpButton, 2);
        GridPane.setHalignment(signUpButton, HPos.CENTER);

        root.setVgap(5);
        

        return root;
    }

    /**
     * showAlert: Shows an alert with a message.
     *
     * @param message The message being displayed in the alert.
     */
    protected void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Alarmy");
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * getUserProfile: Retrieves the user profile associated with the application.
     *
     * @return The UserProfile object associated with the application.
     */
    protected UserProfile getUserProfile() {
        return userProfile;
    }
}








   