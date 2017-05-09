package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jakubinyi on 2017.04.19..
 */
public class LoginServlet extends HttpServlet {

    private static final Map<String, String> poets = new HashMap<>();
    static {
        poets.put("Petofi", "Sandor");
        poets.put("Varro", "Daniel");
        poets.put("admin", "admin");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("profile.html").include(request, response);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        Object usernameInSession = session.getAttribute("username");

        if(poets.containsKey(username) && poets.get(username).equals(password)){
            if (usernameInSession != null && usernameInSession.equals(username)) {
                out.print("You are already logged in.\n");
            } else {
                session.setAttribute("username", username);
                //out.print("Welcome, "+username);
                response.sendRedirect("ProfileServlet");
            }
        } else{
            out.print("Sorry, username or password error!");
            request.getRequestDispatcher("login.html").include(request, response);
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
