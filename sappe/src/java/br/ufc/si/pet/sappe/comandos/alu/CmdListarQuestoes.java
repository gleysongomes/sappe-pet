/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.entidades.Tipo;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.ProvaService;
import br.ufc.si.pet.sappe.service.QuestaoProvaService;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.service.TipoService;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.util.Msg;
import br.ufc.si.pet.sappe.util.Util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

/**
 *
 * @author gleyson
 */
public class CmdListarQuestoes implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

        HttpSession hS = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("id"));
        Integer n = Integer.parseInt(request.getParameter("nQ"));
        String caminho = request.getParameter("caminho");
        if (n == 0) {
            return Mensagens(request, caminho, "Selecione uma opção.");
        }
        DateTime hI = new DateTime();
        QuestaoService qS = new QuestaoService();
        List<Questao> listaDeQuestoes = qS.getListQuestoesByArea(id);
        List<Questao> subListaDeQuestoes = new ArrayList<Questao>();
        if (listaDeQuestoes.size() == 0) {
            return Mensagens(request, caminho, Msg.msg);
        }

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "postgres");

        Perfil perfil = (Perfil) hS.getAttribute("user");
        Usuario usuario = new Usuario();
        UsuarioService uS = new UsuarioService();
        usuario = uS.getUsuarioById(perfil.getUsuario().getId());
        ProvaService pS = new ProvaService();
        List<Prova> provas = pS.getAllListProvas(usuario.getId());
        if (provas.size() == 0) {
            Collections.shuffle(listaDeQuestoes);
            for (int i = 0; i < n; i++) {
                subListaDeQuestoes.add(listaDeQuestoes.get(i));
                criar(listaDeQuestoes.get(i).getNome(), conn, listaDeQuestoes.get(i).getId());
            }
            if (subListaDeQuestoes.size() < n) {
                return Mensagens(request, caminho, Msg.msg2);
            }
            hS.setAttribute("subListaDeQuestoes", subListaDeQuestoes);
        } else {
            int condicao, u = 0;
            for (Questao q : listaDeQuestoes) {
                condicao = 1;
                for (Prova p : provas) {
                    QuestaoProvaService questaoProvaService = new QuestaoProvaService();
                    List<QuestaoProva> qps = questaoProvaService.getListQuestaoProvaById(p.getId());
                    for (QuestaoProva qp : qps) {
                        if (qp.getQuestao_id().equals(q.getId())
                                && usuario.getId().equals(p.getPerfil_id())) {
                            condicao = 0;
                        }
                    }
                }
                if (condicao == 1) {
                    subListaDeQuestoes.add(listaDeQuestoes.get(u));
                }
                u++;
            }

            if (subListaDeQuestoes.size() < n) {
                return Mensagens(request, caminho, Msg.msg2);
            }
            Collections.shuffle(subListaDeQuestoes);
            List<Questao> subListaDeQuestoes2 = new ArrayList<Questao>();
            for (int i = 0; i < n; i++) {
                subListaDeQuestoes2.add(subListaDeQuestoes.get(i));
                criar(listaDeQuestoes.get(i).getNome(), conn, listaDeQuestoes.get(i).getId());
            }
            hS.setAttribute("subListaDeQuestoes", subListaDeQuestoes2);
        }
        hS.setAttribute("hI", hI);
        TipoService tS = new TipoService();
        Tipo tipo = tS.getTipoById(id);
        hS.setAttribute("tipo", tipo);
        hS.setAttribute("oP", n);
        conn.close();
        return "/alu/listar_questoes.jsp";
    }

    private String Mensagens(HttpServletRequest request, String caminho, String msg) {
        HttpSession hS = request.getSession(true);
        hS.setAttribute("erro", msg);
        return caminho;
    }

    private void criar(String nome, Connection conn, Long id) {
        try {
            File arquivo = new File("/tmp/" + nome);
            FileOutputStream foS = new FileOutputStream(arquivo);
            BufferedOutputStream boS = new BufferedOutputStream(foS);
            PreparedStatement pS = conn.prepareStatement("SELECT arquivo FROM sappe.questao WHERE id=?");
            pS.setLong(1, id);
            ResultSet rs = pS.executeQuery();
            rs.next();
            InputStream in = rs.getBinaryStream(1);
            byte[] arqBytes = new byte[in.available()];
            in.read(arqBytes, 0, in.available());
            boS.write(arqBytes);
            boS.flush();
            boS.close();
        } catch (SQLException ex) {
            Logger.getLogger(CmdListarQuestoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CmdListarQuestoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CmdListarQuestoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
