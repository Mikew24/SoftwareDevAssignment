package Code;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameOverScreen {
    BorderPane pane;
    VBox paneMenu;

    public GameOverScreen(){
        BorderPane pane = new BorderPane(); //Creates a border pane
        pane.setStyle("-fx-background-color: black"); //Sets background colour of pane

        //---------------------------- Title ---------------------------------------
        Text title = new Text(50, 50, "Game Over"); //Creates title
        title.setFill(Color.WHITE); //Sets colour
        Font fontNormal = Font.font("Futura", FontWeight.NORMAL, FontPosture.REGULAR, 50); //creates font option
        title.setFont(fontNormal); //applies font to title

        //------------------------ Save Score Instructions -------------------------
        Text saveInstructions = new Text(50, 50, "Enter your initials below");
        saveInstructions.setFill(Color.WHITE); //Sets colour
        Font fontNormal2 = Font.font("Futura", FontWeight.NORMAL, FontPosture.REGULAR, 25); //creates font option
        saveInstructions.setFont(fontNormal2); //applies font

        //---------------------------- Buttons -------------------------------------
        Button btSaveScore = new Button("SAVE SCORE");
        Button btExit = new Button("EXIT");

        //---------------------------- Textfield -----------------------------------
        TextField tfName = new TextField();
        tfName.setMaxWidth(100);

        //------------------- Setting Title & Buttons to Pane ----------------------
        VBox paneMenu = new VBox(20);
        paneMenu.setPadding(new Insets(15, 5, 5, 5));
        paneMenu.getChildren().addAll(title, saveInstructions, tfName, btSaveScore, btExit);
        paneMenu.setAlignment(Pos.CENTER);

        pane.setCenter(paneMenu);
        this.pane=pane;
        this.paneMenu=paneMenu;

    }
    public BorderPane getPane(){
        return pane;
    }
    public VBox getPaneMenu(){
        return paneMenu;
    }
}
