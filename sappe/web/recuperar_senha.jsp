<%--
    Document   : alu_recuperar_senha
    Created on : 06/01/2012, 20:37:16
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
                <h1 class="titulo">Recuperar Senha</h1><br /><br /><br />
                
                <form action="ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdRecuperarSenha" />
                    <%@include file="error.jsp" %>
                    <label>Digite aqui o email que você cadastrou:</label><br><br>
                    <input type="text" name="emailrec"  style="width: 270px"/><br><br>
                    <input type="submit" value="Enviar"  class="button">
                    <input type="button" value="Voltar" onclick="history.back(); return false;" class="button" />
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
