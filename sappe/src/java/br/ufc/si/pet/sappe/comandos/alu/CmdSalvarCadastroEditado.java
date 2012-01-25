/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

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
public class CmdSalvarCadastroEditado implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String instituicao = request.getParameter("instituicao");
        String rua = request.getParameter("rua");
        String bairro = request.getParameter("bairro");
        Integer nunero = Integer.getInteger(request.getParameter("numero"));
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String senha = request.getParameter("senha");
        String rSenha = request.getParameter("rsenha");
        if (!senha.trim().equals(rSenha)) {
            hS.setAttribute("erro", "A senha não confere com a sua confirmação.");
            return "/alu/cadastro.jsp";
        }
        Perfil perfil = (Perfil) hS.getAttribute("user");
        UsuarioService usuarioService = new UsuarioService();
        Usuario u = new Usuario();
        u = usuarioService.getUsuarioById(perfil.getUsuario().getId());
        u.setNome(nome);
        u.setEmail(email);
        u.setFone(telefone);
        u.setBairro(bairro);
        u.setInstituicao(instituicao);
        u.setCidade(cidade);
        u.setRua(rua);
        u.setNumero(nunero);
        u.setUf(uf);
        u.setSenha(senha);
        if (usuarioService.updateUsuario(u)) {
            hS.setAttribute("sucesso", "Cadastro editado com sucesso.");
        } else {
            hS.setAttribute("sucesso", "Erro ao tentar editar seu cadastro..");
        }
        return "/alu/editar_cadastro.jsp";
    }
}
