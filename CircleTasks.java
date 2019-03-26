package SoftwareDevAssignment;

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
    private Scene scene;
    private Pane pane;
    private SceneControl sceneCtrl;
    Text scoreText;
    ImageView lives[];

    public CircleTasks(Scene scene, Pane pane, SceneControl sceneCtrl,Text scoreText, ImageView lives[]) {
        this.scene = scene;
        this.pane = pane;
        this.sceneCtrl = sceneCtrl;
        this.lives = lives;
        this.scoreText = scoreText;
    }

    //Creating Red Circles
    public Circle redCircle() {
        Circle redCircle = new Circle();
        redCircle.setLayoutX(rand(0, (int) pane.getWidth()));
        redCircle.setLayoutY(1);
        redCircle.setRadius(6);
        redCircle.setFill(Color.RED);
        return redCircle;
    }

    //Creating Blue Circles
    public Circle blueCircle() {
        Circle blueCircle = new Circle();
        blueCircle.setLayoutX(rand(0, (int) pane.getWidth()));
        blueCircle.setLayoutY(1);
        blueCircle.setRadius(10);
        blueCircle.setFill(Color.BLUE);
        return blueCircle;
    }

    //Random number generator
    public int rand(int min, int max) {
        return (int) (Math.random() * max + min);
    }

    List redCircles = new ArrayList();
    List blueCircles = new ArrayList();

    double speed;
    double falling;

    AnimationTimer timer;
    Timeline timeline;
    int sum = 0;
    int lifes = 3;

    //Used in for loops in gameUpdate() method
    int i;
    int j;

    //oof sound
    String oof ="oof.wav";
    Media oofSound = new Media(new File(oof).toURI().toString());
    MediaPlayer playOof = new MediaPlayer(oofSound);

    //boop sound
    String boop ="boop.wav";
    Media boopSound = new Media(new File(boop).toURI().toString());
    MediaPlayer playBoop = new MediaPlayer(boopSound);
    MediaView boopViewMedia = new MediaView(playBoop);
    MediaView oofViewMedia = new MediaView(playOof);

    public void run() {
        int xincrease = 0;
        for (ImageView k : lives) {
            k.setX(15 + xincrease);
            k.setY(430);
            xincrease += 30;
        }

        //Initial speed of circles
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

            //Speed increases as game continues running
            speed += falling / 5000;

            //Adding circles to a list
            redCircles.add(redCircle());
            blueCircles.add(blueCircle());

            scoreText.setFont(new Font(20));

            //Add circles to pane
            pane.getChildren().add(((Node) blueCircles.get(blueCircles.size() - 1)));
            pane.getChildren().add(((Node) redCircles.get(redCircles.size() - 1)));

            //Update Score on screen
            Node score = pane.getChildren().get(0);
            if (score instanceof Text) {
                ((Text) score).setText("Score:" + Integer.toString(sum));
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //Animate circles falling
        timer = new AnimationTimer() {
            @Override
            public void handle(long arg0) {

                gameUpdate();

                //When game is over (all lives lost)
                if (lifes == 0) {
                    pane.getChildren().remove(lives[0]);
                    timeline.stop();
                    pane.getChildren().clear();
                    GameOverScreen GS = new GameOverScreen();
                    GameOverTasks GameOvers = new GameOverTasks(scene, sceneCtrl, GS.getPaneMenu(), sum);
                    Thread GO = new Thread(GameOvers);
                    sceneCtrl.add("GO",GS.getPane());
                    sceneCtrl.switchPane("GO");
                    GO.start();
                    this.stop();
                }
            }
        };
        timer.start();
    }

    //Method to update game
    public void gameUpdate(){

        //Loop dedicated to blue circles
        for( i = 0; i < blueCircles.size()-1; i++){
            //Update circle location (circle falling)
            ((Circle) blueCircles.get(i)).setLayoutY(((Circle) blueCircles.get(i)).getLayoutY() + speed + ((Circle) blueCircles.get(i)).getLayoutY() / 150 );

            Node n = (Node)blueCircles.get(i);

            //When mouse hits blue circle, boop sound is played, blue circle disappears, and score is increased
            n.setOnMouseEntered(event -> {
                if(((Circle) blueCircles.get(i)).getFill()!=Color.BLACK) {
                    boopViewMedia.getMediaPlayer().play();
                    boopViewMedia.getMediaPlayer().seek(Duration.ZERO);
                    ((Circle) blueCircles.get(i)).setFill(Color.BLACK);
                    pane.getChildren().remove(n);
                    sum++;
                }
            });

            //Blue circles get removed if they are not hit and fall out of bounds
            if(n.getLayoutY() >= 350){
                pane.getChildren().remove(n);
                blueCircles.remove(i);
            }
        }

        //Loop dedicated to blue circles
        for(j = 0; j < redCircles.size()-1; j++){
            //Update circle location (circle falling)
            ((Circle) redCircles.get(j)).setLayoutY(((Circle) redCircles.get(j)).getLayoutY() + speed + ((Circle) redCircles.get(j)).getLayoutY() / 150 );
            Node n = (Node)redCircles.get(j);

            //When mouse hits red circle, oof sound plays and lives decreases
            n.setOnMouseEntered(event -> {
                oofViewMedia.getMediaPlayer().play();
                oofViewMedia.getMediaPlayer().seek(Duration.ZERO);
                lifes--;
                System.out.println("Lives:" + lifes);
            });

            //Red circles get removed when they fall out of bounds
            if(n.getLayoutY() >= 350){
                pane.getChildren().remove(n);
                redCircles.remove(j);
            }

        }
    }
}
