package org.jcandystore.views;

import org.jcandystore.model.Product;
import org.jcandystore.services.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        String productId = req.getParameter("id"); 
        
        if (productId == null) {  // show list
            String category = req.getParameter("cat");

            List<Product> allProducts = null;
            if (category == null ) {
                allProducts = productService.findAll();
            } else {
                allProducts = productService.findByCategory(category);
            }

            for (Product p : allProducts) {
                out.println("<br/><a href=\"./productView?id=" + p.getProdId() + "\">");
                out.println("" + p.getDescription() + "</a>");
            }
        } else { // show individual product and buy it
            Product zeProduct = null;
            zeProduct = productService.find(productId);
            if (zeProduct == null) {
                out.println(productId + " : no such product found!");
                out.println("<br/><a href=\"/\">Back</a>");
                //resp.sendRedirect();
            } else {
                out.println (zeProduct.getDescription());
                // TODO: print price
                
                String prodId = zeProduct.getProdId();
                HttpSession session = req.getSession();
                
                Integer currentAmount = (Integer)session.getAttribute( prodId );
                if (currentAmount == null ) { // no such product in the shopping cart 
                    session.setAttribute(prodId, new Integer(1));
                } else { // increment count by 1
                    session.setAttribute(prodId, new Integer(currentAmount.intValue() + 1));
                }
            }
        }
    }

}
