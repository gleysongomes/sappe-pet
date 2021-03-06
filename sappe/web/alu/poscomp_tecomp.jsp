<%--
    Document   : poscomp_tecomp
    Created on : 06/01/2012, 20:39:29
    Author     : gleyson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style1.css" rel="stylesheet" type="text/css" />
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content">
            <div id="top">
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../alu/menu2.jsp" %>
            <div id="content_left">
                <h1 class="titulo">Tecnologia da Computação</h1><br />

                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdListarQuestoes" />
                    <input type="hidden" name="id" value="3" />
                    <input type="hidden" name="ide" value="1" />
                    <input type="hidden" name="caminho" value="/alu/poscomp_tecomp.jsp" />
                    <%@include file="../error.jsp" %>
                    <label style="font: caption; font-size: 15px;">Selecione o número de Questões:<select name="nQ">
                            <option value="0">Selecione</option>
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="15">15</option>
                            <option value="20">20</option>
                        </select>
                    </label>
                    <input type="submit" value="Buscar" name="Buscar" class="button"/>
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
