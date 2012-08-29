/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.UsuarioService;
import com.ibatis.sqlmap.client.SqlMapException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdAdminBuscarAluno implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);
        session.removeAttribute("uS2");
        try {
            String nome = request.getParameter("nome");
            if (nome == null || nome.isEmpty()) {
                session.setAttribute("erro", "Preencha todos os campos obrigatórios (*).");
                return "/admin/admin_adicionar_supervisor.jsp";
            }
            UsuarioService us = new UsuarioService();
            List<Usuario> usuarios = us.getUsuariosByNome(nome);
            System.out.println("====" + usuarios.size());
            session.setAttribute("uS2", usuarios);
        } catch (NumberFormatException nfe) {
            session.setAttribute("erro", nfe.getMessage());
            nfe.printStackTrace();
            return "/admin/admin_adicionar_supervisor.jsp";
        } catch (SqlMapException e) {
            session.setAttribute("erro", e.getMessage());
            e.printStackTrace();
            return "/admin/admin_adicionar_supervisor.jsp";
        } catch (NullPointerException npe) {
            session.setAttribute("erro", npe.getMessage());
            npe.printStackTrace();
            return "/admin/admin_adicionar_supervisor.jsp";
        } catch (Exception e) {
            session.setAttribute("erro", e.getMessage());
            e.printStackTrace();
            return "/admin/admin_adicionar_supervisor.jsp";
        }
        return "/admin/admin_adicionar_supervisor.jsp";
    }
}
