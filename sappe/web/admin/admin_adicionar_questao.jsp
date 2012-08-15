<%-- 
    Document   : admin_adicionar_questao
    Created on : 24/07/2012, 22:16:23
    Author     : gleyson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo">
                <img src="../images/sappe2.gif" width="959" height="76" alt="sappe2"/>
            </div>
            <%@include file="../admin/menu.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h2 class="titulo2">Adicionar Questão</h2></label><br /><br /><br />
                <div id="bh"></div>
                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdAdminAdicionarQuestao" />
                    <%@include file="../error.jsp" %>
                    <table border="0">
                        <tbody>
                            <tr>
                                <td>Área (*): </td>
                                <td>
                                    <select name="aid">
                                        <option value="0">Selecione</option>
                                        <option value="1">Matemática</option>
                                        <option value="2">Fundamentos da Computação</option>
                                        <option value="3">Tecnologia da Computação</option>
                                        <option value="4">Sistemas de Informação</option>
                                        <option value="5">Engenharia de Software</option>
                                        <option value="6">Conhecimentos Gerais</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Exame (*):</td>
                                <td>
                                    <select name="eid">
                                        <option value="0">Selecione</option>
                                        <option value="1">Poscomp</option>
                                        <option value="2">Enade</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Ano (*):</td>
                                <td><input type="text" name="ano" value="" size="20" /></td>
                            </tr>
                            <tr>
                                <td>Item Certo (*):</td>
                                <td>
                                    <select name="ic">
                                        <option value="0">Selecione</option>
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="D">D</option>
                                        <option value="E">E</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td> Questão (*):</td>
                                <td><input type="file" name="questao" value="" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Enviar" name="Enviar" /></td>
                                <td><input type="reset" value="Limpar" name="Limpar" /></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div id="direita"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>