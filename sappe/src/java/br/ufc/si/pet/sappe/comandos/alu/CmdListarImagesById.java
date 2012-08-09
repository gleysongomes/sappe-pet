/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.interfaces.Comando;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdListarImagesById implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        response.setContentType("image/png");
        Long id = Long.parseLong(request.getParameter("id"));
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "postgres");
        PreparedStatement pS = conn.prepareStatement("SELECT arquivo FROM sappe.questao WHERE id=?");
        pS.setLong(1, id);
        ResultSet rs = pS.executeQuery();
        rs.next();
        InputStream in = rs.getBinaryStream(1);
        byte[] arqBytes = new byte[in.available()];
        in.read(arqBytes, 0, in.available());
        response.setContentLength(arqBytes.length);
        response.getOutputStream().write(arqBytes);
        pS.close();
        conn.close();
        return "/alu/listar_questoes.jsp";
    }
}
