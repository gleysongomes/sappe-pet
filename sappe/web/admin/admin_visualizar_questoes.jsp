<%-- 
    Document   : admin_visualizar_questoes
    Created on : 03/09/2012, 19:11:38
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
                <h1 class="titulo"style="width: 875px;" >Visualizar Questões</h1><br />

                    <%@include file="../error.jsp" %>
                    <form action="../ServletCentral?comando=CmdAdminVisualizarQuestoes">
                        
                    </form>

                    <table border="1px;" style="margin-left: 170px;">
                        <thead>
                            <tr >
                                <th class="tabela" style="width: 50px;">Nome</th>
                                <th class="tabela" style="width: 50px;">Item Correto</th>
                                <th class="tabela" style="width: 100px;">Exame</th>
                                <th class="tabela"style="width: 200px;">Area</th>
                                <th class="tabela" style="width: 50px;">Ano</th>
                                <th class="tabela" style="width: 50px;">Ação</th>
                              </tr>
                        </thead>
                   <tbody>
                        <%

                        ExameService es = new ExameService();
                        AreaService as = new AreaService();

                                Exame exame = new Exame();
                                Area area = new Area();

                                List<Questao> questoes = (List<Questao>) session.getAttribute("visualiza_Questoes");
                                for (Questao q : questoes) {
                                    long idx = q.getExame_id();
                                    long ida = q.getArea_id();
                                    exame = es.getExameById(idx);
                                    area = as.getAreaById(ida);
                                   %>
                   
                            <tr>
                                <td><%=q.getNome() %></td>
                                <td><%=q.getItem() %></td>
                                <td><%= exame.getNome() %></td>
                                <td><%=area.getNome() %></td>
                                <td><%=q.getAno() %></td>
                                <td><a href="../ServletCentral?comando=CmdAdminExcluirQuestao&id=<%= q.getId()%>">Excluir</a>/<a href="../admin/admin_atualizar_questao.jsp?id=<%= q.getId()%>">Atualizar</a></td>
                            </tr>
                   <%}%>
                   </tbody>
                    </table>
                    
                    <br /><br />
                
            </div>

            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.12 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>