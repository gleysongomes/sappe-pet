/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Supervisor;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.PapelService;
import br.ufc.si.pet.sappe.service.PerfilService;
import br.ufc.si.pet.sappe.service.SupervisorService;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.util.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdAdminAdicionarSupervisor implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {


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
        Usuario usuario = usuarioService.getUsuarioByEmail(email);
        if (nome == null || nome.trim().isEmpty() || email == null || email.trim().isEmpty()
                || senha == null || senha.trim().isEmpty() || rSenha == null || rSenha.trim().isEmpty()) {
            hS.setAttribute("erro", "Preencha todos os campos obrigatórios.");
            return "/admin/admin_adicionar_supervisor.jsp";
        } else if (!senha.trim().equals(rSenha)) {
            hS.setAttribute("erro", "A senha não confere com a sua confirmação.");
            return "/admin/admin_adicionar_supervisor.jsp";
        } else if (usuario != null) {
            hS.setAttribute("erro", "Este email já estão cadastrado.");
            return "/admin/admin_adicionar_supervisor.jsp";
        } else {
            Usuario u = new Usuario();
            u.setLogin(login);
            u.setNome(nome);
            u.setEmail(email);
            u.setSenha(Util.criptografar(senha));
            u.setCodigo(Util.createRandomString(9));
            UsuarioService uS = new UsuarioService();
            uS.insertUsuario(u);

            usuario = uS.getUsuarioById(uS.getProxId());
            Perfil perfil = new Perfil();
            perfil.setUsuario(usuario);
            perfil.setPapel(new PapelService().getPapelById(2L));
            perfil.setDataCriacao(data);
            perfil.setAtivo(true);
            PerfilService pS = new PerfilService();
            if (pS.insertPerfil(perfil)) {
                hS.setAttribute("sucesso", "Supervisor cadastrado com sucesso.");
                return "/admin/admin_adicionar_supervisor.jsp";
            } else {
                hS.setAttribute("erro", "Erro ao tentar cadastrar.");
                return "/admin/admin_adicionar_supervisor.jsp";
            }
        }
    }//fim do método




    
}
