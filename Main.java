import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import java.util.*;
import java.io.*;


public class Main extends Application {

  @Override //Overrides start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane(); //Creates a border pane
    pane.setStyle("-fx-background-color: black"); //Sets background colour of pane

    //---------------------------- Title ---------------------------------------
    Text title = new Text(50, 50, "Asteroid Dodger"); //Creates title
    title.setFill(Color.WHITE); //Sets colour
    Font fontNormal = Font.font("Futura", FontWeight.NORMAL, FontPosture.REGULAR, 50); //creates font option
    title.setFont(fontNormal); //applies font to title

    //---------------------------- Buttons -------------------------------------
    Button btPlay = new Button("PLAY");
    Button btScore = new Button("SCORE");
    Button btExit = new Button("EXIT");

    //------------------- Setting Title & Buttons to Pane ----------------------
    VBox paneMenu = new VBox(20);
    paneMenu.setPadding(new Insets(15, 5, 5, 5));
    paneMenu.getChildren().addAll(title, btPlay, btScore, btExit);
    paneMenu.setAlignment(Pos.CENTER);

    pane.setCenter(paneMenu);

    //Launches game
    btPlay.setOnAction(e -> {
      //TODO
      System.out.println("Launching game ...");
    });

    //Prints previous scores
    btScore.setOnAction(e -> {
      //TODO
      System.out.println("Loading previous scores ...");
      //readScore();
    });

    //Closes menu
    btExit.setOnAction(e -> {
      System.out.println("Closing menu ...");
      primaryStage.close();
    });

    //---------------------------- Placing Scene in Stage ----------------------
    Scene scene = new Scene(pane, 500, 500);
    primaryStage.setTitle("Asteroid Dodger"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /*
  public static void readScore() throws Exception {
    try {
      // Create a File instance
      java.io.File file = new java.io.File("scores.txt");

      // Create a Scanner for the file
      Scanner input = new Scanner(file);

      // Read data from a file
      while (input.hasNext()) {
        String name = input.next();
        int score = input.nextInt();
        System.out.println(name + " " + score);
      }

      // Close the file
      input.close();
    }
    catch(Exception e){
      System.out.println("Exception caught...");
    }
  }
  */

  public static void main(String[] args) {
    launch(args);
  }
}
