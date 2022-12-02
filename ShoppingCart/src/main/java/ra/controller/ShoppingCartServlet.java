package ra.controller;

import ra.model.entity.Cart;
import ra.model.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShoppingCartServlet", value = "/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
    private List<Product> listProduct = new ArrayList<>();

    public ShoppingCartServlet() {
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
        if (action.equals("AddCart")) {
            String productId = request.getParameter("productId");
            //1. Lay thong tin san pham theo productId
            Product proAdd = new Product();
            for (Product pro : listProduct) {
                if (pro.getProductId().equals(productId)) {
                    proAdd = pro;
                }
            }
            //2. Lấy giỏ hàng từ session (Mặc định tên giỏ hàng trong session là listCart)
            HttpSession session = request.getSession();
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
            //3. Kiểm tra listCart
            if (listCart == null) {
                //Khách hàng chưa mua hàng
                //-Khởi tạo giỏ hàng
                listCart = new ArrayList<>();
                //-add cart vao listCart
                Cart cart = new Cart(proAdd, 1);
                listCart.add(cart);
            } else {
                //Khách hàng đã mua hàng
                //-Kiem tra san pham da ton tai trong gio hang cua khach hang chua
                boolean checkExist = false;
                for (Cart cart : listCart) {
                    if (cart.getProduct().getProductId().equals(productId)) {
                        //Sản phẩm thêm vào giỏ hàng đã tồn tại trong giỏ hàng
                        cart.setQuantity(cart.getQuantity() + 1);
                        checkExist = true;
                        break;
                    }
                }
                if (!checkExist) {
                    //Sản phẩm thêm chưa có trong giỏ hàng
                    Cart cart = new Cart(proAdd, 1);
                    listCart.add(cart);
                }
            }
            //4. add listCart vao session
            session.setAttribute("listCart", listCart);
            //5. Tinh tong tien va add vao session
            session.setAttribute("totalAmount", calTotalAmount(listCart));
            //6. Chuyen sang trang shopping cart
            request.getRequestDispatcher("views/shoppingCart.jsp").forward(request, response);
        }else if (action.equals("Delete")) {
            //1. Lay listCart tu session
            List<Cart> listCart = (List<Cart>) request.getSession().getAttribute("listCart");
            //2. Lay productId tu request
            String productId = request.getParameter("productId");
            //3. Thuc hien xoa san pham trong gio hang
            for (int i = 0; i < listCart.size(); i++) {
                if (listCart.get(i).getProduct().getProductId().equals(productId)){
                    listCart.remove(i);
                }
            }
            //4. add listCart vao session
            request.getSession().setAttribute("listCart",listCart);
            //5. Tinh tong tien va add vao session
            request.getSession().setAttribute("totalAmount",calTotalAmount(listCart));
            //6. Chuyen sang trang gio hang
            request.getRequestDispatcher("views/shoppingCart.jsp").forward(request,response);
        }
    }

    public static float calTotalAmount(List<Cart> listCart) {
        float totalAmount = 0;
        for (Cart cart : listCart) {
            totalAmount += cart.getQuantity() * cart.getProduct().getPrice();
        }
        return totalAmount;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("Update")) {
            //1. Lay listCart tu session
            List<Cart> listCart = (List<Cart>) request.getSession().getAttribute("listCart");
            //2. Lay mang quantity cua gio hang
            String[] arrQuantity = request.getParameterValues("quantity");
            //3. Cap nhat quantity cho tat ca gio hang
            for (int i = 0; i < listCart.size(); i++) {
                listCart.get(i).setQuantity(Integer.parseInt(arrQuantity[i]));
            }
            //4. add listCart vao session
            request.getSession().setAttribute("listCart",listCart);
            //5. Tinh lai tong tien va add vao session
            request.getSession().setAttribute("totalAmount",calTotalAmount(listCart));
            //6. Chuyen lai trang shoppingCart.jsp
            request.getRequestDispatcher("views/shoppingCart.jsp").forward(request,response);
        }
    }
}
