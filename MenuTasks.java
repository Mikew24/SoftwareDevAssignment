//package SoftwareDevAssignment;
package Code;
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
    private SceneControl a;
    Scene s;


    VBox btns;
    public MenuTasks(Scene menu, SceneControl a, VBox btns){
        this.menu=menu;
        this.a=a;

        this.btns=btns;

    }

    public void run(){
            btns.getChildren().get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //Invokes the switchpane method to set the scene to the game pane

                    FallingCircle fc = new FallingCircle();
                    CircleTasks ct = new CircleTasks(s,fc.getPane(),a,fc.getScoret(),fc.getLives());
                    Thread GameWindow= new Thread(ct);
                    a.add("G",fc.getPane());
                    a.switchPane("G");
                    GameWindow.start();
                    Thread.yield();
                    System.out.print("Im running");

                }
            });
        btns.getChildren().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GridPane Scores = new GridPane();
                BackgroundFill b = new BackgroundFill(Color.DARKGOLDENROD, CornerRadii.EMPTY, Insets.EMPTY);
                Scores.setBackground(new Background(b));
                File scoreFile = new File("scores.txt");
                int y =1;
                Scores.add(new Text("Name"),0,0);
                Scores.add(new Text("  "),1,0);
                Scores.add(new Text("Score"),2,0);
                if(scoreFile.exists()) {
                    try{
                        Scanner scan = new Scanner(scoreFile);
                        while(scan.hasNext()){
                            String namescore[]=scan.nextLine().split(" ");

                            Scores.add(new Text(namescore[0]),0,y);
                            Scores.add(new Text("  "),1,y);
                            Scores.add(new Text(namescore[1]),2,y);
                            y++;

                        }
                    }catch(Exception e ){

                    }
                    Scene scene = new Scene(Scores,400,200);
                    Stage ScoreStage=new Stage();
                    ScoreStage.setTitle("Scores");
                    ScoreStage.setScene(scene);
                    ScoreStage.show();
                }




            }
        });
        btns.getChildren().get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) btns.getChildren().get(3).getScene().getWindow();
                stage.close();
            }
        });
        }



}
