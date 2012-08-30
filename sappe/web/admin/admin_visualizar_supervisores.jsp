<%-- 
    Document   : admin_visualizar_supervisores
    Created on : 29/08/2012, 10:58:49
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.Supervisor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style1.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript"  language="javascript" src="../js/Script.js"></script>
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content">
            <div id="top">
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../admin/menu.jsp" %>
            <div id="content_left" style="width: 900px; overflow:auto;height:440px;">
                <h1 class="titulo">Visualizar Supervisores</h1><br />
                <%@include file="../error.jsp" %>
                <table border="1px" style="margin-left: 170px;">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                                    List<Supervisor> supervisores = (List<Supervisor>) session.getAttribute("supervisores");
                                    if (supervisores == null) {
                                        supervisores = new ArrayList<Supervisor>();
                                    }
                                    for (Supervisor s : supervisores) {
                        %>
                        <tr>
                            <td><center><%= s.getUsuario().getNome()%></center></td>
                            <td><center><%= s.getUsuario().getEmail()%></center></td>
                            <td><center><a href="../ServletCentral?comando=CmdAdminExcluirSupervisor&id=<%=s.getId()%>">Excluir</a></center></td>
                        </tr>
                        <%}%>
                    </tbody>
                    <tr>
                    </tr>
                </table>
            </div>
            <div id="content_right"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.12 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>