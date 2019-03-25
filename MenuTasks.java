package Code;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
