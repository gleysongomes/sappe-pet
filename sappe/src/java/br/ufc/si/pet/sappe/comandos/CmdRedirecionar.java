/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.interfaces.Comando;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdRedirecionar implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);
        try {
            String str = request.getParameter("url");
            session.removeAttribute("sucesso");
            session.removeAttribute("erro");
            Perfil perfil = (Perfil) session.getAttribute("user");
            if (perfil != null)
                return str;
            else{
                session.setAttribute("erro", "Faça login novamente.");
                return "/index.jsp";
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return "/index.jsp";
        }
    }
}
