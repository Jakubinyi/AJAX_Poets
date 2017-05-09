package servlet;

import com.google.gson.Gson;
import gson.DataReader;
import gson.PoetNotFoundException;
import gson.WorkNotFoundException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by jakubinyi on 2017.05.07..
 */
public class WorkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendError(401, "unauthorized");
            return;
        }
        DataReader dataReader = (DataReader) getServletContext().getAttribute("gson.DataReader");
        response.setContentType("text");
        String title = request.getParameter("title");
        try {
            InputStream inputStream = dataReader.getWork(session.getAttribute("username").toString().toLowerCase(), title);
            OutputStream outputStream = response.getOutputStream();
            copyStream(inputStream, outputStream);
            outputStream.flush();
        }
        catch (WorkNotFoundException e) {
            response.sendError(404, "Work not found");
        }
        catch (PoetNotFoundException e) {
            response.sendError(500, "Poet not found");
        }
    }

    private void copyStream(InputStream in, OutputStream out) throws IOException {
        int ch;
        while ((ch = in.read()) != -1) {
            out.write(ch);
        }
    }
}
//http://localhost:8080/work?title=borozo

