package Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Maxime on 10/10/2015.
 */
public class Client {

    private static int port;
    private static Socket socket;
    private String addressServer;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;

    public Client(String addressServer, int port) {
        this.addressServer = addressServer;
        Client.port = port;
    }

    public void connectionToServer() {
        try {
            socket = new Socket(addressServer, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnectionToServer() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receive() {
        String result = "";
        int receive;
        try {
            receive = inputStream.read();
            while (receive > 0 && receive <= 255) {
                result += (char) receive;
                receive = inputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void send(String message) {
        for (int i = 0; i < message.length(); i++) {
            try {
                outputStream.write((int) message.charAt(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
