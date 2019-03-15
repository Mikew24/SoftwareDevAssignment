package Code;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input .KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class playerTasks implements Runnable {
    private Player p;
    private Scene inFrame;
    private SceneControl a;
    public playerTasks(Player p, Scene inFrame,SceneControl a){
        this.p = p;
        this.inFrame=inFrame;
        this.a=a;
    }

    public void run(){
        BooleanProperty u = new SimpleBooleanProperty();
        BooleanProperty d = new SimpleBooleanProperty();
        BooleanProperty r = new SimpleBooleanProperty();
        BooleanProperty l = new SimpleBooleanProperty();
        BooleanBinding ur = u.and(r);
        BooleanBinding ul = u.and(l);
        BooleanBinding dl = d.and(l);
        BooleanBinding dr = d.and(r);
            inFrame.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if(ur.get()==true)p.move("UR");
                    if(ul.get()==true)p.move("UL");
                    if(dl.get()==true)p.move("DL");
                    if(dr.get()==true)p.move("DR");
                    if(event.getCode()== KeyCode.RIGHT||event.getCode()==KeyCode.D){
                        r.set(true);
                        p.move("RIGHT");
                    }
                    if(event.getCode()== KeyCode.LEFT||event.getCode()==KeyCode.A){
                        l.set(true);
                        p.move("LEFT");
                    }
                    if(event.getCode()== KeyCode.DOWN||event.getCode()==KeyCode.S){
                        d.set(true);
                        p.move("DOWN");

                    }
                    if(event.getCode()== KeyCode.UP||event.getCode()==KeyCode.W){
                        u.set(true);
                        p.move("UP");
                    }
                    if(event.getCode()== KeyCode.ESCAPE){
                        Thread.interrupted();
                       a.switchPane("Flow");
                    }
                }
            });
            inFrame.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if(event.getCode()== KeyCode.RIGHT||event.getCode()==KeyCode.D){
                        r.set(false);
                    }
                    if(event.getCode()== KeyCode.LEFT||event.getCode()==KeyCode.A){
                        l.set(false);
                    }
                    if(event.getCode()== KeyCode.DOWN||event.getCode()==KeyCode.S){
                        d.set(false);
                    }
                    if(event.getCode()== KeyCode.UP||event.getCode()==KeyCode.W){
                        u.set(false);
                    }
                }
            });

//            else if(u.get()==true)p.move("UP");
//            else if(d.get()==true)p.move("DOWN");
//            else if(r.get()==true)p.move("RIGHT");
//            else if(l.get()==true)p.move("LEFT");

    }
}
