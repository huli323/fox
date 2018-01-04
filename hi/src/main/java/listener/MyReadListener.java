package listener;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class MyReadListener implements ReadListener {
    private ServletInputStream inputStream;

    private AsyncContext asyncContext;

    public MyReadListener(ServletInputStream inputStream, AsyncContext asyncContext) {
        this.inputStream = inputStream;
        this.asyncContext = asyncContext;
    }

    public void onDataAvailable() throws IOException {
        System.out.println("数据可用时触发");
    }

    public void onAllDataRead() throws IOException {
        try {
            Thread.sleep(3000); // 模拟请求处理
            PrintWriter writer = asyncContext.getResponse().getWriter();
            writer.write("<p>数据读取完毕</p>");
            writer.flush();
            writer.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onError(Throwable throwable) {
        System.out.println("数据出错");
    }
}
