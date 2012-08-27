/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Area;
import br.ufc.si.pet.sappe.entidades.Tipo;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.AreaService;
import br.ufc.si.pet.sappe.service.TipoService;
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
public class CmdAdminAdicionarArea implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);
        Integer id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        if (id == 0 || nome == null || nome.isEmpty()) {
            session.setAttribute("erro", "Preencha todos os campos (*).");
            return "/admin/admin_adicionar_area.jsp";
        } else {
            Area a = new Area();
            a.setNome(nome);
            a.setExame_id(id);
            AreaService areaService = new AreaService();
            areaService.inserir(a);
            Tipo tipo = new Tipo();
            TipoService tipoService = new TipoService();
            tipoService.inserir(tipo);
            return "/admin/admin_adicionar_area.jsp";
        }
    }
}
