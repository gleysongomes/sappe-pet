<%-- 
    Document   : visualizar_resultado_simulado
    Created on : 13/08/2012, 00:39:04
    Author     : gleyson
--%>
<%@page import="br.ufc.si.pet.sappe.util.Util"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Simulado"%>
<%@page import="br.ufc.si.pet.sappe.entidades.UsuarioSimulado"%>
<%@page import="br.ufc.si.pet.sappe.entidades.QuestaoSimulado"%>
<%@page import="br.ufc.si.pet.sappe.entidades.QuestaoUsuarioSimulado"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
            Simulado simulado = (Simulado) session.getAttribute("simulado2");
            UsuarioSimulado us = (UsuarioSimulado) session.getAttribute("usuarioSimulado");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <title>Simulador do Ambiente das Provas do Poscomp e Enade</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo">
                <img src="../images/sappe2.gif" width="959" height="76" alt="sappe2"/>
            </div>
            <%@include file="../alu/menu.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h3 class="titulo2">Questões do Simulado</h3></label><br /><br /><br />
                <div id="bh"></div>
                <%
                            int itemIncr = 0;
                            List<QuestaoUsuarioSimulado> quses = (List<QuestaoUsuarioSimulado>) session.getAttribute("quses2");
                            for (QuestaoUsuarioSimulado qs : quses) {
                %>
                <label>Questão <%= itemIncr + 1%>:<br /><img src="../ServletCentral?comando=CmdListarImagesById&id=<%= qs.getQuestao_id()%>" alt="images"/>
                </label><br />
                <table border="0">
                    <thead>
                        <tr>
                            <td width="80px">(a)<input type="checkbox" name="iM<%= itemIncr%>" value="A" <%= Util.marcarRadio("A", quses.get(itemIncr).getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                            <td width="80px">(b)<input type="checkbox" name="iM<%= itemIncr%>" value="B" <%= Util.marcarRadio("B", quses.get(itemIncr).getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                            <td width="80px">(c)<input type="checkbox" name="iM<%= itemIncr%>" value="C" <%= Util.marcarRadio("C", quses.get(itemIncr).getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                            <td width="80px">(d)<input type="checkbox" name="iM<%= itemIncr%>" value="D" <%= Util.marcarRadio("D", quses.get(itemIncr).getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                            <td width="80px">(e)<input type="checkbox" name="iM<%= itemIncr%>" value="E" <%= Util.marcarRadio("E", quses.get(itemIncr).getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                        </tr>
                    </thead>
                </table><br />
                <label>Status:  <%if (quses.get(itemIncr).getStatus() == 0) {%> <label>Branca</label><%} else if (quses.get(itemIncr).getStatus() == 1) {%> <label style="color: green;">Aceita</label><%} else {%><label style="color: red;">Errada</label><%}%></label><br /><br />
                <br />
                <%itemIncr++;}%>
                <br />
                <h3>Relatório do Simulado</h3><br>
                <div id="content">
                    <table>
                        <thead>
                            <tr>
                                <th width="200px">Nome</th>
                                <th>Questões Respondidas</th>
                                <th>Questões Certas</th>
                                <th>Questões Brancas</th>
                                <th>Questões Erradas</th>
                                <th>Tempo de Prova</th>
                                <th>Data</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><%= simulado.getNome()%></td>
                                <td><%= us.getRespondidas()%></td>
                                <td><%= us.getCertas()%></td>
                                <td><%= us.getBrancas()%></td>
                                <td><%= us.getErradas()%></td>
                                <td><%= us.getTempo_prova()%></td>
                                <td><%= simulado.getData()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <br /><br />
                <table border="0">
                    <thead>
                        <tr>
                            <th><a href="visualizar_simulados.jsp" class="button2">Voltar</a></th>
                        </tr>
                    </thead>
                </table>
            </div>
            <div id="direita"></div>
            <div id="footer">
                <center><img alt="Logoarea UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>
