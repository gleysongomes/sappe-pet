/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author mardson
 */
public class CmdAdminAtualizarQuestao implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {

        HttpSession hs = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String item = request.getParameter("item");
        String ano = request.getParameter("ano");
        String area_id = request.getParameter("area_id");
        String exame_id = request.getParameter("exame_id");
        String anoExame = (String) hs.getAttribute("anoExame");
        if (nome == null || nome.isEmpty() || item == null || item.isEmpty() || ano == null || ano.isEmpty()
                || area_id == null || area_id.isEmpty() || exame_id == null || exame_id.isEmpty()) {
            hs.setAttribute("erro", "preecha todos os campos");
            return "/admin/admin_atualizar_questao.jsp";
        } else {
            try {
                //atualiza os campos de questão
                QuestaoService questaoService = new QuestaoService();
                Questao q = new Questao();
                q.setId(id);
                q.setNome(nome);
                q.setAno(ano);
                q.setItem(item);
                q.setArea_id(Integer.parseInt(area_id));
                q.setExame_id(Integer.parseInt(exame_id));
                questaoService.updateQuestao(q);
                List<Questao> questoes = questaoService.visualizarQuestoesAnoExame(anoExame);
                if (questoes == null || questoes.isEmpty()) {
                    hs.setAttribute("sucesso", "Atualização realizada com sucesso.");
                    return "/admin/admin_buscar_questao_ano.jsp";
                }
                hs.setAttribute("visualiza_Questoes", questoes);
                hs.setAttribute("sucesso", "Atualização realizada com sucesso.");
                return "/admin/admin_visualizar_questoes.jsp";
            } catch (Exception e) {
                hs.setAttribute("erro", e.getMessage());
                return "/admin/admin_atualizar_questao.jsp";
            }
        }
    }//fim do método
}//fim da classe