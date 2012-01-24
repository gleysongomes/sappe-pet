/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

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

/**
 *
 * @author gleyson
 */
public class CmdAdminCorrigirProva implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        //System.out.println("======"+id);
        QuestaoProvaService qpS = new QuestaoProvaService();
        List<QuestaoProva> questaoProvas = qpS.getListQuestaoProvaById(id);
        hS.setAttribute("questaoProvas", questaoProvas);
        ProvaService pS = new ProvaService();
        Prova prova = pS.getProvaById(id);
        hS.setAttribute("prova3", prova);
        TipoService tS = new TipoService();
        Tipo tipo = tS.getTipoById(prova.getTipo_id());
        hS.setAttribute("tipo3", tipo);
        return "/admin/admin_corrigir_prova.jsp";
    }
}
