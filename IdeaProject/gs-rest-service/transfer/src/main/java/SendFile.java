import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by joydebbarman on 5/8/16. Forked by Ayush.
 */
public class SendFile {

    public final static int SOCKET_PORT = 13267;
    public final static String FILE_TO_SEND = "/home/joydebbarman/Public/baagi.mkv";

    public static void main (String [] args ) throws IOException {

        try {
            ServerSocket servsock = new ServerSocket(SOCKET_PORT);
            while (true) {
                System.out.println("Waiting for connection...");

                    Socket sock = servsock.accept();
                    System.out.println("connection is accepted : " + sock);
                    // send file
                    File myFile = new File (FILE_TO_SEND);
                    byte [] mybytearray  = new byte [(int)myFile.length()];
                    FileInputStream fis = new FileInputStream(myFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    bis.read(mybytearray,0,mybytearray.length);
                    OutputStream os = sock.getOutputStream();
                    System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
                    os.write(mybytearray,0,mybytearray.length);
                    os.flush();
                    System.out.println("Done.");

                if (bis != null) bis.close();
                if (os != null) os.close();
                if (sock!=null) sock.close();

                if (servsock != null)
                    servsock.close();
            }
        }
        catch (Exception e){
            e.getMessage();
        }

    }
}
