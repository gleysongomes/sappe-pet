<%-- 
    Document   : enviar_arquivo
    Created on : 30/01/2012, 20:56:06
    Author     : gleyson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
            Long id = Long.parseLong(request.getParameter("id"));
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Enviar arquivo:</h3>
        <form name="upolud" action="../ServletUploud?id=<%=id%>" method="POST" enctype="multipart/form-data"
              <%@include file="/error.jsp" %>
              <input type="file" name="arq" size="5" value=""/>
            <input type="submit" value="Enviar" name="Enviar" />
        </form>
    </body>
</html>
