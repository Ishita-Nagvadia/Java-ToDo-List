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
public class userLogin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter(); 
        String uemail = request.getParameter("email");
        String upass = request.getParameter("password");
        try {
            Statement s1;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/to-do-app", "root", ""); 
            s1 = con.createStatement();
            ResultSet rs = s1.executeQuery("select uid,email,password from user_list where email='"+uemail+"' and password='"+upass+"'");
           if(rs.next()){
                int uid = rs.getInt(1);
                HttpSession session = request.getSession(); 
                session.setAttribute("uid",uid);
                response.sendRedirect("display.jsp");
            }else {
                out.println("<h1 style=\"text-align:center;color:white;\">Username or Password error!!!</h1>");
                RequestDispatcher rd = request.getRequestDispatcher("login.html");
                rd.include(request, response);
            }
        } catch (Exception e) {}
    }
}


