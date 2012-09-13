<%--
    Document   : menu
    Created on : 30/12/2011, 16:53:29
    Author     : gleyson
--%>
<%@page import="br.ufc.si.pet.sappe.entidades.Aluno"%>

<%
            Aluno a = (Aluno) session.getAttribute("user");
%>

<div id="menu">
    <ul id="nav">
        <li>
            <a href="../ServletCentral?comando=CmdRedirecionar&url=/alu/index.jsp">Home</a>
        </li>
        <li>
            <a href="#" style="width: 170px;">Provas</a>
            <ul>
                <li>
                    <a href="../ServletCentral?comando=CmdRedirecionar&url=/alu/realizar_prova.jsp" style="width: 170px;">Realizar Prova</a>
                </li>
                 <li>
                    <a href="../ServletCentral?comando=CmdVisualizarProvas&id=<%= a.getUsuario().getId()%>" style="width: 170px;">Visualizar Provas</a>
                </li>
            </ul>
        </li>
       

        <li>
            <a href="#" style="width: 170px;">Simulados</a>
            <ul>
                <li>
                    <a href="../ServletCentral?comando=CmdVisualizarSimulados" style="width: 170px;">Visualizar Simulados</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#" style="width: 170px;">Cadastro</a>
            <ul>
                <li>
                    <a href="../ServletCentral?comando=CmdEditarCadastro" style="width: 170px;">Editar</a>
                </li>
            </ul>
        </li>

        <li>
            <a href="../ServletCentral?comando=CmdLogout">Sair</a>
        </li>
    </ul>
</div>


