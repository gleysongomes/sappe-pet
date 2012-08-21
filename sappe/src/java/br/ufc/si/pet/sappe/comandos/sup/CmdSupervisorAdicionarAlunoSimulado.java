/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.sup;

import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.entidades.UsuarioSimulado;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.service.UsuarioSimuladoService;
import br.ufc.si.pet.sappe.util.SendMail;
import com.ibatis.sqlmap.client.SqlMapException;
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
public class CmdSupervisorAdicionarAlunoSimulado implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {

        HttpSession session = request.getSession(true);
        session.removeAttribute("mSucesso");
        session.removeAttribute("mErro");
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Long idS = (Long) session.getAttribute("idSimulado");
            UsuarioSimuladoService uss = new UsuarioSimuladoService();
            UsuarioSimulado usuarioSimulado = new UsuarioSimulado();
            usuarioSimulado.setSimulado_id(idS);
            usuarioSimulado.setUsuario_id(id);
            uss.insertUsuarioSimulado(usuarioSimulado);
            UsuarioService us = new UsuarioService();
            Usuario u = us.getUsuarioById(id);
            SendMail.sendMail(u.getEmail(), "Realizar Simulado.", "Oi " + u.getNome() + ", <br />"
                    + "um simulado foi adicionado ao sistema.<br /><br />"
                    + "<a href=" + request.getLocalName() + request.getContextPath() + "/index.jsp" + "> Realizar Simulado </a>");
            session.setAttribute("mSucesso", "Aluno adicionado com sucesso.");
        } catch (NumberFormatException nfe) {
            session.setAttribute("mErro", nfe.getMessage());
            nfe.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        } catch (SqlMapException e) {
            session.setAttribute("mErro", e.getMessage());
            e.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        } catch (NullPointerException npe) {
            session.setAttribute("mErro", npe.getMessage());
            npe.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        } catch (Exception e) {
            session.setAttribute("mErro", e.getMessage());
            e.printStackTrace();
            return "/sup/sup_adicionar_simulado_restrito.jsp";
        }
        return "/sup/sup_adicionar_simulado_restrito.jsp";
    }
}
