/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.entidades.Tipo;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.ProvaService;
import br.ufc.si.pet.sappe.service.QuestaoProvaService;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.service.TipoService;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.util.Msg;
import java.util.ArrayList;
import java.util.Collections;
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

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        Long ano = Long.parseLong(request.getParameter("ano"));
        Integer n = Integer.parseInt(request.getParameter("nQ"));
        String caminho = request.getParameter("caminho");
        if (ano == 0)
            return Mensagens(request, caminho, "Selecione uma opção.");

        DateTime hI = new DateTime();
        QuestaoService qS = new QuestaoService();
        List<Questao> listaDeQuestoes = qS.getListQuestoes(ano);
        List<Questao> subListaDeQuestoes = new ArrayList<Questao>();

        if (listaDeQuestoes.size() == 0)
            return Mensagens(request, caminho, Msg.msg);

        Perfil perfil = (Perfil) hS.getAttribute("user");
        Usuario usuario = new Usuario();
        UsuarioService uS = new UsuarioService();
        usuario = uS.getUsuarioById(perfil.getUsuario().getId());
        ProvaService pS = new ProvaService();
        List<Prova> provas = pS.getAllListProvas(usuario.getId());
        if (provas.size() == 0) {
            Collections.shuffle(listaDeQuestoes);
            for (int i = 0; i < n; i++)
                subListaDeQuestoes.add(listaDeQuestoes.get(i));
            if (subListaDeQuestoes.size() < n)
                return Mensagens(request, caminho, Msg.msg2);
            hS.setAttribute("subListaDeQuestoes", subListaDeQuestoes);
        } else {
            int condicao, u = 0;
            for (Questao q : listaDeQuestoes) {
                condicao = 1;
                for (Prova p : provas) {
                    QuestaoProvaService questaoProvaService = new QuestaoProvaService();
                    List<QuestaoProva> qps = questaoProvaService.getListQuestaoProvaById(p.getId());
                    for (QuestaoProva qp : qps) {
                        if (qp.getQuestao_id().equals(q.getId())
                                && usuario.getId().equals(p.getUsuario_id())) {
                            condicao = 0;
                        }
                    }
                }
                if (condicao == 1)
                    subListaDeQuestoes.add(listaDeQuestoes.get(u));
                u++;
            }

            if (subListaDeQuestoes.size() < n)
                return Mensagens(request, caminho, Msg.msg2);
            Collections.shuffle(subListaDeQuestoes);
            List<Questao> subListaDeQuestoes2 = new ArrayList<Questao>();
            for (int i = 0; i < n; i++)
                subListaDeQuestoes2.add(subListaDeQuestoes.get(i));
            hS.setAttribute("subListaDeQuestoes", subListaDeQuestoes2);
        }
        hS.setAttribute("hI", hI);
        TipoService tS = new TipoService();
        Tipo tipo = tS.getTipoById(id);
        hS.setAttribute("tipo", tipo);
        hS.setAttribute("oP", n);
        return "/alu/listar_questoes.jsp";
    }

    private String Mensagens(HttpServletRequest request, String caminho, String msg) {
        HttpSession hS = request.getSession(true);
        hS.setAttribute("erro", msg);
        return caminho;
    }
}
