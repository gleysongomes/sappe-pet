/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.sup;

import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.QuestaoSimulado;
import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.entidades.UsuarioSimulado;
import br.ufc.si.pet.sappe.entidades.Utility;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.service.QuestaoSimuladoService;
import br.ufc.si.pet.sappe.service.SimuladoService;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.service.UsuarioSimuladoService;
import br.ufc.si.pet.sappe.util.SendMail;
import br.ufc.si.pet.sappe.util.Util;
import com.ibatis.sqlmap.client.SqlMapException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdSupervisorAdicionarSimulado implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);

        try {
            String nome = request.getParameter("nome");
            Long eid = Long.parseLong(request.getParameter("exame"));
            String data = request.getParameter("data");
            String hi = request.getParameter("hi");
            String ht = request.getParameter("ht");
            Integer nq = Integer.parseInt(request.getParameter("nq"));
            if (nome == null || nome.isEmpty() || eid == null || eid.equals(0L) || data == null || data.isEmpty() || hi == null || hi.isEmpty() || ht == null || ht.isEmpty()) {
                session.setAttribute("erro", "Preencha todos os campos (*).");
                return "/sup/sup_adicionar_simulado.jsp";
            } else {
                Utility utility = new Utility();
                utility.setIde(eid);
                utility.setQtdq(nq);
                QuestaoService qS = new QuestaoService();
                List<Questao> subListaDeQuestoes = qS.getListQuestoesByExame(utility);
                QuestaoSimuladoService questaoSimuladoService = new QuestaoSimuladoService();
                if (nq <= subListaDeQuestoes.size()) {
                    Simulado simulado = new Simulado();
                    simulado.setNome(nome);
                    simulado.setData(data);
                    simulado.setHoraini(hi);
                    simulado.setHorafim(ht);
                    simulado.setNum_questao(nq);
                    simulado.setExame_id(eid);
                    SimuladoService simuladoService = new SimuladoService();
                    simuladoService.inserir(simulado);
                    for (Questao q : subListaDeQuestoes) {
                        QuestaoSimulado questaoSimulado = new QuestaoSimulado();
                        questaoSimulado.setSimulado_id(simuladoService.proxId());
                        questaoSimulado.setQuestao_id(q.getId());
                        questaoSimuladoService.inserir(questaoSimulado);
                    }
                    UsuarioService us = new UsuarioService();
                    UsuarioSimuladoService uss = new UsuarioSimuladoService();
                    List<Usuario> usuarios = us.getAllUsuariosAlunos();
                    for (Usuario u : usuarios) {
                        try {
                            UsuarioSimulado usuarioSimulado = new UsuarioSimulado();
                            usuarioSimulado.setSimulado_id(simuladoService.proxId());
                            usuarioSimulado.setUsuario_id(u.getId());
                            uss.insertUsuarioSimulado(usuarioSimulado);
                            SendMail.sendMail(u.getEmail(), "Realizar Simulado.", "Oi " + u.getNome() + ", <br />"
                                    + "um simulado foi adicionado ao sistema.<br /><br />"
                                    + "<a href=" + Util.getUrl(request) + "/sappe/index.jsp" + ">Realizar Simulado</a>");
                        } catch (AddressException ex) {
                            Logger.getLogger(CmdSupervisorAdicionarSimulado.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (MessagingException ex) {
                            Logger.getLogger(CmdSupervisorAdicionarSimulado.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    session.setAttribute("sucesso", "Simulado cadastrado com sucesso.");
                } else {
                    session.setAttribute("erro", "No momento temos apenas " + subListaDeQuestoes.size() + " questões disponíveis para este exame.");
                }
            }
        } catch (SqlMapException e) {
            e.printStackTrace();
            session.setAttribute("erro", e.getMessage());
        } catch (NullPointerException npe) {
            session.setAttribute("erro", npe.getMessage());
            npe.printStackTrace();
        } catch (Exception e) {
            session.setAttribute("erro", e.getMessage());
            e.printStackTrace();
        }
        return "/sup/sup_adicionar_simulado.jsp";
    }
}
