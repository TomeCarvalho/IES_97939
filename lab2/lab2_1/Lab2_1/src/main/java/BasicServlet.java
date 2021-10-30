import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BasicServlet", value = "/BasicServlet")
public class BasicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        out.println("<html><head><title>BasicServlet</title></head><body>");
        out.println("<h1>Greetings, " + name + "!</h1>");
        out.println("</body></html>");
        // Uncomment to cause a NullPointerException
//        String nil = null;
//        String error = nil.substring(0);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
