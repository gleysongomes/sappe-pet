<%--
    Document   : visualizar_resultado_aluno
    Created on : 13/08/2012, 06:51:31
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.ResultadoUsuarioSimulado"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            ResultadoUsuarioSimulado us = (ResultadoUsuarioSimulado) session.getAttribute("resultadoUsuarioSimulado");

%>
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
            <%@include file="../sup/menu.jsp" %>
            <div id="content_left">
                <h1 class="titulo">Resultado do Simulado</h1><br />
                <table border="1px" style="margin-left: 170px;" >
                        <thead>
                            <tr>
                                <th>Questões Respondidas</th>
                                <th>Questões Certas</th>
                                <th>Questões Brancas</th>
                                <th>Questões Erradas</th>
                                <th>Tempo de Prova</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><%= us.getRespondidas()%></td>
                                <td><%= us.getCertas()%></td>
                                <td><%= us.getBrancas()%></td>
                                <td><%= us.getErradas()%></td>
                                <td><%= us.getTempo_prova()%></td>
                            </tr>
                        </tbody>
                    </table>

                <br /><br />
                <table border="0">
                    <thead>
                        <tr>
                            <th><a href="../sup/visualizar_resultado_simulado.jsp" class="button">Voltar</a></th>
                        </tr>
                    </thead>
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
