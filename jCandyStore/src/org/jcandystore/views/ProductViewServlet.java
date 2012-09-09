package org.jcandystore.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jcandystore.model.Item;
import org.jcandystore.model.Product;
import org.jcandystore.services.ItemService;
import org.jcandystore.services.ProductService;

public class ProductViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductViewServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");
        
        ProductService productService = new ProductService();
        ItemService itemService = new ItemService();
        
        String productId = req.getParameter("id"); 
        
        // show list of products
        if (productId == null) {
            String category = req.getParameter("cat");

            List<Product> allProducts = null;
            
            // restrict search to given category if provided
            if (category == null ) {
                allProducts = productService.findAll();
            } else {
                allProducts = productService.findByCategory(category);
            }

            for (Product p : allProducts) {
                out.println("\n<br/><a href=\"./productView?id=" + p.getProdId() + "\">");
                out.println("<br/>" + p.getDescription() + "</a>");
                // link to RESTful representation
                String restfulURI = "/resources/product/" + p.getProdId();
                out.println("<br/><a href=\""+ restfulURI + "\">" + restfulURI + "</a>");
            }
        } else { // show individual product and add it to the shopping cart
        	out.println("<h2>You've just added* this candy to your <a href=\"/cart\">shopping cart</a>:</h2>");
            Product zeProduct = productService.find(productId);
            if (zeProduct == null) {
                out.println(productId + " : no such product found!");
                out.println("<br/><a href=\"/\">Back</a>");
                //resp.sendRedirect();
            } else {
                out.print (zeProduct.getDescription());

                // get price from equivalent item
                Item zeItem = itemService.findByProdId(zeProduct.getProdId());
                if (zeItem == null) {
                	// Application Error : should not happen
                	out.println( "(<i>Could not retrieve product price</i>)" );
                } else {
                	out.println( " : " + zeItem.getListPrice()  + " euros");
                }
                
                // link to RESTful representation
                String restfulURI = "/resources/product/" + zeProduct.getProdId();
                out.println("<br/><a href=\""+ restfulURI + "\">" + restfulURI + "</a>");
                
                String prodId = zeProduct.getProdId();
                HttpSession session = req.getSession();
                
                Integer currentAmount = (Integer)session.getAttribute( prodId );
                if (currentAmount == null ) { // no such product in the shopping cart 
                    session.setAttribute(prodId, new Integer(1));
                } else { // increment count by 1
                    session.setAttribute(prodId, new Integer(currentAmount.intValue() + 1));
                }
            }
        	out.println("<p><small><small>*: yes, this is the worse restful mistake one can make...</small></small></p>");            
        }
    }

}
