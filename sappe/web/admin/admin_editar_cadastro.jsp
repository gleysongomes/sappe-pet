<%-- 
    Document   : admin_editar_cadastro
    Created on : 17/10/2012, 15:03:02
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Administrador" %>
<%@page import="br.ufc.si.pet.sappe.entidades.Perfil" %>
<%@page import="br.ufc.si.pet.sappe.entidades.Usuario" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    Usuario user = (Usuario) session.getAttribute("editar");
%>

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
            <%@include file="../admin/menu.jsp" %>
            <div id="content_left" style="width: 900px; overflow:auto;height:430px; margin-top: 10px;" >
                <h1 class="titulo"style="width: 875px;" >Buscar Questões por Ano</h1><br />

                    <%@include file="../error.jsp" %>
                    <form action="../ServletCentral">
                        <input type="hidden" name="comando" value="CmdAdminSalvarCadastroEditado" />

                        <table border="0" style="margin-left: 0px;" >
                            <tbody>
                                <tr>
                                    <td ><label>Login:</label></td>
                                    <td><input type="text" name="login" value="<%= user.getLogin()%>" size="40" readonly="false"/></td>
                                </tr>
                                <tr>
                                    <td><label>Nome:</label></td>
                                    <td><input type="text" name="nome" value="<%= user.getNome()%>" size="40" /></td>
                                </tr>
                                <tr>
                                    <td ><label>Email:</label></td>
                                    <td><input type="text" name="email" value="<%= user.getEmail()%>" size="40" /></td>
                                </tr>
                                <tr>
                                    <td ><label>Senha Antiga:</label></td>
                                    <td><input type="password" name="santiga" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td ><label>Nova Senha:</label></td>
                                    <td><input type="password" name="nsenha" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td><label>Repita Senha:</label></td>
                                    <td><input type="password" name="rsenha" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="submit" value="Enviar" name="Enviar" class="button"/>
                                        <input type="reset" value="Limpar" name="Limpar" class="button"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
            </div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.12 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>
