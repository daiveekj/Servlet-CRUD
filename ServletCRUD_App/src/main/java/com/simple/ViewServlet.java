package com.simple;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/view")
public class ViewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<a href='add.jsp'>Add New User</a>");
        out.println("<h2>User List</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/simpledb",
                "root",
                "root@39"
            );

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");

            out.println("<table border='1' cellpadding='5'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Edit</th><th>Delete</th></tr>");

            while (rs.next()) {
                out.println("<tr>"
                    + "<td>" + rs.getInt("id") + "</td>"
                    + "<td>" + rs.getString("name") + "</td>"
                    + "<td>" + rs.getString("email") + "</td>"
                    + "<td><a href='edit?id=" + rs.getInt("id") + "'>Edit</a></td>"
                    + "<td><a href='delete?id=" + rs.getInt("id") + "'>Delete</a></td>"
                    + "</tr>");
            }

            out.println("</table>");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
