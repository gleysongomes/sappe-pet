<%-- 
    Document   : admin_adicionar_supervisor
    Created on : 29/08/2012, 09:53:51
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

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
            <div id="content_left" style="width: 900px; overflow:auto;height:440px;">
                <h1 class="titulo">Adicionar Supervisor</h1><br />
                <%@include file="../error.jsp" %>
                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdAdminBuscarAluno" />
                    <input type="text" name="nome" value="" size="20" />
                    <input type="submit" name="Buscar" value="Buscar" class="button"/>
                    <br/>
                </form>
                <br/>
                <table border="1px" style="margin-left: 0px;">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                                    int i = 0;
                                    List<Usuario> usuarios = (List<Usuario>) session.getAttribute("uS2");
                                    if (usuarios == null) {
                                        usuarios = new ArrayList<Usuario>();
                                    }
                                    for (Usuario u : usuarios) {
                        %>
                        <tr>
                            <td><center><%= u.getNome()%></center></td>
                            <td><center><%= u.getEmail()%></center></td>
                            <td><center><a href="../ServletCentral?comando=CmdAdminAdicionarSupervisor&id=<%=u.getId()%>">Adicionar</a></center></td>
                        </tr>
                        <%}%>
                    </tbody>
                    <tr>
                    </tr>
                </table>
            </div>
            <div id="content_right"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.12 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>