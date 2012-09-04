package org.jcandystore.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");
        out.println("<h1>Shopping Cart</h1>");
        
        HttpSession session = request.getSession(true);
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String element = e.nextElement();
            out.println("<br/>"+ element + " : ");
            out.println(session.getAttribute(element));
        }
	}


}
