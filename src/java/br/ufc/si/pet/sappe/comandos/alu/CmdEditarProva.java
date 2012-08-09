/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.entidades.Tipo;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.ProvaService;
import br.ufc.si.pet.sappe.service.QuestaoProvaService;
import br.ufc.si.pet.sappe.service.TipoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

/**
 *
 * @author gleyson
 */
public class CmdEditarProva implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        //JspCalendar jS = new JspCalendar();
        //String hI2 = jS.getTime();
        DateTime hI2  = new DateTime();
        hS.setAttribute("hI2", hI2);
        QuestaoProvaService qpS = new QuestaoProvaService();
        List<QuestaoProva> qPs = qpS.getListQuestaoProvaById(id);
        hS.setAttribute("qPs", qPs);
        ProvaService pS = new ProvaService();
        Prova p = pS.getProvaById(id);
        hS.setAttribute("prova2", p);
        TipoService tS = new TipoService();
        Tipo tipo = tS.getTipoById(p.getTipo_id());
        hS.setAttribute("tipo2", tipo);
        hS.setAttribute("nQ2", p.getNumero_questoes());
        return "/alu/refazer_prova.jsp";
    }
}
