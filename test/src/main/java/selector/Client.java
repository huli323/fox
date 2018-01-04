package selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 5000));
            ByteBuffer writeBuffer = ByteBuffer.allocate(32);
            ByteBuffer readBuffer = ByteBuffer.allocate(32);
            Scanner scanner = new Scanner(System.in);
            while(true){
                String str = scanner.nextLine();
                System.out.println("----" + str);
                socketChannel.write(writeBuffer.put(str.getBytes()));
//                writeBuffer.flip();
//                writeBuffer.rewind();

//                readBuffer.clear();
//                socketChannel.read(readBuffer);
//                readBuffer.flip();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
