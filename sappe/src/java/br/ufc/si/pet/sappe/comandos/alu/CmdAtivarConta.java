/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.PerfilService;
import br.ufc.si.pet.sappe.service.UsuarioService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gleyson
 */
public class CmdAtivarConta implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {
        String aux = request.getParameter("id");
        String codigo = request.getParameter("cod");

        HttpSession hS = request.getSession(true);

        if(aux == null || aux.trim().isEmpty() || codigo == null || codigo.trim().isEmpty() ){
          hS.setAttribute("erro", "não foi possivel ativar sua conta,tente novamente");
            return "/index.jsp";
        }else{
         Long id = Long.parseLong(aux);
        PerfilService pS = new PerfilService();
        Perfil perfil = pS.getPerfilById(id);
        //System.out.println("======" + id);
        UsuarioService uS = new UsuarioService();
        Usuario u = new Usuario();
        u = uS.getUsuarioById(perfil.getUsuario().getId());
        //System.out.println(u.getCodigo() + "======" + codigo);
        if (u != null && codigo.trim().equals(u.getCodigo())) {
            perfil.setAtivo(true);
            if (pS.ativarConta(perfil)) {
                System.out.println("deu certo!!");
            }
        }
        hS.setAttribute("sucesso", "sua conta foi ativada");
        return "/index.jsp";
    }
    }
}
