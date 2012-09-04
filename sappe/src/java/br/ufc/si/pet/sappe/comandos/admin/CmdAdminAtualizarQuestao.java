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

        Questao q = new  Questao();
        QuestaoService qs = new QuestaoService();

       long id = Long.parseLong(request.getParameter("id"));
       System.out.println(id);

       String nome = request.getParameter("nome");
       String item = request.getParameter("item");
       String ano = request.getParameter("ano");
       String area_id = request.getParameter("area_id");
       String exame_id = request.getParameter("exame_id");

           

       if(nome==null || nome.isEmpty() || item==null || item.isEmpty() || ano==null || ano.isEmpty() ||
          area_id==null || area_id.isEmpty() || exame_id==null || exame_id.isEmpty()){
           hs.setAttribute("erro", "preecha todos os campos");
            
           //pega a questao e coloca na sessão
           q = qs.getQuestaoById(id);
           hs.setAttribute("questaoEditada", q);

           return "/admin/admin_atualizar_questao.jsp";
       }else{
            //atualiza os campos de questão
           q.setNome(nome);
           q.setAno(ano);
           q.setItem(item);
           q.setArea_id(Integer.parseInt(area_id));
           q.setExame_id(Integer.parseInt(exame_id));
           try{

                qs.updateQuestao(q);
                
                    return "/admin/admin_visualizar_questoes.jsp";
           }catch(Exception e){
             e.printStackTrace();
               hs.setAttribute("erro", "erro ao atualizar");
                    //pega a questao e coloca na sessão
                    q = qs.getQuestaoById(id);
                    hs.setAttribute("questaoEditada", q);
                    return "/admin/admin_atualizar_questao.jsp";
                }
         }

       }//fim do método

}//fim da classe
