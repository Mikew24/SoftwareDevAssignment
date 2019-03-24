import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Client extends Application {
  // IO streams
  DataOutputStream toServer = null;
  //DataInputStream fromServer = null;
  int score = 0;

  @Override
  public void start(Stage primaryStage) {

    BorderPane mainPane = new BorderPane();

    // Text area to display contents
    TextArea ta = new TextArea();
    mainPane.setCenter(new ScrollPane(ta));
    //mainPane.setTop(paneForTextField);

    // Create a scene and place it in the stage
    Scene scene = new Scene(mainPane, 450, 200);
    primaryStage.setTitle("Client");
    primaryStage.setScene(scene);
    primaryStage.show();

    try {

      //Create a socket to connect to the server
      Socket socket = new Socket("localhost", 8000);

      //update score
      //TO DO

      //updates score to client
      ta.appendText("Score: " + score + "\n");

      //Create an input stream to receive data from the server
      //fromServer = new DataInputStream(socket.getInputStream());

      //Create an output stream to send data to the server
      toServer = new DataOutputStream(socket.getOutputStream());

      //output final score to server
      //TO DO need to print final score to server when collision with RED
      //ball occurs
      toServer.writeInt(score);
      toServer.flush();

    }
    catch (IOException ex) {
      ta.appendText(ex.toString() + '\n');
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
