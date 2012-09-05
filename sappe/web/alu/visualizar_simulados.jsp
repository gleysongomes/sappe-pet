<%--
    Document   : visualizar_simulados
    Created on : 12/08/2012, 22:34:42
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.Simulado"%>
<%@page import="java.util.*"%>

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
                <h1 class="titulo">Visualizar Simulados</h1><br /><br /><br />
                <%@include file="../error.jsp" %>
                <table border="1px" style="margin-left: 170px;">
                    <thead>
                        <tr>
                            <th >Nome</th>
                            <th >Data</th>
                            <th >Horário Início</th>
                            <th >Horário Término</th>
                            <th >Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                                    List<Simulado> simulados = (List<Simulado>) session.getAttribute("simulados");
                                    if (simulados == null) {
                                        simulados = new ArrayList<Simulado>();
                                    }
                                    for (Simulado s : simulados) {
                        %>
                        <tr>
                            <td>
                                <%= s.getNome()%>
                            </td>
                            <td>
                                <%= s.getData()%>
                            </td>
                            <td>
                                <%= s.getHoraini()%>
                            </td>
                            <td>
                                <%= s.getHorafim()%>
                            </td>
                            <td><a href="../ServletCentral?comando=CmdRealizarSimulado&id=<%= s.getId()%>">Realizar Simulado</a>/<a href="../ServletCentral?comando=CmdVisualizarResultadoSimulado&id=<%= s.getId()%>">Visualizar Resultado</a></td>
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