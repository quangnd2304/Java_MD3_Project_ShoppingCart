<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 18/11/2022
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Product</title>
</head>
<body>
<c:forEach items="${listProduct}" var="pro">
    <div>
        <img src="images/${pro.productImage}" alt="${pro.productName}" width="200" height="200" border="1"/>
        <h3>${pro.productName}</h3>
        <h3>${pro.price}</h3>
        <a href="<%=request.getContextPath()%>/ShoppingCartServlet?action=AddCart&&productId=${pro.productId}">Add Cart</a>

    </div>
</c:forEach>
</body>
</html>
