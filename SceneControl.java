package SoftwareDevAssignment;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.util.HashMap;

public class SceneControl {
    //A Hash map to store all the different panes to be switched too
    HashMap<String,Pane> Panelist = new HashMap<String, Pane>();

    //The Scene to be foused
    private Scene Focus;

    //Pane in focus
    Pane focusPane = new Pane();

    //Constructor sets the Target Scene
    public SceneControl(Scene Focus){  this.Focus=Focus; }

    //adds a pane to a list along with its name to the hashmap
    public void add(String s, Pane p){ Panelist.put(s,p); }

    /*uses the name given when adding a scene and fetches the corresponding pane from the hashmap
    then adds it the chances the the pane in focus in the Scene to the desired one*/
    public void switchPane(String s){
        focusPane=Panelist.get(s);
        Focus.setRoot(Panelist.get(s));
    }
    //returns the scene in focus
    public Scene getFocus(){return Focus;}
}
