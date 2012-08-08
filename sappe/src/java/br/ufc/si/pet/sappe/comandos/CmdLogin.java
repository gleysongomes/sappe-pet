/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos;

import br.ufc.si.pet.sappe.entidades.Administrador;
import br.ufc.si.pet.sappe.entidades.Aluno;
import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.UsuarioService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gleyson
 */
public class CmdLogin implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession hS = request.getSession(true);
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String conta = request.getParameter("conta");

        if (senha == null || login == null || conta == null || senha.trim().isEmpty()
                || login.trim().isEmpty() || conta.trim().isEmpty()) {
            hS.setAttribute("erro", "Preencha todos os campos.");
        } else {
            Usuario user = new Usuario();
            user.setLogin(login);
            user.setSenha(senha);
            UsuarioService userService = new UsuarioService();
            Perfil perfil = userService.validarUsuario(user, conta);
            if (perfil == null || perfil.getAtivo() == false) {
                hS.setAttribute("erro", "Usuário, senha e conta não conferem.");
            } else if (perfil instanceof Aluno) {
                hS.setAttribute("user", perfil);
                //Usuario u = new Usuario();
                //u = userService.getUsuarioById(perfil.getUsuario().getId());
                //hS.setAttribute("user", u);
                return "/alu/index.jsp";
            } else if (perfil instanceof Administrador) {
                hS.setAttribute("user", perfil);
                //Usuario u = new Usuario();
                //u = userService.getUsuarioById(perfil.getUsuario().getId());
                //hS.setAttribute("user", u);
                return "/admin/index.jsp";
            }
        }
        return "/index.jsp";
    }
}
