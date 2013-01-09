<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="br.ufc.si.pet.sappe.entidades.*"%>

<%
            Perfil perfil = (Perfil) session.getAttribute("user");
            if ((perfil == null) || !(perfil instanceof Supervisor)) {
                response.sendRedirect("../index.jsp");
            }
%>