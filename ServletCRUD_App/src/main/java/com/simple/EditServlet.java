package com.simple;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpledb", "root", "root@39");

			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id=?");

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			rs.next();
			req.setAttribute("id", rs.getInt(1));
			req.setAttribute("name", rs.getString(2));
			req.setAttribute("email", rs.getString(3));

			con.close();

			RequestDispatcher rd = req.getRequestDispatcher("edit.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpledb", "root", "root@39");

			PreparedStatement ps = con.prepareStatement("UPDATE users SET name=?, email=? WHERE id=?");

			ps.setString(1, name);
			ps.setString(2, email);
			ps.setInt(3, id);
			ps.executeUpdate();

			con.close();
			resp.sendRedirect("view");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
