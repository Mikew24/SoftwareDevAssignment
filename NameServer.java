//package SoftwareDevAssignment;
package Code;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.math.*;
import java.util.Random;

public class NameServer implements Runnable{
    public void run(){
        String names[]={"Comet Catcher"
                ,"Dodge the red Balloons"
                ,"Diamond and rubies"
                ,"dodge the big Oof"
                ,"Dont get hit three times",
                "Can you get the high score",
                "Not that Scuffed"};
        DataOutputStream DO = null;
        try{
            ServerSocket ss = new ServerSocket(8000);
            Socket socket = ss.accept();
            DO=new DataOutputStream(socket.getOutputStream());
            int rnd = new Random().nextInt(names.length);
            DO.writeUTF(names[rnd]);
            DO.close();
        }catch(Exception e){

        }

    }
}
