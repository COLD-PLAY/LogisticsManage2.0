<%@ page import="javafx.scene.shape.Circle" %><%--
  Created by IntelliJ IDEA.
  User: coldplay
  Date: 17-4-20
  Time: 下午11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <%!
    int i = 0;
    int a, b, c;
    Circle d = new Circle(2.0);
  %>
  <body>
  <%
    out.print("Your IP address is " + request.getRemoteAddr());
  %>
  </body>
</html>
