/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.servlets;

import br.ufc.si.pet.sappe.comandos.CmdLogin;
import br.ufc.si.pet.sappe.comandos.CmdLogout;
import br.ufc.si.pet.sappe.comandos.CmdRecuperarSenha;
import br.ufc.si.pet.sappe.comandos.CmdRedirecionar;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminAdicionarQuestao;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminAdicionarSimulado;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminVisualizarDesempenhoAluno;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminVisualizarProvas;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminVisualizarResultadoSimulado;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminVisualizarResultadoSimuladoAluno;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminVisualizarSimulados;
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
import br.ufc.si.pet.sappe.comandos.alu.CmdVisualizarProvas;
import br.ufc.si.pet.sappe.comandos.alu.CmdVisualizarResultado;
import br.ufc.si.pet.sappe.comandos.alu.CmdVisualizarResultadoSimulado;
import br.ufc.si.pet.sappe.comandos.alu.CmdVisualizarSimulados;
import br.ufc.si.pet.sappe.interfaces.Comando;
import java.io.IOException;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gleyson
 */
public class ServletCentral extends HttpServlet {

    private Hashtable comandos;
    private boolean debug = true;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        //response.setCharacterEncoding("UTF-8");
        String cmd = request.getParameter("comando");
        //if (debug) {
        //  System.out.println("Cmd:" + cmd);
        //}
        Comando comando = (Comando) comandos.get(cmd);

        try {
            String tela = comando.executa(request, response);
            if (tela != null && !tela.trim().equals("")) {
                if (debug) {
                    System.out.println("Tela:" + tela);
                }
                response.sendRedirect(request.getContextPath() + tela);
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        Comando cmdo;
        comandos = new Hashtable();
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
        

        //Administrador
        cmdo = new CmdAdminVisualizarProvas();
        comandos.put("CmdAdminVisualizarProvas", cmdo);
        cmdo = new CmdAdminAdicionarSimulado();
        comandos.put("CmdAdminAdicionarSimulado", cmdo);
        cmdo = new CmdAdminVisualizarSimulados();
        comandos.put("CmdAdminVisualizarSimulados", cmdo);
        cmdo = new CmdAdminVisualizarResultadoSimulado();
        comandos.put("CmdAdminVisualizarResultadoSimulado", cmdo);
        cmdo = new CmdAdminVisualizarDesempenhoAluno();
        comandos.put("CmdAdminVisualizarDesempenhoAluno", cmdo);
        cmdo = new CmdAdminVisualizarResultadoSimuladoAluno();
        comandos.put("CmdAdminVisualizarResultadoSimuladoAluno", cmdo);
        cmdo = new CmdAdminAdicionarQuestao();
        comandos.put("CmdAdminAdicionarQuestao", cmdo);


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
