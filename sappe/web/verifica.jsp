<%-- 
    Document   : verifica
    Created on : 08/01/2013, 02:29:45
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
                <h1 class="titulo">Verificar Email</h1><br /><br /><br />

                <form action="ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdAdicionarAluno" />
                    <%@include file="error.jsp" %>
                    <label>Digite o Código de verificação que enviamos para o seu email:</label><br><br>
                    <input type="text" name="codigo"  style="width: 360px"/><br><br>
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