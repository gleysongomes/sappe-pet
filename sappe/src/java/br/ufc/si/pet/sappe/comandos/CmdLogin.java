/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos;

import br.ufc.si.pet.sappe.entidades.Administrador;
import br.ufc.si.pet.sappe.entidades.Aluno;
import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Supervisor;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;

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
            return "/index.jsp";
        } else {
            Usuario user = new Usuario();
            user.setLogin(login);
            user.setSenha(Util.criptografar(senha));
            UsuarioService userService = new UsuarioService();
            Perfil perfil = userService.validarUsuario(user, conta);
            if (perfil == null || perfil.getAtivo() == false) {
                hS.setAttribute("erro", "Usuário, senha e conta não conferem.");
            } else if (perfil instanceof Aluno) {
                hS.setAttribute("user", perfil);
                return "/alu/index.jsp";
            } else if (perfil instanceof Supervisor) {
                hS.setAttribute("user", perfil);
                return "/sup/index.jsp";
            } else if (perfil instanceof Administrador) {
                hS.setAttribute("user", perfil);
                return "/admin/index.jsp";
            }
            return "/index.jsp";
        }
    }
}
