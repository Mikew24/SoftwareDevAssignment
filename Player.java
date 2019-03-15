package Code;

import java.awt.*;
import java.awt.event.ActionEvent;

import javafx.scene.shape.Polygon;

public class Player extends Polygon {

    private double x;
    private double y;
    private Point cord=new Point();

    public Player() {
        this.getPoints().addAll(
                50.0d, 150.0d,
                100.0d, 150.0d,
                75.0d, 90.0d);
        this.x  = this.getLayoutX();
        this.y = this.getLayoutY();
        cord.setLocation(x,y);
    }
    public void move(String input){
        if(input.equals("RIGHT")){
            this.setLayoutX(this.getLayoutX()+5);
            System.out.println("RIGHT");
        }
        if(input.equals("LEFT")){
            this.setLayoutX(this.getLayoutX()-5);
            System.out.println("LEFT");
        }
        if(input.equals("UP")){
            this.setLayoutY(this.getLayoutY()-5);
            System.out.println("UP");
        }
        if(input.equals("DOWN")){
            this.setLayoutY(this.getLayoutY()+5);
            System.out.println("DOWN");
        }


        if(input.equals("UR")){
            this.setLayoutY(this.getLayoutY()-5);
            this.setLayoutX(this.getLayoutX()+5);
            System.out.println("UPRIGHT");
        }
        if(input.equals("UL")){
            this.setLayoutY(this.getLayoutY()-5);
            this.setLayoutX(this.getLayoutX()-5);
            System.out.println("UPLEFT");

        }
        if(input.equals("DR")){
            this.setLayoutY(this.getLayoutY()+5);
            this.setLayoutX(this.getLayoutX()+5);
            System.out.println("DOWNRIGHT");
        }
        if(input.equals("DL")){
            this.setLayoutY(this.getLayoutY()+5);
            this.setLayoutX(this.getLayoutX()-5);
            System.out.println("DOWNLEFT");
        }

    }
}
