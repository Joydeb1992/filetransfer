import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
/**
 * Created by joydebbarman on 5/8/16. Forked by Ayush.
 */


public class ReceiveFile {

    public final static int SOCKET_PORT = 13267;
    public final static String SERVER = "10.120.19.167";
    public final static String
            FILE_TO_RECEIVED = "/home/joydebbarman/baghi.mkv";

    public static void main (String [] args ) throws IOException {
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock = null;
        try {
            sock = new Socket(SERVER, SOCKET_PORT);
            System.out.println("Connecting...");

            byte [] mybytearray  = new byte [1281199900];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVED);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(mybytearray,0,mybytearray.length);
            current = bytesRead;
            do {
                bytesRead =
                        is.read(mybytearray, current, (mybytearray.length-current));
                if(bytesRead >= 0) current += bytesRead;
            } while(bytesRead > -1);

            bos.write(mybytearray, 0 , current);
            bos.flush();
            System.out.println("File " + FILE_TO_RECEIVED
                    + " downloaded (" + current + " bytes read)");
        }
        finally {
            if (fos != null)
                fos.close();
            if (bos != null)
                bos.close();
            if (sock != null)
                sock.close();
        }
    }

}

