<%--
    Document   : visualizar_resultado
    Created on : 29/12/2011, 02:42:17
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.service.TipoService"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Tipo"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Prova"%>
<%@page import="br.ufc.si.pet.sappe.entidades.QuestaoProva"%>
<%@page import="br.ufc.si.pet.sappe.util.Util"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
            String tipoProva2 = (String) session.getAttribute("tipoProva2");
            Prova p = (Prova) session.getAttribute("prova");
            TipoService tS = new TipoService();
            Tipo tipo = tS.getTipoById(p.getTipo_id());
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style1.css" rel="stylesheet" type="text/css" />
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
    </head>
    <body>
        <div id="content">
            <div id="top">
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../alu/menu.jsp" %>
            <div id="content_left" style="width: auto;">
                <form>
                <h1 class="titulo" style="width: 875px;" ><%=tipoProva2%><label class="imagemPdf"><a href="../ServletCentral?comando=CmdGerarRelatorio&id=<%=p.getId()%>" target="_blank"><img src="../images/pdf.jpeg" width="30" height="30" alt="pdf"/></a></label></h1><br />
                            <%@include file="../error.jsp" %>


                <label>Obs (*) : As questões nulas são consideradas como certas.</label>
                <h4 class="titulo">Relatório da Prova</h4><br>
                <div id="">
                    <table  style="margin-left: 0px; width: 99%" border="1px">
                        <thead>
                            <tr>
                                <th style="width:400px" >Tipo da Prova</th>
                                <th>Questões Respondidas</th>
                                <th>Questões Certas</th>
                                <th>Questões Brancas</th>
                                <th>Questões Erradas</th>
                                <th>Tempo de Prova</th>
                                <th>Último Acesso</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><%= tipo.getNome()%></td>
                                <td><%= p.getRespondidas()%></td>
                                <td><%= p.getCertas()%></td>
                                <td><%= p.getBrancas()%></td>
                                <td><%= p.getErradas()%></td>
                                <td><%= p.getTempo_prova()%></td>
                                <td><%= p.getData()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <br /><br />


                

                <%
                            int itemIncr = 0;
                            List<QuestaoProva> qPs = (List<QuestaoProva>) session.getAttribute("qPs2");
                            for (QuestaoProva qp : qPs) {
                %>
                <label>Questão <%= itemIncr + 1%>:<br /><img src="../ServletCentral?comando=CmdListarImagesById&id=<%= qp.getQuestao_id()%>" style="width: 80%; height: 70%" alt="images"/>
                </label><br />
                <table border="0">
                    <thead>
                        <tr>
                            <td width="80px">(a)<input type="checkbox" name="iM<%= itemIncr%>" value="A" <%= Util.marcarRadio("A", qp.getItem_marcado())%>/></td>
                            <td width="80px">(b)<input type="checkbox" name="iM<%= itemIncr%>" value="B" <%= Util.marcarRadio("B", qp.getItem_marcado())%>/></td>
                            <td width="80px">(c)<input type="checkbox" name="iM<%= itemIncr%>" value="C" <%= Util.marcarRadio("C", qp.getItem_marcado())%>/></td>
                            <td width="80px">(d)<input type="checkbox" name="iM<%= itemIncr%>" value="D" <%= Util.marcarRadio("D", qp.getItem_marcado())%>/></td>
                            <td width="80px">(e)<input type="checkbox" name="iM<%= itemIncr%>" value="E" <%= Util.marcarRadio("E", qp.getItem_marcado())%>/></td>
                        </tr>
                    </thead>
                </table>
                <br />
                <label>Status:  <%if (qp.getStatus() == 0) {%> <label>Branca</label><%} else if (qp.getStatus() == 1) {%> <label style="color: green;">Aceita</label><%} else if (qp.getStatus() == 2) {%><label style="color: red;">Errada</label><%} else {%><label style="color: blue;">Nula</label><%}%></label><br /><br />
                <%itemIncr++;}%>
                <br />
                
                <input type="button" value="Voltar" onclick="history.back(); return false;" class="button" style="margin-left: 400px ; width: 80px;" />

            </form>
            </div>
            <div id="content_right"></div>
            <%@include file="../footer2.jsp" %>
        </div>
    </body>
</html>
