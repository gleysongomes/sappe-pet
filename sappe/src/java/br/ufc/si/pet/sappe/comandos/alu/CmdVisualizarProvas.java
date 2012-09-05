/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.ProvaService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gleyson
 */
public class CmdVisualizarProvas implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        ProvaService pS = new ProvaService();
        List<Prova> provas = pS.getListAllProvasByIdUsuario(id);
        hS.setAttribute("provas", provas);
        return "/alu/visualizar_provas.jsp";
    }
}
