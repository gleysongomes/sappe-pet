<%-- 
    Document   : listar_questoes_simulado
    Created on : 12/08/2012, 23:19:05
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.util.Util"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Simulado"%>
<%@page import="br.ufc.si.pet.sappe.entidades.QuestaoSimulado"%>
<%@page import="br.ufc.si.pet.sappe.entidades.QuestaoUsuarioSimulado"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
            Simulado simulado = (Simulado) session.getAttribute("simulado");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript"  language="javascript" src="../js/Script.js"></script>
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo">
                <img src="../images/sappe2.gif" width="959" height="76" alt="sappe2"/>
            </div>
            <%@include file="../alu/menu.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h3 class="titulo2"><%=simulado.getNome()%></h3></label><br /><br /><br />
                <div id="bh"></div>
                <form id="lsQ" action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdSalvarSimulado" />
                    <%@include file="../error.jsp" %>
                    <%
                                int itemIncr = 0;
                                List<QuestaoSimulado> questoes = (List<QuestaoSimulado>) session.getAttribute("questaoSimulados");
                                List<QuestaoUsuarioSimulado> quses = (List<QuestaoUsuarioSimulado>) session.getAttribute("quses");
                                for (QuestaoSimulado qs : questoes) {
                    %>
                    <label>Questão <%= itemIncr + 1%>:<br /><img src="../ServletCentral?comando=CmdListarImagesById&id=<%= qs.getQuestao_id()%>" alt="images"/>
                    </label><br />
                    <table border="0">
                        <thead>
                            <% if (!quses.isEmpty() && quses != null) {%>
                            <tr>
                                <td width="80px">(a)<input type="checkbox" name="iM<%= itemIncr%>" value="A" <%= Util.marcarRadio2("A", quses.get(itemIncr).getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(b)<input type="checkbox" name="iM<%= itemIncr%>" value="B" <%= Util.marcarRadio2("B", quses.get(itemIncr).getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(c)<input type="checkbox" name="iM<%= itemIncr%>" value="C" <%= Util.marcarRadio2("C", quses.get(itemIncr).getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(d)<input type="checkbox" name="iM<%= itemIncr%>" value="D" <%= Util.marcarRadio2("D", quses.get(itemIncr).getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(e)<input type="checkbox" name="iM<%= itemIncr%>" value="E" <%= Util.marcarRadio2("E", quses.get(itemIncr).getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                            </tr>
                            <%} else {%>
                            <tr>
                                <td width="80px">(a)<input type="checkbox" name="iM<%= itemIncr%>" value="A" onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(b)<input type="checkbox" name="iM<%= itemIncr%>" value="B" onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(c)<input type="checkbox" name="iM<%= itemIncr%>" value="C" onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(d)<input type="checkbox" name="iM<%= itemIncr%>" value="D" onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(e)<input type="checkbox" name="iM<%= itemIncr%>" value="E" onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                            </tr>
                            <%}%>
                        </thead>
                    </table><br />
                    <%itemIncr++;}%>
                    <br /><br />
                    <table border="0">
                        <thead>
                            <tr>
                                <th><input type="submit" value="Salvar" name="Salvar" class="button"/></th>
                                <th width="100px"><input type="reset" value="Cancelar" name="Cancelar" class="button"/></th>
                            </tr>
                        </thead>
                    </table>
                </form>
            </div>
            <div id="direita"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.12 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>