<%--
    Document   : sup_adicionar_simulado
    Created on : 12/08/2012, 18:51:26
    Author     : gleyson
--%>
<%@page import="br.ufc.si.pet.sappe.entidades.Supervisor"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Perfil"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            Perfil perfil = (Perfil) session.getAttribute("user");
            if (!(perfil instanceof Supervisor)) {
                response.sendRedirect("../index.jsp");
            }
%>

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
            <div id="content_left">
                <h1 class="titulo">Adicionar Simulado</h1><br />

                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdSupervisorAdicionarSimulado" />
                    <%@include file="../error.jsp" %>
                    <table border="0" >
                        <tbody>
                            <tr>
                                <td><label>Nome:</label></td>
                                <td><input type="text" name="nome" value="" size="35" maxlength="80"/></td>
                            </tr>
                            <tr>
                                <td><label>Exame:</label></td>
                                <td><select name="exame" style="width: 233px;">
                                        <option value="0">Selecione</option>
                                        <option value="1">Poscomp</option>
                                        <option value="2">Enade</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td><label>Data:</label></td>
                                <td><input type="text" name="data" value="" size="35" onkeypress="return formataData(this,event)" maxlength="10"/></td>
                            </tr>
                            <tr>
                                <td><label>Horário de Inicio:</label></td>
                                <td><input type="text" name="hi" value="" size="35" onkeypress="return formataHorario(this,event)" maxlength="8"/></td>
                            </tr>
                            <tr>
                                <td><label>Horário de Término:</label></td>
                                <td><input type="text" name="ht" value="" size="35" onkeypress="return formataHorario(this,event)" maxlength="8"/></td>
                            </tr>
                            <tr>
                                <td><label>Número de Questões:</label></td>
                                <td><input type="text" name="nq" value="" size="35" maxlength="2" onkeypress="return validaNumerosSilencioso(event)"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="Salvar" name="Salvar" class="button"/> <input type="reset" value="Limpar" name="Limpar" class="button" /></td>
                            </tr>
                        </tbody>
                    </table>
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
