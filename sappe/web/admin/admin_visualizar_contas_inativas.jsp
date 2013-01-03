<%-- 
    Document   : admin_visualizar_contas_inativas
    Created on : 05/09/2012, 15:30:38
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.*"%>
<%@page import="java.util.*"%>

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
        <%@include file="../admin/redirect.jsp" %>
        <div id="content">
            <div id="top">
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../admin/menu.jsp" %>
            <div id="content_left" style="width: 900px; overflow:auto;height:440px;">
                <h1 class="titulo">Visualizar Alunos Inativos</h1><br />
                <form action="../ServletCentral" method="POST">
                    <%@include file="../error.jsp" %>
                    <div id="">
                        <table border="1px" style="margin-left: 0px; width: 99%">
                            <thead>
                                <tr >
                                    <th class="tabela">Nome</th>
                                    <th class="tabela" >Email</th>
                                    <th class="tabela" >Ação</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%

                                           
                                            List<Perfil> perfils = (List<Perfil>) session.getAttribute("perfils");
                                            if (perfils == null) {
                                                perfils = new ArrayList<Perfil>();
                                            }
                                            for (Perfil p : perfils) {
                                                System.out.println(p.getId());
                                %>
                            <tr>
                                <td ><center><%= p.getUsuario().getNome()%></center></td>
                                <td><center><%= p.getUsuario().getEmail()%></center></td>
                                <td><center><a  href="../ServletCentral?comando=CmdAdminExcluirTodasContasInativas&id=<%=p.getId()%>" name="Excluir"  class="button" >Excluir</a></center></td>
                            </tr>
                            <%}%>
                            
                           
                            
                            </tbody>
                        </table>
                              </div>
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