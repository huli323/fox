package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private static List<String> names = new ArrayList<>();

    static{
        for (int i = 0; i < 26; i++) {
            names.add(String.valueOf((char)('a' + i)));
        }
    }

    public static void main(String[] args) throws IOException {

        Socket s = new Socket();
        s.connect(new InetSocketAddress("192.168.1.109", 10000));

        // 接收消息
        new Thread(new SocketReader(s)).start();

        // 发送消息
        new Thread(new SocketWriter(s, names.get((int)(Math.random() * 26)) + names.get((int)(Math.random() * 26)))).start();

    }
}
