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
public class SignIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter(); 
        String uemail = request.getParameter("email");
        String upass = request.getParameter("password");
        String ucpass = request.getParameter("cpass");
        String uname = request.getParameter("username");
        try {
            Statement s1;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/to-do-app","root",""); 
            s1 = con.createStatement();
            ResultSet rs = s1.executeQuery("select email from user_list where email='"+uemail+"'");
            if(rs.next()) {   
                out.println("<h1 style=\"text-align:center;color:white;\">This email already exists</h1>");
                RequestDispatcher rd = request.getRequestDispatcher("/signin.html");
                rd.include(request, response);
            } 
            else {
                s1.executeUpdate("insert into user_list(email,username,password) values('"+uemail+"','"+uname+"','"+upass+"')");
                response.sendRedirect("index.html");
            }
        } catch (Exception e) {System.out.println(e);}
    }
}

