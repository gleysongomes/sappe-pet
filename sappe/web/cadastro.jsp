<%--
    Document   : cadastro
    Created on : 28/12/2011, 21:15:12
    Author     : gleyson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style1.css" rel="stylesheet" type="text/css" />
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content">
            <div id="top">
                <img src="images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>

            <div id="content_left">

                <h1 class="titulo">Cadastro</h1><br />

                <form name="addAluno" action="ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdAdicionarAluno" />
                    <%@include file="error.jsp" %>
                    <table border="0" >
                            <tbody>
                                <tr>
                                    <td><label>Login:</label></td>
                                    <td><input type="text" name="login" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td><label>Nome:</label></td>
                                    <td><input type="text" name="nome" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td><label>Email:</label></td>
                                    <td><input type="text" name="email" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td><label>Senha:</label></td>
                                    <td><input type="password" name="senha" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td><label>Repita Senha:</label></td>
                                    <td><input type="password" name="rsenha" value="" size="40" /></td>
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
            <div id="content_right"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>
