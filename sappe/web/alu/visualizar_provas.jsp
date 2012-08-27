<%-- 
    Document   : visualizar_provas
    Created on : 29/12/2011, 02:57:31
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.service.TipoService"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Tipo"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Prova"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style1.css" rel="stylesheet" type="text/css" />
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content">
            <div id="top">
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../alu/menu.jsp" %>
            <div id="content_left" style="width: 900px; overflow:auto;height:440px;">
                <h1 class="titulo">Visualizar Provas</h1><br /><br /><br />
                <%@include file="../error.jsp" %>
                <table border="1px" style="margin-left: 170px;">
                    <thead>
                        <tr>
                            <th >Prova</th>
                            <th >Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                                    List<Prova> provas = (List<Prova>) session.getAttribute("provas");
                                    for (Prova p : provas) {
                                        TipoService tS = new TipoService();
                                        Tipo tipo = tS.getTipoById(p.getTipo_id());
                        %>
                        <tr>
                            <td>
                                <%= tipo.getNome()%> - <%= p.getData()%>
                            </td>
                            <td><a href="../ServletCentral?comando=CmdEditarProva&id=<%= p.getId()%>">Refazer Prova</a>/<a href="../ServletCentral?comando=CmdVisualizarResultado&id=<%= p.getId()%>">Visualizar Resultado</a></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
            <div id="content_right"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>
