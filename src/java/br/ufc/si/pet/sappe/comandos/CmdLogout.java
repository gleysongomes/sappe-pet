/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.comandos;

import br.ufc.si.pet.sappe.interfaces.Comando;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Escritorio projetos
 */
public class CmdLogout implements Comando{

    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "/index.jsp";
    }

}
