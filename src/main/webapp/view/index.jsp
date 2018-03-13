<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 07.03.2018
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose Parcer</title>
</head>
<body>
<form action="FrontController">
    <input type="hidden" name="type"   value="sax" />
    <input type="hidden" name="command"   value="Parse" />
    <button type="submit">SAX</button>
</form>
<form action="FrontController">
    <input type="hidden" name="type"   value="stax" />
    <input type="hidden" name="command"   value="Parse" />
    <button type="submit">STAX</button>
</form>
<form action="FrontController">
    <input type="hidden" name="type"   value="dom" />
    <input type="hidden" name="command"   value="Parse" />
    <button type="submit">DOM</button>
</form>
</body>
</html>
