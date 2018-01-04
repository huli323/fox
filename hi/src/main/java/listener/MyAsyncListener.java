package listener;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class MyAsyncListener implements AsyncListener {

    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        System.out.println("Async complete!");
    }

    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        System.out.println("Async timeout!");
    }

    public void onError(AsyncEvent asyncEvent) throws IOException {
        System.out.println("Async error");
    }

    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        System.out.println("Async start!");
    }
}

