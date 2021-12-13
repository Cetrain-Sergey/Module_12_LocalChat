import java.io.*;
import java.awt.*;
import java.awt.event.*;

class Client extends Frame implements ActionListener {

    static TextArea textArea = new TextArea("", 25, 65, TextArea.SCROLLBARS_VERTICAL_ONLY);
    TextField textField = new TextField(58);
    Button send = new Button("Send");
    Button exit = new Button("Exit");
    static String copyMessage = null;
    static String message = null;
    Frame frame = new Frame("Локальный чат");

    Client() {
        frame.setLayout(new FlowLayout());
        frame.add(textArea);
        frame.add(textField);
        frame.add(send);
        exit.addActionListener(this);
        send.addActionListener(this);
        frame.add(exit);
        frame.setSize(500, 500);
        frame.setLocation(120, 60);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void start() {
        if (ClientWindow.Errors != null) {
            textArea.append(ClientWindow.Errors);
            ClientWindow.Errors = null;
        }

        while (true) {
            if ((ClientWindow.Message != null) && (ClientWindow.Message != "")) {
                copyMessage = ClientWindow.Message;
                ClientWindow.Message = null;
                textArea.append(copyMessage);
                textArea.append("\n");
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            ClientWindow.printWriter.close();
            try {
                ClientWindow.bufferedReader.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }

            try {
                ClientWindow.socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }

        if (e.getSource() == send) {
            message = textField.getText();
            ClientWindow.send(message);
            textField.setText("");
        }
    }
}

