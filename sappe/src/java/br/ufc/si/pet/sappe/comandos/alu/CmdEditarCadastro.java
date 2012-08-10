/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.UsuarioService;
import com.ibatis.sqlmap.client.SqlMapException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gleyson
 */
public class CmdEditarCadastro implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        try {
            Perfil perfil = (Perfil) hS.getAttribute("user");
            UsuarioService usuarioService = new UsuarioService();
            Usuario u = new Usuario();
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
        return "/alu/editar_cadastro.jsp";
    }
}
