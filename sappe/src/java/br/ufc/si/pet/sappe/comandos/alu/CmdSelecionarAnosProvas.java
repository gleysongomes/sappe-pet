/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdSelecionarAnosProvas implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        QuestaoService questaoService = new QuestaoService();
        List<Questao> questoes = questaoService.getAllAnosQuestoesByExame(id);
        System.out.println("===" + id);
        if (questoes == null) {
            questoes = new ArrayList<Questao>();
        }
        if (id == 1L) {
            session.setAttribute("ap", questoes);
            return "/alu/poscomp_padrao.jsp";
        } else {
            session.setAttribute("ae", questoes);
            return "/alu/enade_padrao.jsp";
        }
    }
}
