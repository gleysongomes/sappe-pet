/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;


import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.PapelService;
import br.ufc.si.pet.sappe.service.PerfilService;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.util.SendMail;
import br.ufc.si.pet.sappe.util.Util;
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
public class CmdAdicionarAluno implements Comando {

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
            Usuario u = new Usuario();
            u.setLogin(login);
            u.setNome(nome);
            u.setEmail(email);
            u.setSenha(Util.criptografar(senha));
            u.setCodigo(Util.createRandomString(9));
            //insere o usuario
            UsuarioService uS = new UsuarioService();
            uS.insertUsuario(u);
            //busca o usuario pelo login e a senha
            usuario = uS.getUsuarioByLoginSenha(u);

            Perfil perfil = new Perfil();
            perfil.setUsuario(usuario);
            perfil.setPapel(new PapelService().getPapelById(1L));
            perfil.setDataCriacao(data);
            perfil.setAtivo(false);
            PerfilService pS = new PerfilService();
            if (pS.insertPerfil(perfil)) {
                try {
                    System.out.println("===" + perfil.getUsuario().getEmail());
                    SendMail.sendMail(perfil.getUsuario().getEmail(), "Ativar sua conta.", "<html><head> </head> <body>Oi " + perfil.getUsuario().getNome() + ", <br />"
                            + "para ter seu cadastro aceito, ative sua conta acessando o endereço abaixo. Obs: Você terá sete dias para ativar sua conta.<br /><br />"
                            + "<a href=" + Util.getUrl(request) + "/sappe/ServletCentral?comando=CmdAtivarConta&id=" + perfil.getId() + "&cod=" + perfil.getUsuario().getCodigo()+ ">ativar minha conta</a> </body></html>");
                } catch (AddressException ex) {
                    Logger.getLogger(CmdAdicionarAluno.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(CmdAdicionarAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
                hS.setAttribute("sucesso", "Para ter seu cadastro aceito entre no email fornecido e ative sua conta.");
                return "/cadastro.jsp";
            } else {
                hS.setAttribute("erro", "Erro ao tentar cadastrar.");
                return "/cadastro.jsp";
            }
        }
    
    }//fim do método
}
