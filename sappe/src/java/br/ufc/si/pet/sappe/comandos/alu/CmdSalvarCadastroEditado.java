/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gleyson
 */
public class CmdSalvarCadastroEditado implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String santiga = request.getParameter("santiga");
        String senha = request.getParameter("nsenha");
        String rSenha = request.getParameter("rsenha");
        if (!senha.trim().equals(rSenha)) {
            hS.setAttribute("erro", "A senha não confere com a sua confirmação.");
            return "/alu/editar_cadastro.jsp";
        }
        Perfil perfil = (Perfil) hS.getAttribute("user");
        UsuarioService usuarioService = new UsuarioService();
        Usuario u = new Usuario();
        u = usuarioService.getUsuarioById(perfil.getUsuario().getId());
        if (!Util.criptografar(santiga).trim().equals(u.getSenha())) {
            hS.setAttribute("erro", "A senha antiga é inexistente.");
            return "/alu/editar_cadastro.jsp";
        }
        u.setNome(nome);
        u.setEmail(email);
        u.setSenha(Util.criptografar(senha));
        if (usuarioService.updateUsuario(u)) {
            hS.setAttribute("sucesso", "Cadastro editado com sucesso.");
        } else {
            hS.setAttribute("erro", "Erro ao tentar editar seu cadastro..");
        }
        return "/alu/editar_cadastro.jsp";
    }
}
