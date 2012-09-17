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
        <link href="../css/style1.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" language="javascript" src="../js/Script.js"></script>
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content">
            <div id="top">
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../alu/menu.jsp" %>
            <div id="content_left" style="width: auto;">
                <h1 class="titulo" style="width: 875px;"><%=tipo.getNome()%><label class="imagemPdf"><a href="../ServletCentral?comando=CmdGerarPdfProva" target="_blank"><img src="../images/pdf.jpeg" width="30" height="30" alt="pdf"/></a></label>
                </h1><br /><br /><br />
                <form name="rP" action="../ServletCentral" method="post">
                    <input type="hidden" name="comando" value="CmdSalvarProvaEditada" />
                    <%@include file="../error.jsp" %>
                    <%
                                int itemIncr = 0;
                                List<QuestaoProva> qPs = (List<QuestaoProva>) session.getAttribute("qPs");
                                for (QuestaoProva qp : qPs) {
                    %>
                    <label>Questão <%= itemIncr + 1%>:<br /><img src="../ServletCentral?comando=CmdListarImagesById&id=<%= qp.getQuestao_id()%>" style="width: 80%; height: 70%" alt="images"/>
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
                    <input type="submit" value="Atualizar" name="Atualizar" class="button" style="margin-left: 350px; width: 80px;"/> <input type="button" value="Voltar" onclick="history.back(); return false;" class="button" style=" width: 80px;" />
                </form>
            </div>
            <div id="content_right"></div>
            <%@include file="../footer2.jsp" %>
        </div>
    </body>
</html>
