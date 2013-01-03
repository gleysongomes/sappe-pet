<%-- 
    Document   : admin_adicionar_area
    Created on : 26/08/2012, 22:45:07
    Author     : gleyson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style1.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript"  language="javascript" src="../js/Script.js"></script>
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <%@include file="../admin/redirect.jsp" %>
        <div id="content">
            <div id="top">
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../admin/menu.jsp" %>
            <div id="content_left">
                <h1 class="titulo">Adicionar Área</h1><br />
                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdAdminAdicionarArea" />
                    <%@include file="../error.jsp" %>
                    <table border="0">
                        <tbody>
                            <tr>
                                <td><label>Exame (*):</label></td>
                                <td >
                                    <select name="id" style="width: 250px;">
                                        <option value="0">Selecione</option>
                                        <option value="1">Poscomp</option>
                                        <option value="2">Enade</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Área (*): </td>
                                <td><input type="text" name="nome" value="" size="20" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Salvar" name="Salvar" class="button"/></td>
                                <td><input type="reset" value="Limpar" name="Limpar" class="button"/></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div id="content_right"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>