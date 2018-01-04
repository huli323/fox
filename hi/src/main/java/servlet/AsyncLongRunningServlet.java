package servlet;

import listener.MyAsyncListener;
import service.MyAsyncProcessor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  Servlet异步
 */
@WebServlet(name = "AsyncLongRunningServlet", urlPatterns = "/Async", asyncSupported = true)
public class AsyncLongRunningServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        System.out.println("===============");
        System.out.println("Async start, name: " + Thread.currentThread().getName() + "; ID: " + Thread.currentThread().getId());

//        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

        String time = request.getParameter("time");
        int sec = Integer.valueOf(time);
        if(sec > 10000)
            sec = 10000;

        AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new MyAsyncListener());
        asyncContext.setTimeout(9000);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
        executor.execute(new MyAsyncProcessor(asyncContext, sec));

        long end = System.currentTimeMillis();

        System.out.println("AsyncRunningServlet ends, name: " + Thread.currentThread().getName()
                        + "; ID: " + Thread.currentThread().getId()
                        + "; Using time: " + (end - start) + "ms");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
