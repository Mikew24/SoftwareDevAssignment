package Code;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.util.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.application.Platform;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import java.lang.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;
//import javafx.scene.control.Label;

public class fallingCircles extends Application{
    String img ="heart.jpg";
    Image life1 = new Image(new File(img).toURI().toString());
    ImageView lives[] ={new ImageView(life1),new ImageView(life1),new ImageView(life1)};

    List circles = new ArrayList();
    List circles2 = new ArrayList();
    double speed;
    double falling;
    Pane pane = new Pane();
    AnimationTimer timer;
    Timeline timeline;
    int sum = 0;
    int lifes=3;
    Text score = new Text(15,30,"Score:"+Integer.toString(sum));
    @Override
    public void start(Stage stage) throws Exception{
        //set Images in locations
        int xincrease=0;
        for(ImageView k:lives){
            k.setX(15+xincrease);
            k.setY(430);
            xincrease+=30;
        }

        //Animation loops
        speed = 1;
        falling = 100;

        //draws circles
        timeline = new Timeline(new KeyFrame(Duration.millis(falling), event -> {
            //Updating Lifes
            switch (lifes){
                case 2:
                    pane.getChildren().remove(lives[2]);
                    break;
                case 1:
                    pane.getChildren().remove(lives[1]);
                    break;
            }

            speed += falling / 5000;
            circles.add(circle());
            circles2.add(circle2());
            score.setFont(new Font(20));
            pane.getChildren().add(((Node)circles.get(circles.size() - 1)));
            pane.getChildren().add(((Node)circles2.get(circles2.size() - 1)));
            Node score =pane.getChildren().get(0);
            if(score instanceof Text){
                ((Text) score).setText("Score:"+Integer.toString(sum));
            }

        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //causes circles to fall
        timer = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                gameUpdate();
                if(lifes==0){
                    pane.getChildren().remove(lives[0]);
                    timeline.stop();
                    pane.getChildren().clear();
                }
            }
        };



        //Music
        String mf= "alien.mp3";
        Media sound = new Media(new File(mf).toURI().toString());
        MediaPlayer mp = new MediaPlayer(sound);
        mp.setAutoPlay(true);
        mp.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mp.seek(Duration.ZERO);
            }
        });
        MediaView mv = new MediaView(mp);

        //BackGrounds
        BackgroundFill b = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(b));

        //Score Intialization
        score.setFill(Color.WHITE);
        pane.getChildren().add(score);
        //adding music to pane
        pane.getChildren().add(mv);

        //Adding Hearts
        pane.getChildren().add(lives[0]);
        pane.getChildren().add(lives[1]);
        pane.getChildren().add(lives[2]);

        //Starting Animation Timer
        timer.start();
        stage.setTitle("Project Testing");
        Scene scene = new Scene(pane, 400, 600);
        stage.setScene(scene);
        stage.show();

    }


    //Creating Red Circles
    public Circle circle() {
        Circle circle = new Circle();
        circle.setLayoutX(rand(0, (int)pane.getWidth()));
        circle.setLayoutY(1);
        circle.setRadius(6);
        circle.setFill(Color.RED);
        return circle;
    }

    //Creating Blue Circles
    public Circle circle2() {
        Circle circle2 = new Circle();
        circle2.setLayoutX(rand(0, (int)pane.getWidth()));
        circle2.setLayoutY(1);
        circle2.setRadius(10);
        circle2.setFill(Color.BLUE);
        return circle2;
    }

    //random number generator
    public int rand(int min, int max) {
        return (int)(Math.random() * max + min);
    }

    //int s = 1;
    int s = 0;
    int i;
    int z;

    public void gameUpdate(){

        for(i = 0; i<circles2.size()-1;i++){
            ((Circle) circles2.get(i)).setLayoutY(((Circle) circles2.get(i)).getLayoutY() + speed + ((Circle) circles2.get(i)).getLayoutY() / 150 );
            Node n = (Node)circles2.get(i);
            n.setOnMouseEntered(event -> {
                circles2.remove(i);
                pane.getChildren().remove(n);
                sum++;
            });
            if(n.getLayoutY()>=350){
                pane.getChildren().remove(n);
                circles2.remove(i);
            }

        }
        for(i = 0; i<circles.size()-1;i++){
            ((Circle) circles.get(i)).setLayoutY(((Circle) circles.get(i)).getLayoutY() + speed + ((Circle) circles.get(i)).getLayoutY() / 150 );
            Node n = (Node)circles.get(i);
            n.setOnMouseEntered(event -> {
                lifes--;
                System.out.println("Lives:"+lifes);
//                pane.getChildren().remove(n);
//                circles.remove(z);
            });
            if(n.getLayoutY()>=350){
                pane.getChildren().remove(n);
                circles.remove(i);
            }

        }
//        for(i = 0; i < circles.size()-1; i++) {
//
//            ((Circle) circles.get(i)).setLayoutY(((Circle) circles.get(i)).getLayoutY() + speed + ((Circle) circles.get(i)).getLayoutY() / 150 );
//
//            //When mouse hits red circle
//            ((Circle) circles.get(i)).setOnMouseEntered(e->{
//
//                //implement game over screen
//            });
//
//            s = i;
//            //System.out.println(i);
//            //When mouse hits red circle
//            ((Circle) circles2.get(i)).setOnMouseEntered(e-> {
//                if (((Circle) circles.get(i)).getLayoutBounds().contains(e.getX(), e.getY())){
//                    ((Circle) circles2.get(i)).setFill(Color.BLACK);
//                    pane.getChildren().remove(i);
//
//                    //System.out.println(s);
//                    //pane.getChildren().remove(((Circle) circles2.get(i)));
//                    //circles2.remove(i);
//                    sum++;
//                }
//            });
//
//            //remove red circle(i) when it gets to the bottom of the pane
//            if(((Circle) circles.get(i)).getLayoutY() >= 350) {
//                pane.getChildren().remove( circles.get(i));
//                circles.remove(i);
//            }
//
//            //remove blue circle(i) when it gets to the bottom of the pane
//            if(((Circle) circles2.get(i)).getLayoutY() >= 350) {
//                pane.getChildren().remove( circles2.get(i));
//                circles2.remove(i);
//            }
//        }
    }

    public static void main(String[] args) {
        launch();
    }

}



























//package sample;
//
//import javafx.animation.*;
//import javafx.application.Application;
//import javafx.geometry.Bounds;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import java.util.*;
//import javafx.event.*;
//import javafx.scene.*;
//import javafx.application.Platform;
//import javafx.scene.shape.Shape;
//import javafx.scene.text.*;
//import javafx.scene.input.MouseEvent;
//import java.lang.*;
////import javafx.scene.control.Label;
//
//public class fallingCircles extends Application{
//
//    List circles = new ArrayList();
//    List circles2 = new ArrayList();
//    double speed;
//    double falling;
//    Pane pane = new Pane();
//    AnimationTimer timer;
//    Timeline timeline;
//    int sum = 1;
//
//    @Override
//    public void start(Stage stage) throws Exception{
//
//        speed = 1;
//        falling = 100;
//
//        //draws circles
//        timeline = new Timeline(new KeyFrame(Duration.millis(falling), event -> {
//            speed += falling / 5000;
//            circles.add(circle());
//            circles2.add(circle2());
//            Circle a=circle();
//            Circle b=circle2();
//            pane.getChildren().add((a));
//            pane.getChildren().add(b);
//
//        }));
//
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
//
//        //causes circles to fall
//        timer = new AnimationTimer() {
//            @Override
//            public void handle(long arg0) {
//                gameUpdate();
//            }
//        };
//        timer.start();
//
//        stage.setTitle("Project Testing");
//        Scene scene = new Scene(pane, 400, 600);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    //Creating Red Circles
//    public Circle circle() {
//        Circle circle = new Circle();
//        circle.setLayoutX(rand(0, (int)pane.getWidth()));
//        circle.setLayoutY(1);
//        circle.setRadius(6);
//        circle.setFill(Color.RED);
//        return circle;
//    }
//
//    //Creating Blue Circles
//    public Circle circle2() {
//        Circle circle2 = new Circle();
//        circle2.setLayoutX(rand(0, (int)pane.getWidth()));
//        circle2.setLayoutY(1);
//        circle2.setRadius(6);
//        circle2.setFill(Color.BLUE);
//        return circle2;
//    }
//
//    //random number generator
//    public int rand(int min, int max) {
//        return (int)(Math.random() * max + min);
//    }
//
//    //int s = 1;
//    int s = 0;
//    int i;
//
//    public void gameUpdate(){
////        for(Node n : pane.getChildren()){
////            n.setLayoutY(n.getScaleY()+speed+n.getLayoutY()/150);
////
////            n.setOnMouseEntered((e->{
////                if(n instanceof Circle){
////                    Paint C = ((Circle) n).getFill();
////                    if(C==Color.RED){
////                        pane.getChildren().remove(n);
////                    }
////                }
////            }
////                    ));
////        }
//        for(i = 0; i < circles.size()-1; i++) {
//            pane.getChildren().get(i).setLayoutY(pane.getChildren().get(i).getLayoutY() + speed + (pane.getChildren().get(i)).getLayoutY() / 150 );
//            pane.getChildren().get(i).setLayoutY(pane.getChildren().get(i).getLayoutY() + speed + (pane.getChildren().get(i)).getLayoutY() / 150 );
//
//            //When mouse hits red circle
//            ((pane.getChildren().get(i))).setOnMouseEntered(e->{
//                //GameOver();
//                //implement game over screen
//                System.out.println("true, "+i);
//
//                Node k = pane.getChildren().get(i);
//                pane.getChildren().remove(i);
//                circles.remove(i);
//                System.out.println(i);
//
//            });
//            s = i;
//            //System.out.println(i);
//            //When mouse hits blue circle
//            ((Circle) circles2.get(i)).setOnMouseEntered(e-> {
//                if (((Circle) circles.get(s)).getLayoutBounds().contains(e.getX(), e.getY())){
//                    //((Circle) circles2.get(s)).setFill(Color.BLACK);
//                    //System.out.println(s);
//                    //pane.getChildren().remove(((Circle) circles2.get(i)));
//                    //circles2.remove(i);
//                    sum++;
//
//                }
//            });
//
//            //remove red circle(i) when it gets to the bottom of the pane
//            if(((Circle) circles.get(i)).getLayoutY() >=pane.getHeight()) {
//                pane.getChildren().remove(((Circle) circles.get(i)));
//                circles.remove(i);
//            }
//
//            //remove blue circle(i) when it gets to the bottom of the pane
//            if(((Circle) circles2.get(i)).getLayoutY() >= pane.getHeight()) {
//                pane.getChildren().remove(((Circle) circles2.get(i)));
//                circles2.remove(i);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//
//}
//
