/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Area;
import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.Tipo;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.AreaService;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.util.Msg;
import br.ufc.si.pet.sappe.entidades.Utility;
import br.ufc.si.pet.sappe.service.TipoService;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

/**
 *
 * @author gleyson
 */
public class CmdListarQuestoesExamePadrao implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, FileNotFoundException {

        HttpSession hS = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        String ano = request.getParameter("ano");
        Integer nq = Integer.parseInt(request.getParameter("nQ"));
        String caminho = request.getParameter("caminho");

        DateTime hI = new DateTime();
        Perfil perfil = (Perfil) hS.getAttribute("user");
        Utility utility = new Utility();
        utility.setId(perfil.getUsuario().getId());
        utility.setQtdq(nq);
        utility.setAno(ano);
        QuestaoService qS = new QuestaoService();
        List<Questao> subListaDeQuestoes = qS.getListQuestoes(utility);
        System.out.println("=================="+subListaDeQuestoes.size());
        if (ano.trim().equals("0")) {
            return Mensagens(request, caminho, "Selecione uma opção.");
        } else if (subListaDeQuestoes.size() == 0) {
            return Mensagens(request, caminho, Msg.msg);
        } else if (subListaDeQuestoes.size() < nq) {
            return Mensagens(request, caminho, Msg.msg2);
        } else {
            hS.setAttribute("subListaDeQuestoes", subListaDeQuestoes);
            hS.setAttribute("hI", hI);
            TipoService aS = new TipoService();
            Tipo t = aS.getTipoById(id);
            hS.setAttribute("tipo", t);
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
