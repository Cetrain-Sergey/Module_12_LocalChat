import java.net.*;
import java.io.*;

class Server extends Thread {

    Socket socket;
    BufferedReader bufferedReader = null;
    String message = null;

    Server(Socket socket) {
        this.socket = socket;
        try {
            InputStreamReader isr = new InputStreamReader(this.socket.getInputStream());
            bufferedReader = new BufferedReader(isr);
        } catch (IOException e) {
            System.out.println("ошибка: " + e);
        }
    }

    public void run() {
        while (true) {
            try {
                message = bufferedReader.readLine();
                if (message != null) {
                    ChatServer.Message = message;
                    message = null;
                }
            } catch (IOException e) {
                break;
            }
        }
    }
}

