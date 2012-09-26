/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Supervisor;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.PerfilService;
import br.ufc.si.pet.sappe.service.SupervisorService;
import br.ufc.si.pet.sappe.service.UsuarioService;
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
public class CmdAdminExcluirSupervisor implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            SupervisorService ss = new SupervisorService();
            PerfilService ps = new PerfilService();
            Perfil p = ps.getPerfilById(id);
            if (ss.deleteSupervisor(id)) {
                UsuarioService us = new UsuarioService();
                us.deleteUsuario(p.getUsuario().getId());

                List<Supervisor> supervisores = ss.getAll();
                session.setAttribute("supervisores", supervisores);
                session.setAttribute("sucesso", "Operação efetuada com sucesso.");
                return "/admin/admin_visualizar_supervisores.jsp";
            } else {
                session.setAttribute("erro", "Erro ao tentar excluir.");
                return "/admin/admin_visualizar_supervisores.jsp";
            }
        } catch (NumberFormatException numberFormatException) {
            session.setAttribute("erro", numberFormatException.getMessage());
            return "/admin/admin_visualizar_supervisores.jsp";
        } catch (Exception e) {
            session.setAttribute("erro", e.getMessage());
            return "/admin/admin_visualizar_supervisores.jsp";
        }
    }
}
