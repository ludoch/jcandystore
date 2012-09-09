package org.jcandystore.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jcandystore.model.Orders;
import org.jcandystore.services.OrdersService;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");

		OrdersService orderService = new OrdersService();

		// retrieve grand total
		HttpSession session = request.getSession(true);
		Float grandTotal = (Float)session.getAttribute("grandTotal");		

		// create empty JPA entity
		Orders newOrder = new Orders();
		newOrder.setTotalPrice(new BigDecimal(grandTotal));
		
		// retrieve connected user
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();		
		newOrder.setUserId(user.getEmail());
		
		int id = (user.getUserId()+new Date()).hashCode();
		newOrder.setOrderId(Math.abs(id));

		// (leave other attributes empty)
		
		System.out.println("about to persist Order : " + newOrder);
		
		// persist object using JPA
		orderService.create(newOrder);
		
		// Send detailed order (content of session) to backoffice queue for shipping
		// Work with LineItems, ... TODO ...
		
		// clear session
		session.invalidate();
	}

}
