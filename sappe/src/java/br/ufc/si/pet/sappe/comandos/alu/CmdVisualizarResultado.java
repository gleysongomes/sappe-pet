/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Tipo;
import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.TipoService;
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
        TipoService aS = new TipoService();
        Tipo tipo = aS.getTipoById(p.getTipo_id());
        hS.setAttribute("tipoProva2", tipo.getNome());
        return "/alu/visualizar_resultado.jsp";
    }
}
