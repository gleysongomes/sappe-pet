/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.servlets;

import br.ufc.si.pet.sappe.comandos.CmdLogin;
import br.ufc.si.pet.sappe.comandos.CmdLogout;
import br.ufc.si.pet.sappe.comandos.CmdRecuperarSenha;
import br.ufc.si.pet.sappe.comandos.CmdRedirecionar;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminAdicionarArea;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminAdicionarSupervisor;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminAtualizarQuestao;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminBuscarAluno;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminExcluirQuestao;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminExcluirSupervisor;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminVisualizarAlunos;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminVisualizarSupervisores;
import br.ufc.si.pet.sappe.comandos.admin.cmdAdminVisualizarQuestoes;
import br.ufc.si.pet.sappe.comandos.sup.CmdSupervisorBuscarAluno;
import br.ufc.si.pet.sappe.comandos.alu.CmdAdicionarAluno;
import br.ufc.si.pet.sappe.comandos.alu.CmdAtivarConta;
import br.ufc.si.pet.sappe.comandos.alu.CmdEditarCadastro;
import br.ufc.si.pet.sappe.comandos.alu.CmdListarQuestoes;
import br.ufc.si.pet.sappe.comandos.alu.CmdEditarProva;
import br.ufc.si.pet.sappe.comandos.alu.CmdGerarPdfProva;
import br.ufc.si.pet.sappe.comandos.alu.CmdGerarRelatorio;
import br.ufc.si.pet.sappe.comandos.alu.CmdListarImagesById;
import br.ufc.si.pet.sappe.comandos.alu.CmdListarQuestoesExamePadrao;
import br.ufc.si.pet.sappe.comandos.alu.CmdRealizarSimulado;
import br.ufc.si.pet.sappe.comandos.alu.CmdSalvarCadastroEditado;
import br.ufc.si.pet.sappe.comandos.alu.CmdSalvarProva;
import br.ufc.si.pet.sappe.comandos.alu.CmdSalvarProvaEditada;
import br.ufc.si.pet.sappe.comandos.alu.CmdSalvarSimulado;
import br.ufc.si.pet.sappe.comandos.alu.CmdSelecionarAnosProvas;
import br.ufc.si.pet.sappe.comandos.alu.CmdVisualizarProvas;
import br.ufc.si.pet.sappe.comandos.alu.CmdVisualizarResultado;
import br.ufc.si.pet.sappe.comandos.alu.CmdVisualizarResultadoSimulado;
import br.ufc.si.pet.sappe.comandos.alu.CmdVisualizarSimulados;
import br.ufc.si.pet.sappe.comandos.sup.CmdSupervisorAdicionarAluno;
import br.ufc.si.pet.sappe.comandos.sup.CmdSupervisorAdicionarAlunoSimulado;
import br.ufc.si.pet.sappe.comandos.sup.CmdSupervisorAdicionarSimulado;
import br.ufc.si.pet.sappe.comandos.sup.CmdSupervisorAdicionarSimuladoRestrito;
import br.ufc.si.pet.sappe.comandos.sup.CmdSupervisorExcluirSimulado;
import br.ufc.si.pet.sappe.comandos.sup.CmdSupervisorVisualizarDesempenhoAluno;
import br.ufc.si.pet.sappe.comandos.sup.CmdSupervisorVisualizarGabarito;
import br.ufc.si.pet.sappe.comandos.sup.CmdSupervisorVisualizarResultadoSimulado;
import br.ufc.si.pet.sappe.comandos.sup.CmdSupervisorVisualizarResultadoSimuladoAluno;
import br.ufc.si.pet.sappe.comandos.sup.CmdSupervisorVisualizarSimulados;
import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.interfaces.Comando;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 *
 * @author gleyson
 */
public class ServletCentral extends HttpServlet {

    private HashMap<String, Comando> comandos;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute("sucesso");
        session.removeAttribute("erro");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String cmd = request.getParameter("comando");
        System.out.println("Cmd:" + cmd);
        Comando comando = (Comando) comandos.get(cmd);
        Perfil perfil = (Perfil) request.getSession().getAttribute("user");
        String statusConexao = (perfil == null || perfil.getId() == null)
                ? ("Não logado.") : ("" + perfil.getId());
        try {
            String tela = comando.executa(request, response);
            if (tela != null && !tela.trim().equals("")) {
                response.sendRedirect(request.getContextPath() + tela);
                MDC.put("IP", request.getRemoteAddr());
                LoggerFactory.getLogger(this.getClass()).info("IP: " + request.getRemoteAddr());
                LoggerFactory.getLogger(this.getClass()).info("Id do Perfil: " + statusConexao);
                LoggerFactory.getLogger(this.getClass()).info("Data e Horário: " + new Date().toString());
                LoggerFactory.getLogger(this.getClass()).info("Página: " + tela);
                LoggerFactory.getLogger(this.getClass()).info("Comando obitido da página: " + cmd);
                LoggerFactory.getLogger(this.getClass()).info("Comando executado: " + comando.getClass().getName());
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                MDC.put("IP", request.getRemoteAddr());
                LoggerFactory.getLogger(this.getClass()).error("IP: " + request.getRemoteAddr());
                LoggerFactory.getLogger(this.getClass()).error("Id do Perfil: " + statusConexao);
                LoggerFactory.getLogger(this.getClass()).error("Data e Horário: " + new Date().toString());
                LoggerFactory.getLogger(this.getClass()).error("Página: " + tela);
                LoggerFactory.getLogger(this.getClass()).error("Comando obitido da página: " + cmd);
                LoggerFactory.getLogger(this.getClass()).error("Comando executado: " + comando.getClass().getName());
            }
        } catch (Exception e) {
            System.out.println("====:(");
            session.setAttribute("erro", "Usuário, senha e conta não conferem.");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            MDC.put("IP", request.getRemoteAddr());
            MDC.put("Data e Horário", new Date().toString());
            MDC.put("Id do Perfil", statusConexao);
            MDC.put("Comando obitido da página", cmd);
            MDC.put("Comando executado", comando.getClass().getName());
            LoggerFactory.getLogger(this.getClass()).error("error {}", this.getClass().getSimpleName(), e);
        }
    }

    @Override
    public void init() {
        Comando cmdo;
        comandos = new HashMap<String, Comando>();
        cmdo = new CmdLogin();
        comandos.put("CmdLogin", cmdo);
        cmdo = new CmdLogout();
        comandos.put("CmdLogout", cmdo);

        //Aluno
        cmdo = new CmdAdicionarAluno();
        comandos.put("CmdAdicionarAluno", cmdo);
        cmdo = new CmdAtivarConta();
        comandos.put("CmdAtivarConta", cmdo);
        cmdo = new CmdEditarCadastro();
        comandos.put("CmdEditarCadastro", cmdo);
        cmdo = new CmdEditarProva();
        comandos.put("CmdEditarProva", cmdo);
        cmdo = new CmdGerarPdfProva();
        comandos.put("CmdGerarPdfProva", cmdo);
        cmdo = new CmdListarQuestoes();
        comandos.put("CmdListarQuestoes", cmdo);
        cmdo = new CmdListarQuestoesExamePadrao();
        comandos.put("CmdListarQuestoesExamePadrao", cmdo);
        cmdo = new CmdSalvarProva();
        comandos.put("CmdSalvarProva", cmdo);
        cmdo = new CmdSalvarProvaEditada();
        comandos.put("CmdSalvarProvaEditada", cmdo);
        cmdo = new CmdVisualizarProvas();
        comandos.put("CmdVisualizarProvas", cmdo);
        cmdo = new CmdVisualizarResultado();
        comandos.put("CmdVisualizarResultado", cmdo);
        cmdo = new CmdSalvarCadastroEditado();
        comandos.put("CmdSalvarCadastroEditado", cmdo);
        cmdo = new CmdRecuperarSenha();
        comandos.put("CmdRecuperarSenha", cmdo);
        cmdo = new CmdListarImagesById();
        comandos.put("CmdListarImagesById", cmdo);
        cmdo = new CmdRedirecionar();
        comandos.put("CmdRedirecionar", cmdo);
        cmdo = new CmdGerarRelatorio();
        comandos.put("CmdGerarRelatorio", cmdo);
        cmdo = new CmdVisualizarSimulados();
        comandos.put("CmdVisualizarSimulados", cmdo);
        cmdo = new CmdRealizarSimulado();
        comandos.put("CmdRealizarSimulado", cmdo);
        cmdo = new CmdSalvarSimulado();
        comandos.put("CmdSalvarSimulado", cmdo);
        cmdo = new CmdVisualizarResultadoSimulado();
        comandos.put("CmdVisualizarResultadoSimulado", cmdo);
        cmdo = new CmdSelecionarAnosProvas();
        comandos.put("CmdSelecionarAnosProvas", cmdo);

        //Supervisor
        cmdo = new CmdSupervisorAdicionarSimuladoRestrito();
        comandos.put("CmdSupervisorAdicionarSimuladoRestrito", cmdo);
        cmdo = new CmdSupervisorAdicionarAlunoSimulado();
        comandos.put("CmdSupervisorAdicionarAlunoSimulado", cmdo);
        cmdo = new CmdSupervisorBuscarAluno();
        comandos.put("CmdSupervisorBuscarAluno", cmdo);
        cmdo = new CmdSupervisorAdicionarSimulado();
        comandos.put("CmdSupervisorAdicionarSimulado", cmdo);
        cmdo = new CmdSupervisorVisualizarDesempenhoAluno();
        comandos.put("CmdSupervisorVisualizarDesempenhoAluno", cmdo);
        cmdo = new CmdSupervisorVisualizarResultadoSimuladoAluno();
        comandos.put("CmdSupervisorVisualizarResultadoSimuladoAluno", cmdo);
        cmdo = new CmdSupervisorVisualizarResultadoSimulado();
        comandos.put("CmdSupervisorVisualizarResultadoSimulado", cmdo);
        cmdo = new CmdSupervisorVisualizarGabarito();
        comandos.put("CmdSupervisorVisualizarGabarito", cmdo);
        cmdo = new CmdSupervisorVisualizarSimulados();
        comandos.put("CmdSupervisorVisualizarSimulados", cmdo);
        cmdo = new CmdSupervisorExcluirSimulado();
        comandos.put("CmdSupervisorExcluirSimulado", cmdo);
        cmdo = new CmdSupervisorAdicionarAluno();
        comandos.put("CmdSupervisorAdicionarAluno", cmdo);

        //Administrador
        cmdo = new CmdAdminVisualizarAlunos();
        comandos.put("CmdAdminVisualizarAlunos", cmdo);
        cmdo = new CmdAdminAdicionarArea();
        comandos.put("CmdAdminAdicionarArea", cmdo);
        cmdo = new CmdAdminAdicionarSupervisor();
        comandos.put("CmdAdminAdicionarSupervisor", cmdo);
        cmdo = new CmdAdminBuscarAluno();
        comandos.put("CmdAdminBuscarAluno", cmdo);
        cmdo = new CmdAdminVisualizarSupervisores();
        comandos.put("CmdAdminVisualizarSupervisores", cmdo);
        cmdo = new CmdAdminExcluirSupervisor();
        comandos.put("CmdAdminExcluirSupervisor", cmdo);
        cmdo = new cmdAdminVisualizarQuestoes();
        comandos.put("CmdAdminVisualizarQuestoes", cmdo);
        cmdo = new CmdAdminExcluirQuestao();
        comandos.put("CmdAdminExcluirQuestao", cmdo);
        cmdo = new CmdAdminAtualizarQuestao();
        comandos.put("CmdAdminAtualizarQuestao", cmdo);

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
