import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
public class addNewTask extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter(); 
        String add = request.getParameter("Task");
        try {
            Statement s1;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/to-do-app", "root", ""); 
            s1 = con.createStatement();
            HttpSession session = request.getSession();
            ResultSet rs = s1.executeQuery("select udata from user_data where udata='"+add+"'");
            if(rs.next()) {   
                out.println("<h1 style=\"text-align:center;\">The entered task Already exists</h1>");
                RequestDispatcher rd = request.getRequestDispatcher("add.html");
                rd.include(request, response);
            }else {
                s1.executeUpdate("INSERT INTO user_data (udata,uid) VALUES ('"+add+"','"+session.getAttribute("uid")+"')");
                response.sendRedirect("display.jsp");
            }
        } catch (Exception e) {}
    }
}


