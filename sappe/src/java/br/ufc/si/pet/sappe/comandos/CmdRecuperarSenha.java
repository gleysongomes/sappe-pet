/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos;

import br.ufc.si.pet.sappe.comandos.alu.CmdAdicionarAluno;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.util.SendMail;
import br.ufc.si.pet.sappe.util.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdRecuperarSenha implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession hS = request.getSession(true);
        String email = request.getParameter("emailrec");

        System.out.println(email);

        if (email == null || email.trim().isEmpty()) {
            hS.setAttribute("erro", "Preencha todos os campos.");
            return "/recuperar_senha.jsp";
        } else {
            Usuario u = new Usuario();
            UsuarioService userService = new UsuarioService();

            //verifica se existe alguem com aquele email
            u = userService.getUsuarioByEmail(email);

           
            if (u != null && email.trim().equals(u.getEmail())) {
            
               

                try {
                   

                    UsuarioService us = new UsuarioService();
                    String senha = Util.createRandomString(1);
                    u.setEmail(email);
                    u.setSenha(Util.criptografar(senha));
                    us.updateUsuarioByEmail(u);

                    SendMail.sendMail(u.getEmail(), "Recuperar sua senha.", "<html> <head></head> <body> Oi " + u.getNome() + ", <br />"
                            + "abaixo seu Login e sua nova Senha de acesso.<br /><br />"
                            + "Login: " + u.getLogin() + "<br />" + "Senha: " + senha + "</body> </html>");
                    hS.setAttribute("sucesso", "A senha foi enviada para o seu email.");
                } catch (AddressException ex) {
                    Logger.getLogger(CmdAdicionarAluno.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    Logger.getLogger(CmdAdicionarAluno.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }catch(Exception e){
                    e.printStackTrace();
                }
                hS.setAttribute("erro", "não foi possivel enviar o email.");

                return "/recuperar_senha.jsp";
            } else {
               // System.out.println("não encontrado");

                hS.setAttribute("erro", "Endereço de email não encontrado.");
                return "/recuperar_senha.jsp";
            }
        }
    }
}
