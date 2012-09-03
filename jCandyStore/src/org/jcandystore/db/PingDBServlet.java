package org.jcandystore.db;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class PingDBServlet extends HttpServlet {
	final String jdbcURL = "jdbc:google:rdbms://scott-tiger:scott/jcandystore";
	final String sqlSelect = "select * from PRODUCT;";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		// need to be authenticated
		// TODO: make this available to Administrators only
		if (user == null) {
			resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
		} else {
	        PrintWriter out = resp.getWriter();
			resp.setContentType("text/plain");

			Connection c = null;
			try {
				out.println("Logged in as : " + user.toString());
				out.println("Registering driver with: new AppEngineDriver()");
				DriverManager.registerDriver(new AppEngineDriver());
				out.println(jdbcURL);
				c = DriverManager.getConnection(jdbcURL);

				Statement stmt = c.createStatement();

				out.println(sqlSelect);
				ResultSet rs = stmt.executeQuery(sqlSelect);
				while (rs.next()) {
					out.print(rs.getString("PROD_ID") + "\t");
					out.print(rs.getString("PROD_CATEGORY") + "\t");
					out.println(rs.getString("PROD_NAME"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				out.println(e);
			} finally {
				if (c != null)
					try {
						c.close();
					} catch (SQLException ignore) {
				}
			}
		}
	}
}