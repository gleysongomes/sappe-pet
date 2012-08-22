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
        <link href="css/style2.css" rel="stylesheet" type="text/css" />
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content">
            <div id="top">
                <img src="images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>

            <div id="direita"></div>
            <div id="content_center">
                <label><h1 class="titulo">Recuperar Senha</h1></label><br /><br /><br />
                <div id="bh"></div>
                <form action="ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdRecuperarSenha" />
                    <%@include file="error.jsp" %>
                    <label>Digite aqui o email que você cadastrou:</label><br><br>
                    <input type="text" name="email" class="buscar" style="width: 300px"/><br><br>
                    <input type="submit" value="Enviar"  class="button">
                </form>
                    <br/><br/>
                <a href="" title="Voltar" onclick="history.back(); return false;" class="button2">Voltar</a>
            </div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="images/UFC2.png"/></center>
                <h6>VersÃ£o 1.12 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>