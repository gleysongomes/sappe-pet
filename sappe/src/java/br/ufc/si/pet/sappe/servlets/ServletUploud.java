/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class ServletUploud extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession hS = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        DiskFileUpload fu = new DiskFileUpload();
        fu.setSizeMax(1000000);
        List fileItems = fu.parseRequest(request);
        Iterator itr = fileItems.iterator();
        while (itr.hasNext()) {
            FileItem fi = (FileItem) itr.next();
            if (!fi.isFormField()) {
                System.out.println("\nNAME: " + fi.getName());
                System.out.println("SIZE: " + fi.getSize());
                File fNew = new File("/tmp/", fi.getName());
                System.out.println(fNew.getAbsolutePath());
                fi.write(fNew);
                System.out.println("===" + id);
                Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "postgres");
                File arquivo = new File("/tmp/" + fi.getName());
                FileInputStream fiS = new FileInputStream(arquivo);
                PreparedStatement pS = conn.prepareStatement("UPDATE sappe.questao_prova  SET nome_arquivo=?, arquivo=? WHERE id=?;");
                pS.setString(1, fi.getName());
                pS.setBinaryStream(2, fiS, (int) arquivo.length());
                pS.setLong(3, id);
                pS.execute();
                pS.close();
                conn.close();
                hS.setAttribute("sucesso", "Arquivo enviado com sucesso.");
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/alu/enviar_arquivo.jsp");
        requestDispatcher.forward(request, response);
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
            Logger.getLogger(ServletUploud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletUploud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileUploadException ex) {
            Logger.getLogger(ServletUploud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServletUploud.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletUploud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletUploud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileUploadException ex) {
            Logger.getLogger(ServletUploud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServletUploud.class.getName()).log(Level.SEVERE, null, ex);
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
