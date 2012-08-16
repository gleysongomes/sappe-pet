<%-- 
    Document   : refazer_prova
    Created on : 29/12/2011, 02:52:02
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.Tipo"%>
<%@page import="br.ufc.si.pet.sappe.entidades.QuestaoProva"%>
<%@page import="br.ufc.si.pet.sappe.util.Util"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%

            Tipo tipo = (Tipo) session.getAttribute("tipo2");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" language="javascript" src="../js/Script.js"></script>
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
                <label><h3 class="titulo2"><%=tipo.getNome()%><label class="imagemPdf"><a href="../ServletCentral?comando=CmdGerarPdfProva" target="_blank"><img src="../images/pdf.jpeg" width="30" height="30" alt="pdf"/></a></label>
                    </h3></label><br /><br /><br />
                <div id="bh"></div>
                <form name="rP" action="../ServletCentral" method="post">
                    <input type="hidden" name="comando" value="CmdSalvarProvaEditada" />
                    <%@include file="../error.jsp" %>

                    <%
                                int itemIncr = 0;
                                List<QuestaoProva> qPs = (List<QuestaoProva>) session.getAttribute("qPs");
                                for (QuestaoProva qp : qPs) {
                    %>
                    <label>Questão <%= itemIncr + 1%>:<br /><img src="../ServletCentral?comando=CmdListarImagesById&id=<%= qp.getQuestao_id()%>" alt="images"/>
                    </label><br />
                    <table border="0">
                        <thead>
                            <tr>
                                <td width="80px">(a)<input type="checkbox" name="iM<%= itemIncr%>" value="A" <%= Util.marcarRadio2("A", qp.getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(b)<input type="checkbox" name="iM<%= itemIncr%>" value="B" <%= Util.marcarRadio2("B", qp.getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(c)<input type="checkbox" name="iM<%= itemIncr%>" value="C" <%= Util.marcarRadio2("C", qp.getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(d)<input type="checkbox" name="iM<%= itemIncr%>" value="D" <%= Util.marcarRadio2("D", qp.getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(e)<input type="checkbox" name="iM<%= itemIncr%>" value="E" <%= Util.marcarRadio2("E", qp.getItem_marcado())%> onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                            </tr>
                        </thead>
                    </table>
                    <br /><br /><br />
                    <%itemIncr++;}%>
                    <br /><br />
                    <table border="0">
                        <thead>
                            <tr>
                                <th><input type="submit" value="Enviar" name="Enviar" class="button"/></th>
                                <th width="100px"><a href=""><input type="button" value="Voltar" name="Voltar" class="button"/></a></th>
                            </tr>
                        </thead>
                    </table>
                </form>
            </div>
            <div id="direita"></div>
            <div id="footer">
                <center><img alt="LogoArea UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>
