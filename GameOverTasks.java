package SoftwareDevAssignment;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameOverTasks implements Runnable {
    private Scene Gameover;
    private SceneControl a;
    private VBox Fields;
    private int score;
    public GameOverTasks(Scene Gameover, SceneControl a, VBox Fields,int score) {
        this.Fields = Fields;
        this.a = a;
        this.Gameover = Gameover;
        this.score=score;
    }

    public void run() {
//        File song = new File("bensound-newdawn.mp3");
//        System.out.println(song.exists());
//        MediaPlayer m = new MediaPlayer(new Media("file://bensound-newdawn.mp3"));
//        m.play();
        //save score
        Fields.getChildren().get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node textField = Fields.getChildren().get(2);
                if(textField instanceof TextField){
                    String name = ((TextField) textField).getText();
                    saveFile(name);
                }
            }
        });
        Fields.getChildren().get(4).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) Fields.getChildren().get(4).getScene().getWindow();
                stage.close();
            }
        });

    }

    public boolean saveFile(String name) {
        if (name.isEmpty()) {
            TextField tfName = new TextField("Please enter a Name");
            tfName.setMaxWidth(100);
            Fields.getChildren().set(2,tfName);
            return false;
        }
        else{
            File file = new java.io.File("scores.txt");

            // Create printWriter to write to file
            try {
                PrintWriter output = new PrintWriter(new FileWriter(file, true));

                // Write initials from textfield to file
                output.println(name+" "+Integer.toString(score));

                // Close the file
                output.close();
                return true;
            }catch(Exception e){return false;}

        }
    }
}
