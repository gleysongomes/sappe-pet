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
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <title>Simulador do Ambiente das Provas do POSCOMP e Enade – SAPPE</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo">
                <img src="images/sappe2.gif" width="959" height="76" alt="sappe2"/>
            </div>
            <%@include file="/menu.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h2 class="titulo2">Recuperar Senha</h2></label><br /><br /><br />
                <div id="bh"></div>
                <form action="ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdRecuperarSenha" />
                    <%@include file="error.jsp" %>
                    <label>Digite aqui o email que você cadastrou:</label><br><br>
                    <input type="text" name="email" class="buscar" style="width: 300px"/><br><br>
                    <input type="submit" value="Enviar"  class="button3">
                    <a href="" title="Voltar" onclick="history.back(); return false;" class="button2">Voltar</a>
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
