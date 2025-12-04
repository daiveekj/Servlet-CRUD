package com.simple;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/add")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/simpledb", "root", "root@39");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name, email) VALUES (?, ?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();

            con.close();
            resp.sendRedirect("view");
        }
        catch(Exception e) { e.printStackTrace(); }
    }
}
