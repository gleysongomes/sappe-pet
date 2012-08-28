/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.sup;

import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.QuestaoSimulado;
import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.entidades.Utility;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.service.QuestaoSimuladoService;
import br.ufc.si.pet.sappe.service.SimuladoService;
import com.ibatis.sqlmap.client.SqlMapException;
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
public class CmdSupervisorAdicionarSimuladoRestrito implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);
        try {
            String nome = request.getParameter("nome");
            Long eid = Long.parseLong(request.getParameter("exame"));
            String data = request.getParameter("data");
            String hi = request.getParameter("hi");
            String ht = request.getParameter("ht");

            if (nome == null || nome.isEmpty() || eid == null || eid.equals(0L) || data == null || data.isEmpty() || hi == null || hi.isEmpty() || ht == null || ht.isEmpty()) {
                session.setAttribute("erro", "Preencha todos os campos (*).");
                return "/sup/sup_adicionar_simulado_restrito.jsp";
            } else if (eid == 1L) {
                int nm = Integer.parseInt(request.getParameter("nm"));
                int nfc = Integer.parseInt(request.getParameter("nfc"));
                int ntc = Integer.parseInt(request.getParameter("ntc"));
                Simulado simulado = new Simulado();
                simulado.setNome(nome);
                simulado.setData(data);
                simulado.setHoraini(hi);
                simulado.setHorafim(ht);
                simulado.setNum_questao(nm + nfc + ntc);
                simulado.setExame_id(eid);
                simulado.setStatus(true);
                SimuladoService simuladoService = new SimuladoService();
                simuladoService.inserir(simulado);
                Long idS = simuladoService.proxId();
                //session.setAttribute("idSimulado", idS);
                if (!inserir(1L, idS, nm)) {
                    session.setAttribute("erro", "Temos menos de " + nm + " questões de Matemática disponíveis.");
                    return "/sup/sup_adicionar_simulado_restrito.jsp";
                } else if (!inserir(2L, idS, nfc)) {
                    session.setAttribute("erro", "Temos menos de " + nfc + " questões de Fundamentos da Computação disponíveis.");
                    return "/sup/sup_adicionar_simulado_restrito.jsp";
                } else if (!inserir(3L, idS, ntc)) {
                    session.setAttribute("erro", "Temos menos de " + ntc + " questões de Tecnologia da Computação disponíveis.");
                    return "/sup/sup_adicionar_simulado_restrito.jsp";
                }
            } else {
                int nsi = Integer.parseInt(request.getParameter("nsi"));
                int nes = Integer.parseInt(request.getParameter("nes"));
                int ncg = Integer.parseInt(request.getParameter("ncg"));
                Simulado simulado = new Simulado();
                simulado.setNome(nome);
                simulado.setData(data);
                simulado.setHoraini(hi);
                simulado.setHorafim(ht);
                simulado.setNum_questao(nsi + nes + ncg);
                simulado.setExame_id(eid);
                simulado.setStatus(true);
                SimuladoService simuladoService = new SimuladoService();
                simuladoService.inserir(simulado);
                Long idS = simuladoService.proxId();
                //session.setAttribute("idSimulado", idS);
                if (!inserir(4L, idS, nsi)) {
                    session.setAttribute("erro", "Temos menos de " + nsi + " questões de Sistemas de Informação disponíveis.");
                    return "/sup/sup_adicionar_simulado_restrito.jsp";
                } else if (!inserir(5L, idS, nes)) {
                    session.setAttribute("erro", "Temos menos de " + nes + " questões de Engenharia de Software disponíveis.");
                    return "/sup/sup_adicionar_simulado_restrito.jsp";
                } else if (!inserir(6L, idS, ncg)) {
                    session.setAttribute("erro", "Temos menos de " + ncg + " questões de Conhecimentos Gerais disponíveis.");
                    return "/sup/sup_adicionar_simulado_restrito.jsp";
                }
            }
            session.setAttribute("sucesso", "Simulado cadastrado com sucesso.");
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        } catch (NumberFormatException nfe) {
            session.setAttribute("erro", nfe.getMessage());
            nfe.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        } catch (SqlMapException e) {
            session.setAttribute("erro", e.getMessage());
            e.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        } catch (NullPointerException npe) {
            session.setAttribute("erro", npe.getMessage());
            npe.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        } catch (Exception e) {
            session.setAttribute("erro", e.getMessage());
            e.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        }
    }

    private boolean inserir(Long id, Long idS, int nq) {
        QuestaoSimuladoService questaoSimuladoService = new QuestaoSimuladoService();
        QuestaoService questaoService = new QuestaoService();
        Utility utility = new Utility();
        utility.setId(id);
        utility.setQtdq(nq);
        List<Questao> subListaDeQuestoes = questaoService.getListQuestoesByAreaSimulado(utility);
        if (subListaDeQuestoes.size() < nq) {
            return false;
        } else {
            for (Questao q : subListaDeQuestoes) {
                QuestaoSimulado questaoSimulado = new QuestaoSimulado();
                questaoSimulado.setSimulado_id(idS);
                questaoSimulado.setQuestao_id(q.getId());
                questaoSimuladoService.inserir(questaoSimulado);
            }
            return true;
        }
    }
}
