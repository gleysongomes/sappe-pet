/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Tipo;
import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.Utility;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.TipoService;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.util.Msg;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

/**
 *
 * @author gleyson
 */
public class CmdListarQuestoes implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

        HttpSession hS = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        Long ide = Long.parseLong(request.getParameter("ide"));
        Integer nq = Integer.parseInt(request.getParameter("nQ"));
        String caminho = request.getParameter("caminho");
        DateTime hI = new DateTime();
        QuestaoService qS = new QuestaoService();
        Perfil perfil = (Perfil) hS.getAttribute("user");
        Long u = perfil.getUsuario().getId();
        Utility utility = new Utility();
        utility.setTpid(id);
        utility.setId(u);
        utility.setQtdq(nq);
        utility.setIde(ide);
        List<Questao> subListaDeQuestoes = qS.getListQuestoesByArea(utility);

        Map<Long, Questao> mapQuestoes = new HashMap<Long, Questao>();

        for(Questao elem: subListaDeQuestoes){
            mapQuestoes.put(elem.getId(), elem);
        }


        if (nq == 0) {
            return Mensagens(request, caminho, "Selecione uma opção.");
        } else if (subListaDeQuestoes.size() == 0) {
            return Mensagens(request, caminho, Msg.msg);
        } else if (subListaDeQuestoes.size() < nq) {
            return Mensagens(request, caminho, Msg.msg2);
        } else {
            hS.removeAttribute("provaSalva");
            hS.setAttribute("subListaDeQuestoes", subListaDeQuestoes);
            hS.setAttribute("MapaQuestoes", mapQuestoes);
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
