package ra.controller;

import ra.model.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductController", value = "/ProductController")
public class ProductController extends HttpServlet {
    private List<Product> listProduct = new ArrayList<>();

    public ProductController() {
        Product pro1 = new Product("P001", "Nho Mỹ", 50, "nho11.jpg", "Mô tả nho mỹ", true);
        Product pro2 = new Product("P002", "Nho Canada", 80, "nho22.jpg", "Mô tả nho Canada", true);
        Product pro3 = new Product("P003", "Táo Việt Nam", 100, "tao11.jpg", "Mô tả táo Việt Nam", true);
        listProduct.add(pro1);
        listProduct.add(pro2);
        listProduct.add(pro3);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("GetAll")) {
            request.setAttribute("listProduct", listProduct);
            request.getRequestDispatcher("views/products.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
