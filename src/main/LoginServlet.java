package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import static java.lang.Class.forName;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw =resp.getWriter();
        class.forName(com.mysql.cj.jdbc.Driver);
        Connection con = DriverManager.getConnection("jdbc:mysql:localhost//:3306/onlinereg","root","Mysql1234");

    }
}
