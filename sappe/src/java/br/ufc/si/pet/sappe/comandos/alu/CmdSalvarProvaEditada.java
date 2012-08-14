/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.ProvaService;
import br.ufc.si.pet.sappe.service.QuestaoProvaService;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.util.Util;
import com.ibatis.sqlmap.client.SqlMapException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

/**
 *
 * @author gleyson
 */
public class CmdSalvarProvaEditada implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        try {
            DateTime hI2 = (DateTime) hS.getAttribute("hI2");
            Integer nQ2 = (Integer) hS.getAttribute("nQ2");
            List<QuestaoProva> qp = (List<QuestaoProva>) hS.getAttribute("qPs");
            QuestaoService questaoService = new QuestaoService();
            int i, resolvidas = 0, brancas = 0, certas = 0, erradas = 0;
            for (i = 0; i < nQ2; i++) {
                String itemEscolhido = (String) request.getParameter("iM" + i);
                Questao q = questaoService.getQuestaoById(qp.get(i).getQuestao_id());
                if (itemEscolhido == null) {
                    brancas++;
                } else if (itemEscolhido.equals(q.getItem())) {
                    certas++;
                    resolvidas++;
                } else {
                    erradas++;
                    resolvidas++;
                }
            }
            Prova prova = (Prova) hS.getAttribute("prova2");
            prova.setRespondidas(resolvidas);
            prova.setBrancas(brancas);
            prova.setCertas(certas);
            prova.setErradas(erradas);
            DateTime hT = new DateTime();
            String cT = Util.calcularTempo(hI2.toString(), hT.toString());
            String cH = Util.calcularHorario(cT, prova.getTempo_prova());
            prova.setTempo_prova(cH);
            prova.setData(Util.treatToString(new Date()));
            ProvaService pS = new ProvaService();
            pS.updateProva(prova);
            QuestaoProvaService qpS = new QuestaoProvaService();
            List<QuestaoProva> qPs = (List<QuestaoProva>) hS.getAttribute("qPs");
            int count = 0, status;
            for (QuestaoProva qP : qPs) {
                String iM = (String) request.getParameter("iM" + count);
                Questao q = questaoService.getQuestaoById(qp.get(count).getQuestao_id());
                if (iM == null)
                    status = 0;
                else if (iM.equals(q.getItem()))
                    status = 1;
                else
                    status = 2;

                qP.setProva_id(prova.getId());
                qP.setItem_marcado(iM);
                qP.setStatus(status);
                qpS.updateQuestaoProvaByIdProva(qP);
                count++;
            }
            hS.setAttribute("sucesso", "Prova atualizada com sucesso.");
        } catch (SqlMapException e) {
            e.printStackTrace();
            hS.setAttribute("erro", "Erro ao tentar atualizada a prova.");
        } catch (NullPointerException npe) {
            hS.setAttribute("erro", "Erro ao tentar atualizada a prova.");
            npe.printStackTrace();
        } catch (Exception e) {
            hS.setAttribute("erro", "Erro ao tentar atualizada a prova.");
            e.printStackTrace();
        }
        return "/alu/refazer_prova.jsp";
    }
}
