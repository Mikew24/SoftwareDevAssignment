package Code;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

import javafx.scene.shape.Polygon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class Main extends Application {
    @Override

    public void start(Stage MainStage){
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

        Player player = new Player();
        Pane p = new Pane();

        p.getChildren().addAll(player);
        Scene s=new Scene(pane);
        SceneControl a = new SceneControl(s);
        a.add("Flow",pane);
        a.switchPane("Flow");
        a.add("Game Pane",p);
        //a.switchPane("Game Pane");


        MainStage.setScene(a.getFocus());
        if(a.getFocus()==null){
            MainStage.close();
        }
        playerTasks playermove = new playerTasks(player,s,a);
        MenuTasks Menu = new MenuTasks(s,a,player,paneMenu);
        Thread playerThread = new Thread(playermove);
        Thread ObjectThread;
        Thread PauseThread;
        Thread MenuThread = new Thread(Menu);
        MainStage.show();
        MenuThread.start();


//        s.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if(event.getCode()== KeyCode.RIGHT||event.getCode()==KeyCode.D){
//                    a.switchPane("Game Pane");
//                    playerThread.start();
//                    System.out.println("SWITCHED");
//                }
//            }
//        });
//        s.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                System.out.println(event.getCode());
//                if(event.getCode()== KeyCode.RIGHT||event.getCode()==KeyCode.D){
//                    player.setLayoutX(player.getLayoutX()+1);
//                }
//            }
//        });
    }
//    public void draw(Scene s){
//
//    }
//    public void update(Scene s){
//        s.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if(event.getCode())
//            }
//        });
//    }
}
