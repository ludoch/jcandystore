package org.jcandystore.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

		// retrieve connected user
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if (user == null) {
			resp.sendRedirect(userService.createLoginURL(request.getRequestURI()));
		}
		
		OrdersService orderService = new OrdersService();

		// retrieve grand total
		HttpSession session = request.getSession();
		// check for empty session/cart
		if ( !session.getAttributeNames().hasMoreElements() ) {
			out.println("<h2>Empty cart!</h2>");
			out.println("<br/><a href=\"/\">Back Home</a>");			
		}
		
		// retrieve grandTotal (rather than recalculate)
		Float grandTotal = (Float)session.getAttribute("grandTotal");		

		// create empty JPA entity
		Orders newOrder = new Orders();
		newOrder.setTotalPrice(new BigDecimal(grandTotal));
		
		newOrder.setUserId(user.getEmail());
		
		int id = (user.getUserId()+new Date()).hashCode();
		newOrder.setOrderId(Math.abs(id));

		// (leave other attributes empty)
		
		System.out.println("about to persist Order : " + newOrder);
		
		// persist object using JPA
		orderService.create(newOrder);
		
		out.println("<h2>Order taken!</h2>");
		out.println("<h4>Now processing shipping...</a>");
		out.println("<br/><p><a href=\"/\">Back Home</a></p>");
		
		// Send detailed order (content of session) to backoffice queue for shipping
		// Work with LineItems, ... TODO ...
		
		// TODO send ack email to buyer.
		sendEmailConfirmation(newOrder);
		
		// TODO: decide who is the winner and send him mail
		
		
		// clear session
		session.invalidate();
	}

	private void sendEmailConfirmation(Orders order) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        StringBuilder msgBody = "This is a confirmation that you have made the following order:";
        msgBody.append(order.getUserId());
        msgBody.append(order.get)

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress("user@example.com", "Mr. User"));
            msg.setSubject("Thank you for your order on jCandyStore!");
            msg.setText(msgBody.toString());
            Transport.send(msg);

        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        }
		
	}

}
