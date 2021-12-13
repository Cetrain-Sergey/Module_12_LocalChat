import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer extends Thread {

    String currentMessage;
    Socket socket;
    PrintStream printStream;
    public static String Message = null;

    public ChatServer(Socket socket) {
        currentMessage = null;
        this.socket = socket;
        try {
            printStream = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("ошибка: " + e);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Сервер запущен!");
            ServerSocket server = new ServerSocket(1234);
            while (true) {
                Socket socket;
                socket = server.accept();
                ChatServer nst = new ChatServer(socket);
                Server incom = new Server(socket);
                nst.start();
                incom.start();
            }
        } catch (IOException e) {
            System.out.println("ошибка: " + e);
        }
    }

    public void run() {
        while (true) {
            if ((Message != null) && (currentMessage != Message)) {
                currentMessage = Message;
                send(currentMessage);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String text) {
        printStream.println(text);
        printStream.flush();
    }
}
