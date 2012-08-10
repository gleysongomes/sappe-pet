<%-- 
    Document   : enade_padrao
    Created on : 10/08/2012, 15:34:08
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
            <%@include file="/alu/menu2.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h2 class="titulo2">Enade Padrão</h2></label><br /><br /><br />
                <div id="bh"></div>
                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdListarQuestoesExamePadrao" />
                    <input type="hidden" name="id" value="8" />
                    <input type="hidden" name="ide" value="8" />
                    <input type="hidden" name="nQ" value="60" />
                    <input type="hidden" name="caminho" value="/alu/enade_padrao.jsp" />
                    <%@include file="../error.jsp" %>
                    <label style="font: caption; font-size: 15px;">Selecione o ano da prova:<select name="ano">
                            <option value="0">Selecione</option>
                            <option value="2002">2002</option>
                            <option value="2008">2008</option>
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