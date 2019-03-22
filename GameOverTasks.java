package Code;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GameOverTasks implements Runnable {
    private Scene Gameover;
    private SceneControl a;
    private VBox Fields;

    public GameOverTasks(Scene Gameover, SceneControl a, VBox Fields) {
        this.Fields = Fields;
        this.a = a;
        this.Gameover = Gameover;
    }

    public void run() {
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
                output.println(name);

                // Close the file
                output.close();
                return true;
            }catch(Exception e){return false;}

        }
    }
}