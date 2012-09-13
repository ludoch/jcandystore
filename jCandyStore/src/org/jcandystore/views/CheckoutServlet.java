package org.jcandystore.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jcandystore.gcm.ApiKeyInitializer;
import org.jcandystore.gcm.Datastore;
import org.jcandystore.gcm.MessageSender;
import org.jcandystore.model.Orders;
import org.jcandystore.services.OrdersService;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	private Sender sender;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
//		sender = new Sender("AIzaSyAibtCTKNsXHLttDTWrRdsBrOR1e7Rqi7s");
//		sender = newSender(config);
	}

	/**
	 * Creates the {@link Sender} based on the servlet settings.
	 */
//	protected Sender newSender(ServletConfig config) {
//		String key = (String) config.getServletContext().getAttribute(
//				ApiKeyInitializer.ATTRIBUTE_ACCESS_KEY);
//		return new Sender(key);
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");

		// retrieve connected user
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user == null) {
			resp.sendRedirect(userService.createLoginURL(request
					.getRequestURI()));
		}

		OrdersService orderService = new OrdersService();

		// retrieve grand total
		HttpSession session = request.getSession();
		// check for empty session/cart
		if (!session.getAttributeNames().hasMoreElements()) {
			out.println("<h2>Empty cart!</h2>");
			out.println("<br/><a href=\"/\">Back Home</a>");
		}

		// retrieve grandTotal (rather than recalculate)
		Float grandTotal = (Float) session.getAttribute("grandTotal");

		// create empty JPA entity
		Orders newOrder = new Orders();
		BigDecimal total = new BigDecimal(grandTotal);
		newOrder.setTotalPrice(total.setScale(2, RoundingMode.HALF_UP));

		newOrder.setUserId(user.getEmail());

		int id = (user.getUserId() + new Date()).hashCode();
		newOrder.setOrderId(Math.abs(id));

		// (leave other attributes with their default value)

		System.out.println("about to persist Order : " + newOrder);

		// persist object using JPA
		orderService.create(newOrder);

		out.println("<h2>Order taken!</h2>");
		out.println("<h4>Now processing shipping...</a>");
		out.println("<br/><p><a href=\"/\">Back Home</a></p>");

		// Send detailed order (content of session)
		// to backoffice queue for shipping.
		// Work with LineItems instead, ... TODO ...

		sendEmailConfirmation(newOrder);

		// send GCM message to all registered devices
		
		MessageSender.sendMessageToAllDevices(resp, newOrder);
		
//		List<String> devices = Datastore.getDevices();
//		for (String deviceId : devices) {
//			MessageSender.sendSingleMessage(deviceId, resp, sender, newOrder);
//		}

		// clear session
		session.invalidate();
	}


	private void sendEmailConfirmation(Orders order) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		UserService userService = UserServiceFactory.getUserService();
		userService.getCurrentUser().getEmail();

		StringBuilder msgBody = new StringBuilder();
		msgBody.append("This is a confirmation that you have made the following order:\n");
		msgBody.append(order.getUserId());
		msgBody.append("\n" + order.getTotalPrice() + " euros");
		msgBody.append("\nOrdered on" + order.getOrderDate());
		msgBody.append("\n(OrderID = " + order.getOrderId() + ")");
		msgBody.append("\n\nThank you for your business!");

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("admin@jcandystore.appspotmail.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					order.getUserId(), "Mr. User"));
			// new InternetAddress("alexis.mp@gmail.com", "Mr. User"));
			msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
					"admins"));
			msg.setSubject("Thank you for your order on jCandyStore!");
			msg.setText(msgBody.toString());
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
