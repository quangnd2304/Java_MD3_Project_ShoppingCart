<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 18/11/2022
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/ShoppingCartServlet" method="post">
    <table border="1">
        <thead>
        <tr>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Amount</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCart}" var="cart">
            <tr>
                <td>${cart.product.productName}</td>
                <td>${cart.product.price}</td>
                <td><input type="number" value="${cart.quantity}" name="quantity"></td>
                <td>${cart.quantity*cart.product.price} VNĐ</td>
                <td>
                    <input type="submit" name="action" value="Update"/>
                    <a href="<%=request.getContextPath()%>/ShoppingCartServlet?action=Delete&&productId=${cart.product.productId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3">Total Amount</td>
            <td colspan="2">${totalAmount}VNĐ</td>
        </tr>
        </tbody>
    </table>
</form>
<a href="<%=request.getContextPath()%>/ProductController?action=GetAll">Tiếp tục muc hàng...</a>
</body>
</html>
