/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoProvaService;
import br.ufc.si.pet.sappe.util.Util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gleyson
 */
public class CmdVisualizarArquivo implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        Long id = Long.parseLong(request.getParameter("id"));
        QuestaoProvaService questaoProvaService = new QuestaoProvaService();
        QuestaoProva questaoProva = questaoProvaService.getQuestaoProvaById(id);
        response.setContentType("application/*");
        try {
            String nome = Util.createRandomString(8);
            File file = new File("/tmp/" + nome.concat(questaoProva.getNome_arquivo()));
            FileOutputStream fileout = new FileOutputStream(file);
            BufferedOutputStream buffer = new BufferedOutputStream(fileout);
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "postgres");
            String sql = "SELECT arquivo FROM sappe.questao_prova WHERE id=?;";
            PreparedStatement query = conn.prepareStatement(sql);
            query.setLong(1, id);
            ResultSet rS = query.executeQuery();
            rS.next();
            InputStream iS = rS.getBinaryStream(1);
            byte[] bytes;
            bytes = new byte[iS.available()];
            iS.read(bytes, 0, iS.available());
            buffer.write(bytes);
            buffer.flush();
            buffer.close();
            conn.close();
            response.setContentLength((int) file.length());
            FileInputStream fileInputStream = new FileInputStream(file);
            ServletOutputStream out = null;
            out = response.getOutputStream();
            int bytes2;
            while ((bytes2 = fileInputStream.read()) != -1)
                out.write(bytes2);
            out.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CmdVisualizarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CmdVisualizarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CmdVisualizarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "/alu/visualizar_resultado.jsp";
    }
}
