<%@ page import="logic.helper" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer_SSD
  Date: 01.08.2019
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>First JSP</title>
</head>
<body>
    <h1>Testing JSP</h1>
    <p>
        <% helper helper = new helper(); %>
        <br>
        <%= helper.getInfo() %>

        <%
            for (int i=0; i<10; i++) {
                out.println("<br>" + i);
            }
        %>
        <%
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
        %>
        <br>
        <%= "Hello " + name + " "+ surname %>
    </p>
</body>
</html>
