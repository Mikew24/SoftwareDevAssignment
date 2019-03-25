package SoftwareDevAssignment;
import java.io.*;
import java.net.*;
import java.util.HashMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.scene.text.Text;

public class Client  {
  public String getname() {
    String name="";
    try {
      Socket s = new Socket("localhost", 8000);
      DataInputStream getn = new DataInputStream(s.getInputStream());
      return getn.readUTF();
    } catch (Exception e) {
      return "not working";
    }
  }

}
