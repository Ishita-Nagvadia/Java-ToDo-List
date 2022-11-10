import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class update_query extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String udata = request.getParameter("Task");
        HttpSession session = request.getSession();
        String udid = (String) session.getAttribute("udid");
        try {
            Statement s1;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/to-do-app", "root", ""); 
            s1 = con.createStatement();
            s1.executeUpdate("Update user_data set udata = '"+udata+"' where udid = '"+udid+"'");
            response.sendRedirect("display.jsp");
        } catch (Exception e) {}
    }
}
