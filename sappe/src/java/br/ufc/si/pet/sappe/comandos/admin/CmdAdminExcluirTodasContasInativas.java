/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Usuario;
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
            String idc = request.getParameter("id");
            PerfilService ps = new PerfilService();
            UsuarioService us = new UsuarioService();
                Long id = Long.parseLong(idc);

                Perfil p = ps.getPerfilByUsuarioId(id);
                p.setUsuario(us.getUsuarioById(id));
                
                System.out.println(p.getId());
                System.out.println(p.getUsuario().getId());
                ps.deletePerfil(p.getId());
                us.deleteUsuario(p.getUsuario().getId());
            
            session.removeAttribute("perfils");
            session.setAttribute("sucesso", "Conta excluida com sucesso.");
            return "/admin/admin_visualizar_contas_inativas.jsp";
        } catch (NumberFormatException nfe) {
            session.setAttribute("erro", "não foi possivel remover o aluno");
            return "/admin/admin_visualizar_contas_inativas.jsp";
        } catch (Exception e) {
            session.setAttribute("erro", e.getMessage());
            return "/admin/admin_visualizar_contas_inativas.jsp";
        }
    }
}
