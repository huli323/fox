package socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketWriter implements Runnable {
    private Socket socket;
    private String name;

    public SocketWriter(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(name + " 进入聊天室");
            writer.newLine();
            writer.flush();
            String out;
            while(!(out = scanner.nextLine()).equalsIgnoreCase("exit")){
                writer.write(name + ": " + out);
                writer.newLine();
                writer.flush();
            }
            writer.write(name + " 离开了了聊天室 ");
            writer.newLine();
            writer.flush();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
