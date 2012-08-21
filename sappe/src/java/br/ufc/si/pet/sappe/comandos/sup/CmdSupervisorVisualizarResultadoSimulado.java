/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.sup;

import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.entidades.ResultadoUsuarioSimulado;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.SimuladoService;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.service.ResultadoUsuarioSimuladoService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class CmdSupervisorVisualizarResultadoSimulado implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {

        HttpSession session = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        ResultadoUsuarioSimuladoService uss = new ResultadoUsuarioSimuladoService();
        List<ResultadoUsuarioSimulado> usuarioSimulados = uss.getResultadosUsuariosSimuladosBySimuladoId(id);
        UsuarioService us = new UsuarioService();
        List<Usuario> usuarios = new ArrayList<Usuario>();
        for (ResultadoUsuarioSimulado usuarioSimulado : usuarioSimulados) {
            Usuario u = us.getUsuarioById(usuarioSimulado.getUsuario_id());
            usuarios.add(u);
        }
        SimuladoService simuladoService = new SimuladoService();
        Simulado simulado = simuladoService.getSimuladoById(id);
        session.setAttribute("sup_simulado2", simulado);
        session.setAttribute("usuarios", usuarios);
        return "/sup/visualizar_resultado_simulado.jsp";
    }
}
