<%-- 
    Document   : visualizar_simulados
    Created on : 12/08/2012, 22:34:42
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.Simulado"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo">
                <img src="../images/sappe2.gif" width="959" height="76" alt="sappe2"/>
            </div>
            <%@include file="../alu/menu2.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h3 class="titulo">Visualizar Simulados</h3></label><br /><br /><br />
                <div id="bh"></div>
                <%@include file="../error.jsp" %>
                <div id="content">
                    <table>
                        <thead>
                            <tr>
                                <th class="tabela">Nome</th>
                                <th class="tabela">Data</th>
                                <th class="tabela">Horário Início</th>
                                <th class="tabela">Horário Término</th>
                                <th class="tabela">Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                        List<Simulado> simulados = (List<Simulado>) session.getAttribute("simulados");
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
            </div>
            <div id="direita"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>
