<%-- 
    Document   : sup_adicionar_aluno_simulado
    Created on : 28/08/2012, 09:34:10
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
            <%@include file="../sup/menu.jsp" %>
            <div id="content_left">
                <h1 class="titulo">Adicionar Alunos</h1><br />
                <%@include file="../error.jsp" %>
                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdSupervisorBuscarAluno" />
                    <input type="text" name="nome" value="" size="20" />
                    <input type="submit" name="Buscar" value="Bucar"/>
                </form>
                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdSupervisorAdicionarAlunoSimulado" />
                    <table>
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
                                        List<Usuario> usuarios = (List<Usuario>) session.getAttribute("uS");
                                        if (usuarios == null) {
                                            usuarios = new ArrayList<Usuario>();
                                        }
                                        for (Usuario u : usuarios) {
                            %>
                            <tr>
                                <td><center><%= u.getNome()%></center></td>
                                <td><center><%= u.getEmail()%></center></td>
                                <td><center><input type="checkbox" name="id<%=i++%>" value="<%= u.getId()%>"/></center></td>
                            </tr>
                            <%}%>
                        </tbody>
                        <tr>
                            <td><input type="hidden" value="<%= usuarios.size()%>" name="na" /></td>
                            <td><input type="submit" value="Salvar" name="Salvar" /></td>
                        </tr>
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