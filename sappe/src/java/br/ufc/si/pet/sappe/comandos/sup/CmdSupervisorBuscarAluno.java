/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.sup;

import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.UsuarioService;
import com.ibatis.sqlmap.client.SqlMapException;
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
 * @author gleyson
 */
public class CmdSupervisorBuscarAluno implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);
        session.removeAttribute("uS");
        try {
            String nome = request.getParameter("nome");
            if (nome == null || nome.isEmpty()) {
                session.setAttribute("erro", "Preencha todos os campos obrigatórios (*).");
                return "/sup/sup_adicionar_aluno_simulado.jsp";
            }
            UsuarioService us = new UsuarioService();
            List<Usuario> usuarios = us.getUsuariosByNome(nome);
            System.out.println("====" + usuarios.size());
            session.setAttribute("uS", usuarios);
        } catch (NumberFormatException nfe) {
            session.setAttribute("erro", nfe.getMessage());
            nfe.printStackTrace();
            return "/sup/sup_adicionar_aluno_simulado.jsp";
        } catch (SqlMapException e) {
            session.setAttribute("erro", e.getMessage());
            e.printStackTrace();
            return "/sup/sup_adicionar_aluno_simulado.jsp";
        } catch (NullPointerException npe) {
            session.setAttribute("erro", npe.getMessage());
            npe.printStackTrace();
            return "/sup/sup_adicionar_aluno_simulado.jsp";
        } catch (Exception e) {
            session.setAttribute("erro", e.getMessage());
            e.printStackTrace();
            return "/sup/sup_adicionar_aluno_simulado.jsp";
        }
        return "/sup/sup_adicionar_aluno_simulado.jsp";
    }
}
