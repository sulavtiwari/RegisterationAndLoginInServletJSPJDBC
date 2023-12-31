package main;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static java.lang.Class.forName;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw =resp.getWriter();
        resp.setContentType("text/html");

        try {

            String email= req.getParameter("email");
            String password = req.getParameter("password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinereg","root","@Mysql1234");
            PreparedStatement ps = con.prepareStatement("select * from user where email=? AND password=?");
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                HttpSession session=req.getSession();
                session.setAttribute("email",email);
              RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
              rd.forward(req,resp);
            } else{

                pw.print("<h3 style='color:red'>Email or Password did not match</h3>");
                RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                rd.include(req,resp);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            pw.print("Failed to login "+e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.include(req,resp);


        }

    }
}
