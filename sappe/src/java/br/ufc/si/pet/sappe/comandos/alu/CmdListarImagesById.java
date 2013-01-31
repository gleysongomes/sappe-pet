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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdListarImagesById implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        response.setContentType("image/png");
        HttpSession hs = request.getSession();

        try{

        String id = request.getParameter("id");
        
            if(id == null || id.trim().isEmpty()){
                hs.setAttribute("erro", "id não encontrado");
                return "/alu/listar_questoes.jsp";
            }else{
             System.out.println(id);
                Long temp = Long.parseLong(id);
                Map<Long, Questao> map = new HashMap<Long, Questao>();
                   map = (Map<Long, Questao>) hs.getAttribute("MapaQuestoes");

                if(map  == null){
                     hs.setAttribute("erro", "id não encontrado");
                     return "/alu/listar_questoes.jsp";
                }else{
                    Questao questao = new Questao();
                    questao = map.get(temp);
                    
                    response.setContentLength(questao.getArquivo().length);
                    response.getOutputStream().write(questao.getArquivo());
                    return "/alu/listar_questoes.jsp";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return "/alu/listar_questoes.jsp";
        }
    }
}
