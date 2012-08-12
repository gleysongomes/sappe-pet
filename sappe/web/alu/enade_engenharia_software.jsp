<%-- 
    Document   : enade_engenharia_software
    Created on : 10/08/2012, 15:34:54
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
            <%@include file="../alu/menu2.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h2 class="titulo2">Engenharia de Software</h2></label><br /><br /><br />
                <div id="bh"></div>
                <form action="../ServletCentral" method="post" class="login">
                    <input type="hidden" name="comando" value="CmdListarQuestoes" />
                    <input type="hidden" name="id" value="5" />
                    <input type="hidden" name="ide" value="2" />
                    <input type="hidden" name="caminho" value="/alu/enade_engenharia_software.jsp" />
                    <%@include file="../error.jsp" %>
                    <label style="font: caption; font-size: 15px;">Selecione o número de Questões:<select name="nQ">
                            <option value="0">Selecione</option>
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="15">15</option>
                            <option value="20">20</option>
                        </select>
                    </label>
                    <input type="submit" value="Buscar" name="Buscar" class="button3"/>
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