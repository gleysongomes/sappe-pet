<%--
    Document   : index
    Created on : 28/12/2011, 18:20:23
    Author     : gleyson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link href="css/styleIndex.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="Script.js"> </script>
        <title>Simulador do Ambiente das Provas do POSCOMP e Enade – SAPPE</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo"><img src="images/sappe2.gif" width="959" height="76" alt="sappe2"/></div>
                <%@include file="/menu.jsp" %>
            <br />
            <div id="esquerda">
                <div id="loginSenha">
                    <div align="center">
                        <form action="ServletCentral" method="POST">
                            <input type="hidden" name="comando" value="CmdLogin" />
                            <%@include file="error.jsp" %>
                            <div id="">
                                <label>Login:</label><br/>
                                <input type="text" name="login" value="" size="20"/><br/>
                                <label>Senha:</label><br/>
                                <input type="password" name="senha" value="" size="20"/><br/>
                                <label>Conta:</label><br/>
                                <select name="conta" style="width: 180px;">
                                    <option value="0">Selecione</option>
                                    <option value="alu">Aluno</option>
                                    <option value="admin">Administrador</option>
                                </select>
                            </div>
                            <br /><br />
                            <table border="0">
                                <thead>
                                    <tr>
                                        <th width="95px;">
                                            <input class="button" title="Enviar" type="submit" value="Enviar" name="enviar" />
                                        </th>
                                        <th width="95px;">
                                            <input class="button" title="Limpar" type="reset" value="Limpar" name="Limpar" />
                                        </th>
                                </thead>
                            </table>
                        </form>
                        <p><a style="font-size: small" href="recuperar_senha.jsp">Esqueceu a senha?</a></p>
                    </div>
                </div>
            </div>
            <div id="meio">
            </div>
            <div id="direita"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="images/UFC2.png"/></center>
                <h6>Versão 1.12 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>