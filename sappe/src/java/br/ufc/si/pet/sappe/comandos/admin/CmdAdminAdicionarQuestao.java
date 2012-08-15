/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.interfaces.Comando;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
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
public class CmdAdminAdicionarQuestao extends HttpServlet implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession hS = request.getSession(true);
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
                if (id.equals("aid")) {
                    if (valor == null || valor.isEmpty()) {
                        hS.setAttribute("erro", "Preencha todos os campos (*).");
                        return "/admin/admin_adicionar_questao.jsp";//response.sendRedirect(request.getContextPath() + "/admin/admin_adicionar_questao.jsp");
                    }
                    aid = Integer.parseInt(valor);
                } else if (id.equals("eid")) {
                    if (valor == null || valor.isEmpty()) {
                        hS.setAttribute("erro", "Preencha todos os campos (*).");
                        return "/admin/admin_adicionar_questao.jsp";//response.sendRedirect(request.getContextPath() + "/admin/admin_adicionar_questao.jsp");
                    }
                    eid = Integer.parseInt(valor);
                } else if (id.equals("ano")) {
                    if (valor == null || valor.isEmpty()) {
                        hS.setAttribute("erro", "Preencha todos os campos (*).");
                        return "/admin/admin_adicionar_questao.jsp";//response.sendRedirect(request.getContextPath() + "/admin/admin_adicionar_questao.jsp");
                    }
                    ano = valor;
                } else if (id.equals("ic")) {
                    if (valor == null || valor.isEmpty()) {
                        hS.setAttribute("erro", "Preencha todos os campos (*).");
                        return "/admin/admin_adicionar_questao.jsp";//response.sendRedirect(request.getContextPath() + "/admin/admin_adicionar_questao.jsp");
                    }
                    ic = valor;
                } else if (!fi.isFormField()) {
                    try {
                        File fNew = new File("/tmp/", fi.getName());
                        System.out.println(fNew.getAbsolutePath());
                        fi.write(fNew);
                        Class.forName("org.postgresql.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "postgres");
                        File arquivo = new File("/tmp/" + fi.getName());
                        FileInputStream fiS = new FileInputStream(arquivo);
                        PreparedStatement pS = conn.prepareStatement("INSERT INTO sappe.questao(exame_id, area_id, ano, arquivo, nome, item) VALUES(?,?,?,?,?,?)");
                        pS.setInt(1, eid);
                        pS.setInt(2, aid);
                        pS.setString(3, ano);
                        pS.setBinaryStream(4, fiS, (int) arquivo.length());
                        pS.setString(5, fi.getName());
                        pS.setString(6, ic);
                        pS.execute();
                        pS.close();
                        conn.close();
                        fileItems.clear();
                        hS.setAttribute("sucesso", "Cadastro realizado com sucesso.");
                        response.sendRedirect(request.getContextPath() + "/admin/admin_adicionar_questao.jsp");
                    } catch (Exception ex) {
                        hS.setAttribute("erro", "Erro ao tentar cadastrar.");
                        response.sendRedirect(request.getContextPath() + "/admin/admin_adicionar_questao.jsp");
                        ex.printStackTrace();
                    }
                    return "/admin/admin_adicionar_questao.jsp";
                }
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            hS.setAttribute("erro", nfe.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/admin_adicionar_questao.jsp");
        } catch (FileUploadException fue) {
            fue.printStackTrace();
            hS.setAttribute("erro", fue.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/admin_adicionar_questao.jsp");
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            hS.setAttribute("erro", npe.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/admin_adicionar_questao.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
            hS.setAttribute("erro", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/admin_adicionar_questao.jsp");
        }

        return "/admin/admin_adicionar_questao.jsp";
    }
}