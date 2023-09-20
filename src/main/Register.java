package main;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter pr = resp.getWriter();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        String telephone = req.getParameter("phone");
        String password = req.getParameter("password");
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinereg","root","@Mysql1234");
            PreparedStatement ps = con.prepareStatement("Insert into user values(?,?,?,?,?,?)");
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,gender);
            ps.setString(4,age);
            ps.setString(5,telephone);
            ps.setString(6,password);

            int count = ps.executeUpdate();

            if(count>0){
                pr.print("User is registered successfully");
                resp.setContentType("text/html");
                RequestDispatcher rd = req.getRequestDispatcher("/RegisterSuccess.jsp");
                rd.include(req,resp);
            }else {

            pr.print("<h3 style='color:red'>Failed to register User</h3>");
    }}
        catch(Exception e){
            e.printStackTrace();
            pr.print("<h3 style='color:red'>Failed to register. "+e.getMessage()+"</h3>");
            resp.setContentType("text/html");
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.include(req,resp);
        }
}}
