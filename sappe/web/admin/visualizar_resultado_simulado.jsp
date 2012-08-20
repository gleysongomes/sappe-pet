<%-- 
    Document   : visualizar_resultado_simulado
    Created on : 13/08/2012, 05:18:45
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.Simulado"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Usuario"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo">
                <img src="../images/sappe2.gif" width="959" height="76" alt="sappe2"/>
            </div>
            <%@include file="../admin/menu.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h3 class="titulo">Visualizar Resultado</h3></label><br /><br /><br />
                <div id="bh"></div>
                <%@include file="../error.jsp" %>
                <div id="content">
                    <table>
                        <thead>
                            <tr>
                                <th class="tabela">Nome</th>
                                <th class="tabela">Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                        List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuarios");
                                        for (Usuario u : usuarios) {
                            %>
                            <tr>
                                <td><center><%= u.getNome()%></center></td>
                                <td><center><a href="../ServletCentral?comando=CmdAdminVisualizarResultadoSimuladoAluno&id=<%= u.getId()%>" target="_blank">Visualizar Desempenho</a></center></td>
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
