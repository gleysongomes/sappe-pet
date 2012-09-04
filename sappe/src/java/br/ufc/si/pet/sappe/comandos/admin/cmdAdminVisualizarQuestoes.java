/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Tipo;
import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.Utility;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.TipoService;
import br.ufc.si.pet.sappe.service.QuestaoService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

/**
 *
 * @author mardson
 */
public class cmdAdminVisualizarQuestoes implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {
         HttpSession session = request.getSession(true);
        QuestaoService qs = new QuestaoService();
        List<Questao> questoes = qs.visualizarTodasQuestoes();
        if (questoes == null)
            questoes = new ArrayList<Questao>();
        session.setAttribute("visualiza_Questoes", questoes);
        return "/admin/admin_visualizar_questoes.jsp";
    }//fim do método

}
