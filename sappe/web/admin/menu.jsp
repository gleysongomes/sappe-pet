<%-- 
    Document   : menu
    Created on : 11/01/2012, 22:46:12
    Author     : gleyson
--%>


<div id="menu">
    <ul id="nav">
        <li>
            <a href="../admin/index.jsp" style="width: 110px;">Home</a>
        </li>

        <li>
            <a href="#" style="width: 170px;">Questões</a>
            <ul>
                <li>
            <a href="../admin/admin_adicionar_questao.jsp" style="width: 170px;">Adiconar Questão</a>
        </li>
         <li>
             <a href="../admin/admin_buscar_questao_ano.jsp" style="width: 170px;">Buscar Questões</a>
        </li>
            </ul>
        </li>

        <li>
            <a href="#" style="width: 170px;">Supervisores</a>
            <ul>
                <li>
                    <a href="../admin/admin_adicionar_supervisor.jsp" style="width: 170px;">Adiconar </a>
                </li>
                <li>
                <a href="../ServletCentral?comando=CmdAdminVisualizarSupervisores" style="width: 170px;">Visualizar </a>
                </li>
            </ul>
        </li>

        <li>

            <a href="#" style="width: 170px;">Alunos</a>
            <ul>
                <li>
                    <a href="../ServletCentral?comando=CmdAdminVisualizarAlunos" style="width: 170px;">Visualizar</a>
                </li>
                <li>
                    <a href="../ServletCentral?comando=CmdAdminVisualizarContasInativas" style="width: 170px;">Alunos Inativos</a>
                </li>
            </ul>
        </li>
        
        <li>
            <a href="../ServletCentral?comando=CmdAdminEditarPerfil" style="width: 110px;"> Editar</a>
        </li>
        
        <li>
            <a href="../ServletCentral?comando=CmdLogout" style="width: 110px;">Sair</a>
        </li>
    </ul>
</div>