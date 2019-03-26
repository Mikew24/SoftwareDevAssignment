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
    private Scene s;
    private Pane pane;
    private Text scoret;
    private ImageView[] lives;
    public FallingCircle(){
        this.setPane(new Pane());
        Pane p =this.getPane();
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

        String img = "heart.jpg";
        Image life1 = new Image(new File(img).toURI().toString());
        ImageView lives[] = {new ImageView(life1), new ImageView(life1), new ImageView(life1)};
        this.setLives(lives);
        //BackGrounds
        BackgroundFill b = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        p.setBackground(new Background(b));
        int sum = 0;
        Text scoret = new Text(15, 30, "Score:" + Integer.toString(sum));
        this.setScoret(scoret);
        //Score Intialization
        scoret.setFill(Color.WHITE);
        p.getChildren().add(scoret);
        //adding music to pane
        p.getChildren().add(mv);

        //Adding Hearts
        p.getChildren().add(lives[0]);
        p.getChildren().add(lives[1]);
        p.getChildren().add(lives[2]);
        this.setPane(p);
        //this.setS(new Scene(getPane(),400,600));


    }


    public Scene getS() {
        return s;
    }

    public void setS(Scene s) {
        this.s = s;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Text getScoret() {
        return scoret;
    }

    public void setScoret(Text scoret) {
        this.scoret = scoret;
    }

    public ImageView[] getLives() {
        return lives;
    }

    public void setLives(ImageView[] lives) {
        this.lives = lives;
    }
}
