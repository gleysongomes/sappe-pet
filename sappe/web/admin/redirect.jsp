<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="br.ufc.si.pet.sappe.entidades.*"%>

<%
            Perfil p = (Perfil) session.getAttribute("user");
            if (p == null) {
                response.sendRedirect("/index.jsp");
            } else {
                //esta logado mas não é admin.
                if ((p != null) && (p.getPapel().getId() != 3)) {
                    session.removeAttribute("user");
                    response.sendRedirect("/index.jsp");
                }
            }
%>