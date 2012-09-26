<%--
    Document   : listar_questoes
    Created on : 28/12/2011, 21:54:10
    Autdor     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.util.Util"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Tipo"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Questao"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 tdansitional//EN"
    "http://www.w3.org/td/html4/loose.dtd">
<%
            Tipo tipo = (Tipo) session.getAttribute("tipo");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style1.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript"  language="javascript" src="../js/Script.js"></script>
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content" >
            <div id="top" >
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../alu/menu.jsp" %>
            <div id="content_left" style="width: auto;">
                <h1 class="titulo"style="width: 875px;" ><%=tipo.getNome()%></h1><br />
                <form id="lsQ" action="../ServletCentral" method="POST"  style="margin-top: 0px;">
                    <input type="hidden" name="comando" value="CmdSalvarProva" />
                    <%--  <%@include file="../error.jsp" %> --%>
                    <%
                                int itemIncr = 0;
                                List<Questao> questoes = (List<Questao>) session.getAttribute("subListaDeQuestoes");
                                for (Questao q : questoes) {
                    %>
                    <label>Questão <%= itemIncr+1%>:<br /><img src="../ServletCentral?comando=CmdListarImagesById&id=<%= q.getId()%>" style="width: 80%; height: 70%" alt="images" />
                    </label><br />
                    <table border="0">
                        <thead>
                            <tr>
                                <td width="80px">(a)<input type="checkbox" name="iM<%= itemIncr%>" value="A" onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(b)<input type="checkbox" name="iM<%= itemIncr%>" value="B" onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(c)<input type="checkbox" name="iM<%= itemIncr%>" value="C" onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(d)<input type="checkbox" name="iM<%= itemIncr%>" value="D" onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                                <td width="80px">(e)<input type="checkbox" name="iM<%= itemIncr%>" value="E" onclick="Checkbox(this, 'iM<%= itemIncr%>');"/></td>
                            </tr>
                        </thead>
                    </table>
                    <br />
                    <%itemIncr++;}%>
                    <br /><br />
                    <input type="submit" value="Salvar" name="Salvar" class="button" style="margin-left: 350px; width: 80px;"/>
                               <input type="reset" value="Cancelar" name="Cancelar" class="button"/>
                </form>
            </div>
                    <%@include file="../footer2.jsp" %>
        </div>
    </body>
</html>