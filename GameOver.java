package  Code;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameOver extends Application {

  @Override //Overrides start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane(); //Creates a border pane
    pane.setStyle("-fx-background-color: black"); //Sets background colour of pane

    //---------------------------- Title ---------------------------------------
    Text title = new Text(50, 50, "Game Over"); //Creates title
    title.setFill(Color.WHITE); //Sets colour
    Font fontNormal = Font.font("Futura", FontWeight.NORMAL, FontPosture.REGULAR, 50); //creates font option
    title.setFont(fontNormal); //applies font to title

    //------------------------ Save Score Instructions -------------------------
    Text saveInstructions = new Text(50, 50, "Enter your initials below");
    saveInstructions.setFill(Color.WHITE); //Sets colour
    Font fontNormal2 = Font.font("Futura", FontWeight.NORMAL, FontPosture.REGULAR, 25); //creates font option
    saveInstructions.setFont(fontNormal2); //applies font

    //---------------------------- Buttons -------------------------------------
    Button btSaveScore = new Button("SAVE SCORE");
    Button btExit = new Button("EXIT");

    //---------------------------- Textfield -----------------------------------
    TextField tfName = new TextField();
    tfName.setMaxWidth(100);

    //------------------- Setting Title & Buttons to Pane ----------------------
    VBox paneMenu = new VBox(20);
    paneMenu.setPadding(new Insets(15, 5, 5, 5));
    paneMenu.getChildren().addAll(title, saveInstructions, tfName, btSaveScore, btExit);
    paneMenu.setAlignment(Pos.CENTER);

    pane.setCenter(paneMenu);

    //Prints previous scores
    btSaveScore.setOnAction(e -> {
      System.out.println("Saving score ...");

      try {
        writeScore(tfName.getText());
      }
      catch (IOException err) {
        System.out.println("ERROR");
      }
    });

    //Closes menu
    btExit.setOnAction(e -> {
      System.out.println("Closing game ...");
      primaryStage.close();
    });

    //---------------------------- Placing Scene in Stage ----------------------
    Scene scene = new Scene(pane, 500, 500);
    primaryStage.setTitle("Asteroid Dodger"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  public static void writeScore(String name) throws IOException {
      // Create a File instance
      File file = new java.io.File("scores.txt");

      // Create printWriter to write to file
      PrintWriter output = new PrintWriter(new FileWriter(file, true));

      // Write initials from textfield to file
      output.println(name);

      // Close the file
      output.close();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
