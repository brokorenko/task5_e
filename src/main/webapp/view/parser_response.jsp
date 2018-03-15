<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.02.2018
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Parser responce</title>
</head>
<body>

<table border="1" width="100%" cellpadding="5">

    <c:forEach items="${books}" var="book">

    <tr>
        <th>category = ${book.category}</th>
    </tr>
    <td>
        title = ${book.title}
    </td>
    <td>
        author = ${book.author}
    </td>
    <td>
        year = ${book.year}
    </td>
    <td>
        price = ${book.price}
    </td>

    </c:forEach>
</table>

<c:forEach var="i" begin="0" end="${pageCount}" step="1">
    <a href="${pageContext.request.contextPath}?command=ParseResult&currentPage=${i}">${i + 1}</a>
</c:forEach>

</body>
</html>
