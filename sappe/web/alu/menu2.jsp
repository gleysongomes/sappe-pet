<%--
    Document   : menu2
    Created on : 04/01/2012, 21:15:55
    Author     : gleyson
--%>


<div id="menu">
    <ul id="nav">
        <li>
            <a href="../alu/index.jsp" style="width: 100px;">Home</a>
        </li>
        <li>
            <a href="../ServletCentral?comando=CmdSelecionarAnosProvas&id=1">Poscomp Padr�o</a>
        </li>
        <li>
            <a href="" style="width: 215px;">Poscomp Personalizado</a>
            <ul id="nav">
                <li>
                    <a href="../alu/poscomp_matematica.jsp" style="width: 215px;">Matem�tica</a>
                </li>
                <li>
                    <a href="../alu/poscomp_funcomp.jsp" style="width: 215px;">Fundamentos da Computa��o</a>
                </li>
                <li>
                    <a href="../alu/poscomp_tecomp.jsp" style="width: 215px;">Tecnologia da Computa��o</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="../ServletCentral?comando=CmdSelecionarAnosProvas&id=2">Enade Padr�o</a>
        </li>
        <li>
            <a href="" style="width: 200px;">Enade Personalizado</a>
            <ul id="nav">
                <li>
                    <a href="../alu/enade_sistemas_informacao.jsp" style="width: 200px;">Sistema de Informa��o</a>
                </li>
                <li>
                    <a href="../alu/enade_ciencia_computacao.jsp" style="width: 200px;">Ci�ncia da Computa��o</a>
                </li>
                <li>
                    <a href="../alu/enade_engenharia_computacao.jsp" style="width: 200px;">Engenharia de Computa��o</a>
                </li>
                <li>
                    <a href="../alu/enade_licenciatura.jsp" style="width: 200px;">Licenciatura</a>
                </li>
                <li>
                    <a href="../alu/enade_conhecimentos_gerais.jsp" style="width: 200px;">Conhecimentos Gerais</a>
                </li>
            </ul>
        </li>
        <li><a href="../ServletCentral?comando=CmdLogout" style="width: 100px;">Sair</a></li>
    </ul>
</div>