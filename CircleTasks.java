package Code;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CircleTasks implements Runnable {
    private Scene s;
    private Pane pane;
    private SceneControl a;
    Text scoret;
    ImageView lives[];

    public CircleTasks(Scene s, Pane pane, SceneControl a,Text scoret, ImageView lives[]) {
        this.s = s;
        this.pane = pane;
        this.a = a;
        this.lives=lives;
        this.scoret=scoret;
    }


    //Creating Red Circles
    public Circle circle() {
        Circle circle = new Circle();
        circle.setLayoutX(rand(0, (int) pane.getWidth()));
        circle.setLayoutY(1);
        circle.setRadius(6);
        circle.setFill(Color.RED);
        return circle;
    }

    //Creating Blue Circles
    public Circle circle2() {
        Circle circle2 = new Circle();
        circle2.setLayoutX(rand(0, (int) pane.getWidth()));
        circle2.setLayoutY(1);
        circle2.setRadius(10);
        circle2.setFill(Color.BLUE);
        return circle2;
    }

    //random number generator
    public int rand(int min, int max) {
        return (int) (Math.random() * max + min);
    }

    List circles = new ArrayList();
    List circles2 = new ArrayList();
    double speed;
    double falling;

    AnimationTimer timer;
    Timeline timeline;
    int sum = 0;
    int lifes = 3;

    int i;
    int z;
    String oof ="oof.wav";
    Media oofSound = new Media(new File(oof).toURI().toString());
    MediaPlayer playOof = new MediaPlayer(oofSound);

    String boop ="boop.wav";
    Media boopSound = new Media(new File(boop).toURI().toString());
    MediaPlayer playBoop = new MediaPlayer(boopSound);
    MediaView boopViewMedia =new MediaView(playBoop);
    MediaView oofViewMedia =new MediaView(playOof);
    public void run() {

        //pane.getChildren().addAll(oofViewMedia,boopViewMedia);
        int xincrease = 0;
        for (ImageView k : lives) {
            k.setX(15 + xincrease);
            k.setY(430);
            xincrease += 30;
        }

        //Animation loops
        speed = 1;
        falling = 100;

        //draws circles
        timeline = new Timeline(new KeyFrame(Duration.millis(falling), event -> {
            //Updating Lifes

            switch (lifes) {
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
            scoret.setFont(new Font(20));
            pane.getChildren().add(((Node) circles2.get(circles2.size() - 1)));
            pane.getChildren().add(((Node) circles.get(circles.size() - 1)));
            Node score = pane.getChildren().get(0);
            if (score instanceof Text) {
                ((Text) score).setText("Score:" + Integer.toString(sum));
            }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        timer = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                gameUpdate();
                if (lifes == 0) {
                    //Game Over stuff
                    pane.getChildren().remove(lives[0]);
                    timeline.stop();
                    pane.getChildren().clear();
                    GameOverScreen GS = new GameOverScreen();
                    GameOverTasks GameOvers = new GameOverTasks(s,a,GS.getPaneMenu(),sum);
                    Thread GO = new Thread(GameOvers);
                    a.add("GO",GS.getPane());
                    a.switchPane("GO");
                    GO.start();
                    this.stop();

                }
            }
        };
        timer.start();
    }
    public void gameUpdate(){

        for( i = 0; i<circles2.size()-1;i++){
            ((Circle) circles2.get(i)).setLayoutY(((Circle) circles2.get(i)).getLayoutY() + speed + ((Circle) circles2.get(i)).getLayoutY() / 150 );
            Node n = (Node)circles2.get(i);
            n.setOnMouseEntered(event -> {
                if(((Circle) circles2.get(i)).getFill()!=Color.BLACK) {

                    boopViewMedia.getMediaPlayer().play();
                    boopViewMedia.getMediaPlayer().seek(Duration.ZERO);
                    ((Circle) circles2.get(i)).setFill(Color.BLACK);
                    pane.getChildren().remove(n);
                    sum++;
                }
            });
            if(n.getLayoutY()>=350){


                pane.getChildren().remove(n);
                circles2.remove(i);

            }

        }
        for( z = 0; z<circles.size()-1;z++){
            ((Circle) circles.get(z)).setLayoutY(((Circle) circles.get(z)).getLayoutY() + speed + ((Circle) circles.get(z)).getLayoutY() / 150 );
            Node n = (Node)circles.get(z);
            n.setOnMouseEntered(event -> {
                oofViewMedia.getMediaPlayer().play();
                oofViewMedia.getMediaPlayer().seek(Duration.ZERO);
                lifes--;
                System.out.println("Lives:"+lifes);
            });
            if(n.getLayoutY()>=350){
                pane.getChildren().remove(n);
                circles.remove(z);
            }

        }
}

}


