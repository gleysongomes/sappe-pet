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
public class CmdAdminVisualizarContasInativas implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {

        HttpSession session = request.getSession(true);
        UsuarioService us = new UsuarioService();
        PerfilService perfilService = new PerfilService();
        List<Perfil> perfils = perfilService.getListAllContasInativas();
        int v = 0;
        for (Perfil p : perfils) {
            Usuario u = us.getUsuarioById(p.getUsuario().getId());
            perfils.get(v++).setUsuario(u);
        }
        session.setAttribute("perfils", perfils);
        return "/admin/admin_visualizar_contas_inativas.jsp";
    }
}
