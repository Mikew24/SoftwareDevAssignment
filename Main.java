//package SoftwareDevAssignment;
package Code;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
    @Override

    public void start(Stage MainStage){
        //Creates a instance of the Menu
        Menu m = new Menu();

        // Initial Border pane to start the program
        BorderPane pane = m.getPane();

        //to make using the buttons in another class easier as the VBox contains the buttons
        VBox paneMenu = m.getPaneMenu();

        //pane to intialize the scene
        Pane p = new Pane();

        //Intial scene to be shown by Stage
        Scene s=new Scene(pane,400,600);

        //Creating a instance of the Scene control class intializing it with the main scene
        SceneControl a = new SceneControl(s);

        // adding the Menu to the Scene control
        a.add("Flow",pane);

        //switing to the menu to be displayed on the stage
        a.switchPane("Flow");

        a.add("Game Pane",p);
        //Gameover//


        //CREATING THE THREAD

        //Creating the task and Thread to which the Menu will sustain input and handle requests
        MenuTasks Menu = new MenuTasks(s,a,paneMenu);
        Thread MenuThread = new Thread(Menu);
        MenuThread.start();
        
        //making the stage display the active scene in the scenecontrol class aka the focus
        MainStage.setScene(a.getFocus());

        // GETTING TITLE NAME

        //created a instance of the client class
        Client c= new Client();

        //created a instance of the NameServer class which is the task to what the thread will handle
        NameServer ns = new NameServer();

        //creating thread and starting it which causes the nameserver task to listen for a client request
        Thread gettheTitle = new Thread(ns);
        gettheTitle.start();
        //c.getname connects to the client and receives a random string from an array of potential title names from the server
        MainStage.setTitle(c.getname());


        //Showing stage
        MainStage.show();


    }
}
