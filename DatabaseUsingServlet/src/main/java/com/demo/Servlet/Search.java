package com.demo.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String db_url="jdbc:mysql://localhost:3306/rahul_db";
	private static String db_user ="root";
	private static String db_pass= "admin2023";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		String empid=request.getParameter("id");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		try(				
				Connection conn=DriverManager.getConnection(db_url,db_user,db_pass);
				PreparedStatement stmt= conn.prepareStatement("Select * from student where id=?")){
			
			stmt.setString(1, empid);
			out.print ("<table width=50% border=1>");
            out.print ("<caption>Employee Details:</caption>");
            ResultSet rs = stmt.executeQuery ();
            /* Printing column names */
            out.print ("</br></br>");
            ResultSetMetaData rsmd = rs.getMetaData ();
            int total = rsmd.getColumnCount ();
            out.print ("<tr>");
            for (int i = 1; i <= total; i++)
         {
             out.print ("<th>" + rsmd.getColumnName (i) + "</th>");
         }
            out.print ("</tr>");
            /* Printing result */
            while (rs.next ())
         {
             out.print ("<tr><td>" + rs.getString (1) + "</td><td>" +  rs.getString (2) + " </td><td>" + rs.getInt (3) + "</td></tr>");
         }
            out.print ("</table>");
            out.println("</br>");;
            out.println("<h2> <a href = ./index.html> Reset </a></h2>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
