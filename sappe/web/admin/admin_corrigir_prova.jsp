<%-- 
    Document   : admin_corrigir_prova
    Created on : 20/01/2012, 07:31:48
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.service.ProvaService"%>
<%@page import="br.ufc.si.pet.sappe.service.TipoService"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Tipo"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Prova"%>
<%@page import="br.ufc.si.pet.sappe.entidades.QuestaoProva"%>
<%@page import="br.ufc.si.pet.sappe.util.Util"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
            Tipo tipo3 = (Tipo) session.getAttribute("tipo3");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript"  language="javascript" src="../js/Script.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo">
                <img src="../images/sappe2.gif" width="959" height="76" alt="sappe2"/>
            </div>
            <%@include file="/admin/menu.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h3 class="titulo2"><%=tipo3.getNome()%></h3></label><br /><br /><br />
                <div id="bh"></div>
                <form id="corrigirProva" name="corrigirProva" action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdAdminSalvarCorrecaoProva" />
                    <%@include file="/error.jsp" %>
                    <%
                                int itemIncr = 1;
                                List<QuestaoProva> qPs = (List<QuestaoProva>) session.getAttribute("questaoProvas");

                                for (QuestaoProva qp : qPs) {
                    %>
                    <label>Questão <%= itemIncr%>:<br /><img src="../images/poscomp/<%= qp.getNome()%>" alt="images"/>
                    </label><br />
                    <table border="0">
                        <thead>
                            <tr>
                                <td width="80px">(a)<input type="checkbox" name="iM<%= itemIncr%>" value="A" <%= Util.marcarRadio("A", qp.getItem_marcado())%>/></td>
                                <td width="80px">(b)<input type="checkbox" name="iM<%= itemIncr%>" value="B" <%= Util.marcarRadio("B", qp.getItem_marcado())%>/></td>
                                <td width="80px">(c)<input type="checkbox" name="iM<%= itemIncr%>" value="C" <%= Util.marcarRadio("C", qp.getItem_marcado())%>/></td>
                                <td width="80px">(d)<input type="checkbox" name="iM<%= itemIncr%>" value="D" <%= Util.marcarRadio("D", qp.getItem_marcado())%>/></td>
                                <td width="80px">(e)<input type="checkbox" name="iM<%= itemIncr%>" value="E" <%= Util.marcarRadio("E", qp.getItem_marcado())%>/></td>
                            </tr>
                        </thead>
                    </table>
                    <br />
                    <label>Resposta desta Questão:<br><textarea cols="50" rows="6" disabled><%= qp.getResposta()%></textarea></label><br /><br />
                    <label style="font-size: 15px;">Dica para solução desta Questão:</label><br />
                    <textarea cols="50" rows="2" name="dica<%= itemIncr%>" onkeydown="textCounter(this.form.dica<%= itemIncr%>,this.form.remLen<%= itemIncr%>,299);" onkeyup="textCounter(this.form.res<%= itemIncr%>,this.form.remLen<%= itemIncr%>,299);"><%= qp.getDica()%></textarea>
                    <br /><label style="font-size: 9pt; color: gray; font-style: normal;">Limite de 299 caracteres. Faltam <input readonly type="text" name="remLen<%= itemIncr%>" size="3" maxlength="3" value="299" disabled>.</label><br/><br />
                    <label>Quanto a resposta desta Questão: </label><input type="checkbox" id="status<%= itemIncr%>" name="status<%= itemIncr%>" value="1" onclick="Checkbox(this, 'status<%= itemIncr%>');" <%= Util.marcarRadio3(1, qp.getStatus())%>/> Aceitar<input type="checkbox" id="status<%= itemIncr%>" name="status<%= itemIncr%>" value="2" onclick="Checkbox(this, 'status<%= itemIncr%>');" <%= Util.marcarRadio3(2, qp.getStatus())%>/>Não Aceitar
                    <br /><br /><br />
                    <%itemIncr++;}%>
                    <br /><br />
                   <table border="0">
                        <thead>
                            <tr>
                                <th><input type="submit" value="Enviar" name="Enviar" class="button"/></th>
                                <th width="100px"><input type="reset" value="Cancelar" name="Cancelar" class="button"/></th>
                            </tr>
                        </thead>
                    </table>
                </form>
            </div>
            <div id="direita"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>
