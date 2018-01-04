package service;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;

public class MyAsyncProcessor implements Runnable {
    private AsyncContext asyncContext;
    private int sec;

    public MyAsyncProcessor() {
    }

    public MyAsyncProcessor(AsyncContext asyncContext, int sec) {
        this.asyncContext = asyncContext;
        this.sec = sec;
    }

    public void run() {
        System.out.println("Async support? " + asyncContext.getRequest().isAsyncSupported());
        longProcessing(sec);

        try {
            PrintWriter writer = asyncContext.getResponse().getWriter();
            writer.write("Process done in " + sec / 1000 + "s");
        } catch (IOException e) {
            e.printStackTrace();
        }
        asyncContext.complete();
    }

    public void longProcessing(int sec){
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
