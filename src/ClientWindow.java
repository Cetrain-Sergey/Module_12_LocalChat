import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWindow extends Thread {

    static String Errors = null;
    static String Message = null;
    static BufferedReader bufferedReader = null;
    static PrintWriter printWriter;
    static Socket socket = null;

    public ClientWindow() {
        try {
            socket = new Socket("127.0.0.1", 1234);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            Errors = "Ошибка соединения";
        }

    }

    public static void main(String[] args) {
        ClientWindow clientWindow = new ClientWindow();
        clientWindow.start();
        Client newClient = new Client();
        newClient.start();
    }

    public void run() {
        send("У нас новый собеседник!");
        while (true) {
            try {
                Message = bufferedReader.readLine();
            } catch (IOException e) {
                break;
            }
        }
        Client.textArea.append("Ошибка соединения с сервером");
    }

    public static void send(String text) {
        printWriter.println(text);
        printWriter.flush();
    }

}
