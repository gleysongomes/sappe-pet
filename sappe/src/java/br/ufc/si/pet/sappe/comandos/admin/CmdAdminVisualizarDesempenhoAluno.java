/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.entidades.UsuarioSimulado;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.UsuarioSimuladoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gleyson
 */
public class CmdAdminVisualizarDesempenhoAluno implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        Simulado simulado=(Simulado)session.getAttribute("adimin_simulado2");
        UsuarioSimulado u = new UsuarioSimulado();
        u.setSimulado_id(simulado.getId());
        u.setUsuario_id(id);
        UsuarioSimuladoService usuarioSimuladoService = new UsuarioSimuladoService();
        UsuarioSimulado usuarioSimulado = usuarioSimuladoService.getUsuarioSimuladoByUsuarioId(u);
        session.setAttribute("usuarioSimulado", usuarioSimulado);
        return "/admin/visualizar_resultado_aluno.jsp";
    }
}
