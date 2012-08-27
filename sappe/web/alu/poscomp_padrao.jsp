<%--
    Document   : poscomp_padrao
    Created on : 23/01/2012, 01:40:22
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.Questao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
            <%@include file="../alu/menu2.jsp" %>
            <div id="content_left">
                <h1 class="titulo">Poscomp Padrão</h1><br />

                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdListarQuestoesExamePadrao" />
                    <input type="hidden" name="id" value="9" />
                    <input type="hidden" name="ide" value="1" />
                    <input type="hidden" name="nQ" value="70" />
                    <input type="hidden" name="caminho" value="/alu/poscomp_padrao.jsp" />
                    <%@include file="../error.jsp" %>
                    <label style="font: caption; font-size: 15px;">Selecione o ano da prova:<select name="ano">
                            <option value="0">Selecione</option>
                            <%
                                        List<Questao> questoes = (ArrayList<Questao>) session.getAttribute("ap");
                                        for (Questao q : questoes) {
                            %>
                            <option value="<%= q.getAno()%>"><%= q.getAno()%></option>
                            <%}%>
                        </select>
                    </label>
                    <input type="submit" value="Buscar" name="Buscar" class="button"/>
                </form>
            </div>
            <div id="content_right"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>