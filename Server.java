import java.io.*;
import java.net.*;


public class Server {

//  TextArea ta = new TextArea();
  DataOutputStream outputToClient = null;

  public void readFile(String scores){
    try{
      ServerSocket serverSocket = new ServerSocket(8000);
      Socket socket = serverSocket.accept();
      BufferedReader reader = new BufferedReader(new FileReader(scores));
      outputToClient = new DataOutputStream(socket.getOutputStream());
      String line = reader.readLine();

      while(line != null){
        outputToClient.writeUTF(line);
        line = reader.readLine();
      }

      reader.close();
    }catch(FileNotFoundException e){
      System.out.println("File not found");
    }catch(IOException e){

    }
  }
}
