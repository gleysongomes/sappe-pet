/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.Utility;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mardson
 */
public class CmdAdminVisualizarQuestoes implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String ano = request.getParameter("ano");
        //Long id = Long.parseLong(request.getParameter("id"));
        Long ide = Long.parseLong(request.getParameter("ide"));
        //Integer nq = Integer.parseInt(request.getParameter("nQ"));
        Utility utility = new Utility();
        //utility.setTpid(id);
        //utility.setId(id);
        //utility.setQtdq(nq);
        utility.setIde(ide);

        session.setAttribute("anoExame", ano);
        QuestaoService qs = new QuestaoService();
        List<Questao> questoes = qs.visualizarQuestoesAnoExame(ano);
        if (questoes == null || questoes.isEmpty()) {
            session.setAttribute("erro", "nenhuma questão encontrada");
            return "/admin/admin_buscar_questao_ano.jsp";
        } else {
            session.setAttribute("visualiza_Questoes", questoes);
            return "/admin/admin_visualizar_questoes.jsp";
        }
    }//fim do método
}
