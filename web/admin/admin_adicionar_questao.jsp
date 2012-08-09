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
            <%@include file="/alu/menu2.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h2 class="titulo2">Adicionar Questão</h2></label><br /><br /><br />
                <div id="bh"></div>
                <form action="../ServletAdminAdicionarQuestao" method="POST" enctype="multipart/form-data">
                    <%@include file="../error.jsp" %>
                    <label style="font: caption; font-size: 15px;">Área: <select name="aid">
                            <option value="0">Selecione</option>
                            <option value="1">Matemática</option>
                            <option value="2">Fundamentos da Computação</option>
                            <option value="3">Tecnologia da Computação</option>
                        </select>
                    </label>
                    <br />
                    <label style="font: caption; font-size: 15px;">Exame: <select name="eid">
                            <option value="0">Selecione</option>
                            <option value="1">Poscomp</option>
                            <option value="2">Enade</option>
                        </select>
                    </label>
                    <br />
                    <label style="font: caption; font-size: 15px;">Ano:
                        <input type="text" name="ano" value="" size="20" />
                    </label>
                    <br />
                    <label style="font: caption; font-size: 15px;">Questão: </label>
                    <input type="file" name="questao" value="" />
                    <br />
                    <input type="submit" id="btn" value="Cadastrar" name="cadastrar" class="button"/>
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