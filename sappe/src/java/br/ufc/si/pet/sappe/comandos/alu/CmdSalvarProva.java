/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Tipo;
import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.Questao;
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
public class CmdSalvarProva implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        List<Questao> questoes = (List<Questao>) session.getAttribute("subListaDeQuestoes");
        Tipo tipo = (Tipo) session.getAttribute("tipo");
        DateTime hI = (DateTime) session.getAttribute("hI");
        Integer nQ = (Integer) session.getAttribute("oP");
        Perfil p = (Perfil) session.getAttribute("user");
        ProvaService pS = new ProvaService();
        Prova provaSalva = (Prova) session.getAttribute("provaSalva");
        Prova prova = pS.getProvaById(util(provaSalva));
        if (prova == null) {
            int i, resolvidas = 0, brancas = 0, certas = 0, erradas = 0;
            for (i = 0; i < nQ; i++) {
                String itemEscolhido = (String) request.getParameter("iM" + i);
                if (itemEscolhido == null) {
                    brancas++;
                    if(questoes.get(i).getItem().equals("N")){certas++;}
                }
                else if (itemEscolhido.equals(questoes.get(i).getItem()) ||
                        questoes.get(i).getItem().equals("N")){certas++;resolvidas++;}
                else {erradas++;resolvidas++;}
            }
            DateTime dT = new DateTime();
            prova = new Prova();
            prova.setTipo_id(tipo.getId());
            prova.setUsuario_id(p.getUsuario().getId());
            prova.setNumero_questoes(nQ);
            prova.setRespondidas(resolvidas);
            prova.setBrancas(brancas);
            prova.setCertas(certas);
            prova.setErradas(erradas);
            prova.setTempo_prova(Util.calcularTempo(hI.toString(), dT.toString()));
            prova.setData(Util.treatToString(new Date()));
            pS.inserir(prova);
            QuestaoProvaService qpS = new QuestaoProvaService();
            int count = 0, status;
            for (Questao questao : questoes) {
                String iM = (String) request.getParameter("iM" + count);
                if (iM == null) {
                    if(questoes.get(count).getItem().equals("N")){status=3;}
                    else {status = 0;}
                }else if(questoes.get(count).getItem().equals("N")){status=3;}
                else if (iM.equals(questoes.get(count).getItem())){status = 1;}
                else{status = 2;}
                QuestaoProva qP = inserir(prova.getId(), questao.getId(), iM, status);
                qpS.inserir(qP);
                count++;
            }
            List<Prova> provas = pS.getListAllProvasByIdUsuario(p.getUsuario().getId());
            session.setAttribute("provas", provas);
            session.setAttribute("provaSalva", prova);
            session.setAttribute("sucesso", "Prova salva com sucesso.");
            return "/alu/visualizar_provas.jsp";
        } else {
            session.setAttribute("erro", "Esta prova já foi salva. Para editar e visualizar uma prova acesse o menu Visualizar Provas.");
            return "/alu/listar_questoes.jsp";
        }
    }

    public QuestaoProva inserir(Long prova_id, Long questao_id, String item_marcado, Integer status) {

        QuestaoProva qP = new QuestaoProva();
        qP.setProva_id(prova_id);
        qP.setQuestao_id(questao_id);
        qP.setItem_marcado(item_marcado);
        qP.setStatus(status);
        return qP;
    }

    private Long util(Prova p) {
        return p == null ? 0L : p.getId();
    }
}
