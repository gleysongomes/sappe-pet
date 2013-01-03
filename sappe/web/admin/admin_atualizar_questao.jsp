<%-- 
    Document   : admin_atualizar_questao
    Created on : 04/09/2012, 14:31:33
    Author     : mardson
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.ufc.si.pet.sappe.entidades.Questao"%>

<%
            Questao q = (Questao) session.getAttribute("questao");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style1.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript"  language="javascript" src="../js/Script.js"></script>
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <%@include file="../admin/redirect.jsp" %>
        <div id="content" >
            <div id="top" >
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../admin/menu.jsp" %>
            <div id="content_left" style="width: 900px; overflow:auto;height:430px; margin-top: 10px;" >
                <h1 class="titulo"style="width: 875px;" >Atualizar Questão</h1><br />
                <%@include file="../error.jsp" %>
                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdAdminAtualizarQuestao" />
                    <input type="hidden" name="id" value="<%= q.getId()%>"/>
                    <table border="0" >
                        <tbody>
                            <tr>
                                <td><label>Nome:</label></td>
                                <td><input type="text" name="nome" value="<%= q.getNome()%>" size="40" /></td>
                            </tr>
                            <tr>
                                <td><label>Item Correto:</label></td>
                                <td><input type="text" name="item" value="<%= q.getItem()%>" size="40" /></td>
                            </tr>
                            <tr>
                                <td><label>ano:</label></td>
                                <td><input type="text" name="ano" value="<%= q.getAno()%>" size="40" /></td>
                            </tr>
                            <tr>
                                <td><label>Area_id:</label></td>
                                <td><input type="text" name="area_id" value="<%= q.getArea_id()%>" size="40" /></td>
                            </tr>
                            <tr>
                                <td><label>Exame_id:</label></td>
                                <td><input type="text" name="exame_id" value="<%= q.getExame_id()%>" size="40" /></td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td><input type="submit" value="Enviar" name="Enviar" class="button"/><input type="button" value="Voltar" onclick="history.back(); return false;" class="button" /></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.12 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>