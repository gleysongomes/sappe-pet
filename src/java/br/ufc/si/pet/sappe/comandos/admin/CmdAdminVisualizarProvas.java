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
import br.ufc.si.pet.sappe.util.Msg;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gleyson
 */
public class CmdAdminVisualizarProvas implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        String caminho = request.getParameter("caminho");
        if (id == 0)
            return Mensagens(request, caminho, "Selecione uma área.");

        ProvaService pS = new ProvaService();
        List<Prova> provas = pS.getProvaByTipoId(id);

        if (provas.size() == 0)
            return Mensagens(request, caminho, Msg.msg3);
        else {
            int condicao;
            int u = 0;
            List<Prova> subListaDeProvas = new ArrayList<Prova>();
            for (Prova p : provas) {
                QuestaoProvaService questaoProvaService = new QuestaoProvaService();
                List<QuestaoProva> qps = questaoProvaService.getListQuestaoProvaById(p.getId());
                condicao = 1;
                for (QuestaoProva qp : qps) {
                    if (qp.getStatus() == 0 || qp.getStatus() == 2) {
                        condicao = 0;
                    }
                }
                if (condicao == 0)
                    subListaDeProvas.add(provas.get(u));
                u++;
            }
            if (subListaDeProvas.size() == 0)
                return Mensagens(request, caminho, Msg.msg3);
            hS.setAttribute("subListaDeProvas", subListaDeProvas);
        }
        return "/admin/admin_visualizar_provas.jsp";
    }

    private String Mensagens(HttpServletRequest request, String caminho, String msg) {
        HttpSession hS = request.getSession(true);
        hS.setAttribute("erro", msg);
        return caminho;
    }
}
