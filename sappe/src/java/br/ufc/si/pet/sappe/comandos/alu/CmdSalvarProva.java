/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Aluno;
import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.entidades.Tipo;
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
public class CmdSalvarProva implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        try {
            List<Questao> questoes = (List<Questao>) hS.getAttribute("subListaDeQuestoes");
            Tipo tipo = (Tipo) hS.getAttribute("tipo");
            DateTime hI = (DateTime) hS.getAttribute("hI");
            Integer nQ = (Integer) hS.getAttribute("oP");
            Aluno a = (Aluno) hS.getAttribute("user");
            int i, resolvidas = 0, brancas = 0, certas = 0, erradas = 0;
            for (i = 0; i < nQ; i++) {
                String itemEscolhido = (String) request.getParameter("iM" + i);
                if (itemEscolhido == null)
                    brancas++;
                else if (itemEscolhido.equals(questoes.get(i).getItem()))
                {certas++;resolvidas++;}
                else
                {erradas++;resolvidas++;}
            }

            DateTime dT = new DateTime();
            Prova prova = new Prova();
            prova.setTipo_id(tipo.getId());
            prova.setUsuario_id(a.getUsuario().getId());
            prova.setNumero_questoes(nQ);
            prova.setRespondidas(resolvidas);
            prova.setBrancas(brancas);
            prova.setCertas(certas);
            prova.setErradas(erradas);
            prova.setTempo_prova(Util.calcularTempo(hI.toString(), dT.toString()));
            prova.setData(Util.treatToString(new Date()));
            ProvaService pS = new ProvaService();
            pS.inserir(prova);
            //System.out.println("="+prova.getId());
            QuestaoProvaService qpS = new QuestaoProvaService();
            //List<Questao> questoes = (List<Questao>) hS.getAttribute("subListaDeQuestoes");
            int count = 0, status;
            for (Questao questao : questoes) {
                String iM = (String) request.getParameter("iM" + count);
                if (iM == null)
                    status = 0;
                else if (iM.equals(questoes.get(count).getItem()))
                    status = 1;
                else
                    status = 2;
                QuestaoProva qP = inserir(prova.getId(), questao.getId(), iM, status);
                qpS.inserir(qP);
                count++;
            }
            hS.setAttribute("sucesso", "Prova salva com sucesso.");
        } catch (Error e) {
            e.printStackTrace();
            hS.setAttribute("erro", "Erro ao tentar salvar a prova.");
        }
        return "/alu/listar_questoes.jsp";
    }

    public QuestaoProva inserir(Long prova_id, Long questao_id, String item_marcado, Integer status) {

        QuestaoProva qP = new QuestaoProva();
        qP.setProva_id(prova_id);
        qP.setQuestao_id(questao_id);
        qP.setItem_marcado(item_marcado);
        qP.setStatus(status);
        return qP;
    }
}
