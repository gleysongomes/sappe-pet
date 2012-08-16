<%-- 
    Document   : poscomp_padrao
    Created on : 23/01/2012, 01:40:22
    Author     : gleyson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <title>Simulador do Ambiente das Provas do POSCOMP e Enade – SAPPE</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo">
                <img src="../images/sappe2.gif" width="959" height="76" alt="sappe2"/>
            </div>
            <%@include file="../alu/menu2.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h2 class="titulo2">Poscomp Padrão</h2></label><br /><br /><br />
                <div id="bh"></div>
                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdListarQuestoesExamePadrao" />
                    <input type="hidden" name="id" value="7" />
                    <input type="hidden" name="ide" value="7" />
                    <input type="hidden" name="nQ" value="70" />
                    <input type="hidden" name="caminho" value="/alu/poscomp_padrao.jsp" />
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
