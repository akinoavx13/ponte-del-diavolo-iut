package Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Maxime on 10/10/2015.
 */
public class Server {

    private static String message = "Hello I'm your server.";
    private static int port;
    private static ServerSocket socket;

    public static void main(String[] args) {
        try {
            port = (args.length == 1) ? Integer.parseInt(args[0]) : 8080;
            socket = new ServerSocket(port);

            System.out.println("TCP server is running on " + port + "...");

            while (true) {
                // Accept new TCP client
                Socket client = socket.accept();

                OutputStream outputStream = client.getOutputStream();
                InputStream inputStream = client.getInputStream();


/*
                int res = inputStream.read();
                String receive = "Recu de " + client.getInetAddress() + " : \"";

                while(res > 0 && res <= 255){
                    receive += (char) res;
                    res = inputStream.read();
                }

                receive += "\"";
                System.out.println(receive);
*/


                outputStream.write((int) '1');
                outputStream.write((int) '1');
                outputStream.write((int) '+');
                outputStream.write((int) '2');
                outputStream.write((int) '2');
                outputStream.write((int) 'F');


                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
