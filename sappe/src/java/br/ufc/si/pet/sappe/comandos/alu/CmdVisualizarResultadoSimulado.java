/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.QuestaoUsuarioSimulado;
import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.entidades.ResultadoUsuarioSimulado;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoUsuarioSimuladoService;
import br.ufc.si.pet.sappe.service.SimuladoService;
import br.ufc.si.pet.sappe.service.ResultadoUsuarioSimuladoService;
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
public class CmdVisualizarResultadoSimulado implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {

        HttpSession session = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        Perfil p = (Perfil) session.getAttribute("user");
        ResultadoUsuarioSimuladoService uss = new ResultadoUsuarioSimuladoService();
        ResultadoUsuarioSimulado u = new ResultadoUsuarioSimulado();
        u.setSimulado_id(id);
        u.setUsuario_id(p.getUsuario().getId());
        ResultadoUsuarioSimulado us = uss.getResultadoUsuarioSimuladoByUsuarioId(u);
        if (us == null || !(us.getUsuario_id().equals(p.getUsuario().getId()))) {
            session.setAttribute("erro", "Realize o simulado primeiro.");
            return "/alu/visualizar_simulados.jsp";
        } else {
            SimuladoService simuladoService = new SimuladoService();
            Simulado simulado = simuladoService.getSimuladoById(id);
            session.setAttribute("simulado2", simulado);
            ResultadoUsuarioSimuladoService usuarioSimuladoService = new ResultadoUsuarioSimuladoService();
            ResultadoUsuarioSimulado usuarioSimulado = usuarioSimuladoService.getResultadoUsuarioSimuladoByUsuarioId(us);
            session.setAttribute("resultadoUsuarioSimulado", usuarioSimulado);
            QuestaoUsuarioSimuladoService quss = new QuestaoUsuarioSimuladoService();
            QuestaoUsuarioSimulado qus = new QuestaoUsuarioSimulado();
            qus.setSimulado_id(simulado.getId());
            qus.setUsuario_id(p.getUsuario().getId());
            List<QuestaoUsuarioSimulado> quses = quss.getQuestoesUsuarioSimuladoByIdUsuarioESimulado(qus);
            session.setAttribute("quses2", quses);
            return "/alu/visualizar_resultado_simulado.jsp";
        }
    }
}
