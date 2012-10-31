/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.servlets.admin;

import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.util.Upload;
import br.ufc.si.pet.sappe.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class ServletAdminAdicionarQuestao extends HttpServlet implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        int eid = 0, aid = 0;
        String ano = "", ic = "";
        try {
            DiskFileUpload fu = new DiskFileUpload();
            fu.setSizeMax(1000000);
            List fileItems = fu.parseRequest(request);
            Iterator itr = fileItems.iterator();
            while (itr.hasNext()) {
                FileItem fi = (FileItem) itr.next();
                String id = fi.getFieldName();
                String valor = fi.getString();



                if (id.equals("area_id")) {
                    if (valor == null || valor.isEmpty()) {
                        session.setAttribute("erro", "Preencha todos os campos (*).");
                        return "/admin/admin_adicionar_questao.jsp";
                    }
                    aid = Integer.parseInt(valor);
                } else if (id.equals("exame_id")) {
                    if (valor == null || valor.isEmpty()) {
                        session.setAttribute("erro", "Preencha todos os campos (*).");
                        return "/admin/admin_adicionar_questao.jsp";
                    }
                    eid = Integer.parseInt(valor);
                } else if (id.equals("ano")) {
                    if (valor == null || valor.isEmpty()) {
                        session.setAttribute("erro", "Preencha todos os campos (*).");
                        return "/admin/admin_adicionar_questao.jsp";
                    }
                    ano = valor;
                } else if (id.equals("ic")) {
                    if (valor == null || valor.isEmpty()) {
                        session.setAttribute("erro", "Preencha todos os campos (*).");
                        return "/admin/admin_adicionar_questao.jsp";
                    }
                    ic = valor;
                } else if (!fi.isFormField()) {
                    try {
                        QuestaoService questaoService = new QuestaoService();
                        Questao questao = new Questao();
                        questao.setExame_id(eid);
                        questao.setArea_id(aid);
                        questao.setAno(ano);
                        questao.setItem(ic);

                        
                        questao.setNome(fi.getName());
                        //insere a questão no banco
                        questaoService.inserir(questao);
                        //insere a img na pasta do projeto
                       
                        try {
                        fi.write(new File(Util.getDiretorio() + "/" + questao.getExame_id()+ "/" + questao.getAno() + fi.getName()));
                       
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                       
                        fileItems.clear();
                        session.setAttribute("sucesso", "Cadastro realizado com sucesso.");
                        return "/admin/admin_adicionar_questao.jsp";
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        session.setAttribute("erro", ex.getMessage());
                        return "/admin/admin_adicionar_questao.jsp";
                    }
                }
            }

        } catch (NumberFormatException nfe) {
            session.setAttribute("erro", nfe.getMessage());
            return "/admin/admin_adicionar_questao.jsp";
        } catch (FileUploadException fue) {
            session.setAttribute("erro", fue.getMessage());
            return "/admin/admin_adicionar_questao.jsp";
        } catch (NullPointerException npe) {
            session.setAttribute("erro", npe.getMessage());
            return "/admin/admin_adicionar_questao.jsp";
        } catch (Exception ex) {
            session.setAttribute("erro", ex.getMessage());
            return "/admin/admin_adicionar_questao.jsp";
        }
        session.setAttribute("erro", "Erro ao tentar cadastrar a questão.");
        return "/admin/admin_adicionar_questao.jsp";
    }//fim do método

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        String tela = executa(request, response);
        response.sendRedirect(request.getContextPath() + tela);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletAdminAdicionarQuestao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletAdminAdicionarQuestao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileUploadException ex) {
            Logger.getLogger(ServletAdminAdicionarQuestao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServletAdminAdicionarQuestao.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletAdminAdicionarQuestao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletAdminAdicionarQuestao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileUploadException ex) {
            Logger.getLogger(ServletAdminAdicionarQuestao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServletAdminAdicionarQuestao.class.getName()).log(Level.SEVERE, null, ex);
        }

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
