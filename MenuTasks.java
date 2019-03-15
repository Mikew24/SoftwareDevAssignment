package Code;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuTasks implements Runnable {
    private Scene menu;
    private SceneControl a;
    Scene s;
    Player p;

    VBox btns;
    public MenuTasks(Scene menu, SceneControl a, Player p, VBox btns){
        this.menu=menu;
        this.a=a;
        this.p=p;
        this.btns=btns;

    }

    public void run(){
            btns.getChildren().get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    a.switchPane("Game Pane");
                    playerTasks playertask = new playerTasks(p,a.getFocus(),a);
                    Thread pthread=new Thread(playertask);
                    pthread.start();
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
                a.kill();
                Thread.yield();
                System.out.print("Im running");

            }
        });
        }



}
