package SoftwareDevAssignment;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/* Task thats starts up a server socket to pass a psuedo string to the client which would be used as a title*/

public class NameServer implements Runnable{
    public void run(){
        //List of Potential names for the title
        String names[]={"Comet Catcher"
                ,"Dodge the red Balloons"
                ,"Diamond and rubies"
                ,"dodge the big Oof"
                ,"Dont get hit three times",
                "Can you get the high score",
                "Not that Scuffed"};

        //creating a null Data output stream to be utilized later
        DataOutputStream DO = null;
        try{
            //Listens for Client request
            ServerSocket ss = new ServerSocket(8000);
            Socket socket = ss.accept();

            //Intializes our Data output stream from earlier
            DO=new DataOutputStream(socket.getOutputStream());

            //Selects a random name from the names array
            int rnd = new Random().nextInt(names.length);

            //writes a the selected name from the arry to UTF to the client
            DO.writeUTF(names[rnd]);

            //closes the Data output Stream
            DO.close();
        }catch(Exception e){

        }

    }
}
