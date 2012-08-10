/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Area;
import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.AreaService;
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
public class CmdVisualizarResultado implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession hS = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        ProvaService pS = new ProvaService();
        Prova prova = pS.getProvaById(id);
        hS.setAttribute("prova", prova);
        QuestaoProvaService qpS = new QuestaoProvaService();
        List<QuestaoProva> qPs = qpS.getListQuestaoProvaById(id);
        hS.setAttribute("qPs2", qPs);
        Prova p = pS.getProvaById(id);
        AreaService aS = new AreaService();
        Area area = aS.getAreaById(p.getArea_id());
        hS.setAttribute("tipoProva2", area.getNome());
        return "/alu/visualizar_resultado.jsp";
    }
}
