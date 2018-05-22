package socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketReader implements Runnable {
    private Socket socket;

    public SocketReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(socket.getInputStream())) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
