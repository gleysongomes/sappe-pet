<%--
    Document   : editar_cadastro
    Created on : 22/01/2012, 18:17:12
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.Usuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            Usuario usuario = (Usuario) session.getAttribute("editar");
%>

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
            <%@include file="/alu/menu.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h2 class="titulo2">Editar Cadastro</h2></label><br /><br /><br />
                <div id="bh"></div>
                <form name="editarCadastro" action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdEditarCadastro" />
                    <%@include file="/error.jsp" %>
                    <div id="cadastro">
                        <table border="0">
                            <tbody>
                                <tr>
                                    <td>Nome (*):</td>
                                    <td><input type="text" name="nome" value="<%= usuario.getNome()%>" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Email (*):</td>
                                    <td><input type="text" name="email" value="<%= usuario.getEmail()%>" size="40" /></td>
                                </tr>

                                <tr>
                                    <td>Telefone :</td>
                                    <td><input type="text" name="telefone" value="<%= usuario.getFone()%>" size="40" /></td>
                                </tr>

                                <tr>
                                    <td>Instituição :</td>
                                    <td><input type="text" name="instituicao" value="<%= usuario.getEmail()%>" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Rua :</td>
                                    <td><input type="text" name="rua" value="<%= usuario.getRua()%>" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Bairro :</td>
                                    <td><input type="text" name="bairro" value="<%= usuario.getBairro()%>" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Numero :</td>
                                    <td><input type="text" name="numero" value="<%= usuario.getNumero()%>" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Cidade :</td>
                                    <td><input type="text" name="cidade" value="<%= usuario.getCidade()%>" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>UF :</td>
                                    <td><input type="text" name="uf" value="<%= usuario.getUf()%>" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Senha (*):</td>
                                    <td><input type="password" name="senha" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td>Repita Senha (*):</td>
                                    <td><input type="password" name="rsenha" value="" size="40" /></td>
                                </tr>
                                <tr>
                                    <td><input type="submit" value="Enviar" name="Enviar" class="button3"/></td>
                                    <td><input type="reset" value="Limpar" name="Limpar" class="button3"/></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
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
