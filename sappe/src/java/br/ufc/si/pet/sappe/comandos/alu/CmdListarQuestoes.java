/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Area;
import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.AreaService;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.util.Msg;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

/**
 *
 * @author gleyson
 */
public class CmdListarQuestoes implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

        HttpSession hS = request.getSession(true);
        Long idArea = Long.parseLong(request.getParameter("id"));
        Integer nq = Integer.parseInt(request.getParameter("nQ"));
        String caminho = request.getParameter("caminho");

        DateTime hI = new DateTime();
        QuestaoService qS = new QuestaoService();
        Perfil perfil = (Perfil) hS.getAttribute("user");
        Long u = perfil.getUsuario().getId();
        Prova prova = new Prova();
        prova.setArea_id(idArea);
        prova.setUsuario_id(u);
        prova.setNumero_questoes(nq);
        List<Questao> subListaDeQuestoes = qS.getListQuestoesByArea(prova);
        System.out.println("====++" + subListaDeQuestoes.size());
        if (nq == 0) {
            return Mensagens(request, caminho, "Selecione uma opção.");
        } else if (subListaDeQuestoes.size() == 0) {
            return Mensagens(request, caminho, Msg.msg);
        } else if (subListaDeQuestoes.size() < nq) {
            return Mensagens(request, caminho, Msg.msg2);
        } else {
            hS.setAttribute("subListaDeQuestoes", subListaDeQuestoes);
            hS.setAttribute("hI", hI);
            AreaService aS = new AreaService();
            Area a = aS.getAreaById(idArea);
            hS.setAttribute("area", a);
            hS.setAttribute("oP", nq);
            return "/alu/listar_questoes.jsp";
        }
    }

    private String Mensagens(HttpServletRequest request, String caminho, String msg) {
        HttpSession hS = request.getSession(true);
        hS.setAttribute("erro", msg);
        return caminho;
    }
}
