package Code;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Menu {

    private BorderPane pane;
    private VBox paneMenu;
    public Menu(){     BorderPane pane = new BorderPane(); //Creates a border pane
        pane.setStyle("-fx-background-color: black"); //Sets background colour of pane

        //---------------------------- Title ---------------------------------------
        Text title = new Text(50, 50, "Asteroid Dodger"); //Creates title
        title.setFill(Color.WHITE); //Sets colour
        Font fontNormal = Font.font("Futura", FontWeight.NORMAL, FontPosture.REGULAR, 50); //creates font option
        title.setFont(fontNormal); //applies font to title

        //---------------------------- Buttons -------------------------------------
        Button btPlay = new Button("PLAY");
        Button btScore = new Button("SCORE");
        Button btExit = new Button("EXIT");
        //------------------- Setting Title & Buttons to Pane ----------------------
        VBox paneMenu = new VBox(20);
        paneMenu.setPadding(new Insets(15, 5, 5, 5));
        paneMenu.getChildren().addAll(title, btPlay, btScore, btExit);
        paneMenu.setAlignment(Pos.CENTER);

        pane.setCenter(paneMenu);
        this.paneMenu=paneMenu;
        this.pane = pane;

    }
    public BorderPane getPane(){
        return pane;
    }
    public VBox getPaneMenu(){
        return paneMenu;
    }
}
