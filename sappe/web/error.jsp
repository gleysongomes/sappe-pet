<%--
    Document   : error
    Created on : 15/12/2010, 19:15:28
    Author     : ismaily
--%>

<div id="error_msg">
    <%
                String sucesso = (String) session.getAttribute("sucesso");
                String erro = (String) session.getAttribute("erro");
                session.removeAttribute("sucesso");
                session.removeAttribute("erro");
                if (erro != null) {%>
                <p class="mensagemErro" style="color: red"><%=erro%></p>
    <%}
                if (sucesso != null) {%>
                <p class="mensagemSucesso" style="color: blue"><%=sucesso%></p>
    <%}%>
</div>