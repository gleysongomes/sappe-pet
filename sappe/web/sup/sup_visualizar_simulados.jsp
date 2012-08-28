<%--
    Document   : sup_visualizar_simulados
    Created on : 13/08/2012, 04:57:12
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
        <link href="../css/style1.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript"  language="javascript" src="../js/Script.js"></script>
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content">
            <div id="top">
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../sup/menu.jsp" %>
            <div id="content_left" style="width: 900px; overflow:auto;height:440px;">
                <h1 class="titulo">Visualizar Simulados</h1><br/>
                <%@include file="../error.jsp" %>
                <table style="margin-left: 170px;" border="1px">
                    <thead>
                        <tr>
                            <th class="tabela">Nome</th>
                            <th class="tabela">Data</th>
                            <th class="tabela">Número de Questões</th>
                            <th class="tabela">Horário Início</th>
                            <th class="tabela">Horário Término</th>
                            <th class="tabela">Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                                    List<Simulado> simulados = (List<Simulado>) session.getAttribute("sup_simulados");
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
                                <%= s.getNum_questao()%>
                            </td>
                            <td>
                                <%= s.getHoraini()%>
                            </td>
                            <td>
                                <%= s.getHorafim()%>
                            </td>
                            <td><a href="../ServletCentral?comando=CmdSupervisorVisualizarResultadoSimulado&id=<%= s.getId()%>">Visualizar Resultado</a>/<%if (s.getStatus() == true) {%><a href="../ServletCentral?comando=CmdSupervisorAdicionarAluno&id=<%= s.getId()%>">Adicionar Alunos</a>/<%}%><a href="../ServletCentral?comando=CmdSupervisorVisualizarGabarito&id=<%= s.getId()%>" target="_blank">Visualizar Gabarito </a>/<a href="../ServletCentral?comando=CmdSupervisorExcluirSimulado&id=<%= s.getId()%>" onclick="return confirmarExclucao();">Excluir</a></td>
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
