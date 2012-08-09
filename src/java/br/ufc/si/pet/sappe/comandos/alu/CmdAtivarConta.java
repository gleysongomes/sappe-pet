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

/**
 *
 * @author gleyson
 */
public class CmdAtivarConta implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        Long id = Long.parseLong(request.getParameter("id"));
        String codigo = request.getParameter("cod");
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
        return "/index.jsp";
    }
}
