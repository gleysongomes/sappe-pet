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
import br.ufc.si.pet.sappe.entidades.ResultadoUsuarioSimulado;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.service.QuestaoUsuarioSimuladoService;
import br.ufc.si.pet.sappe.service.ResultadoUsuarioSimuladoService;
import br.ufc.si.pet.sappe.util.Util;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

/**
 *
 * @author gleyson
 */
public class CmdSalvarSimulado implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ParseException {

        HttpSession session = request.getSession(true);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Format format = new SimpleDateFormat("dd/MM/yyyy");
        Simulado simulado = (Simulado) session.getAttribute("simulado");
        String data_ini = simulado.getData_ini();
        String data_fim = simulado.getData_fim();

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        GregorianCalendar gc = new GregorianCalendar();

        Date inicio = formatador.parse(data_ini);
        Date fim = formatador.parse(data_fim);
        Date data_atual = gc.getTime();


        Date horaini = sdf.parse(simulado.getHoraini());
        Date horafim = sdf.parse(simulado.getHorafim());
        Date horaAtual = sdf.parse(Util.getTime());

        if ((data_atual.before(fim)|| data_atual.equals(fim)) && horaAtual.after(horaini) && horaAtual.before(horafim)) {
            List<QuestaoSimulado> questoes = (List<QuestaoSimulado>) session.getAttribute("questaoSimulados");
            DateTime hi = (DateTime) session.getAttribute("hi");
            DateTime dt = new DateTime();
            Perfil p = (Perfil) session.getAttribute("user");
            QuestaoService questaoService = new QuestaoService();
            int i, resolvidas = 0, brancas = 0, certas = 0, erradas = 0;
            for (i = 0; i < simulado.getNum_questao(); i++) {
                String itemEscolhido = (String) request.getParameter("iM" + i);
                Questao q = questaoService.getQuestaoById(questoes.get(i).getQuestao_id());
                if (itemEscolhido == null) {
                    brancas++;
                    if (q.getItem().equals("N")) {
                        certas++;
                    }
                } else if (itemEscolhido.equals(q.getItem()) || q.getItem().equals("N")) {
                    certas++;
                    resolvidas++;
                } else {
                    erradas++;
                    resolvidas++;
                }
            }
            ResultadoUsuarioSimulado us = new ResultadoUsuarioSimulado();
            us.setSimulado_id(simulado.getId());
            us.setUsuario_id(p.getUsuario().getId());
            us.setRespondidas(resolvidas);
            us.setCertas(certas);
            us.setBrancas(brancas);
            us.setErradas(erradas);
            us.setTempo_prova(Util.calcularTempo(hi.toString(), dt.toString()));
            ResultadoUsuarioSimulado usuarioSimulado = new ResultadoUsuarioSimulado();
            usuarioSimulado.setSimulado_id(simulado.getId());
            usuarioSimulado.setUsuario_id(p.getUsuario().getId());
            ResultadoUsuarioSimuladoService usuarioSimuladoService = new ResultadoUsuarioSimuladoService();
            ResultadoUsuarioSimulado u = usuarioSimuladoService.getResultadoUsuarioSimuladoByUsuarioId(usuarioSimulado);
            if (u == null || !(u.getUsuario_id().equals(p.getUsuario().getId()))) {
                usuarioSimuladoService.inserir(us);
                inserir(request, questoes, simulado, p, questaoService);
                session.setAttribute("sucesso", "Simulado salvo com sucesso.");
            } else {
                usuarioSimuladoService.updateResultadoUsuarioSimulado(us);
                update(request, questoes, simulado, p, questaoService);
                session.setAttribute("sucesso", "Simulado atualizado com sucesso.");
            }
            return "/alu/visualizar_simulados.jsp";
        } else {
            session.setAttribute("erro", "O tempo destinado a este simulado terminou.");
            return "/alu/visualizar_simulados.jsp";
        }
    }

    public QuestaoUsuarioSimulado criar(Long simulado_id, Long questao_id, Long usuario_id, String item_marcado, int status) {

        QuestaoUsuarioSimulado qus = new QuestaoUsuarioSimulado();
        qus.setSimulado_id(simulado_id);
        qus.setQuestao_id(questao_id);
        qus.setUsuario_id(usuario_id);
        qus.setItem_marcado(item_marcado);
        qus.setStatus(status);
        return qus;
    }

    private void inserir(HttpServletRequest request, List<QuestaoSimulado> questoes, Simulado simulado, Perfil p, QuestaoService questaoService) {
        QuestaoUsuarioSimuladoService quss = new QuestaoUsuarioSimuladoService();
        int status;
        for (int i = 0; i < simulado.getNum_questao(); i++) {
            String iM = (String) request.getParameter("iM" + i);
            Questao q = questaoService.getQuestaoById(questoes.get(i).getQuestao_id());
            if (iM == null) {
                if (q.getItem().equals("N")) {
                    status = 3;
                } else {
                    status = 0;
                }
            } else if (q.getItem().equals("N")) {
                status = 3;
            } else if (iM.equals(q.getItem())) {
                status = 1;
            } else {
                status = 2;
            }
            QuestaoUsuarioSimulado questaoUsuarioSimulado = criar(simulado.getId(), q.getId(), p.getUsuario().getId(), iM, status);
            quss.inserir(questaoUsuarioSimulado);
        }
    }

    private void update(HttpServletRequest request, List<QuestaoSimulado> questoes, Simulado simulado, Perfil p, QuestaoService questaoService) {
        QuestaoUsuarioSimuladoService quss = new QuestaoUsuarioSimuladoService();
        int status;
        for (int i = 0; i < simulado.getNum_questao(); i++) {
            String iM = (String) request.getParameter("iM" + i);
            Questao q = questaoService.getQuestaoById(questoes.get(i).getQuestao_id());
            if (iM == null) {
                if (q.getItem().equals("N")) {
                    status = 3;
                } else {
                    status = 0;
                }
            } else if (q.getItem().equals("N")) {
                status = 3;
            } else if (iM.equals(q.getItem())) {
                status = 1;
            } else {
                status = 2;
            }
            QuestaoUsuarioSimulado questaoUsuarioSimulado = criar(simulado.getId(), q.getId(), p.getUsuario().getId(), iM, status);
            quss.update(questaoUsuarioSimulado);
        }
    }
}
