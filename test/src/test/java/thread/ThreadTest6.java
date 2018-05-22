package thread;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest6 {
    public static void main(String[] args) {
        ExecutorService exc = Executors.newCachedThreadPool();
        Sender sender = new Sender();
        exc.execute(sender);
        exc.execute(new Receiver(sender));
    }
}
class Sender implements Runnable {
    private PipedWriter writer = new PipedWriter();

    public PipedWriter getWriter(){
        return writer;
    }
    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
                writer.write(str);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class Receiver implements Runnable {
    private PipedReader reader;

    public Receiver(Sender sender){
        try {
            reader = new PipedReader(sender.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                System.out.println(" get " + (char)reader.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
