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
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo">
                <img src="images/sappe2.gif" width="959" height="76" alt="sappe2"/>
            </div>
            <%@include file="menu.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h2 class="titulo2">Cadastro</h2></label><br /><br /><br />
                <div id="bh"></div>
                <form name="addAluno" action="ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdAdicionarAluno" />
                    <%@include file="error.jsp" %>
                    <div id="cadastro">
                        <table border="0">
                            <tbody>
                                <tr>
                                    <td>Login (*):</td>
                                    <td><input type="text" name="login" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Nome (*):</td>
                                    <td><input type="text" name="nome" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Email (*):</td>
                                    <td><input type="text" name="email" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Senha (*):</td>
                                    <td><input type="password" name="senha" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Repita Senha (*):</td>
                                    <td><input type="password" name="rsenha" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td><input type="submit" value="Enviar" name="Enviar" class="button3"/></td>
                                    <td><input type="reset" value="Limpar" name="Limpar" class="button3"/></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
            <div id="direita"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>
