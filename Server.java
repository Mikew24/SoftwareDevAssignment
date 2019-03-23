import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application {

  TextArea ta = new TextArea();

  @Override
  public void start(Stage primaryStage) {

    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 450, 200);
    primaryStage.setTitle("Server");
    primaryStage.setScene(scene);
    primaryStage.show();

    new Thread( () -> {
      try {
        //Creating socket server
    	  ServerSocket serverSocket = new ServerSocket(8000);

        Platform.runLater(() -> ta.appendText("Server started at " + new Date() + '\n'));

        while (true) {
          Socket socket = serverSocket.accept();
          new Thread(new HandleAClient(socket)).start();
        }
      }
      catch(IOException ex) {
        ex.printStackTrace();
      }
    }).start();
  }

  //Handle multiple clients
  class HandleAClient implements Runnable {
    private Socket socket;

    public HandleAClient(Socket socket) {
      this.socket = socket;
    }

    public void run() {
      try {

        DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
        DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

        while (true) {
          //read score from clent
          int score = inputFromClient.readInt();

          //display final score on server
          Platform.runLater(() -> {
            ta.appendText("Your score is: " + score + "\n");
          });
        }
      }
      catch(IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
