/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Supervisor;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.PapelService;
import br.ufc.si.pet.sappe.service.SupervisorService;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.util.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdAdminAdicionarSupervisor implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {

        HttpSession session = request.getSession(true);
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            Date data = Util.treatToDate(dateFormat.format(date));
            PapelService ps = new PapelService();
            UsuarioService us = new UsuarioService();
            Usuario u = us.getUsuarioById(id);
            
            SupervisorService supervisorService = new SupervisorService();
            Supervisor supervisor = new Supervisor();
            supervisor.setUsuario(u);
            supervisor.setPapel(ps.getPapelById(2L));
            supervisor.setDataCriacao(data);
            supervisor.setAtivo(true);
            if (supervisorService.getSupervisorByUsuarioId(id) != null) {
                session.setAttribute("erro", "Já é um supervisor.");
                return "/admin/admin_adicionar_supervisor.jsp";
            } else if (supervisorService.insertSupervisor(supervisor)) {
                session.setAttribute("sucesso", "Cadastrado realizado com sucesso.");
                return "/admin/admin_adicionar_supervisor.jsp";
            } else {
                session.setAttribute("erro", "Erro ao realizar cadastro.");
                return "/admin/admin_adicionar_supervisor.jsp";
            }
        } catch (NumberFormatException numberFormatException) {
            session.setAttribute("erro", numberFormatException.getMessage());
            return "/admin/admin_adicionar_supervisor.jsp";
        } catch (Error e) {
            session.setAttribute("erro", e.getMessage());
            return "/admin/admin_adicionar_supervisor.jsp";
        } catch (Exception ex) {
            session.setAttribute("erro", ex.getMessage());
            return "/admin/admin_adicionar_supervisor.jsp";
        }
    }
}
