package SoftwareDevAssignment;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class SceneControl {
    HashMap<String,Pane> Panelist = new HashMap<String, Pane>();
    private Scene Focus;
    Pane focusPane = new Pane();

    public SceneControl(Scene Focus){
        this.Focus=Focus;

    }
    public void kill(){
        Panelist=null;
        Focus=null;
    }
    public void add(String s, Pane p){
        Panelist.put(s,p);
        System.out.print(s+" "+p);
    }
    public Pane getPane(String s){
        System.out.println(Panelist.get(s));
        return Panelist.get(s);
    }
    public void switchPane(String s){
        focusPane=Panelist.get(s);
        Focus.setRoot(Panelist.get(s));
    }
    public Scene getFocus(){return Focus;}
}
