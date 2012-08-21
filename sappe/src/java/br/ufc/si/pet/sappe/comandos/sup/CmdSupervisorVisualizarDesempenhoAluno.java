/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.sup;

import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.entidades.ResultadoUsuarioSimulado;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.ResultadoUsuarioSimuladoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gleyson
 */
public class CmdSupervisorVisualizarDesempenhoAluno implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        Simulado simulado=(Simulado)session.getAttribute("sup_simulado2");
        ResultadoUsuarioSimulado u = new ResultadoUsuarioSimulado();
        u.setSimulado_id(simulado.getId());
        u.setUsuario_id(id);
        ResultadoUsuarioSimuladoService usuarioSimuladoService = new ResultadoUsuarioSimuladoService();
        ResultadoUsuarioSimulado usuarioSimulado = usuarioSimuladoService.getResultadoUsuarioSimuladoByUsuarioId(u);
        session.setAttribute("usuarioSimulado", usuarioSimulado);
        return "/sup/visualizar_resultado_aluno.jsp";
    }
}
