/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.ProvaService;
import br.ufc.si.pet.sappe.service.QuestaoProvaService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gleyson
 */
public class CmdAdminSalvarCorrecaoProva implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        try {
            Prova prova = (Prova) hS.getAttribute("prova3");
            QuestaoProvaService qpS = new QuestaoProvaService();
            List<QuestaoProva> questaoProvas = (List<QuestaoProva>) hS.getAttribute("questaoProvas");
            int u = 1, aceitas=0, erradas=0;
            for (QuestaoProva questaoProva : questaoProvas) {
                Integer status = Integer.parseInt(isTrue(request.getParameter("status" + u)));
                String dica = (String) request.getParameter("dica" + u);
                if(status==1)aceitas++;
                else if(status==2)erradas++;
                //QuestaoProva qp = new QuestaoProva();
                questaoProva.setProva_id(prova.getId());
                questaoProva.setStatus(status);
                questaoProva.setDica(dica);
                qpS.updateQuestaoProvaByIdProva(questaoProva);
                u++;
            }
            System.out.println("======"+aceitas);
            prova.setAceitas(aceitas);
            prova.setErradas(erradas);
            ProvaService pS = new ProvaService();
            pS.updateProvaById(prova);
            hS.setAttribute("sucesso", "Correção salva com sucesso.");
        } catch (Exception e) {
            hS.setAttribute("erro", "Erro ao tentar salvar correção.");
        }
        return "/admin/admin_corrigir_prova.jsp";
    }

    private String isTrue(String str) {
        if (str == null || str.trim().equals("")) {
            return "0";
        } else {
            return str;
        }
    }
}
