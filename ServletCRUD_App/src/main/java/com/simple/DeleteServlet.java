package com.simple;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/simpledb", "root", "root@39");

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM users WHERE id=?");

            ps.setInt(1, id);
            ps.executeUpdate();

            con.close();
            resp.sendRedirect("view");
        }
        catch(Exception e) { e.printStackTrace(); }
    }
}
