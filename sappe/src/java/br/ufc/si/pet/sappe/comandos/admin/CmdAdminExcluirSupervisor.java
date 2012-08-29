/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.comandos.admin;

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
public class CmdAdminExcluirSupervisor implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
            
        return "";
    }
   
}
