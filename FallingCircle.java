package SoftwareDevAssignment;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;

public class FallingCircle {
    private Scene scene;
    private Pane pane;
    private Text scoreText;
    private ImageView[] lives;

    public FallingCircle(){
        this.setPane(new Pane());
        Pane pane = this.getPane();

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

        //Creating hearts
        String img = "heart.jpg";
        Image life1 = new Image(new File(img).toURI().toString());
        ImageView lives[] = {new ImageView(life1), new ImageView(life1), new ImageView(life1)};
        this.setLives(lives);

        //BackGrounds
        BackgroundFill b = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(b));
        int sum = 0;
        Text scoreText = new Text(15, 30, "Score:" + Integer.toString(sum));
        this.setScoreText(scoreText);

        //Score Intialization
        scoreText.setFill(Color.WHITE);
        pane.getChildren().add(scoreText);

        //Adding music to pane
        pane.getChildren().add(mv);

        //Adding Hearts
        pane.getChildren().add(lives[0]);
        pane.getChildren().add(lives[1]);
        pane.getChildren().add(lives[2]);
        this.setPane(pane);
    }


    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Text getScoreText() {
        return scoreText;
    }

    public void setScoreText(Text scoreText) {
        this.scoreText = scoreText;
    }

    public ImageView[] getLives() {
        return lives;
    }

    public void setLives(ImageView[] lives) {
        this.lives = lives;
    }
}
