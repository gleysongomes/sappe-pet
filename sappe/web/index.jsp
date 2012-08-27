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
        <link href="css/styleIndex1.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="Script.js"> </script>
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content">
            <div id="top"><img src="images/sappe2.gif" width="910" height="76" alt="sappe2"/></div>
            <div id="content_left">
                <h1 class="titulo">Login</h1>
                <center>
                    <form action="ServletCentral" method="POST" class="login">
                        <input type="hidden" name="comando" value="CmdLogin" />
                        <%@include file="error.jsp" %>
                        <fieldset>
                            <label>Login:</label><br/>
                            <input type="text" name="login" value="" size="30"/><br/>
                            <label>Senha:</label><br/>
                            <input type="password" name="senha" value="" size="30"/><br/>
                            <label>Conta:</label><br/>
                            <select name="conta" >
                                <option value="0">Selecione</option>
                                <option value="alu">Aluno</option>
                                <option value="sup">Supervisor</option>
                                <option value="admin">Administrador</option>
                            </select>
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
                        </fieldset>
                        <div id="gambis">
                            <a  href="recuperar_senha.jsp">Esqueceu a senha?</a><br>
                            <a  href="ServletCentral?comando=CmdRedirecionar&url=/cadastro.jsp">Criar uma Conta?</a>
                        </div>
                    </form>
                </center>
            </div>
            <div id="content_right">
                <h1 class="titulo">Simulador de Provas</h1>
                <p>O SAPPE tem como objetivo oferecer um suporte para os alunos que estudam para as provas do Poscomp e/ou Enade a fim de preparar-lo para a realização da mesma. Neste ambiente podem-se simular as provas ou personalizar-las de acordo com os seus objetivos de estudo. </p>
            </div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="images/UFC2.png" style=""/>
                    <h6>Versão 1.12 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
                </center>
            </div>
        </div>
    </body>
</html>