package org.jcandystore.views;

import com.google.appengine.api.search.ExpressionParser.num_return;

import org.jcandystore.model.Product;
import org.jcandystore.services.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        } else { // show individual product
            Product zeProduct = productService.find(productId);
            if (zeProduct == null) {
                out.println(productId + " : no such product found");
                out.println("<a href=\"/\">Back</a>");
                //resp.sendRedirect();
            } else {
                out.println (zeProduct.getDescription());
                // TODO: add buy link
            }
        }
    }

}
