package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    private static ArrayList<Socket> sockets = new ArrayList<>();
    private static int counter = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(10000)) {
            System.out.println("server started!");

            while(true) {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                counter++;

                BufferedWriter w = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                w.write("欢迎进入聊天室！ 当前人数：" + counter);
                w.newLine();
                w.flush();

                // 消息分发
                new Thread(() -> {
                    try (Scanner scanner = new Scanner(socket.getInputStream())) {
                        while (scanner.hasNextLine()) {
                            String msg = scanner.nextLine();
                            String tmp;
                            for (Socket s : sockets) {
                                if(s.isClosed()){
                                    sockets.remove(s);
                                    continue;
                                }
                                if(s.equals(socket)) {
                                    continue;
                                }
                                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                                writer.write(msg);
                                writer.newLine();
                                writer.flush();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
