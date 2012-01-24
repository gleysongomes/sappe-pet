/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.ProvaService;
import br.ufc.si.pet.sappe.service.QuestaoProvaService;
import br.ufc.si.pet.sappe.util.Util;
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
        DateTime hI2 = (DateTime) hS.getAttribute("hI2");
        Integer nQ2 = (Integer) hS.getAttribute("nQ2");
        int i, r = 0, b = 0;
        for (i = 1; i <= nQ2; i++) {
            String itemEscolhido = (String) request.getParameter("iM" + i);
            String respostaQuestao = (String) request.getParameter("res" + i);
            if (itemEscolhido == null || respostaQuestao == null
                    || respostaQuestao.trim().equals(""))b++;
            else r++;
        }
        Prova prova = (Prova) hS.getAttribute("prova2");
        prova.setRespondida(r);
        prova.setBranca(b);
        DateTime hT = new DateTime();
        String cT = Util.calcularTempo(hI2.toString(), hT.toString());
        String cH = Util.calcularHorario(cT, prova.getTempo_prova());
        prova.setTempo_prova(cH);
        prova.setData(Util.treatToString(new Date()));
        ProvaService pS = new ProvaService();
        pS.updateProva(prova);
        QuestaoProvaService qpS = new QuestaoProvaService();
        List<QuestaoProva> qPs = (List<QuestaoProva>) hS.getAttribute("qPs");
        int count = 1;
        for (QuestaoProva qP : qPs) {
            String iM = (String) request.getParameter("iM" + count);
            String res = (String) request.getParameter("res" + count);
            qP.setProva_id(prova.getId());
            qP.setItem_marcado(iM);
            qP.setResposta(res);
            qpS.updateQuestaoProva(qP);
            count++;
        }
        hS.setAttribute("msgSucesso", "Prova salva com sucesso.");
        return "/alu/visualizar_provas.jsp";
    }
}
