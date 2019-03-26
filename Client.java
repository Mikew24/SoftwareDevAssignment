package SoftwareDevAssignment;

import java.io.*;
import java.net.*;

/* Client class to recive the random name the Nameserver gives it */


public class Client  {
  public String getname() {
    try {
        //Creates socket and trys to connect to server (NameServer)
        Socket s = new Socket("localhost", 8000);

        //Creates Data Input stream to get what  the server is goin to give it
        DataInputStream getn = new DataInputStream(s.getInputStream());

        //reads in the name from NameServer and returns it as a string
        return getn.readUTF();
    } catch (Exception e) {
      // if a error happens the title gets set to not working
      return "not working";
    }
  }

}
