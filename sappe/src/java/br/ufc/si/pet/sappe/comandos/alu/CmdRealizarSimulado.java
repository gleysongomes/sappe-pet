/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.QuestaoSimulado;
import br.ufc.si.pet.sappe.entidades.QuestaoUsuarioSimulado;
import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoSimuladoService;
import br.ufc.si.pet.sappe.service.QuestaoUsuarioSimuladoService;
import br.ufc.si.pet.sappe.service.SimuladoService;
import br.ufc.si.pet.sappe.util.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;
import org.joda.time.DateTime;

/**
 *
 * @author gleyson
 */
public class CmdRealizarSimulado implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {

        HttpSession session = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        Perfil p = (Perfil) session.getAttribute("user");
        SimuladoService simuladoService = new SimuladoService();
        Simulado simulado = simuladoService.getSimuladoById(id);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Format format = new SimpleDateFormat("dd/MM/yyyy");
        String data = simulado.getData();
        String dataAtual = format.format(new Date());
        System.out.println(data+ "===="+dataAtual);
        Date horaini = sdf.parse(simulado.getHoraini());
        Date horafim = sdf.parse(simulado.getHorafim());
        Date horaAtual = sdf.parse(Util.getTime());
        if (dataAtual.equals(data) && horaAtual.after(horaini) && horaAtual.before(horafim)) {
            QuestaoSimuladoService questaoSimuladoService = new QuestaoSimuladoService();
            List<QuestaoSimulado> questaoSimulados = questaoSimuladoService.getListQuestaoSimuladoByIdSimulado(id);
            QuestaoUsuarioSimuladoService quss = new QuestaoUsuarioSimuladoService();
            QuestaoUsuarioSimulado qus = new QuestaoUsuarioSimulado();
            qus.setSimulado_id(simulado.getId());
            qus.setUsuario_id(p.getUsuario().getId());
            List<QuestaoUsuarioSimulado> quses = quss.getQuestoesUsuarioSimuladoByIdUsuarioESimulado(qus);
            DateTime hi = new DateTime();
            session.setAttribute("simulado", simulado);
            session.setAttribute("hi", hi);
            session.setAttribute("questaoSimulados", questaoSimulados);
            //if(quses.size()==0)return "/index.jsp";
            session.setAttribute("quses", quses);
            return "/alu/listar_questoes_simulado.jsp";
        } else {
            session.setAttribute("erro", "Sem permissão para realizar este simulado, verifique os horários corretamente.");
            return "/alu/visualizar_simulados.jsp";
        }
    }
}
