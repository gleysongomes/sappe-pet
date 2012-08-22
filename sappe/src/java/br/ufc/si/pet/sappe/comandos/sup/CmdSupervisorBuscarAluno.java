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
        session.removeAttribute("mSucesso");
        session.removeAttribute("mErro");
        session.removeAttribute("uS");
        try {
            String nome = request.getParameter("nome");
            if (nome == null || nome.isEmpty()) {
                session.setAttribute("mErro", "Preencha todos os campos obrigatórios (*).");
                return "/sup/sup_adicionar_simulado_restrito.jsp";
            }
            UsuarioService us = new UsuarioService();
            List<Usuario> usuarios = us.getUsuariosByNome(nome);
            System.out.println("====" + usuarios.size());
            session.setAttribute("uS", usuarios);
        } catch (NumberFormatException nfe) {
            session.setAttribute("mErro", nfe.getMessage());
            nfe.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        } catch (SqlMapException e) {
            session.setAttribute("mErro", e.getMessage());
            e.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        } catch (NullPointerException npe) {
            session.setAttribute("mErro", npe.getMessage());
            npe.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        } catch (Exception e) {
            session.setAttribute("mErro", e.getMessage());
            e.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        }
        return "/sup/sup_adicionar_simulado_restrito.jsp";
    }
}
