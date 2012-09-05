/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.PerfilService;
import br.ufc.si.pet.sappe.service.UsuarioService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdAdminExcluirTodasContasInativas implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {

        HttpSession session = request.getSession(true);
        try {
            int numeroContasInativas = Integer.parseInt(request.getParameter("nci"));
            PerfilService ps = new PerfilService();
            UsuarioService us = new UsuarioService();
            for (int i = 0; i < numeroContasInativas; i++) {
                Long id = Long.parseLong(request.getParameter("id" + i));
                Perfil p = ps.getPerfilById(id);
                ps.deletePerfil(id);
                us.deleteUsuario(p.getUsuario().getId());
            }
            session.removeAttribute("perfils");
            session.setAttribute("sucesso", "Contas excluidas com sucesso.");
            return "/admin/admin_visualizar_contas_inativas.jsp";
        } catch (NumberFormatException nfe) {
            session.setAttribute("erro", nfe.getMessage());
            return "/admin/admin_visualizar_contas_inativas.jsp";
        } catch (Exception e) {
            session.setAttribute("erro", e.getMessage());
            return "/admin/admin_visualizar_contas_inativas.jsp";
        }
    }
}
