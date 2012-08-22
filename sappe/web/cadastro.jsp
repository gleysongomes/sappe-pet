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
        <link href="css/style2.css" rel="stylesheet" type="text/css" />
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content">
            <div id="top">
                <img src="images/sappe2.gif" width="910"  height="76" alt="sappe2"/>
            </div>

            <div id="content_center">
                <label><h1 class="titulo">Cadastro</h1></label><br /><br /><br />
                <div id="bh"></div>
                <form name="addAluno" action="ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdAdicionarAluno" />

                    <div id="cadastro">
                        <table border="0">
                            <tbody>
                                <tr>
                                    <td></td>
                                    <td><%@include file="error.jsp"%></td>
                                </tr>
                                <tr>
                                    <td>Login*:</td>
                                    <td><input type="text" name="login" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Nome*:</td>
                                    <td><input type="text" name="nome" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Email*:</td>
                                    <td><input type="text" name="email" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Senha*:</td>
                                    <td><input type="password" name="senha" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Repetir Senha*:</td>
                                    <td><input type="password" name="rsenha" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input type="submit" value="Enviar" name="Enviar" class="button"/><input type="reset" value="Limpar" name="Limpar" class="button"/></td>
                                </tr>
                            </tbody>
                        </table>
                        <br /><br />
                                <a href="" title="Voltar" onclick="history.back(); return false;" class="button2">Voltar</a>
                    </div>
                </form>
            </div>

            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="images/UFC2.png"/></center>
                <h6>VersÃ£o 1.12 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>