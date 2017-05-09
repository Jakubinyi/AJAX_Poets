package servlet;

import com.google.gson.Gson;
import gson.DataReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Initializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        Gson gson = new Gson();
        DataReader dataReader = new DataReader(gson);
        context.setAttribute("com.google.gson.Gson", gson);
        context.setAttribute("gson.DataReader", dataReader);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // nothing to do on shutdown
    }
}
