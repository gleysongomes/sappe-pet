<%--
    Document   : editar_cadastro
    Created on : 22/01/2012, 18:17:12
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.Usuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            Usuario usuario = (Usuario) session.getAttribute("editar");
%>

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
            <%@include file="../alu/menu.jsp" %>
            <div id="content_left">
                <h1 class="titulo">Editar Cadastro</h1><br /><br /><br />

                <form name="editarCadastro" action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdSalvarCadastroEditado" />
                    <%@include file="../error.jsp" %>

                    <table border="0" style="margin-left: 0px;" >
                            <tbody>
                                <tr>
                                    <td ><label>Login:</label></td>
                                    <td><input type="text" name="login" value="<%= usuario.getLogin()%>" size="40" readonly="false"/></td>
                                </tr>
                                <tr>
                                    <td><label>Nome:</label></td>
                                    <td><input type="text" name="nome" value="<%= usuario.getNome()%>" size="40" /></td>
                                </tr>
                                <tr>
                                    <td ><label>Email:</label></td>
                                    <td><input type="text" name="email" value="<%= usuario.getEmail()%>" size="40" /></td>
                                </tr>
                                <tr>
                                    <td ><label>Senha Antiga:</label></td>
                                    <td><input type="password" name="santiga" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td ><label>Nova Senha:</label></td>
                                    <td><input type="password" name="nsenha" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td><label>Repita Senha:</label></td>
                                    <td><input type="password" name="rsenha" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input type="submit" value="Enviar" name="Enviar" class="button"/> <input type="reset" value="Limpar" name="Limpar" class="button"/> </td>
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
