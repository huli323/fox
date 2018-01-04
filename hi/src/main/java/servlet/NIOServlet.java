package servlet;

import listener.MyReadListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet3.1 非阻塞IO
 * Tomcat: 8+
 * 注：非阻塞IO仅对在Servlet中的异步处理请求有效
 */
@WebServlet(name = "NIOServlet", urlPatterns = "/nio", asyncSupported = true)
public class NIOServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(30 * 3000);

        ServletInputStream inputStream = request.getInputStream();

        MyReadListener myReadListener = new MyReadListener(inputStream, asyncContext);

        inputStream.setReadListener(myReadListener);

        PrintWriter writer = response.getWriter();
        writer.write("<h3>直接返回，不等异步处理结果</h3>");
        writer.flush();


    }
}
