<%--
    Document   : menu
    Created on : 21/08/2012, 05:40:37
    Author     : gleyson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<div id="menu">
    <ul id="nav">
        <li>
            <a href="../sup/index.jsp">Home</a>
        </li>
        <li>
            <a href="../sup/sup_adicionar_simulado.jsp">Adiconar Simulado</a>
        </li>
         <li>
             <a href="../sup/sup_adicionar_simulado_restrito.jsp" style="width: 250px;">Adiconar Simulado Restrito</a>
        </li>
        <li>
            <a href="../ServletCentral?comando=CmdSupervisorVisualizarSimulados">Visualizar Simulados</a>
        </li>
        <li>
            <a href="../ServletCentral?comando=CmdLogout">Sair</a>
        </li>
    </ul>
</div>