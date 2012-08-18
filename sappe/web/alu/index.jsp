<%-- 
    Document   : index
    Created on : 28/12/2011, 18:29:52
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.Aluno"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Perfil"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            Perfil perfil = (Perfil) session.getAttribute("user");
            if (!(perfil instanceof Aluno)) {
                response.sendRedirect("../index.jsp");
            }

            Aluno alu = (Aluno) session.getAttribute("user");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <title>Simulador do Ambiente das Provas do POSCOMP e Enade – SAPPE</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo">
                <img src="../images/sappe2.gif" width="959" height="76" alt="sappe2"/>
            </div>
            <%@include file="../alu/menu.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h2 class="titulo2">Bem-vindo: <%= alu.getUsuario().getNome()%></h2></label><br /><br /><br />
                <div id="bh"></div>
            </div>
            <div id="direita"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>
