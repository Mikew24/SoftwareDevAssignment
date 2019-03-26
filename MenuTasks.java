package SoftwareDevAssignment;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.text.Text;
import java.io.File;
import java.util.Scanner;

public class MenuTasks implements Runnable {
    private Scene menu;
    private SceneControl sceneCtrl;
    Scene scene;

    VBox btns;
    public MenuTasks(Scene menu, SceneControl sceneCtrl, VBox btns){
        this.menu = menu;
        this.sceneCtrl = sceneCtrl;
        this.btns = btns;
    }

    public void run(){
        //Start game when "PLAY" button is pressed
        btns.getChildren().get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Invokes the switchpane method to set the scene to the game pane
                FallingCircle fc = new FallingCircle();
                CircleTasks ct = new CircleTasks(scene, fc.getPane(), sceneCtrl, fc.getScoreText(), fc.getLives());
                Thread GameWindow = new Thread(ct);
                sceneCtrl.add("G", fc.getPane());
                sceneCtrl.switchPane("G");
                GameWindow.start();
                Thread.yield();
            }
        });
        //Display scores stored in "scores.txt" when "SCORES" button is pressed
        btns.getChildren().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Displays score in new window
                GridPane Scores = new GridPane();
                BackgroundFill b = new BackgroundFill(Color.DARKGOLDENROD, CornerRadii.EMPTY, Insets.EMPTY);
                Scores.setBackground(new Background(b));
                File scoreFile = new File("scores.txt");
                int y = 1;
                Scores.add(new Text("Name"), 0, 0);
                Scores.add(new Text("  "), 1, 0);
                Scores.add(new Text("Score"), 2, 0);
                if(scoreFile.exists()){
                    try{
                        Scanner scan = new Scanner(scoreFile);
                        //Read names and scores from file (scores.txt)
                        while(scan.hasNext()){
                            String namescore[] = scan.nextLine().split(" ");

                            Scores.add(new Text(namescore[0]), 0, y);
                            Scores.add(new Text("  "), 1, y);
                            Scores.add(new Text(namescore[1]), 2, y);

                            y++;

                        }
                    }catch(Exception e ){}

                    Scene scene = new Scene(Scores,400,200);
                    Stage ScoreStage = new Stage();
                    ScoreStage.setTitle("Scores");
                    ScoreStage.setScene(scene);
                    ScoreStage.show();
                }
            }
        });

        //Exit game when "Exit" button is pressed
        btns.getChildren().get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) btns.getChildren().get(3).getScene().getWindow();
                stage.close();
            }
        });
    }
}
