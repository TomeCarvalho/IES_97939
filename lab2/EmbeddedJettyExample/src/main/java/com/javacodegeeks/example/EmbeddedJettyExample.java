package com.javacodegeeks.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class EmbeddedJettyExample {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8680);
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
        servletHandler.addServletWithMapping(HelloServlet.class, "/");
        server.start();
        server.join();
    }

    public static class HelloServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            response.setContentType("text/html");
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.getWriter().println("<h1>New Hello Simple Servlet</h1>");
            PrintWriter out = response.getWriter();
            String name = request.getParameter("name");
            out.println("<html><head><title>BasicServlet</title></head><body>");
            out.println("<h1>Greetings, " + name + "!</h1>");
            out.println("</body></html>");
            // Uncomment to cause a NullPointerException
//        String nil = null;
//        String error = nil.substring(0);
        }
    }
}