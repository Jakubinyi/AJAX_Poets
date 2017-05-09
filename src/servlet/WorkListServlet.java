package servlet;

import com.google.gson.Gson;
import gson.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by jakubinyi on 2017.04.26..
 */

public class WorkListServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().println("[{\"name\": 2}]");
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendError(401, "unauthorized");
            return;
        }
        Gson gson = (Gson) getServletContext().getAttribute("com.google.gson.Gson");
        DataReader dataReader = (DataReader) getServletContext().getAttribute("gson.DataReader");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            Poet poet = dataReader.getPoet(request.getSession().getAttribute("username").toString().toLowerCase());
            ArrayList<WorkResponse> worksResponse = getWorksResponse(poet);
            out.print(gson.toJson(worksResponse));
            out.flush();
        }
        catch (PoetNotFoundException e) {
            response.sendError(404, "Poet not found");
        }
    }

    private ArrayList<WorkResponse> getWorksResponse(Poet poet) {
        ArrayList<WorkResponse> works = new ArrayList<>();
        ArrayList<WorkWithPath> worksWithPath = poet.getPoems();
        for (WorkWithPath wwp : worksWithPath) {
            works.add(new WorkResponse(wwp));
        }
        return works;
    }

}
