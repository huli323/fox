package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("=============================");

        String username = request.getParameter("username");
        String isLogin = null;

        if(username != null && !"".equals(username.trim())) {
            isLogin = "yes";
            session.setAttribute("isLogin", "yes");
            session.setAttribute("username", username);
        }

        if(isLogin == null){
            response.sendRedirect("login.jsp");
            return;
        }
        System.out.println("username:" + username);
        for (Cookie cookie :
                request.getCookies()) {
            System.out.println("name:" + cookie.getName() + "  | value:" + cookie.getValue());

        }

//        response.sendRedirect("detail.jsp");
        request.getRequestDispatcher("/detail.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
