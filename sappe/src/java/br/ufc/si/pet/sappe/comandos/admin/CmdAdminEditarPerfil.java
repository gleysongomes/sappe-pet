/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.UsuarioService;
import com.ibatis.sqlmap.client.SqlMapException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;


/**
 *
 * @author Filipe
 */
public class CmdAdminEditarPerfil implements Comando{

    public String executa(HttpServletRequest request, HttpServletResponse 
           response) throws ServletException, IOException, ClassNotFoundException, 
           SQLException, FileUploadException, Exception {
        HttpSession hS = request.getSession(true);

        try {
            Perfil perfil = (Perfil) hS.getAttribute("user");
            UsuarioService usuarioService = new UsuarioService();
            Usuario  u = new Usuario();
            u = usuarioService.getUsuarioById(perfil.getUsuario().getId());
            hS.setAttribute("editar", u);
        } catch (SqlMapException e) {
            e.printStackTrace();
            hS.setAttribute("erro", "Erro ao tentar editar cadastro.");
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            hS.setAttribute("erro", "Erro ao tentar editar cadastro.");
        } catch (NumberFormatException nfe) {
            hS.setAttribute("erro", "Erro ao tentar editar a prova.");
            nfe.printStackTrace();
        }
        return "/admin/admin_editar_cadastro.jsp";

    }    
}
