package edu.bhcc;
/**
 * @author A.b Almasri
 * Date: 12/14/2023
 * @version
 * 2.0
 * 
 * Class: CSC-239-04H
*/

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * MapSelectionForm: represents the graphical interface for selecting a train line
 * and choosing stations on the route.
 */
public class MapSelectionForm {

  /**
   * display: Displays the main map selection form with train line options.
   *
   * @param primaryStage The primary stage of the JavaFX application.
   */
  public static void display(Stage primaryStage) {

    VBox root = new VBox(20);
    root.setAlignment(Pos.CENTER);

    // setting the color of the background to fit the theme
    root.setBackground(
      new Background(new BackgroundFill(Color.ALICEBLUE, null, null))
    );

    // Creating the header title of the page
    Text headerTitle = new Text("Welcome!");
    headerTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: BURLYWOOD;");
    Text subText = new Text("Please choose which line you will be riding:");
    subText.setStyle("-fx-font-size: 16px;  -fx-fill: BURLYWOOD;");
    VBox headerBox = new VBox(5, headerTitle, subText);
    headerBox.setAlignment(Pos.CENTER);

    // Adding elements
    root.getChildren().add(headerBox);

    // Button for the implementation
    Button orangeLineButton = createLineButton(
      "Orange Line",
      primaryStage,
      Color.ORANGE
    );

    // Demo buttons without use
    Button demoRedButton = createDemoButton("Demo Red Line", Color.RED);
    Button demoGreenButton = createDemoButton("Demo Green Line", Color.GREEN);
    Button demoBlueButton = createDemoButton("Demo Blue Line", Color.BLUE);


    // Create the "Return Home" button
    Button returnHomeButton = new Button("Return Home");
    returnHomeButton.setStyle("-fx-background-color:BURLYWOOD ; -fx-text-fill:ALICEBLUE;");
    returnHomeButton.setOnAction(e -> displaySignUpForm(primaryStage));
    returnHomeButton.setPrefSize(150, 25);

    // Setting the button size
    orangeLineButton.setPrefSize(200, 50);
    demoRedButton.setPrefSize(200, 50);
    demoGreenButton.setPrefSize(200, 50);
    demoBlueButton.setPrefSize(200, 50);

    // Adding elements
    root
      .getChildren()
      .addAll(orangeLineButton, demoRedButton, demoGreenButton, demoBlueButton, returnHomeButton);

    // Show the scene
    Scene scene = new Scene(root, 1000, 700);
    primaryStage.setScene(scene);
  }

  /**
   * createLineButton: Creates a train line button with the specified name and color.
   * 
   * Note: I made this a different method to create a separation between my in
   * use and demo button as I implement them in the future
   *
   * @param lineName      The name of the train line.
   * @param primaryStage  The primary stage of the JavaFX application.
   * @param buttonColor   The color of the button.
   * @return The created train line button.
   */
  private static Button createLineButton(
    String lineName,
    Stage primaryStage,
    Color buttonColor
  ) {
    Button lineButton = new Button(lineName);
    lineButton.setStyle(
      "-fx-border-color: black; -fx-background-color: " +
      toRGBCode(buttonColor) +
      ";"
    );
    lineButton.setOnAction(e -> createLineMap(primaryStage, lineName));
    return lineButton;
  }

  /**
   * createDemoButton: Creates a demo button with the specified name and color.
   * Note: I made this a different method to create a separation between my in
   * use and demo button as I implement them in the future
   *
   * @param lineName      The name of the demo line.
   * @param buttonColor   The color of the button.
   * @return The created demo button.
   */
  private static Button createDemoButton(String lineName, Color buttonColor) {
    Button demoButton = new Button(lineName);
    demoButton.setStyle(
      "-fx-border-color: black; -fx-background-color: " +
      toRGBCode(buttonColor) +
      ";"
    );

    // No specific action for demo buttons. Note: This program severs as a
    // simulation so there is no need for multiple buttons but I still wanted to
    // add them to make the direction the program is heading clear 
    return demoButton;
  }

  /**
   * createLineMap: Creates a map for the selected train line and displays the station buttons.
   *
   * @param primaryStage  The primary stage of the JavaFX application.
   * @param lineName      The name of the selected train line.
   */
  private static void createLineMap(Stage primaryStage, String lineName) {

    // Creating buttons for Oak Grove and Forest Hills
    Button oakGroveButton = createStationButton(
      "Oak Grove",
      primaryStage,
      lineName
    );
    Button forestHillsButton = createStationButton(
      "Forest Hills",
      primaryStage,
      lineName
    );

    // Setting button size for better formatting 
    oakGroveButton.setPrefSize(150, 50);
    forestHillsButton.setPrefSize(150, 50);

    // Creating a VBox for the title and buttons
    VBox titleAndButtonsBox = new VBox(10);
    titleAndButtonsBox.setAlignment(Pos.CENTER);

    // Creating an HBox for the buttons
    HBox buttonsBox = new HBox(20, oakGroveButton, forestHillsButton);
    buttonsBox.setAlignment(Pos.CENTER);

    // Creating line image view for background map
    ImageView lineImageView = new ImageView(
      new Image("File:/home/developer/Desktop/orange.jpg")
    );
    lineImageView.setFitWidth(800);
    lineImageView.setFitHeight(600);

    // Creating a BorderPane for the main layout
    BorderPane root = new BorderPane();

    // Setting the lineImageView as the center of the BorderPane
    root.setCenter(lineImageView);

    // Creating an HBox for the title
    Text titleText = new Text("Which station would you like Alarmy to go off at?");
    titleText.setStyle("-fx-font-size: 18px;  -fx-fill: BURLYWOOD;");

    Circle redDot = createRedDot();

    // Creating PathTransition for the red dot to animate it
    PathTransition pathTransition = new PathTransition();
    pathTransition.setNode(redDot);
    pathTransition.setCycleCount(1); // Play once
    pathTransition.setAutoReverse(false);

    // Setting up action for Oak Grove button
    oakGroveButton.setOnAction(
      e -> {
        animateRedDot(
          primaryStage,
          root,
          redDot,
          pathTransition,
          createBlueLine(652, 370, 652, 218)
        );
      }
    );

    // Setting up action for Forest Hills button
    forestHillsButton.setOnAction(
      e -> {
        animateRedDot(
          primaryStage,
          root,
          redDot,
          pathTransition,
          createBlueLine(652, 370, 495, 527)
        );
      }
    );

    // Adding title and buttons to the VBox
    titleAndButtonsBox.getChildren().addAll(titleText, buttonsBox);

    // Setting the VBox at the top
    root.setTop(titleAndButtonsBox);

    // Showing the scene
    Scene lineScene = new Scene(root, 1000, 700);
    primaryStage.setScene(lineScene);
  }

  /**
   * animateRedDot: Animates a red dot along the specified blue line and shows an alert when the animation ends.
   *
   * @param primaryStage    The primary stage of the JavaFX application.
   * @param root            The root layout of the scene.
   * @param redDot          The red dot to be animated.
   * @param pathTransition  The path transition for the red dot animation.
   * @param blueLine        The blue line along which the red dot will move.
   */
  private static void animateRedDot(
    Stage primaryStage,
    BorderPane root,
    Circle redDot,
    PathTransition pathTransition,
    Line blueLine
  ) {
    // Creating blue line and red dot
    root.getChildren().addAll(blueLine, redDot);

    // Creating a path for the blue line
    Path path = new Path();
    path
      .getElements()
      .add(
        new javafx.scene.shape.MoveTo(
          blueLine.getStartX(),
          blueLine.getStartY()
        )
      );
    path
      .getElements()
      .add(
        new javafx.scene.shape.LineTo(blueLine.getEndX(), blueLine.getEndY())
      );

    // Setting the path for the PathTransition
    pathTransition.setPath(path);

    // Setting the duration for the transition 
    pathTransition.setDuration(Duration.seconds(5));

    // And playing the transition
    pathTransition.play();

    // Setting an event handler for when the animation ends that makes it
    // trigger he alarm
    pathTransition.setOnFinished(
      event -> {
        // Run the alert on the JavaFX application thread
        Platform.runLater(
          () -> {
            showAlert("Alarm", "WAKEUP!\tThe alarm is going off!");

            // Going back to the line selection form
            displayLineSelectionForm(primaryStage);
          }
        );
      }
    );
  }

  /**
   * createBlueLine: Creates a blue line with specified start and end coordinates.
   *
   * @param startX  The x-coordinate of the starting point.
   * @param startY  The y-coordinate of the starting point.
   * @param endX    The x-coordinate of the ending point.
   * @param endY    The y-coordinate of the ending point.
   * @return The created blue line.
   */
  private static Line createBlueLine(
    int startX,
    int startY,
    int endX,
    int endY
  ) {
    Line blueLine = new Line(startX, startY, endX, endY); 
    blueLine.setStroke(Color.BLUE);
    blueLine.setStrokeWidth(5);
    return blueLine;
  }

  /**
   * createStationButton: Creates a station button with the specified name and action for handling button click.
   * 
   * Note: I made this a separate method to implement the stations one by one.
   *
   * @param stationName   The name of the station.
   * @param primaryStage  The primary stage of the JavaFX application.
   * @param lineName      The name of the train line associated with the station.
   * @return The created station button.
   */
  private static Button createStationButton(
    String stationName,
    Stage primaryStage,
    String lineName
  ) {
    Button stationButton = new Button(stationName);
    stationButton.setOnAction(
      e -> {
        // Handle the station button click
        System.out.println("Station button clicked: " + stationName);
        // You can perform additional actions here based on the selected station
      }
    );
    return stationButton;
  }

  /**
   * createRedDot: Creates a red dot for animation.
   * 
   * Note: I made this a separate method so I can use it with multiple different
   * lines as needed
   *
   * @return The created red dot.
   */
  private static Circle createRedDot() {
    Circle redDot = new Circle(5, Color.RED);

    // Set initial position to the center of the page
    redDot.setTranslateX(552);
    redDot.setTranslateY(355);

    return redDot;
  }

  /**
   * toRGBCode: Converts a JavaFX Color object to its RGB code representation.
   * 
   * This is a method I used to color the buttons and placed here to be used in
   * different places as the program grows.
   *
   * @param color The JavaFX Color object.
   * @return The RGB code representation of the color.
   */
  private static String toRGBCode(Color color) {
    return String.format(
      "#%02X%02X%02X",
      (int) (color.getRed() * 255),
      (int) (color.getGreen() * 255),
      (int) (color.getBlue() * 255)
    );
  }

  /**
   * displayLineSelectionForm: Displays the initial line selection form.
   * 
   * I used this for returning after the alert pop up and it is a separate
   * method in order to allow for returning to the initial line selection form
   * flexibly.
   *
   * @param primaryStage The primary stage of the JavaFX application.
   */
  private static void displayLineSelectionForm(Stage primaryStage) {
    // Go back to the line selection form
    Platform.runLater(
      () -> {
        MapSelectionForm.display(primaryStage);
      }
    );
  }

  /**
 * displaySignUpForm: Displays the initial sign-up form again to give the user
 * ability to go back.
 *
 * @param primaryStage The primary stage for the application.
 */
  private static void displaySignUpForm(Stage primaryStage) {
  WelcomeForm welcomeForm = new WelcomeForm();
  welcomeForm.start(primaryStage);
}

  /**
   * showAlert: Displays The alert in a pop up.
   *
   * @param title   The title of the alert.
   * @param content The content of the alert.
   */
  private static void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText("Alarmy");
    alert.setContentText(content);
    alert.showAndWait();
  }
}