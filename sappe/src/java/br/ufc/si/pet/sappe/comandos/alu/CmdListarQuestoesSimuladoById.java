/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdListarQuestoesSimuladoById implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        response.setContentType("image/png");
        Long id = Long.parseLong(request.getParameter("id"));
        QuestaoService questaoService = new QuestaoService();
        Questao q = questaoService.getQuestaoById(id);

           File originFile = new File(Util.getDiretorio() + "/" + q.getExame_id() + "/" + q.getAno() + "/" + q.getNome());

    if (!originFile.exists() ) {
      return "";
    }
    try {
      byte[] readData = new byte[1024];
      FileInputStream fis = new FileInputStream(originFile);
      int i = fis.read(readData);

      while (i != -1) {
         response.getOutputStream().write(readData);
        i = fis.read(readData);
      }
      fis.close();

    } catch (IOException e) {
      System.out.println(e);
    }

        //response.setContentLength(q.getArquivo().length);
        //response.getOutputStream().write(q.getArquivo());
        return "/alu/listar_questoes_simulado.jsp";
    }
}
