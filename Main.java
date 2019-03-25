package Code;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
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


        Menu m = new Menu();
        BorderPane pane = m.getPane();
        VBox paneMenu = m.getPaneMenu();

        Pane p = new Pane();
        //BorderPane Gameover =
        Scene s=new Scene(pane,400,600);
        SceneControl a = new SceneControl(s);
        a.add("Flow",pane);
        a.switchPane("Flow");
        a.add("Game Pane",p);
        //Gameover//



        MenuTasks Menu = new MenuTasks(s,a,paneMenu);
      //  Thread playerThread = new Thread(playermove);
        Thread ObjectThread;
        Thread PauseThread;
        Thread MenuThread = new Thread(Menu);
        MenuThread.start();
       // goThread.start();
        MainStage.setScene(a.getFocus());
        MainStage.show();
        //MenuThread.start();


    }
}
