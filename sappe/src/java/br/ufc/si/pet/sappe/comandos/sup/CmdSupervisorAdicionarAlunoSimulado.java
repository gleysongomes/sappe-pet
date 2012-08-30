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
import br.ufc.si.pet.sappe.util.Util;
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
        try {
            int na = Integer.parseInt(request.getParameter("na"));
            Long idS = (Long) session.getAttribute("idSimulado");
            if (idS != null) {
                for (int i = 0; i < na; i++) {
                    Long id = Long.parseLong(request.getParameter("id" + i));
                    UsuarioSimuladoService uss = new UsuarioSimuladoService();
                    UsuarioSimulado usuarioSimulado = new UsuarioSimulado();
                    usuarioSimulado.setSimulado_id(idS);
                    usuarioSimulado.setUsuario_id(id);
                    uss.insertUsuarioSimulado(usuarioSimulado);
                    UsuarioService us = new UsuarioService();
                    Usuario u = us.getUsuarioById(id);
                    //{Se der erro e aqui :)}
                    SendMail.sendMail(u.getEmail(), "Realizar Simulado.", "Oi " + u.getNome() + ", <br />"
                            + "um simulado foi adicionado ao sistema.<br /><br />"
                            + "<a href=" + Util.getUrl(request) + "/sappe/index.jsp> Realizar Simulado </a>");
                }
                session.setAttribute("sucesso", "Alunos adicionados com sucesso.");
                return "/sup/sup_adicionar_aluno_simulado.jsp";
            } else {
                session.setAttribute("erro", "Cadastre um simulado primeiro, depois adicione os alunos participantes.");
                return "/sup/sup_adicionar_aluno_simulado.jsp";
            }

        } catch (NumberFormatException nfe) {
            session.setAttribute("erro", nfe.getMessage());
            nfe.printStackTrace();
            return "/sup/sup_adicionar_aluno_simulado.jsp";
        } catch (SqlMapException e) {
            session.setAttribute("erro", e.getMessage());
            e.printStackTrace();
            return "/sup/sup_adicionar_aluno_simulado.jsp";
        } catch (NullPointerException npe) {
            session.setAttribute("erro", npe.getMessage());
            //npe.printStackTrace();
            return "/sup/sup_adicionar_aluno_simulado.jsp";
        } catch (Exception e) {
            session.setAttribute("erro", e.getMessage());
            e.printStackTrace();
            return "/sup/sup_adicionar_aluno_simulado.jsp";
        }
    }
}
