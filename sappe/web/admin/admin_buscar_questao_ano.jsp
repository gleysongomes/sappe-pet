<%-- 
    Document   : admin_buscar_questao_ano
    Created on : 04/09/2012, 09:34:44
    Author     : mardson
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="br.ufc.si.pet.sappe.util.Util"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Tipo"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Questao"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Exame"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Area"%>
<%@page import="br.ufc.si.pet.sappe.service.ExameService"  %>
<%@page import="br.ufc.si.pet.sappe.service.AreaService"  %>
<%@page import="java.util.List"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style1.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript"  language="javascript" src="../js/Script.js"></script>
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content" >
            <div id="top" >
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../admin/menu2.jsp" %>
            <div id="content_left" style="width: 900px; overflow:auto;height:430px; margin-top: 10px;" >
                <h1 class="titulo"style="width: 875px;" >Buscar Questões por Ano</h1><br />

                    <%@include file="../error.jsp" %>
                    <form action="../ServletCentral">
                        <input type="hidden" name="comando" value="CmdAdminVisualizarQuestoes" />
                        <label>Digite o ano:</label><input type="text" name="ano"  />
                         <input type="submit" value="buscar" name="buscar" class="button" />
                    </form>



            </div>

            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.12 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>