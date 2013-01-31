/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.QuestaoSimulado;
import br.ufc.si.pet.sappe.entidades.QuestaoUsuarioSimulado;
import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.service.QuestaoSimuladoService;
import br.ufc.si.pet.sappe.service.QuestaoUsuarioSimuladoService;
import br.ufc.si.pet.sappe.service.SimuladoService;
import br.ufc.si.pet.sappe.util.Horario;
import br.ufc.si.pet.sappe.util.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String data_ini = simulado.getData_ini();
        String hora_ini = simulado.getHoraini();
        String data_fim = simulado.getData_fim();
        String hora_fim = simulado.getHorafim();
        Horario h = new Horario();
        boolean statusSimulado = h.verificar(data_ini,hora_ini,data_fim,hora_fim,new Date());
        if (statusSimulado) {
            QuestaoSimuladoService questaoSimuladoService = new QuestaoSimuladoService();
            List<QuestaoSimulado> questaoSimulados = questaoSimuladoService.getListQuestaoSimuladoByIdSimulado(id);
            QuestaoUsuarioSimuladoService quss = new QuestaoUsuarioSimuladoService();
            QuestaoUsuarioSimulado qus = new QuestaoUsuarioSimulado();
            qus.setSimulado_id(simulado.getId());
            qus.setUsuario_id(p.getUsuario().getId());
            List<QuestaoUsuarioSimulado> quses = quss.getQuestoesUsuarioSimuladoByIdUsuarioESimulado(qus);
            DateTime hi = new DateTime();


            QuestaoService qs = new QuestaoService();


             Map<Long, Questao> mapQuestoesSimulado = new HashMap<Long, Questao>();

        for(QuestaoSimulado elem: questaoSimulados){
            Questao temp = qs.getQuestaoById(elem.getQuestao_id());
            mapQuestoesSimulado.put(temp.getId(), temp);
        }



            session.setAttribute("simulado", simulado);
            session.setAttribute("hi", hi);
            session.setAttribute("questaoSimulados", questaoSimulados);
            session.setAttribute("MapQuestaoSimulados", mapQuestoesSimulado);

            //if(quses.size()==0)return "/index.jsp";
            session.setAttribute("quses", quses);
            return "/alu/listar_questoes_simulado.jsp";
        } else {
 session.setAttribute("erro", "Sem permissão para realizar este simulado, verifique os horários corretamente.");
            return "/alu/visualizar_simulados.jsp";
        }
    }
}
