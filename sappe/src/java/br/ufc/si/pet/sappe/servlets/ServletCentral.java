/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.servlets;

import br.ufc.si.pet.sappe.comandos.CmdLogin;
import br.ufc.si.pet.sappe.comandos.CmdLogout;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminCorrigirProva;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminSalvarCorrecaoProva;
import br.ufc.si.pet.sappe.comandos.admin.CmdAdminVisualizarProvas;
import br.ufc.si.pet.sappe.comandos.alu.CmdAdicionarAluno;
import br.ufc.si.pet.sappe.comandos.alu.CmdAtivarConta;
import br.ufc.si.pet.sappe.comandos.alu.CmdEditarCadastro;
import br.ufc.si.pet.sappe.comandos.alu.CmdListarQuestoes;
import br.ufc.si.pet.sappe.comandos.alu.CmdEditarProva;
import br.ufc.si.pet.sappe.comandos.alu.CmdGerarPdfProva;
import br.ufc.si.pet.sappe.comandos.alu.CmdGerarRelatorio;
import br.ufc.si.pet.sappe.comandos.alu.CmdListarQuestoesExamePadrao;
import br.ufc.si.pet.sappe.comandos.alu.CmdSalvarProva;
import br.ufc.si.pet.sappe.comandos.alu.CmdSalvarProvaEditada;
import br.ufc.si.pet.sappe.comandos.alu.CmdVisualizarArquivo;
import br.ufc.si.pet.sappe.comandos.alu.CmdVisualizarProvas;
import br.ufc.si.pet.sappe.comandos.alu.CmdVisualizarResultado;
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
        cmdo = new CmdGerarRelatorio();
        comandos.put("CmdGerarRelatorio", cmdo);
        cmdo = new CmdListarQuestoes();
        comandos.put("CmdListarQuestoes", cmdo);
        cmdo = new CmdListarQuestoesExamePadrao();
        comandos.put("CmdListarQuestoesExamePadrao", cmdo);
        cmdo = new CmdSalvarProva();
        comandos.put("CmdSalvarProva", cmdo);
        cmdo = new CmdSalvarProvaEditada();
        comandos.put("CmdSalvarProvaEditada", cmdo);
        cmdo = new CmdVisualizarArquivo();
        comandos.put("CmdVisualizarArquivo", cmdo);
        cmdo = new CmdVisualizarProvas();
        comandos.put("CmdVisualizarProvas", cmdo);
        cmdo = new CmdVisualizarResultado();
        comandos.put("CmdVisualizarResultado", cmdo);
        
        //Administrador
        cmdo = new CmdAdminCorrigirProva();
        comandos.put("CmdAdminCorrigirProva", cmdo);
        cmdo = new CmdAdminSalvarCorrecaoProva();
        comandos.put("CmdAdminSalvarCorrecaoProva", cmdo);
        cmdo = new CmdAdminVisualizarProvas();
        comandos.put("CmdAdminVisualizarProvas", cmdo);  
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
