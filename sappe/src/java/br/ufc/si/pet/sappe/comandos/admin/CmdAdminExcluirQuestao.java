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
public class CmdAdminExcluirQuestao implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);

        String id = request.getParameter("id");
        String ano = request.getParameter("ano");
        System.out.println(id);
        QuestaoService qs = new QuestaoService();
        qs.deleteQuestaoById(Long.parseLong(id));

        List<Questao> questoes = qs.visualizarQuestoesAnoExame(ano);
        if (questoes == null || questoes.isEmpty()) {
           session.setAttribute("visualiza_Questoes", questoes);
            return "/admin/admin_visualizar_questoes.jsp";
        } else {
            session.setAttribute("visualiza_Questoes", questoes);
            return "/admin/admin_visualizar_questoes.jsp";
        }
       }

}
