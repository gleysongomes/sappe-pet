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
            Tipo tipo = (Tipo) hS.getAttribute("tipo");
            DateTime hI = (DateTime) hS.getAttribute("hI");
            Integer nQ = (Integer) hS.getAttribute("oP");
            Aluno a = (Aluno) hS.getAttribute("user");
            int i, r = 0, b = 0;
            for (i = 1; i <= nQ; i++) {
                String itemEscolhido = (String) request.getParameter("iM" + i);
                String respostaQuestao = (String) request.getParameter("res" + i);
                if (itemEscolhido == null || respostaQuestao == null
                        || respostaQuestao.trim().equals(""))b++;
                else r++;
            }

            DateTime dT = new DateTime();
            Prova prova = new Prova();
            prova.setTipo_id(tipo.getId());
            prova.setUsuario_id(a.getUsuario().getId());
            prova.setNumero_questoes(nQ);
            prova.setRespondida(r);
            prova.setBranca(b);
            prova.setTempo_prova(Util.calcularTempo(hI.toString(), dT.toString()));
            prova.setData(Util.treatToString(new Date()));
            ProvaService pS = new ProvaService();
            pS.inserir(prova);
            //System.out.println("="+prova.getId());
            QuestaoProvaService qpS = new QuestaoProvaService();
            List<Questao> questoes = (List<Questao>) hS.getAttribute("subListaDeQuestoes");
            int count = 1;
            for (Questao questao : questoes) {
                String iM = (String) request.getParameter("iM" + count);
                //System.out.println("==" + iM);
                String res = (String) request.getParameter("res" + count);
                QuestaoProva qP = inserir(prova.getId(), questao.getId(), questao.getNome(), iM, res, 0, "", 0);
                qpS.inserir(qP);
                count++;
            }
            hS.setAttribute("sucesso", "Prova salva com sucesso.");
        } catch (Exception e) {
            hS.setAttribute("erro", "Erro ao tentar salvar a prova.");
        }
        return "/alu/listar_questoes.jsp";
    }

    public QuestaoProva inserir(Long prova_id, Long questao_id, String nome, String item_marcado, String resposta, Integer status, String dica, int nota) {

        QuestaoProva qP = new QuestaoProva();
        qP.setProva_id(prova_id);
        qP.setQuestao_id(questao_id);
        qP.setNome(nome);
        qP.setItem_marcado(item_marcado);
        qP.setResposta(resposta);
        qP.setStatus(status);
        qP.setDica(dica);
        qP.setNota(nota);
        return qP;
    }
}
