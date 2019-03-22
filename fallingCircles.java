package Code;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.application.Platform;
import javafx.scene.shape.Shape;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;
import java.lang.*;
//import javafx.scene.control.Label;

public class fallingCircles extends Application{

  List circles = new ArrayList();
  List circles2 = new ArrayList();
  double speed;
  double falling;
  Pane pane = new Pane();
  AnimationTimer timer;
  Timeline timeline;
  Rectangle block = new Rectangle(150, 550.0, 100.0, 50.0);

  Text t = new Text();

  @Override
  public void start(Stage stage) throws Exception{

    //test object for collision
    //block.setFill(Color.BLACK);
    //pane.getChildren().add(block);

    speed = 1;
    falling = 100;

    //draws circles
    timeline = new Timeline(new KeyFrame(Duration.millis(falling), event -> {
      speed += falling / 5000;
      circles.add(circle());
      circles2.add(circle2());
      pane.getChildren().add(((Node)circles.get(circles.size() - 1)));
      pane.getChildren().add(((Node)circles2.get(circles2.size() - 1)));

    }));

    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();

    //causes circles to fall
    timer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				gameUpdate();
			}
		};
		timer.start();

    stage.setTitle("Project Testing");
    Scene scene = new Scene(pane, 400, 600);
    stage.setScene(scene);
    stage.show();
  }

  //Creating a circle
  public Circle circle() {
     Circle circle = new Circle();
     circle.setLayoutX(rand(0, (int)pane.getWidth()));
     circle.setLayoutY(1);
     circle.setRadius(6);
     circle.setFill(Color.RED);
     return circle;
  }

  public Circle circle2() {
     Circle circle2 = new Circle();
     circle2.setLayoutX(rand(0, (int)pane.getWidth()));
     circle2.setLayoutY(1);
     circle2.setRadius(6);
     circle2.setFill(Color.BLUE);
     return circle2;
  }

  //random number generator
  public int rand(int min, int max) {
       return (int)(Math.random() * max + min);
   }

   //int s = 1;
   public void gameUpdate(){
     for(int i = 0; i < circles.size(); i++) {
      ((Circle) circles.get(i)).setLayoutY(((Circle) circles.get(i)).getLayoutY() + speed + ((Circle) circles.get(i)).getLayoutY() / 150 );
      ((Circle) circles2.get(i)).setLayoutY(((Circle) circles2.get(i)).getLayoutY() + speed + ((Circle) circles2.get(i)).getLayoutY() / 150 );

      //testing what happens when mouse intersects with shape
      /*((Circle) circles2.get(i)).setOnMouseEntered(new EventHandler<MouseEvent>() {
          //@Override
          public void handle(MouseEvent e) {
          pane.getChildren().remove(((Circle) circles2.get(i)));
          circles2.remove(i);
          }
      });*/

      //testing collision event
      ((Circle) circles.get(i)).setOnMouseEntered(e->{
          //@Override
          //public void handle(MouseEvent e) {
            //System.out.println("game over");
            System.exit(0);

      });

      //remove circle(s) when it gets to the bottom of the pane
      if(((Circle) circles.get(i)).getLayoutY() >= pane.getHeight()) {
        pane.getChildren().remove(((Circle) circles.get(i)));
        circles.remove(i);
      }

      if(((Circle) circles2.get(i)).getLayoutY() >= pane.getHeight()) {
        pane.getChildren().remove(((Circle) circles2.get(i)));
        circles2.remove(i);
      }
    }
  }

  public static void main(String[] args) {
      launch();
  }

}
