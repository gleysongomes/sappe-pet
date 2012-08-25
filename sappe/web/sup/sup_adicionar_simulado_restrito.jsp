<%-- 
    Document   : sup_adicionar_simulado_restrito
    Created on : 20/08/2012, 20:24:24
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.entidades.Simulado"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String mSucesso = (String) session.getAttribute("mSucesso");
            String mErro = (String) session.getAttribute("mErro");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style1.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript"  language="javascript" src="../js/Script.js"></script>
        <title>SAPPE - Simulador do Ambiente das Provas do POSCOMP e Enade</title>
        <script type="text/javascript"  language="javascript">
            function formulario(obj,v,v2){
                valor = obj.options[obj.selectedIndex].value;
                document.getElementById(v).style.display = 'none';
                document.getElementById(v2).style.display = 'none';
                if(valor.match(v)){
                    document.getElementById(v).style.display = 'block';
                }else if(valor.match(v2)){
                    document.getElementById(v2).style.display = 'block';
                }
            }
        </script>
    </head>
    <body>
        <div id="content">
            <div id="top">
                <img src="../images/sappe2.gif" width="910" height="76" alt="sappe2"/>
            </div>
            <%@include file="../sup/menu.jsp" %>
            <div id="content_left">
                 <h2 class="titulo">Adicionar Simulado Restrito</h2><br/>
               
                <form name="asr" action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdSupervisorAdicionarSimuladoRestrito" />
                    
                    <%@include file="../error.jsp" %>
                   
                    <table border="0" style="width: 400px; color:#0E464E; " >
                            <tbody>
                                <tr>
                                    <td><label>Nome</label>:</td>
                                    <td width="10px;"><input type="text" name="nome" value="" size="20" maxlength="80"/></td>
                                </tr>
                                <tr>
                                    <td><label>Data:</label></td>
                                    <td><input type="text" name="data" value="" size="20" onkeypress="return formataData(this,event)" maxlength="10"/></td>
                                </tr>
                                <tr>
                                    <td><label>Horário de Inicio:</label></td>
                                    <td><input type="text" name="hi" value="" size="20" onkeypress="return formataHorario(this,event)" maxlength="8"/></td>
                                </tr>
                                <tr>
                                    <td><label>Horário de Término:</label></td>
                                    <td><input type="text" name="ht" value="" size="20" onkeypress="return formataHorario(this,event)" maxlength="8"/></td>
                                </tr>
                                <tr>

                                    <td><label>Exame:</label></td>
                                    <td>
                                        <select name="exame" onchange="formulario(this,'1','2');" style="width: 143px;">
                                            <option value="0">Selecione</option>
                                            <option value="1">Poscomp</option>
                                            <option value="2">Enade</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                            </tbody>
                        </table>


                        <table border="0" style="width: 400px;">
                            <tbody id="1" style="display: none;">
                                 <tr>
                                    <td><label>Número de Questões</label></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td><label>Matemática:</label></td>
                                    <td><input type="text" name="nm" value="" size="20" onkeypress="return validaNumerosSilencioso(event);"/></td>
                                </tr>
                                <tr>
                                    <td><label>Fundamentos da Computação:</label></td>
                                    <td><input type="text" name="nfc" value="" size="20" onkeypress="return validaNumerosSilencioso(event);"/></td>
                                </tr>
                                <tr>
                                    <td><label>Tecnologia da Computação:</label></td>
                                    <td><input type="text" name="ntc" value="" size="20" onkeypress="return validaNumerosSilencioso(event);"/></td>
                                </tr>
                            </tbody>
                        </table>
                        <table border="0" style="width: 400px;">
                            <tbody id="2" style="display: none;">
                                <tr>
                                    <td style="width: 300px;"><label>Número de Questões:</label></td>
                                    <td><br /></td>
                                </tr>
                                <tr>
                                    <td><label>Sitemas de Informação:</label></td>
                                    <td><input type="text" name="nsi" value="" size="20" onkeypress="return validaNumerosSilencioso(event);"/></td>
                                </tr>
                                <tr>
                                    <td><label>Engenharia de Software:</label></td>
                                    <td><input type="text" name="nes" value="" size="20" onkeypress="return validaNumerosSilencioso(event);"/></td>
                                </tr>
                                <tr>
                                    <td><label>Conhecimentos Gerais:</label></td>
                                    <td><input type="text" name="ncg" value="" size="20" onkeypress="return validaNumerosSilencioso(event);"/></td>
                                </tr>
                            </tbody>
                        </table>
                        <table border="0" style="width: 400px;">
                            <tbody>
                                <tr>
                                    <td></td>
                                    <td><input type="submit" value="Salvar" name="Salvar" class="button"/> <input type="reset" value="Limpar" name="Limpar" class="button"/></td>
                                </tr>
                            </tbody>
                        </table>
                   
                </form>
                <br />
               
            </div>
            <div id="content_right">
                
                
                <h2 class="titulo">Adicionar Alunos Participantes</h2><br />
                <div id="bh"></div>
                <form action="../ServletCentral" method="POST">
                    <input type="hidden" name="comando" value="CmdSupervisorBuscarAluno" />
                    <%if (mSucesso != null) {%><label style="color: blue;"><%=mSucesso%></label><%} else if(mErro!=null){%><label style="color: red;"><%=mErro %></label><%}%><br />
                    <table border="0" >
                        <tbody>
                            <tr>
                                <td><label>Busca Por Nome:</label></td>
                                <td><input type="text" name="nome" value="" size="20"/> <input type="submit" value="Buscar" name="Buscar" class="button" /></td>
                            </tr>
                            <%
                                        List<Usuario> usuarios = (List<Usuario>) session.getAttribute("uS");
                                        if (usuarios == null) {
                                            usuarios = new ArrayList<Usuario>();
                                        }
                                        for (Usuario u : usuarios) {
                            %>
                            <tr>
                                <td><%= u.getNome()%> - <a href="../ServletCentral?comando=CmdSupervisorAdicionarAlunoSimulado&id=<%=u.getId()%>">Adicionar</a> </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </form>
            </div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>