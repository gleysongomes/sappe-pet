/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.util.SendMail;
import br.ufc.si.pet.sappe.util.Util;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gleyson
 */
public class CmdVerificarEmail implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        //recebe os dados da requisição
        HttpSession hS = request.getSession(true);
        String login = request.getParameter("login");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String rSenha = request.getParameter("rsenha");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Date data = Util.treatToDate(dateFormat.format(date));
        UsuarioService usuarioService = new UsuarioService();

        //busca o usuario por email
        Usuario usuario = usuarioService.getUsuarioByEmail(email);
        if (nome == null || nome.trim().isEmpty() || email == null || email.trim().isEmpty()
                || senha == null || senha.trim().isEmpty() || rSenha == null || rSenha.trim().isEmpty()) {
            hS.setAttribute("erro", "Preencha todos os campos.");
            return "/cadastro.jsp";
        } else if (!senha.trim().equals(rSenha)) {
            hS.setAttribute("erro", "A senha não confere com a sua confirmação.");
            return "/cadastro.jsp";
        } else if (usuario != null) {
            hS.setAttribute("erro", "Este email já está cadastrado.");
            return "/cadastro.jsp";
        } else {
            try {
                String codigo = Util.createRandomString(9);
                Usuario u = new Usuario();
                u.setLogin(login);
                u.setNome(nome);
                u.setEmail(email);
                u.setSenha(Util.criptografar(senha));
                u.setCodigo(codigo);
                hS.setAttribute("usuario", u);
                SendMail.enviarEmail(u.getEmail(), "Código de Verificação.", "Oi " + u.getNome() + ", obrigado por criar uma conta no sappe, a seguir o código de ativação da sua conta.<br /><br />" + codigo);
                return "/verifica.jsp";
            } catch (AddressException ex) {
                hS.setAttribute("erro", ex.getMessage());
                Logger.getLogger(CmdVerificarEmail.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                hS.setAttribute("erro", ex.getMessage());
                Logger.getLogger(CmdVerificarEmail.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                hS.setAttribute("erro", ex.getMessage());
                Logger.getLogger(CmdVerificarEmail.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "/cadastro.jsp";
        }
    }
}
