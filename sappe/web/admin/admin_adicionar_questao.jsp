<%-- 
    Document   : admin_adicionar_questao
    Created on : 24/07/2012, 22:16:23
    Author     : gleyson
--%>
<%@page import="br.ufc.si.pet.sappe.entidades.Administrador"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Perfil"%>

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
        <div id="content">
            <div id="top">
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../admin/menu.jsp" %>
            <div id="content_left">
                <h1 class="titulo">Adicionar Questão</h1><br />
               
                <form action="../ServletAdminAdicionarQuestao" method="POST" enctype="multipart/form-data">
                    <%@include file="../error.jsp" %>
                    <table border="0">
                        <tbody>
                            <tr>
                                <td><label>Exame:</label></td>
                                <td >
                                    <select name="eid" style="width: 250px;">
                                        <option value="0">Selecione</option>
                                        <option value="1">Poscomp</option>
                                        <option value="2">Enade</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Área: </label></td>
                                <td>
                                    <select name="aid" style="width: 250px;">
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
                                <td><label>Ano:</label></td>
                                <td><input type="text" name="ano" value="" style="width: 245px;" onkeypress="return validaNumerosSilencioso(event);" maxlength="4"/></td>
                            </tr>
                            <tr>
                                <td><label>Item Certo:</label></td>
                                <td>
                                    <select name="ic" style="width: 250px;">
                                        <option value="0">Selecione</option>
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="D">D</option>
                                        <option value="E">E</option>
                                        <option value="N">NULA</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Questão:</label></td>
                                <td><input type="file" name="questao" value="Arquivo" size="14" /></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="Salvar" name="Salvar" class="button"/> <input type="reset" value="Limpar" name="Limpar" class="button"/></td>
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